package hello.controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

/**
 * packageName :  hello.controller
 * fileName : TrafficController
 * author :  JinWoong
 * date : 10/29/23 
 * description :
 * ===========================================================
 * DATE                 AUTHOR              NOTE
 * -----------------------------------------------------------
 * 10/29/23           JinWoong            init
 */

@Slf4j
@RestController
public class TrafficController {
	@GetMapping("cpu")
	public String cpu() {
		log.info("cpu");
		long value = 0;
		for (long i = 0; i < 10000000000000L; i++) {
			value++;
		}
		return "ok value=" + value;
	}

	List<Integer> list = new ArrayList<>();

	@GetMapping("jvm")
	public String jvm() {
		log.info("jvm");
		for (int i = 0; i < 10000000; i++) {
			list.add(i);
		}
		return "ok list.size()=" + list.size();
	}

	@Autowired
	DataSource dataSource;

	@GetMapping("jdbc")
	public String jdbc() throws SQLException {
		log.info("jdbc");
		Connection connection = dataSource.getConnection();
		log.info("connection info={}" + connection);
		// connection.close() // 커넥션을 닫지 않는다. -> 반복호출시 커넥션이 쌓인다.
		return "ok";
	}

	@GetMapping("/error-log")
	public String errorLog() {
		log.error("error log");
		return "error log";
	}
}
