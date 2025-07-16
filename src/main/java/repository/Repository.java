package repository;

import domain.Member;
import jakarta.persistence.*;

import java.util.List;

public class Repository {
    public void main1() {
        EntityManagerFactory hi = Persistence.createEntityManagerFactory("hi");//파라미터 바인딩
        EntityManager em = hi.createEntityManager();

        List<Member> resultList = em.createQuery("select m from Member m where m.name=:name", Member.class)
                .setParameter("name", "User1")
                .getResultList();//한번에 가능
//        query.setParameter("name", "User1");
//        List<Member> resultList = query.getResultList();
        for (Member member : resultList) {
            System.out.println("member="+member);
        }

        em.createQuery("select m from Member m where m.name=?1", Member.class)//위치 기준 파라미터
                .setParameter(1, "User")
                .getResultList();
        //파라미터 바인딩 형식을 적극 추천함 쿼리의 파싱 결과를 재사용할 수 있기 때문에 성능 향상이됨.

        Query query1 = em.createQuery("select m.name from Member m");//반환타입이 명확하지 않은 경우 Query 명확하면 TypeQuery
        List resultList1 = query1.getResultList();
        for (Object o : resultList1) {
            Object[] result = (Object[]) o;//결과가 둘 이상이면 배열을 반환
        }

    }
}
