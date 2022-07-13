package Spring.servlet.web.frontcontroller.v5.adapter;

import Spring.servlet.web.frontcontroller.ModelView;
import Spring.servlet.web.frontcontroller.v4.ControllerV4;
import Spring.servlet.web.frontcontroller.v5.MyHandlerAdapter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ControllerV4HandlerAdapter implements MyHandlerAdapter {

    @Override
    public boolean supports(Object handler) {
        return handler instanceof ControllerV4;
    }

    @Override
    public ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException {

        ControllerV4 controller = (ControllerV4) handler;

        Map<String, String> paramMap = createParamMap(request);

        // 여기서 adapter의 중요한 역할이 나온다.
        // controller의 process는 viewName만 반환하지만, adapter의 handle은 ModelView를 반환한다.
        // 상황에 맞게 잘 반환되는것을 확인할 수 있다.
        Map<String, Object> model = new HashMap<>();
        String viewName = controller.process(paramMap, model);

        ModelView mv = new ModelView(viewName);
        mv.setModel(model); // process에서 작업한 model 값을 그대로 넣는다.

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
