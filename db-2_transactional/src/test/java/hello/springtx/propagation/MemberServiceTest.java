package hello.springtx.propagation;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;

/**
 * packageName :  hello.springtx.propagation
 * fileName : MemberServiceTest
 * author :  JinWoong
 * date : 2023/08/13 
 * description :
 * ===========================================================
 * DATE                 AUTHOR              NOTE
 * -----------------------------------------------------------
 * 2023/08/13           JinWoong            init
 */

@Slf4j
@SpringBootTest
class MemberServiceTest {

	@Autowired
	MemberService memberService;
	@Autowired
	MemberRepository memberRepository;
	@Autowired
	LogRepository logRepository;

	/**
	 * memberService 	@Transactional : OFF
	 * memberRepository @Transactional : ON
	 * logRepository 	@Transactional : ON
	 */
	@Test
	void outerTxOff_success() {

		// given
		String username = "outerTxOff_success";

		// when
		memberService.joinV1(username);

		// then: 모든 데이터 장상 저장
		Assertions.assertTrue(memberRepository.find(username).isPresent());
		Assertions.assertTrue(logRepository.find(username).isPresent());
	}

	@Test
	void outerTxOff_fail() {

		// given
		String username = "로그예외_outerTxOff_success";

		// when
		org.assertj.core.api.Assertions.assertThatThrownBy(() -> memberService.joinV1(username))
			.isInstanceOf(RuntimeException.class);

		// then: 모든 데이터 장상 저장
		Assertions.assertTrue(memberRepository.find(username).isPresent());
		Assertions.assertTrue(logRepository.find(username).isEmpty());

	}

	@Test
	void outerTxOn_success() {

		// given
		String username = "outerTxOn_success";

		// when
		memberService.joinV1(username);

		// then: 모든 데이터 장상 저장
		Assertions.assertTrue(memberRepository.find(username).isPresent());
		Assertions.assertTrue(logRepository.find(username).isPresent());
	}

	@Test
	void outerTxOn_fail() {

		// given
		String username = "로그예외_outerTxOn_success";

		// when
		org.assertj.core.api.Assertions.assertThatThrownBy(() -> memberService.joinV1(username))
			.isInstanceOf(RuntimeException.class);

		// then: 모든 데이터 장상 저장
		Assertions.assertTrue(memberRepository.find(username).isPresent());
		Assertions.assertTrue(logRepository.find(username).isEmpty());

	}
}