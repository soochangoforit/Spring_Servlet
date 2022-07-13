package Spring.servlet.web.frontcontroller.v4;

import Spring.servlet.web.frontcontroller.MyView;
import Spring.servlet.web.frontcontroller.v4.controller.MemberFormControllerV4;
import Spring.servlet.web.frontcontroller.v4.controller.MemberListControllerV4;
import Spring.servlet.web.frontcontroller.v4.controller.MemberSaveControllerV4;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV4", urlPatterns = "/front-controller/v4/*")
public class FrontControllerServletV4 extends HttpServlet {

    private Map<String, ControllerV4> controllerMap = new HashMap<>();
    public FrontControllerServletV4() {
        controllerMap.put("/front-controller/v4/members/new-form", new MemberFormControllerV4());
        controllerMap.put("/front-controller/v4/members/save", new MemberSaveControllerV4());
        controllerMap.put("/front-controller/v4/members", new MemberListControllerV4());
    }
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String requestURI = request.getRequestURI();
        ControllerV4 controller = controllerMap.get(requestURI);
        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // paramMap에는 Request에 담긴 key - value를 Map<String, String> 형식으로 받아주는 역할을 하고 있다.
        Map<String, String> paramMap = createParamMap(request);

        Map<String, Object> model = new HashMap<>(); //추가
        // 아니 model에 빈 인스턴스를 넘겨주고 실제 각 controller의 process에서 파라미터로 넘겨온 model에 데이터를 담아준다.
        // 하지만 return은 단순히 viewName만 하고 있다.
        // 아니 파라미터로 인스턴스를 넘겨주고 값을 담고 활용하려면 당연히 return에 String에 viewName과 model을 반환해야하지 않나?
        // 난 항상 그렇게 해왔지만 , 잘 생각해보면 controller의 porcess method가 끝나는 시점에는 model에 put()으로 인해서
        // 데이터가 이미 담겨있는 상태이다. 따라서 process가 끝나는 시점에는 파라미터로 넘어온 model에는 데이터가 담겨 있으니
        // 따로 return으로 model를 반환하지 않고, 파라미터의 model를 그대로 사용한다.
        String viewName = controller.process(paramMap, model);

        MyView view = viewResolver(viewName);

        // 해당 model에는 Map 형식으로 이루어진 데이터가 들어가있다.
        view.render(model, request, response);
    }
    private Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName,
                        request.getParameter(paramName)));
        return paramMap;
    }
    private MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }

}

