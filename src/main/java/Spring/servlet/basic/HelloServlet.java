package Spring.servlet.basic;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="helloServlet", urlPatterns="/hello")
public class HelloServlet extends HttpServlet {

    @Override // 서블릿이 호출되면 ,서비스 매서드가 호출된다.
    protected void service(HttpServletRequest reqest, HttpServletResponse response) throws ServletException, IOException {

        // 서블릿 http 요청이 오면, WAS(서블릿 컨테이너)가 request , response 객체를 만들어서 servlet에 던져준다.
        System.out.println("HelloServlet.service");
        System.out.println("reqest = " + reqest);
        System.out.println("response = " + response);

        String username = reqest.getParameter("username"); // 쿼리 파라미터 조회 가능 , 서블릿이 개발을 편라하게 하도록 도와준다.
        System.out.println("username = " + username);

        // http 응답 메시지에 데이터가 담겨서 나가게 된다.
        response.setContentType("text/plain"); //header
        response.setCharacterEncoding("UTF-8"); //header
        response.getWriter().write("Hello, " + username);



        // 시작하면, 스프링 부트가 내장 톰켓 서버를 실행한다.
        // 그러면서 내장 톰켓 서버는 내부적으로 서블릿 컨테이너 기능을 가지고 있다.
        // 서블릿 컨테이너를 통해서 내부적으로 서블릿을 모두 생성한다. (서블릿 풀)
        // 서블릿 컨테이너보다 앞에 있는 웹 애플리케이션 서버가 request, response 객체를 만들어서 서블릿 컨테이너로 넘겨준다.
        // 싱글톤으로 떠 있는 helloSevlet을 호출한다.
        // 내부적으로 처리 후 , response에 데이터를 담아서 다시 앞으로 전달한다.
        // 나미저 contentLenth 같은 정보는 Tomcat (WAS , 서블릿 컨테이너)가 알아서 처리해준다.


    }
}
