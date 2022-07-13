package Spring.servlet.web.frontcontroller.v3;

import Spring.servlet.web.frontcontroller.ModelView;
import Spring.servlet.web.frontcontroller.MyView;
import Spring.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import Spring.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import Spring.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV3", urlPatterns = "/front-controller/v3/*")
public class FrontControllerServletV3 extends HttpServlet {
    private Map<String, ControllerV3> controllerMap = new HashMap<>();

    public FrontControllerServletV3() {
        controllerMap.put("/front-controller/v3/members/new-form", new MemberFormControllerV3());
        controllerMap.put("/front-controller/v3/members/save", new MemberSaveControllerV3());
        controllerMap.put("/front-controller/v3/members", new MemberListControllerV3());
    }

    // 프론트에서 request 요청이 오면 가장 먼저 반응하는 Method이다.
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 프론트에서 받은 request에서 url를 가져온다.
        String requestURI = request.getRequestURI();

        // 가져온 url를 통해서 적절한 controller를 반환한다.
        ControllerV3 controller = controllerMap.get(requestURI);
        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        Map<String, String> paramMap = createParamMap(request);
        ModelView mv = controller.process(paramMap);

        String viewName = mv.getViewName();
        // viewResolver는 컨트롤러가 반환하는 논리 뷰 이름을 실제 물리 뷰 경로로 변경한다.
        // 그리고 실제 물리 경로가 있는 MyView 객체를 반환한다.
        MyView view = viewResolver(viewName);

        //MyView 객체에 ModelView가 가지고 있는 Map 데이터를 전달한다.
        view.render(mv.getModel(), request, response);
    }

    // 프론트에서 날라온 request를 통해서 데이터를 map 형태로 보관하고자 한다.
    private Map<String, String> createParamMap(HttpServletRequest request) {

        Map<String, String> paramMap = new HashMap<>();

        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName,
                        request.getParameter(paramName)));

        // key - value로 이루어진 map을 반환한다. 해당 map에는 request에서 보낸 데이터들이 담겨있다.
        return paramMap;
    }

    // view Resolver의 역할을 대신 해주는 역할을 한다. ModelView에서 MyView 객체로 변환해서 반환한다.
    private MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }
}
