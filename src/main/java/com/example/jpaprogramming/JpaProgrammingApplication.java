package com.example.jpaprogramming;

import domain.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class JpaProgrammingApplication {

    public static void main(String[] args) {
        //엔티티 매니저 팩토리-생성
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");

        //엔티티 매니저-생성
        EntityManager em = emf.createEntityManager();

        //트랜잭션-획득
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            logic(em);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();
    }

    private static void logic(EntityManager entityManager) {

        String id = "id1";
        Member member = new Member();
        member.setId(id);
        member.setUsername("선재");
        member.setAge(2);

        //등록
        entityManager.persist(member);

        //수정
        member.setAge(20);

        //한 건 조회
        Member findMember = entityManager.find(Member.class, id);
        System.out.println("findMember = " + findMember.getUsername()+", age="+findMember.getAge());

        //목록 조회
        List<Member> members = entityManager.createQuery("select m from Member m", Member.class).getResultList();
        System.out.println(members.size());

        //삭제
        entityManager.remove(member);

    }

}
