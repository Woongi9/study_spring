package hello.jdbc.repository;

import java.sql.SQLException;

import hello.jdbc.domain.Member;

/**
 * packageName :  hello.jdbc.repository
 * fileName : MemberRepositoryEx
 * author :  JinWoong
 * date : 2023/06/04 
 * description :
 * ===========================================================
 * DATE                 AUTHOR              NOTE
 * -----------------------------------------------------------
 * 2023/06/04           eomjin-ung          init
 */

public interface MemberRepositoryEx {
	Member save(Member member);
	Member findById(String memberId);
	void update(String memberId, int money);
	void delete(String memberId);
}
