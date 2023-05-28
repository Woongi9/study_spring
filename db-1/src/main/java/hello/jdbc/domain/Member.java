package hello.jdbc.domain;

import lombok.Data;

/**
 * packageName :  hello.jdbc.domain
 * fileName : Member
 * author :  JinWoong
 * date : 2023/05/27 
 * description :
 * ===========================================================
 * DATE                 AUTHOR              NOTE
 * -----------------------------------------------------------
 * 2023/05/27           eomjin-ung          init
 */

@Data
public class Member {

	private String memberId;
	private int money;

	public Member() {
	}

	public Member(String memberId, int money) {
		this.memberId = memberId;
		this.money = money;
	}
}
