package study.querydsl.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

/**
 * packageName :  study.querydsl.entity
 * fileName : Hello
 * author :  JinWoong
 * date : 2023/05/15 
 * description :
 * ===========================================================
 * DATE                 AUTHOR              NOTE
 * -----------------------------------------------------------
 * 2023/05/15           eomjin-ung          init
 */

@Entity
@Getter @Setter
public class Hello {

	@Id
	@GeneratedValue
	private Long id;
}
