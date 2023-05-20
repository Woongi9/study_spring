package study.querydsl.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import study.querydsl.entity.Member;

/**
 * packageName :  study.querydsl.repository
 * fileName : MemberRepository
 * author :  JinWoong
 * date : 2023/05/19 
 * description :
 * ===========================================================
 * DATE                 AUTHOR              NOTE
 * -----------------------------------------------------------
 * 2023/05/19           eomjin-ung          init
 */
public interface MemberRepository extends JpaRepository<Member, Long>, MemberRepositoryCustom {

	List<Member> findByUsername(String username);
}
