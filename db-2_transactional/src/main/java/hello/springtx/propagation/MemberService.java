package hello.springtx.propagation;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * packageName :  hello.springtx.propagation
 * fileName : MemberService
 * author :  JinWoong
 * date : 2023/08/13 
 * description :
 * ===========================================================
 * DATE                 AUTHOR              NOTE
 * -----------------------------------------------------------
 * 2023/08/13           JinWoong            init
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {

	private final MemberRepository memberRepository;
	private final LogRepository logRepository;

	@Transactional
	public void joinV1(String username) {
		Member member = new Member(username);
		Log logMessage = new Log(username);

		log.info("-------- memberRepository 호출 시작 --------");
		memberRepository.save(member);
		log.info("-------- memberRepository 호출 종료 --------");

		log.info("-------- logRepository 호출 시작 --------");
		logRepository.save(logMessage);
		log.info("-------- logRepository 호출 종료 --------");
	}

	public void joinV2(String username) {
		Member member = new Member(username);
		Log logMessage = new Log(username);

		log.info("-------- memberRepository 호출 시작 --------");
		memberRepository.save(member);
		log.info("-------- memberRepository 호출 종료 --------");

		try {
			log.info("-------- logRepository 호출 시작 --------");
			logRepository.save(logMessage);
			log.info("-------- logRepository 호출 종료 --------");
		} catch (RuntimeException e) {
			log.info("log 저장에 실패했습니다. logMessage={}", logMessage);
			log.info("정상 흐름 반환");
		}
	}
}
