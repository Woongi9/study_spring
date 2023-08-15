package hello.springtx.propagation;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

/**
 * packageName :  hello.springtx.propagation
 * fileName : Log
 * author :  JinWoong
 * date : 2023/08/13 
 * description :
 * ===========================================================
 * DATE                 AUTHOR              NOTE
 * -----------------------------------------------------------
 * 2023/08/13           JinWoong            init
 */

@Entity
@Getter @Setter
public class Log {

	@Id	@GeneratedValue
	private Long id;
	private String message;

	public Log() {
	}

	public Log(String message) {
		this.message = message;
	}
}
