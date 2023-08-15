package hello.springtx.propagation;

import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * packageName :  hello.springtx.propagation
 * fileName : MemberRepository
 * author :  JinWoong
 * date : 2023/08/13 
 * description :
 * ===========================================================
 * DATE                 AUTHOR              NOTE
 * -----------------------------------------------------------
 * 2023/08/13           JinWoong            init
 */

@Slf4j
@Repository
@RequiredArgsConstructor
public class MemberRepository {

	private final EntityManager em;

	@Transactional
	public void save(Member member) {
		log.info("member 저장");
		em.persist(member);
	}

	public Optional<Member> find(String username) {
		return em.createQuery("select m from Member m where m.username = :username", Member.class)
			.setParameter("username", username)
			.getResultList().stream().findAny();

	}
}
