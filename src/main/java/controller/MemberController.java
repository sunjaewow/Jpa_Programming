package controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import practice.Member8;
import repository.MemberRepository;
import service.MemberService;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberRepository memberRepository;
    private final MemberService memberService;

    @RequestMapping("member/")
    public String memberUpdateForm(@RequestParam("id") Long id, Model model) {
        Optional<Member8> member = memberRepository.findById(id);
        model.addAttribute("member", member);//회원을 찾고 뷰에 넣어줘야함.
        return "member";
    }//도메인 클래스 컨버터를 사용하기전


    @RequestMapping("member/")//사용 후 아이디를 받지만 도메인 클래스 컨버터가 중간에 동작해서 아이디를 회원 엔티티객체로 변환해서 넘겨준다. 따라서 컨트롤러를 단순하게 사용할 수 있다.
    //도메인 클래스 컨버터는 해당 엔티티와 관련된 리포지토리를 사용해서 엔티티를 찾는다. 여기서는 memberRepository를 통해서 회원 아이디로 회원 엔티티를 찾는다.
    //도메인 클래스 컨버터를 통해 넘어온 회원 엔티티를 컨트롤러에서 직접 수정해도 실제 데이터베이스에는 반영되지 않는다.
    public String memberUpdateForm1(@RequestParam("id") Member8 member8, Model model) {
        model.addAttribute("member", member8);
        return "Member";
    }

    //members?page=0&size=20&sort=name, desc&sort=address.city
    @GetMapping("/members")//HandlerMethodArgumentResolver
    public String list(Pageable pageable, Model model) {//Pageable은 인터페이스 실제로는 PageRequest가 만들어짐.
        Page<Member8> page = memberService.findMembers(pageable);
        model.addAttribute("members", page.getContent());
        return "members";
    }

    @GetMapping("/members")//Pageable의 기본값은 page=0, size=20이다.
    //변경 하고싶으면 @PageableDefault를 설정하면 된다.
    public String list1(@PageableDefault(size = 12, sort="name", direction = Sort.Direction.DESC) Pageable pageable, Model model, Sort sort) {
        Page<Member8> page = memberService.findMembers(pageable);
        model.addAttribute("members", page.getContent());
        return "members";
    }

}
