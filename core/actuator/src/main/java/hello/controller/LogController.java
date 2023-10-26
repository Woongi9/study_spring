package hello.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

/**
 * packageName :  hello
 * fileName : LogController
 * author :  JinWoong
 * date : 10/26/23 
 * description :
 * ===========================================================
 * DATE                 AUTHOR              NOTE
 * -----------------------------------------------------------
 * 10/26/23           JinWoong            init
 */

@Slf4j
@RestController
public class LogController {

	@GetMapping("/log")
	public String log() {
		log.trace("trace log");
		log.debug("debug log");
		log.info("info log");
		log.warn("warn log");
		log.error("error log");
		return "ok";
	}
	/**
	 * hotfix
	 */
}
