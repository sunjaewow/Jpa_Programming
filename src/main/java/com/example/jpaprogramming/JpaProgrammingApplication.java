package com.example.jpaprogramming;

import domain.Member;
import domain.Member1;
import domain.Team;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class JpaProgrammingApplication {
    //엔티티 매니저 팩토리-생성
    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");

    public static void main(String[] args) {

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();
        Team team1 = em.find(Team.class, "team1");
        List<Member1> member1s = team1.getMember1s();

        for (Member1 member1 : member1s) {
            System.out.println(member1.getUsername());
        }
    }

    private static Member createMember(String id, String username) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        Member member = new Member();
        member.setId(id);
        member.setUsername(username);

        em.persist(member);
        tx.commit();

        em.close();

        return member;
    }

    private static void mergeMember(Member member) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        //Member mergeMember = em.merge(member);
        member = em.merge(member);
        tx.commit();

        //준영속 상태
        System.out.println(member.getUsername());

        //영속 상태
//        System.out.println(mergeMember.getUsername());

        System.out.println(em.contains(member));

//        System.out.println(em.contains(mergeMember));

        em.close();

    }



//    private static void logic(EntityManager entityManager) {
//
//        String id = "id1";
//        Member member = new Member();
//        member.setId(id);
//        member.setUsername("선재");
//        member.setAge(2);
//
//        //등록
//        entityManager.persist(member);
//
//        //수정
//        member.setAge(20);
//
//        //한 건 조회
//        Member findMember = entityManager.find(Member.class, id);
//        System.out.println("findMember = " + findMember.getUsername()+", age="+findMember.getAge());
//
//        //목록 조회
//        List<Member> members = entityManager.createQuery("select m from Member m", Member.class).getResultList();
//        System.out.println(members.size());
//
//        //삭제
//        entityManager.remove(member);
//
//    }

}
