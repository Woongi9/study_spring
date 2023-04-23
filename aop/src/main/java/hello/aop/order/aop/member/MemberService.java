package hello.aop.order.aop.member;

import org.springframework.stereotype.Service;

/**
 * packageName :  hello.aop.order.aop.member
 * fileName : MemberService
 * author :  JinWoong
 * date : 2023/04/23
 * description :
 * ===========================================================
 * DATE                 AUTHOR              NOTE
 * -----------------------------------------------------------
 * 2023/04/23           eomjin-ung          init
 */

@Service
public interface MemberService {
    String hello(String param);
}
