package study.querydsl;

import static org.assertj.core.api.Assertions.*;
import static study.querydsl.entity.QMember.*;
import static study.querydsl.entity.QTeam.*;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;

import study.querydsl.dto.MemberDto;
import study.querydsl.dto.QMemberDto;
import study.querydsl.dto.UserDto;
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

	/**
	 * 예) 회원과 팀을 조인하면서, 팀 이름이 teamA인 팀만 조인, 회원은 모두 조회
	 * JPQL : select m, t from Member m left join m.team t on t.name = 'teamA'
	 */
	@Test
	public void join_on_filtering() {

		JPAQueryFactory queryFactory = new JPAQueryFactory(em);

		List<Tuple> result = queryFactory
			.select(member, team)
			.from(member)
			.leftJoin(member.team, team).on(team.name.eq("teamA"))
			.fetch();

		for (Tuple tuple : result) {
			System.out.println("tuple = " + tuple);
		}

	}

	/**
	 * 연관관계 없는 엔티티 외부 조인
	 * 회원의 이름이 팀 이름과 같은 대상을 외부 조인
	 */
	@Test
	public void join_on_no_relation() {

		em.persist(new Member("teamA"));
		em.persist(new Member("teamB"));
		em.persist(new Member("teamC"));

		JPAQueryFactory queryFactory = new JPAQueryFactory(em);

		List<Tuple> result = queryFactory
			.select(member, team)
			.from(member)
			.leftJoin(team).on(member.username.eq(team.name))
			.fetch();

		for (Tuple tuple : result) {
			System.out.println("tuple = " + tuple);
		}
	}

	@PersistenceUnit
	EntityManagerFactory emf;


	@Test
	public void fetchJoinNo() {
		em.flush();
		em.clear();

		JPAQueryFactory queryFactory = new JPAQueryFactory(em);

		Member findMember = queryFactory
			.selectFrom(member)
			.where(member.username.eq("member1"))
			.fetchOne();

		boolean loaded = emf.getPersistenceUnitUtil().isLoaded(findMember.getTeam());
		assertThat(loaded).as("페치 조인 미적용").isFalse();

	}

	@Test
	public void fetchJoinUse() {
		em.flush();
		em.clear();

		JPAQueryFactory queryFactory = new JPAQueryFactory(em);

		Member findMember = queryFactory
			.selectFrom(member)
			.join(member.team, team).fetchJoin()
			.where(member.username.eq("member1"))
			.fetchOne();

		boolean loaded = emf.getPersistenceUnitUtil().isLoaded(findMember.getTeam());
		assertThat(loaded).as("페치 조인 미적용").isTrue();

	}

	/**
	 * 나이가 가장 많은 회원 조회
	 */
	@Test
	public void subQuery() {
		JPAQueryFactory queryFactory = new JPAQueryFactory(em);

		QMember memberSub = new QMember("memberSub");

		List<Member> result = queryFactory
			.selectFrom(member)
			.where(member.age.goe(
				JPAExpressions
					.select(memberSub.age.avg())
					.from(memberSub)
			))
			.fetch();

		assertThat(result).extracting("age")
			.containsExactly(30, 40);
	}

	/**
	 * 나이가 가장 많은 회원 조회
	 */
	@Test
	public void subQueryIn() {
		JPAQueryFactory queryFactory = new JPAQueryFactory(em);

		QMember memberSub = new QMember("memberSub");

		List<Member> result = queryFactory
			.selectFrom(member)
			.where(member.age.in(
				JPAExpressions
					.select(memberSub.age)
					.from(memberSub)
					.where(memberSub.age.gt(10))
			))
			.fetch();

		assertThat(result).extracting("age")
			.containsExactly(20, 30, 40);
	}

	@Test
	public void selectSubQuery() {
		JPAQueryFactory queryFactory = new JPAQueryFactory(em);

		QMember memberSub = new QMember("memberSub");

		List<Tuple> fetch = queryFactory
			.select(member.username,
				JPAExpressions
					.select(memberSub.age.avg())
					.from(memberSub))
			.from(member)
			.fetch();

		for (Tuple tuple : fetch) {
			System.out.println("tuple = " + tuple);
		}
	}

	@Test
	public void basicCase() {
		JPAQueryFactory queryFactory = new JPAQueryFactory(em);

		List<String> result = queryFactory
			.select(member.age
				.when(10).then("열살")
				.when(20).then("스무살")
				.otherwise("기타"))
			.from(member)
			.fetch();

		for (String s : result) {
			System.out.println("s = " + s);
		}
	}

	@Test
	public void complexCase() {
		JPAQueryFactory queryFactory = new JPAQueryFactory(em);

		List<String> result = queryFactory
			.select(new CaseBuilder()
				.when(member.age.between(0, 20)).then("0~20살")
				.when(member.age.between(21, 30)).then("21~30살")
				.otherwise("기타"))
			.from(member)
			.fetch();

		for (String s : result) {
			System.out.println("s = " + s);
		}
	}

	@Test
	public void concat() {

		JPAQueryFactory queryFactory = new JPAQueryFactory(em);

		List<String> result = queryFactory
			.select(member.username.concat("_").concat(member.age.stringValue()))
			.from(member)
			.where(member.username.eq("member1"))
			.fetch();

		for (String s : result) {
			System.out.println("s = " + s);
		}
	}

	@Test
	public void simpleProjection() {

		JPAQueryFactory queryFactory = new JPAQueryFactory(em);

		List<String> result = queryFactory
			.select(member.username)
			.from(member)
			.fetch();

		for (String s : result) {
			System.out.println("s = " + s);
		}
	}

	@Test
	public void tupleProjection() {

		JPAQueryFactory queryFactory = new JPAQueryFactory(em);

		List<Tuple> result = queryFactory
			.select(member.username, member.age)
			.from(member)
			.fetch();

		for (Tuple tuple : result) {
			String username = tuple.get(member.username);
			Integer age = tuple.get(member.age);
			System.out.println("username = " + username);
			System.out.println("age = " + age);
		}
	}

	@Test
	public void findDtoBySetter() {
		JPAQueryFactory queryFactory = new JPAQueryFactory(em);

		List<MemberDto> result = queryFactory
			.select(Projections.bean(MemberDto.class,
				member.username,
				member.age))
			.from(member)
			.fetch();

		for (MemberDto memberDto : result) {
			System.out.println("memberDto = " + memberDto);
		}
	}

	@Test
	public void findDtoByField() {
		JPAQueryFactory queryFactory = new JPAQueryFactory(em);

		List<MemberDto> result = queryFactory
			.select(Projections.fields(MemberDto.class,
				member.username,
				member.age))
			.from(member)
			.fetch();

		for (MemberDto memberDto : result) {
			System.out.println("memberDto = " + memberDto);
		}
	}

	@Test
	public void findDtoByConstructor() {
		JPAQueryFactory queryFactory = new JPAQueryFactory(em);

		List<MemberDto> result = queryFactory
			.select(Projections.constructor(MemberDto.class,
				member.username,
				member.age))
			.from(member)
			.fetch();

		for (MemberDto memberDto : result) {
			System.out.println("memberDto = " + memberDto);
		}
	}

	@Test
	public void findUserDtoByField() {
		JPAQueryFactory queryFactory = new JPAQueryFactory(em);

		QMember memberSub = new QMember("memberSub");

		List<UserDto> result = queryFactory
			.select(Projections.fields(UserDto.class,
				member.username.as("name"),

				// subQuery
				ExpressionUtils.as(JPAExpressions
					.select(memberSub.age.max())
					.from(memberSub), "age")
			))
			.from(member)
			.fetch();

		for (UserDto userDto : result) {
			System.out.println("memberDto = " + userDto);
		}
	}

	@Test
	public void findDtoByQueryProjection() {
		JPAQueryFactory queryFactory = new JPAQueryFactory(em);

		List<MemberDto> result = queryFactory
			.select(new QMemberDto(member.username, member.age))
			.from(member)
			.fetch();

		for (MemberDto memberDto : result) {
			System.out.println("memberDto = " + memberDto);
		}
	}

	@Test
	public void dynamicQuery_booleanBuilder() {

		JPAQueryFactory queryFactory = new JPAQueryFactory(em);

		String usernameParam = "member1";
		Integer ageParam = 10;

		List<Member> result = searchMember1(usernameParam, ageParam);
		assertThat(result.size()).isEqualTo(1);

	}

	private List<Member> searchMember1(String usernameParam, Integer ageParam) {
		JPAQueryFactory queryFactory = new JPAQueryFactory(em);

		BooleanBuilder builder = new BooleanBuilder();
		if (usernameParam != null) {
			builder.and(member.username.eq(usernameParam));
		}

		if (ageParam != null) {
			builder.and(member.age.eq(ageParam));
		}

		return queryFactory
			.selectFrom(member)
			.where(builder)
			.fetch();
	}

	@Test
	public void dynamicQuery_WhereParam() {
		JPAQueryFactory queryFactory = new JPAQueryFactory(em);

		String usernameParam = "member1";
		Integer ageParam = 10;

		List<Member> result = searchMember2(usernameParam, ageParam);
		assertThat(result.size()).isEqualTo(1);

	}

	private List<Member> searchMember2(String usernameParam, Integer ageParam) {
		JPAQueryFactory queryFactory = new JPAQueryFactory(em);

		return queryFactory
			.selectFrom(member)
			.where(allEq(usernameParam, ageParam))
			.fetch();
	}

	private BooleanExpression ageEq(Integer ageParam) {
		if (ageParam == null) {
			return null;
		}
		return member.age.eq(ageParam);
	}

	private BooleanExpression usernameEq(String usernameParam) {
		if (usernameParam != null) {
			return member.username.eq(usernameParam);
		} else {
			return null;
		}

	}

	private BooleanExpression allEq(String usernameCond, Integer ageCond) {
		return usernameEq(usernameCond).and(ageEq(ageCond));
	}

	@Test
	public void bulkUpdate() {

		JPAQueryFactory queryFactory = new JPAQueryFactory(em);

		// member1 = 10 -> 비회원
		// member2 = 20 -> 비회원
		long count = queryFactory
			.update(member)
			.set(member.username, "비회원")
			.where(member.age.lt(20))
			.execute();

		// 벌크 연산은 후에 영속성 컨텍스트 초기화 필요
		// 영속성 컨텍스트 초기화 (이전 상태 : DB에는 비회원, 영컨은 member1)
		em.flush();
		em.clear();

	}

}
