package Spring.servlet.web.servlet;

import Spring.servlet.domain.member.Member;
import Spring.servlet.domain.member.MemberRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "memberSaveServlet", urlPatterns = "/servlet/members/save")
public class MemberSaveServlet extends HttpServlet {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    // servlet를 만들때는 GET, POST 구분없이 하나의 Request , Response로 보고 service 메소드를 만들어준다.
    // GET 의 쿼리 스트링이든, POST의 body에 form-data는 모두 username=이수찬&age=20 이런식으로 보내준다.
    // GET에서는 쿼리 스트링이 POST에서는 그대로 body로 들어간다고 생각하면 된다.
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("MemberSaveServlet.service");

        // get이든 post이든 같은 메소드로 데이터를 읽을수 있다.
        // 쿼리스트링 혹은 post의 body에 부분에 데이터가 들어오는것들은 전부 string 형태이다. 따라서 age string을 int로 사용하기 위해
        // 형변환 실행
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));

        Member member = new Member(username, age);

        System.out.println("member = " + member);
        memberRepository.save(member);

        //response
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");

        PrintWriter w = response.getWriter();
        w.write("<html>\n" +
                "<head>\n" +
                " <meta charset=\"UTF-8\">\n" +
                "</head>\n" +
                "<body>\n" +
                "성공\n" +
                "<ul>\n" +
                " <li>id="+member.getId()+"</li>\n" +
                " <li>username="+member.getUsername()+"</li>\n" +
                " <li>age="+member.getAge()+"</li>\n" +
                "</ul>\n" +
                "<a href=\"/index.html\">메인</a>\n" +
                "</body>\n" +
                "</html>");
    }

}
