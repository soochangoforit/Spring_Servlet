package Spring.servlet.basic.response;

import Spring.servlet.basic.HelloData;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * http://localhost:8080/response-json
 *
 */
@WebServlet(name = "responseJsonServlet", urlPatterns = "/response-json")
public class ResponseJsonServlet extends HttpServlet {
    private ObjectMapper objectMapper = new ObjectMapper();
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //Content-Type: application/json
        response.setHeader("content-type", "application/json");
        response.setCharacterEncoding("utf-8");

        HelloData data = new HelloData();
        data.setUsername("kim");
        data.setAge(20);

        //{"username":"kim","age":20}
        String result = objectMapper.writeValueAsString(data); //json 객체를 string으로 변환
        response.getWriter().write(result);

        //spring 프레임 워크에서는 JSON을 반환하고자 할때 객체만 반환하면 알아서 JSON 객체가 String으로 변환되어 넘어간다.

    }
}
