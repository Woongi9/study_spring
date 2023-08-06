package hello.itemservice.repository.v2;

import org.springframework.data.jpa.repository.JpaRepository;

import hello.itemservice.domain.Item;

/**
 * packageName :  hello.itemservice.repository.v2
 * fileName : ItemRepositoryV2
 * author :  JinWoong
 * date : 2023/08/06 
 * description :
 * ===========================================================
 * DATE                 AUTHOR              NOTE
 * -----------------------------------------------------------
 * 2023/08/06           JinWoong            init
 */
public interface ItemRepositoryV2 extends JpaRepository<Item, Long> {
}
