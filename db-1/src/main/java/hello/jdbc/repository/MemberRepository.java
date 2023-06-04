package hello.jdbc.repository;

import hello.jdbc.domain.Member;

/**
 * packageName :  hello.jdbc.repository
 * fileName : MemberRepository
 * author :  JinWoong
 * date : 2023/06/04 
 * description :
 * ===========================================================
 * DATE                 AUTHOR              NOTE
 * -----------------------------------------------------------
 * 2023/06/04           eomjin-ung          init
 */
public interface MemberRepository {
	Member save(Member member);
	Member findById(String memberId);
	void update(String memberId, int money);
	void delete(String memberId);
}
