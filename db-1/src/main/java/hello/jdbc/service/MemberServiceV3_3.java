package hello.jdbc.service;

import java.sql.Connection;
import java.sql.SQLException;

import org.springframework.transaction.annotation.Transactional;

import hello.jdbc.domain.Member;
import hello.jdbc.repository.MemberRepositoryV3;
import lombok.extern.slf4j.Slf4j;

/**
 * packageName :  hello.jdbc.service
 * fileName : MemberServiceV1
 * author :  JinWoong
 * date : 2023/05/29 
 * description : 트랜잭션 - @Transactional AOP
 * ===========================================================
 * DATE                 AUTHOR              NOTE
 * -----------------------------------------------------------
 * 2023/05/29           eomjin-ung          init
 */

@Slf4j
public class MemberServiceV3_3 {

	// private final PlatformTransactionManager transactionManager;
	private final MemberRepositoryV3 memberRepository;

	public MemberServiceV3_3(MemberRepositoryV3 memberRepository) {
		this.memberRepository = memberRepository;
	}

	@Transactional
	public void accountTransfer(String fromId, String toId, int money) throws SQLException {
		bizLogic(fromId, toId, money);
	}

	private void bizLogic(String fromId, String toId, int money) throws SQLException {
		Member fromMember = memberRepository.findById(fromId);
		Member toMember = memberRepository.findById(toId);

		memberRepository.update(fromId, fromMember.getMoney() - money);
		validation(toMember);
		memberRepository.update(toId, toMember.getMoney() + money);
	}

	private static void validation(Member member) {
		if (member.getMemberId().equals("ex")) {
			throw new IllegalStateException("이체중 예외 발생");
		}
	}
}
