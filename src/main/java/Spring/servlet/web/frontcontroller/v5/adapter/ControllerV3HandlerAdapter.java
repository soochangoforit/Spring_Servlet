package Spring.servlet.web.frontcontroller.v5.adapter;

import Spring.servlet.web.frontcontroller.ModelView;
import Spring.servlet.web.frontcontroller.v3.ControllerV3;
import Spring.servlet.web.frontcontroller.v5.MyHandlerAdapter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ControllerV3HandlerAdapter implements MyHandlerAdapter {

    // controllerV3를 구현한 컨트롤러이면 true를 반환한다.
    @Override
    public boolean supports(Object handler) {
        return (handler instanceof ControllerV3);
    }

    /*
        Adapter의 역할을 적절한 버전의 Handler(Controller)를 호출하고
        ControllerV3의 process를 호출하여 비니지스 로직을 처리하고
        처리된 데이터와 페이지 정보를 ModelView에 담아서 번환
        현재는 ControllerV3 스펙이라서 ModelView를 반환했지만
        ControllerV4는 단순히 String만 반환함으로, ControllerV4만의 Adapter가 필요하다.
     */
    @Override
    public ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException {

        // frontController에서 supports로 한번 ControllerV3의 인스턴스 인지 거르기 때문에 형변환이 가능하다.
        ControllerV3 controller = (ControllerV3) handler;

        // controllerV3의 process의 스펙상 Map<String, String> paramMap를 넘겨줘야 한다.
        Map<String, String> paramMap = createParamMap(request);
        ModelView mv = controller.process(paramMap);

        // ModelView에는 데이터를 담고 있는 Model과 페이지 정보를 담고 있는 View를 가지고 있다.
        return mv;
    }

    private Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();

        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName,
                        request.getParameter(paramName)));

        return paramMap;
    }
}
