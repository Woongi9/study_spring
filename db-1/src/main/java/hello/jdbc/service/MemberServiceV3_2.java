package hello.jdbc.service;

import java.sql.Connection;
import java.sql.SQLException;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionTemplate;

import hello.jdbc.domain.Member;
import hello.jdbc.repository.MemberRepositoryV3;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * packageName :  hello.jdbc.service
 * fileName : MemberServiceV1
 * author :  JinWoong
 * date : 2023/05/29 
 * description : 트랜잭션 - 트랜잭션 템플릿
 * ===========================================================
 * DATE                 AUTHOR              NOTE
 * -----------------------------------------------------------
 * 2023/05/29           eomjin-ung          init
 */

@Slf4j
@RequiredArgsConstructor
public class MemberServiceV3_2 {

	// private final PlatformTransactionManager transactionManager;
	private final TransactionTemplate template;
	private final MemberRepositoryV3 memberRepository;

	public MemberServiceV3_2(PlatformTransactionManager transactionManager, MemberRepositoryV3 memberRepository) {
		this.template = new TransactionTemplate(transactionManager);
		this.memberRepository = memberRepository;
	}

	public void accountTransfer(String fromId, String toId, int money) throws SQLException {

		template.executeWithoutResult((status) -> {
			try {
				bizLogic(fromId, toId, money);
			} catch (SQLException e) {
				throw new IllegalStateException(e);
			}
		});
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

	private static void releaseConnection(Connection con) {
		if (con != null) {
			try {
				con.setAutoCommit(true); // 커넥션 풀 고려
				con.close();
			} catch (Exception e) {
				log.info("error", e);
			}
		}
	}
}
