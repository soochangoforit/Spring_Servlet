package Spring.servlet.web.frontcontroller.v2.controller;

import Spring.servlet.domain.member.Member;
import Spring.servlet.domain.member.MemberRepository;
import Spring.servlet.web.frontcontroller.MyView;
import Spring.servlet.web.frontcontroller.v2.ControllerV2;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class MemberListControllerV2 implements ControllerV2 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public MyView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Member> members = memberRepository.findAll();
        // request의 작은 저장소에 데이터를 저장한다.
        request.setAttribute("members", members);

        return new MyView("/WEB-INF/views/members.jsp");
    }
}
