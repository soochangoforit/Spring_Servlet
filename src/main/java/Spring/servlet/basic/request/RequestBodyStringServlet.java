package Spring.servlet.basic.request;

import org.springframework.util.StreamUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet(name="requestBodyStringServlet" , urlPatterns = "/request-body-string")
public class RequestBodyStringServlet extends HttpServlet {

    // 프론트로 부터 데이터가 넘어오면, 맨 앞의 was는 해당 요청 데이터들을 request , response객체를 만들어서
    // 직접 하나 하나 parse 과정을 통해 request , response 객체에 데이터를 담고
    // 데이터가 담겨진 각각의 객체를 서블릿 컨테이너가 가지고 있는 servlet의 service 파라미터로 넘겨준다.
    // 해당 service에서 request 객체에 담겨진 데이터를 활용해서 비지니스 로직을 수행하고
    // response 객체에 하나의 응답을 답아서 다시 프론트로 내보낸다.
    // 비지니스 로직을 제외하고 나머지 부부들이 동일하기 때문에 이것을 대신해주는 Spring MVC 프레임워크가 나왔다.
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ServletInputStream inputStream = request.getInputStream(); // 메세지 바다의 내용을 byte 코드로 가져올수 있다.
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8); // string으로 바꿔줘야 한다.
        System.out.println("messageBody = " + messageBody);
        response.getWriter().write("ok");

    }
}
