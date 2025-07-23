package service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import practice.Member8;
import repository.MemberRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public void test1() {
        PageRequest pageRequest = PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "name"));
        Page<Member8> result = memberRepository.findByNameStartingWith("김", pageRequest);

        List<Member8> members = result.getContent();//조회된 데이터
        int totalPages = result.getTotalPages();//전체 페이지 수
        boolean hasNextPage = result.hasNext();//다음 페이지 존재 여부
    }

    public Page<Member8> findMembers(Pageable pageable) {
        return memberRepository.findByNameStartingWith("김", pageable);
    }


}
