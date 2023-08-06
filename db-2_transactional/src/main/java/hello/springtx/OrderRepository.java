package hello.springtx;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * packageName :  hello.springtx
 * fileName : OrderRepository
 * author :  JinWoong
 * date : 2023/08/06 
 * description :
 * ===========================================================
 * DATE                 AUTHOR              NOTE
 * -----------------------------------------------------------
 * 2023/08/06           JinWoong            init
 */
public interface OrderRepository extends JpaRepository<Order, Long> {
}
