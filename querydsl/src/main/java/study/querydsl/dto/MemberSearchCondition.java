package study.querydsl.dto;

import lombok.Data;

/**
 * packageName :  study.querydsl.dto
 * fileName : MemberSearchCondition
 * author :  JinWoong
 * date : 2023/05/19 
 * description :
 * ===========================================================
 * DATE                 AUTHOR              NOTE
 * -----------------------------------------------------------
 * 2023/05/19           eomjin-ung          init
 */

@Data
public class MemberSearchCondition {
	private String username;
	private String teamName;
	private Integer ageGoe;
	private Integer ageLoe;
}
