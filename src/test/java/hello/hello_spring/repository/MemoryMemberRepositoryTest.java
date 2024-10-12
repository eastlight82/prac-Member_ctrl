package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static hello.hello_spring.repository.MemoryMemberRepository.store;
import static org.assertj.core.api.Assertions.*; //static import

public class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository= new MemoryMemberRepository();

    @AfterEach //mthd 끝나고 (동기성)
    public void afterEach(){
        repository.clearStore();
    }

    @Test
    public void save(){
        Member member= new Member();
        member.setName("spring");

        repository.save(member);
        Member result= repository.findById(member.getId()).get();
        //동일여부
//        System.out.println("result = " + (result== member));
        Assertions.assertEquals(member, result); //녹,빨
        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName(){ 
        Member member1= new Member(); //회원obj 만듬
        member1.setName("spring1");
        repository.save(member1);

        Member member2= new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result= repository.findByName("spring1").get();

        assertThat(result).isEqualTo(member1);
        
        //findAll "먼저" 실행-> ByName시 다른 객체
    }

    @Test
    public void findAll(){
        Member member1= new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2= new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result= repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }


}
