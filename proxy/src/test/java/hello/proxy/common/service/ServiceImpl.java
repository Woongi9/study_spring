package hello.proxy.common.service;

import lombok.extern.slf4j.Slf4j;

/**
 * packageName :  hello.proxy.common.service
 * fileName : ServiceImpl
 * author :  JinWoong
 * date : 2023/04/19
 * description :
 * ===========================================================
 * DATE                 AUTHOR              NOTE
 * -----------------------------------------------------------
 * 2023/04/19           eomjin-ung          init
 */
@Slf4j
public class ServiceImpl implements ServiceInterface{

    @Override
    public void save() {
        log.info("save 호출");
    }

    @Override
    public void find() {
        log.info("find 호출");
    }
}
