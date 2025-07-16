package repository;

import domain.Member;
import jakarta.persistence.*;

import java.util.Iterator;
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

        List<Object[]> resultList2 = em.createQuery("select m.name, m.id from Member m").getResultList();//엔티티 프로젝션은 반환 타입
        //확실하지 않아서 Query로 됨.

        for (Object[] o : resultList2) {
            String name = (String) o[0];
            Long id = (Long) o[1];
        }

//        Iterator iterator = resultList2.iterator();
//        while (iterator.hasNext()) {
//            Object[] row = (Object[]) iterator.next();
//            String name = (String) row[0];
//            Long id = (Long) row[1];
//        }

        TypedQuery<MemberDto> query = em.createQuery("select repository.MemberDto from Member m", MemberDto.class);
        query.getResultList();//DTO를 활용한 프로젝션
    }
}
