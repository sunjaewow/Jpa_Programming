package repository;

import domain.Member;
import domain.Member1;
import jakarta.persistence.*;

import java.util.List;

public class Repository {
    EntityManagerFactory hi = Persistence.createEntityManagerFactory("hi");//파라미터 바인딩
    EntityManager em = hi.createEntityManager();
    public void main1() {

        List<Member> resultList = em.createQuery("select m from Member m where m.username=:name", Member.class)
                .setParameter("name", "User1")
                .getResultList();//한번에 가능
//        query.setParameter("name", "User1");
//        List<Member> resultList = query.getResultList();
        for (Member member : resultList) {
            System.out.println("member="+member);
        }

        em.createQuery("select m from Member m where m.username=?1", Member.class)//위치 기준 파라미터
                .setParameter(1, "User")
                .getResultList();
        //파라미터 바인딩 형식을 적극 추천함 쿼리의 파싱 결과를 재사용할 수 있기 때문에 성능 향상이됨.

        Query query1 = em.createQuery("select m.username from Member m");//반환타입이 명확하지 않은 경우 Query 명확하면 TypeQuery
        List resultList1 = query1.getResultList();
        for (Object o : resultList1) {
            Object[] result = (Object[]) o;//결과가 둘 이상이면 배열을 반환
        }

        List<Object[]> resultList2 = em.createQuery("select m.username, m.id from Member m").getResultList();//엔티티 프로젝션은 반환 타입
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

        TypedQuery<Member> query2 = em.createQuery("select m from Member m order by m.username desc ", Member.class);
        query2.setFirstResult(10);//11번째부터
        query2.setMaxResults(20);//20개를 추출
        query2.getResultList();

    }

    public void join() {//내부 조인
        String teamName = "팀A";
        List<Member1> result = em.createQuery("select m from Member1 m inner join m.team t where t.name=:teamName", Member1.class)
                .setParameter("teamName", teamName)
                .getResultList();

        em.createQuery("select m from Member1 m left join m.team t where t.name=:teamName", Member1.class)//외부 조인
                .setParameter("teamName", teamName)
                .getResultList();

        em.createQuery("select  count(m) from Member1 m, m.team t where m.username=t.name");//세타조인
        //전혀 관련없는 username과 name을 조인한다.

        em.createQuery("select m,t from Member1 m left join m.team t on t.name='A'");//join on

        em.createQuery("select m from Member1 m join fetch m.team");//fetch join 지연 로딩 발생 안함
        //조회된 team 엔티티는 프록시 객체가 아닌 실제 객체임 따라서 준영속 상태가 되어도 팀을 조회할 수 있음.

        em.createQuery("select distinct t from Team t join fetch t.member1s where t.name='팀A'");

        em.createQuery("select m.username from Team t join t.member1s m ");

        em.createQuery("select count(t.member1s) from Team t");

        em.createQuery("select t.member1s.size from Team t");

        em.createQuery("select m from Member m where m.age> (select avg(m2.age) from Member m2)");

        em.createQuery("select m from Member m where (select count(o) from Order o where m=o.member)>0");

//        em.createQuery("select m from Member m where m.orders.size");
    }
}
