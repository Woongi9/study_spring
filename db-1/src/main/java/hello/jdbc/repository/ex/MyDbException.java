package hello.jdbc.repository.ex;

/**
 * packageName :  hello.jdbc.repository.ex
 * fileName : MyDbException
 * author :  JinWoong
 * date : 2023/06/04 
 * description :
 * ===========================================================
 * DATE                 AUTHOR              NOTE
 * -----------------------------------------------------------
 * 2023/06/04           eomjin-ung          init
 */
public class MyDbException extends RuntimeException {

	public MyDbException() {
	}

	public MyDbException(String message) {
		super(message);
	}

	public MyDbException(String message, Throwable cause) {
		super(message, cause);
	}

	public MyDbException(Throwable cause) {
		super(cause);
	}
}
