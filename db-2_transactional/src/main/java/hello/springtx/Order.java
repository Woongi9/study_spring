package hello.springtx;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * packageName :  hello.springtx
 * fileName : Order
 * author :  JinWoong
 * date : 2023/08/06 
 * description :
 * ===========================================================
 * DATE                 AUTHOR              NOTE
 * -----------------------------------------------------------
 * 2023/08/06           JinWoong            init
 */

@Entity
@Getter
@Setter
@Table(name = "orders")
public class Order {

	@Id
	@GeneratedValue
	private Long id;

	private String username;	// 정상, 예외, 잔고부족
	private String payStatus;	// 예외
}
