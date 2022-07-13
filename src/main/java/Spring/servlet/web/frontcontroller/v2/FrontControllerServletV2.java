package Spring.servlet.web.frontcontroller.v2;

import Spring.servlet.web.frontcontroller.MyView;
import Spring.servlet.web.frontcontroller.v2.controller.MemberFormControllerV2;
import Spring.servlet.web.frontcontroller.v2.controller.MemberListControllerV2;
import Spring.servlet.web.frontcontroller.v2.controller.MemberSaveControllerV2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV2", urlPatterns = "/front-controller/v2/*")
public class FrontControllerServletV2 extends HttpServlet {

    private Map<String, ControllerV2> controllerMap = new HashMap<>();

    public FrontControllerServletV2() {
        controllerMap.put("/front-controller/v2/members/new-form", new
                MemberFormControllerV2());
        controllerMap.put("/front-controller/v2/members/save", new
                MemberSaveControllerV2());
        controllerMap.put("/front-controller/v2/members", new
                MemberListControllerV2());
    }

    // 맨 앞의 Servlet WAS가 담당한다.
    // 최초의 Request는 해당 service로 들어오고 , URL에 맞게끔 Controller를 연결해준다.
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();

        ControllerV2 controller = controllerMap.get(requestURI);

        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // controller에서 받은 jsp위치를 가지고 html을 반환한다.
        MyView view = controller.process(request, response);
        view.render(request, response);
    }
}
