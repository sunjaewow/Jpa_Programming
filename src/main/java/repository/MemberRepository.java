package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import practice.Member8;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member8, Long> {
    List<Member8> findByEmailAndName(String email, String name);
}
