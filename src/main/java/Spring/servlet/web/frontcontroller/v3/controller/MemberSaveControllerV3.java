package Spring.servlet.web.frontcontroller.v3.controller;

import Spring.servlet.domain.member.Member;
import Spring.servlet.domain.member.MemberRepository;
import Spring.servlet.web.frontcontroller.ModelView;
import Spring.servlet.web.frontcontroller.v3.ControllerV3;

import java.util.Map;

public class MemberSaveControllerV3 implements ControllerV3 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public ModelView process(Map<String, String> paramMap){

        // frontController에서 받은 request 데이터를 Servlet 기술을 사용하지 않고 일반적인 Map을 활용하고자 한다.
        String username = paramMap.get("username");
        int age = Integer.parseInt(paramMap.get("age"));

        Member member = new Member(username, age);
        memberRepository.save(member);

        // ModelView에는 논리적인 이름만 넣어서 반환한다. Model에는 key-value의 값을 넣어준다.
        ModelView mv = new ModelView("save-result");
        mv.getModel().put("member", member);

        return mv;
    }

}
