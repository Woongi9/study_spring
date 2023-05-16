package study.querydsl;

import static org.assertj.core.api.Assertions.*;
import static study.querydsl.entity.QMember.*;
import static study.querydsl.entity.QTeam.*;

import java.util.List;

import javax.persistence.EntityManager;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;

import study.querydsl.entity.Hello;
import study.querydsl.entity.Member;
import study.querydsl.entity.QHello;
import study.querydsl.entity.QMember;
import study.querydsl.entity.QTeam;
import study.querydsl.entity.Team;

/**
 * packageName :  study.querydsl
 * fileName : QuerydslBasicTest
 * author :  JinWoong
 * date : 2023/05/16 
 * description :
 * ===========================================================
 * DATE                 AUTHOR              NOTE
 * -----------------------------------------------------------
 * 2023/05/16           eomjin-ung          init
 */

@SpringBootTest
@Transactional
public class QuerydslBasicTest {

	@Autowired
	EntityManager em;

	@BeforeEach
	public void before() {
		Team teamA = new Team("teamA");
		Team teamB = new Team("teamB");
		em.persist(teamA);
		em.persist(teamB);

		Member member1 = new Member("member1", 10, teamA);
		Member member2 = new Member("member2", 20, teamA);

		Member member3 = new Member("member3", 30, teamB);
		Member member4 = new Member("member4", 40, teamB);
		em.persist(member1);
		em.persist(member2);
		em.persist(member3);
		em.persist(member4);
	}

	@Test
	public void startJPQL() {
		// member1을 찾아라
		String query =
			"select m "
				+ "from Member m "
				+ "where m.username = :username";

		Member findByJPQL = em.createQuery(query, Member.class)
			.setParameter("username", "member1")
			.getSingleResult();

		assertThat(findByJPQL.getUsername()).isEqualTo("member1");
	}

	@Test
	public void startQuerydsl() {
		JPAQueryFactory queryFactory = new JPAQueryFactory(em);
		// QMember m = new QMember("m"); // 어떤 QMember 인지 구분하기 위해 "m" 값 입력, 같은 테이블을 조인해야 할 경우에만 사용
		// QMember m = QMember.member;

		Member findMember = queryFactory
			.select(member)
			.from(member)
			.where(member.username.eq("member2"))	// 파라미터 바인딩이 유리
			.fetchOne();

		assertThat(findMember.getUsername()).isEqualTo("member2");
	}

	@Test
	public void search() {
		JPAQueryFactory queryFactory = new JPAQueryFactory(em);

		Member findMember = queryFactory
			.selectFrom(member)
			// .where(member.username.eq("member1").and(member.age.eq(10)))
			.where(
				member.username.eq("member1"),
				member.age.eq(10)
			)
			.fetchOne();

		assertThat(findMember.getUsername()).isEqualTo("member1");
	}

	@Test
	public void resultFetch() {
		JPAQueryFactory queryFactory = new JPAQueryFactory(em);

		// List<Member> fetch = queryFactory
		// 	.selectFrom(member)
		// 	.fetch();
		//
		// Member fetchOne = queryFactory
		// 	.selectFrom(member)
		// 	.fetchOne();
		//
		// Member fetchFirst = queryFactory
		// 	.selectFrom(member)
		// 	.fetchFirst();
		//
		// QueryResults<Member> results = queryFactory
		// 	.selectFrom(member)
		// 	.fetchResults();
		//
		// results.getTotal();
		// List<Member> content = results.getResults();
		// results.get

		long total = queryFactory
			.selectFrom(member)
			.fetchCount();
	}

	/**
	 * 회원 정렬 순서
	 * 1. 나이 내림차순
	 * 2. 이름 올림차순
	 * 3. 회원 이름 없으면 마지막에 출력
	 */
	@Test
	public void sort() {

		JPAQueryFactory queryFactory = new JPAQueryFactory(em);

		em.persist(new Member(null, 100));
		em.persist(new Member("member5", 100));
		em.persist(new Member("member6", 100));

		List<Member> result = queryFactory.selectFrom(member)
			.where(member.age.eq(100))
			.orderBy(member.age.desc(), member.username.asc().nullsLast())
			.fetch();

		Member member5 = result.get(0);
		Member member6 = result.get(1);
		Member memberNull = result.get(2);

		assertThat(member5.getUsername()).isEqualTo("member5");
		assertThat(member6.getUsername()).isEqualTo("member6");
		assertThat(memberNull.getUsername()).isNull();
	}

	@Test
	public void paging1() {
		JPAQueryFactory queryFactory = new JPAQueryFactory(em);

		List<Member> result = queryFactory
			.selectFrom(member)
			.orderBy(member.username.desc())
			.offset(1)
			.limit(2)
			.fetch();

		assertThat(result.size()).isEqualTo(2);
	}

	@Test
	public void paging2() {
		JPAQueryFactory queryFactory = new JPAQueryFactory(em);

		QueryResults<Member> results = queryFactory
			.selectFrom(member)
			.orderBy(member.username.desc())
			.offset(1)
			.limit(2)
			.fetchResults();

		assertThat(results.getTotal()).isEqualTo(4);
		assertThat(results.getLimit()).isEqualTo(2);
		assertThat(results.getOffset()).isEqualTo(1);
		assertThat(results.getResults().size()).isEqualTo(2);

	}

	@Test
	public void aggregation() {

		JPAQueryFactory queryFactory = new JPAQueryFactory(em);

		List<Tuple> results = queryFactory
			.select(
				member.count(),
				member.age.sum(),
				member.age.avg(),
				member.age.max(),
				member.age.min()
			)
			.from(member)
			.fetch();

		Tuple tuple = results.get(0);
		assertThat(tuple.get(member.count())).isEqualTo(4);
		assertThat(tuple.get(member.age.sum())).isEqualTo(100);
		assertThat(tuple.get(member.age.avg())).isEqualTo(25);
		assertThat(tuple.get(member.age.max())).isEqualTo(40);
		assertThat(tuple.get(member.age.min())).isEqualTo(10);

	}

	/**
	 * 팀의 이름과 각 팀의 평균 연령을 구해라.
	 */
	@Test
	public void groupby() throws Exception {

		JPAQueryFactory queryFactory = new JPAQueryFactory(em);

		List<Tuple> result = queryFactory
			.select(team.name, member.age.avg())
			.from(member)
			.join(member.team, team)
			.groupBy(team.name)
			.fetch();

		Tuple teamA = result.get(0);
		Tuple teamB = result.get(1);

		assertThat(teamA.get(team.name)).isEqualTo("teamA");
		assertThat(teamA.get(member.age.avg())).isEqualTo(15);
		assertThat(teamB.get(team.name)).isEqualTo("teamB");
		assertThat(teamB.get(member.age.avg())).isEqualTo(35);

	}

	/**
	 * 팀 A에 소속된 모든 회원
	 */
	@Test
	public void join() {

		JPAQueryFactory queryFactory = new JPAQueryFactory(em);

		List<Member> result = queryFactory
			.selectFrom(member)
			.join(member.team, team)
			.where(team.name.eq("teamA"))
			.fetch();

		assertThat(result)
			.extracting("username")
			.containsExactly("member1", "member2");
	}

	/**
	 * 세타 조인
	 * 회원의 이름이 팀 이름과 같은 회원 조회
	 */
	@Test
	public void theta_join() {

		em.persist(new Member("teamA"));
		em.persist(new Member("teamB"));
		em.persist(new Member("teamC"));

		JPAQueryFactory queryFactory = new JPAQueryFactory(em);

		List<Member> result = queryFactory
			.select(member)
			.from(member, team)
			.where(member.username.eq(team.name))
			.fetch();

		assertThat(result)
			.extracting("username")
			.containsExactly("teamA", "teamB");
	}


}
