package study.querydsl.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import study.querydsl.dto.MemberSearchCondition;
import study.querydsl.dto.MemberTeamDto;

/**
 * packageName :  study.querydsl.repository
 * fileName : MemberRepositoryCustom
 * author :  JinWoong
 * date : 2023/05/19 
 * description :
 * ===========================================================
 * DATE                 AUTHOR              NOTE
 * -----------------------------------------------------------
 * 2023/05/19           eomjin-ung          init
 */
public interface MemberRepositoryCustom {
	List<MemberTeamDto> search(MemberSearchCondition condition);
	Page<MemberTeamDto> searchPageSimple(MemberSearchCondition condition, Pageable pageable);
	Page<MemberTeamDto> searchPageComplex(MemberSearchCondition condition, Pageable pageable);

}
