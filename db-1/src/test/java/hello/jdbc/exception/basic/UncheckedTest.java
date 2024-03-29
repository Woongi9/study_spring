package hello.jdbc.exception.basic;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;

/**
 * packageName :  hello.jdbc.exception.basic
 * fileName : UncheckedTest
 * author :  JinWoong
 * date : 2023/06/03 
 * description :
 * ===========================================================
 * DATE                 AUTHOR              NOTE
 * -----------------------------------------------------------
 * 2023/06/03           eomjin-ung          init
 */

@Slf4j
public class UncheckedTest {

	@Test
	void Unchecked_catch() {
		Service service = new Service();
		service.callCatch();
	}

	@Test
	void Unchecked_uncatch() {
		Service service = new Service();
		Assertions.assertThatThrownBy(() ->
				service.callThrow())
			.isInstanceOf(MyUncheckedException.class);
	}


	/**
	 * RuntimeException을 상속받은 예외는 언체크 예외가 된다.
	 */
	static class MyUncheckedException extends RuntimeException {
		public MyUncheckedException(String message) {
			super(message);
		}
	}

	/**
	 * Unchecked 예외는
	 * 예외를 잡거나 던지지 않아도 된다.
	 * 예외를 잡지 않으면 자동으로 밖으로 던진다.
	 */
	static class Service {
		Repository repository = new Repository();

		/**
		 * 필요한 경우 예외를 잡아서 처리하면 된다.
		 */
		public void callCatch() {
			try {
				repository.call();
			} catch (MyUncheckedException ex) {
				log.info("예외 처리, message={}", ex.getMessage(), ex);
			}
		}

		/**
		 * 예외를 잡지 않아도 상위로 자연스럽게 넘어간다.
		 */
		public void callThrow() {
			repository.call();
		}
	}


	static class Repository {
		public void call() {
			throw new MyUncheckedException("ex");
		}
	}

}
