package study.querydsl.dto;

import com.querydsl.core.annotations.QueryProjection;

import lombok.Data;

/**
 * packageName :  study.querydsl.dto
 * fileName : MemberTeamDto
 * author :  JinWoong
 * date : 2023/05/19 
 * description :
 * ===========================================================
 * DATE                 AUTHOR              NOTE
 * -----------------------------------------------------------
 * 2023/05/19           eomjin-ung          init
 */

@Data
public class MemberTeamDto {

	private Long memberId;
	private String username;
	private int age;
	private Long teamId;
	private String teamName;

	@QueryProjection
	public MemberTeamDto(Long memberId, String username,
		int age, Long teamId,
		String teamName) {
		this.memberId = memberId;
		this.username = username;
		this.age = age;
		this.teamId = teamId;
		this.teamName = teamName;
	}
}
