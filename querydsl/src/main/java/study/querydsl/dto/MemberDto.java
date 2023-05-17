package study.querydsl.dto;

import com.querydsl.core.annotations.QueryProjection;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * packageName :  study.querydsl.dto
 * fileName : MemberDTo
 * author :  JinWoong
 * date : 2023/05/17 
 * description :
 * ===========================================================
 * DATE                 AUTHOR              NOTE
 * -----------------------------------------------------------
 * 2023/05/17           eomjin-ung          init
 */

@Getter
@NoArgsConstructor
public class MemberDto {

	private String name;
	private Integer age;

	@QueryProjection
	public MemberDto(String name, Integer age) {
		this.name = name;
		this.age = age;
	}
}
