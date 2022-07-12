package Spring.servlet.basic.request;

import Spring.servlet.basic.HelloData;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.util.StreamUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * http://localhost:8080/request-body-json
 *
 * JSON 형식 전송
 * content-type: application/json
 * message body: {"username": "hello", "age": 20}
 *
 */
@WebServlet(name="requestBodyJsonServlet", urlPatterns = "/request-body-json")
public class RequestBodyJsonServlet extends HttpServlet {

    // JSON을 dto 객체로 바인딩 작업해주는 objectMapper 라이브러리
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        System.out.println("messageBody = " + messageBody); // byte -> String 인코딩

        HelloData helloData = objectMapper.readValue(messageBody, HelloData.class); // HelloData로 객체 바인딩

        // 만약 프론트에서 form-data 형식으로 데이터를 전달하면, objectMapper를 이용해서 객체를 바인딩할 수 없다.
        // 일반적으로 폼 데이터 형식으로 들어온다면, request에서 바로 getParameter()를 이용해서 가져오면 된다.
        // 만약 프론트에서 JSON 형식으로 데이터를 전달하면, objectMapper를 이용해서 객체를 바인딩할 수 있다.

        // JSON 결과를 파싱해서 사용할 수 있는 자바 객체로 변환하려면 Jackson , Gson 같은 JSON 변환 라이브러리를 추가해서
        // 사용해야 한다. 스프링 부트로 Spring MVC를 선택하면 기본으로 Jackson 라이브러리('ObjectMapper')가 설치되어 있다.

        // spring boot에서는 form-data에 대해서는 @ModelAttribute를 이용해서 객체를 바인딩 한다.
        // JSON 데이터 같은 경우는 @RequestBody를 이용해서 객체를 바인딩 한다.

        System.out.println("helloData.username = " + helloData.getUsername());
        System.out.println("helloData.age = " + helloData.getAge());
        response.getWriter().write("ok");
    }

}
