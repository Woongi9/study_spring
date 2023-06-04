package hello.jdbc.repository.ex;

/**
 * packageName :  hello.jdbc.repository.ex
 * fileName : MyDuplicateKeyException
 * author :  JinWoong
 * date : 2023/06/04 
 * description :
 * ===========================================================
 * DATE                 AUTHOR              NOTE
 * -----------------------------------------------------------
 * 2023/06/04           eomjin-ung          init
 */
public class MyDuplicateKeyException extends MyDbException {

	public MyDuplicateKeyException() {
	}

	public MyDuplicateKeyException(String message) {
		super(message);
	}

	public MyDuplicateKeyException(String message, Throwable cause) {
		super(message, cause);
	}

	public MyDuplicateKeyException(Throwable cause) {
		super(cause);
	}
}
