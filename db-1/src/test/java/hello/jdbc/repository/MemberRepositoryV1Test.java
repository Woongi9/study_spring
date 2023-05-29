package hello.jdbc.repository;

import static hello.jdbc.connection.ConnectionConst.*;
import static org.assertj.core.api.Assertions.*;

import java.sql.SQLException;
import java.util.NoSuchElementException;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.zaxxer.hikari.HikariDataSource;

import hello.jdbc.domain.Member;
import lombok.extern.slf4j.Slf4j;

/**
 * packageName :  hello.jdbc.repository
 * fileName : MemberRepositoryV0Test
 * author :  JinWoong
 * date : 2023/05/27 
 * description :
 * ===========================================================
 * DATE                 AUTHOR              NOTE
 * -----------------------------------------------------------
 * 2023/05/27           eomjin-ung          init
 */

@SpringBootTest
// @Transactional
@Slf4j
class MemberRepositoryV1Test {

	MemberRepositoryV1 repository;

	@BeforeEach
	void beforeEach() {

		// 커넥션 풀링
		HikariDataSource dataSource = new HikariDataSource();
		dataSource.setJdbcUrl(URL);
		dataSource.setUsername(USERNAME);
		dataSource.setPoolName(PASSWORD);

		repository = new MemberRepositoryV1(dataSource);
	}

	@Test
	void crud() throws SQLException, InterruptedException {
		Member member = new Member("memberV4", 10000);
		repository.save(member);

		// findById
		Member findMember = repository.findById(member.getMemberId());
		log.info("findMember={}", findMember);
		assertThat(findMember).isEqualTo(member);

		// update : money : 10000 -> 20000
		repository.update(member.getMemberId(), 20000);
		Member updateMember = repository.findById(member.getMemberId());
		assertThat(updateMember.getMoney()).isEqualTo(20000);

		// delete
		repository.delete(member.getMemberId());
		Assertions.assertThatThrownBy(() -> repository.findById(member.getMemberId()))
			.isInstanceOf(NoSuchElementException.class);

		Thread.sleep(1000);
	}


}