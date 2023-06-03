package hello.jdbc.exception.basic;

import java.net.ConnectException;
import java.sql.SQLException;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;

/**
 * packageName :  hello.jdbc.exception.basic
 * fileName : CheckedAppTest
 * author :  JinWoong
 * date : 2023/06/03 
 * description :
 * ===========================================================
 * DATE                 AUTHOR              NOTE
 * -----------------------------------------------------------
 * 2023/06/03           eomjin-ung          init
 */

@Slf4j
public class UncheckedAppTest {

	@Test
	public void Unchecked() {
		Controller controller = new Controller();
		Assertions.assertThatThrownBy(() ->
				controller.request())
			.isInstanceOf(RuntimeException.class);
	}

	static class Controller {
		Service service = new Service();

		public void request() throws SQLException, ConnectException {
			service.logic();
		}
	}
	static class Service {

		Repository repository = new Repository();
		NetworkClient networkClient = new NetworkClient();

		public void logic() {
			repository.call();
			networkClient.call();
		}
	}

	static class NetworkClient {
		public void call() { // Runtime 예외로 바꿔서 던져줌 -> Unchecked 예외
			throw new RuntimeConnectException("연결 실패");
		}
	}

	static class Repository {
		public void call() {
			try {
				runSQL();
			} catch (SQLException e) {
				throw new RuntimeException(e);	// Runtime 예외로 바꿔서 던져줌 -> Unchecked 예외
			}
		}

		public void runSQL() throws SQLException {
			throw new SQLException("ex");
		}
	}

	static class RuntimeConnectException extends RuntimeException {
		public RuntimeConnectException(String message) {
			super(message);
		}

		static class RuntimeSQLException extends java.lang.RuntimeException {
			public RuntimeSQLException(Throwable cause) {
				super(cause);
			}
		}
	}

}
