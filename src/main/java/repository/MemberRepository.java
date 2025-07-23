package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import practice.Member8;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member8, Long> {
    List<Member8> findByEmailAndName(String email, String name);

    List<Member8> findByName(@Param("name") String name);//named query 지원

    @Query("select m from Member8 m where m.name=?1")
    Member8 findByNameByQuery(String name);//도 가능 이름 없는 Named 쿼리

    @Query(value = "select *from Member8  where name=?0", nativeQuery = true)
    Member8 findByNameByNativeQuery(String name);//native query지

    @Modifying(clearAutomatically = true)//bulk operation 영속성 컨텍스트를 초기화하고 싶으면 true로 설정.
    @Query("update Product p set p.price=p.price*1.1 where p.stockAmount< :stockAmount")
    int bulkPriceUp(@Param("stockAmount") String stockAmount);

}
