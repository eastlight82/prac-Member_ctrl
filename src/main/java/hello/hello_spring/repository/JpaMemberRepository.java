package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository{

    private final EntityManager em; //SpringBoot가 자동 객체 생성

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member); //JPA가 insertQry 만들어 DB넣고, setId까지
        return member;
    }

    @Override
    public Optional<Member> findById(Long Id) {
        Member member = em.find(Member.class, Id); //조회type, 식별자pk
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result=em.createQuery("select m from Member m where m.name=:name", Member.class)
                .setParameter("name",name)
                .getResultList();
        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() { //객체 대상 query함
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }
}
