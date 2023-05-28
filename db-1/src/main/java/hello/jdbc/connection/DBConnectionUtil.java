package hello.jdbc.connection;

import static hello.jdbc.connection.ConnectionConst.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import lombok.extern.slf4j.Slf4j;

/**
 * packageName :  hello.jdbc.connection
 * fileName : DBConnectionUtil
 * author :  JinWoong
 * date : 2023/05/27 
 * description :
 * ===========================================================
 * DATE                 AUTHOR              NOTE
 * -----------------------------------------------------------
 * 2023/05/27           eomjin-ung          init
 */

@Slf4j
public class DBConnectionUtil {

	public static Connection getConnection() {
		try {
			Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			log.info("get Connection={}, class={}", connection, connection.getClass());
			return connection;
		} catch (SQLException e) {
			throw new IllegalStateException(e);
		}
	}
}
