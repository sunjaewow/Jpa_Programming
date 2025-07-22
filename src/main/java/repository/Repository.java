package repository;

import ch.qos.logback.core.util.StringUtil;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPADeleteClause;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.querydsl.jpa.impl.JPAUpdateClause;
import domain.Member;
import domain.Member1;
import domain.Product;
import domain.QMember;
import dto.ItemDTO;
import jakarta.persistence.*;
import org.springframework.util.StringUtils;
import practice.Member8;
import practice.QMember8;
import practice.QOrder;
import practice.QOrderItem;
import practice.item.Item;
import practice.item.QItem;

import java.util.List;

public class Repository {
    EntityManagerFactory hi = Persistence.createEntityManagerFactory("hi");//파라미터 바인딩
    EntityManager em = hi.createEntityManager();

    public void main1() {

        List<Member> resultList = em.createQuery("select m from Member8 m where m.name=:name", Member.class)
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

        em.createQuery("select m from Member m where (select count(o) from Order o where m=o.member8)>0");

//        em.createQuery("select m from Member m where m.orders.size");

        em.createQuery("select m from Member1 m where exists (select t from m.team t where t.name='팀A')");

        em.createQuery("select m from Member1 m where m.team=any (select t from Team t)");

        em.createQuery("select t from Team t where t in (select t2 from Team t2 join t2.member1s m2 where m2.username='a')");
    }

    public void training() {
        em.createQuery("select m from Member m where m.age between 10 and 20");

        em.createQuery("select m from Member m where m.username in ('a', 'b')");

        em.createQuery("select m from Member m where m.username like '%원%'");

//        em.createQuery("select m from Member m where m.orders is not empty ");//주문이 하나라도 있는 회원 조회

        em.createQuery("select t from Team t where :memberParam member of t.member1s").setParameter("memberParam", 'a');

        em.createQuery("select year(current timestamp ), month (current timestamp ), day (current timestamp )from Member ");

        em.createQuery("select case when m.age <=10 then '학생요금' else '일반요금' end " +
                "from Member m");

        em.createQuery("select i from Item i");

        TypedQuery<Member8> z = em.createNamedQuery("Member.findByName", Member8.class);

    }

    public void queryDSL() {
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
//        JPAQuery<Object> query = new JPAQuery<>(em);
//        QMember qMember = new QMember("m");
        QMember member = QMember.member;

        List<Member> members = queryFactory
                .selectFrom(member)
                .where(member.username.eq("회원1"))
                .orderBy(member.username.desc())
                .fetch();

        QItem item = QItem.item;
        List<Item> list = queryFactory.selectFrom(item)
                .where(item.name.eq("좋은 상품").and(item.price.gt(30000)))
                .fetch();

        List<Item> list1 = queryFactory.selectFrom(item)
                .where(item.price.gt(20000))
                .orderBy(item.price.desc(), item.stockQuantity.asc())
                .offset(10).limit(20)
                .fetch();

        queryFactory.selectFrom(item)
                .groupBy(item.price)
                .having(item.price.gt(10000))
                .fetch();

        QOrder order = QOrder.order;

        QMember8 member8 = QMember8.member8;

        QOrderItem orderItem = QOrderItem.orderItem;

        queryFactory.selectFrom(order)
                .join(order.member8, member8)
                .leftJoin(order.orderItems, orderItem)
                .fetch();

        queryFactory.selectFrom(order)
                .leftJoin(order.orderItems, orderItem)
                .on(orderItem.count.gt(2))
                .fetch();

        queryFactory.selectFrom(order)
                .innerJoin(order.member8, member8).fetchJoin()
                .leftJoin(order.orderItems, orderItem).fetchJoin()//fetch
                .fetch();

        queryFactory.select(order, member8)
                .from(order)
                .where(order.member8.eq(member8))
                .fetch();


        List<String> na = queryFactory.select(item.name)//프로젝션
                .from(item)
                .fetch();

        List<com.querydsl.core.Tuple> fetch = queryFactory.select(item.name, item.price)//여러 컬럼 반환과 튜플
                .from(item)
                .fetch();

        for (com.querydsl.core.Tuple tuple : fetch) {
            System.out.println("name="+tuple.get(item.name));
            System.out.println("price="+tuple.get(item.price));
        }

        queryFactory.select(Projections.constructor(ItemDTO.class, item.name, item.price))
                .from(item)
                .fetch();//DTO 프로젝션

        JPAUpdateClause updateClause = new JPAUpdateClause(em, item);

        updateClause.where(item.name.eq("시골개발자의 JPA 책"))
                .set(item.price, item.price.add(100))
                .execute();//배치 쿼리는 영속성 컨텍스트를 무시하고 데이터베이스를 직접 쿼리한다는 점.

        JPADeleteClause deleteClause = new JPADeleteClause(em, item);
        deleteClause.where(item.name.eq("시골개발자의 JPA 책"))
                .execute();

        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setUsername("시골개발자");
        itemDTO.setPrice(10000);

        BooleanBuilder builder = new BooleanBuilder();
        if (StringUtils.hasText(itemDTO.getUsername())) {
            builder.and(item.name.contains(itemDTO.getUsername()));
        }

        if (itemDTO.getPrice() != null) {
            builder.and(item.price.gt(itemDTO.getPrice()));
        }

        List<Item> fetch1 = queryFactory.selectFrom(item)
                .where(builder)
                .fetch();
    }

    public void nativeQuery() {
        String sql = "select id, age, name " +
                "from member where age>?";

        Query nativeQuery = em.createNativeQuery(sql, Member.class)
                .setParameter(1, 20);

        @SuppressWarnings("unchecked")//자바 컴파일러는 제네릭 타입을 명확히 확인할 수 없는 형변환에 대해 경고를 띄움
                //이런 경우 내가 타입을 안전하게 알고잇으니 경고 무시해도 돼 라고 하는것임.
        List<Member> members = nativeQuery.getResultList();
        
    }

    public void bulk() {
        String qlString = "update Product p " +
                "set p.price =p.price*1.1 " +
                "where p.stockAmount < :stockAmount";

        em.createQuery(qlString)
                .setParameter("stockAmount", 10)
                .executeUpdate();//벌크 연선
        //수백개 이상의 엔티티를 하나씩 처리하기에는 시간이 너무 오래 걸린다. 이럴 때 여러 건을 한 번에 수정하거나 삭제하는 벌크 연산을 사용하면 된다.

        String qlString2 = "delete from Product p " +
                "where p.price< :price";

        em.createQuery(qlString2)
                .setParameter("price", 100)
                .executeUpdate();//벌크 연산은 영속성 컨텍스트를 무시하고 데이터베이스에 직접 쿼리한다는 점에 주의해야함.
        //---------------------------------------------------------------------------------------------------------------------

        Product productA = em.createQuery("select p from Product p where p.name=:name", Product.class)
                .setParameter("name", "productA")
                .getSingleResult();

        System.out.println("productA 수정 전 = " + productA.getPrice());

        em.createQuery("update Product p set p.price=p.price*1.1")
                .executeUpdate();

        System.out.println("productA 수정 후 = "+productA.getPrice());

        //문제점 처음 상품을 조회하고 영속성 컨텍스트에 저장 이후 벌크연산으로 수정을 하면 영속성 컨텍스트는 무시하고 데이터베이스에 직접 쿼리
        //그 결과 조회를 해도 수정 전 가격이 출력이됨. 문제점.
        //---------------------------------------------------------------------------------------------------------------------
    }

}
