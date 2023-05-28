package hello.jdbc.connection;

import static org.assertj.core.api.Assertions.*;

import java.sql.Connection;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;

/**
 * packageName :  hello.jdbc.connection
 * fileName : DBConnectionUtilTest
 * author :  JinWoong
 * date : 2023/05/27 
 * description :
 * ===========================================================
 * DATE                 AUTHOR              NOTE
 * -----------------------------------------------------------
 * 2023/05/27           eomjin-ung          init
 */

@Slf4j
public class DBConnectionUtilTest {

	@Test
	public void connection() {
		Connection connection = DBConnectionUtil.getConnection();
		assertThat(connection).isNotNull();
	}
}
