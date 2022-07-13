package Spring.servlet.web.frontcontroller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * 각각의 Controller에서 response로 jsp가 호출될때, MyView를 생성해서 호출하도록 한다. 공통된 코드의 중복 제거 가능
 */
public class MyView {

    private String viewPath;

    public MyView(String viewPath) {
        this.viewPath = viewPath;
    }

    public void render(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response);
    }

    public void render(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // model을 Servlet request에 저장한다.
        modelToRequestAttribute(model, request);

        //MyView가 가지고 있는 jsp 경로에 대해서 이동하고자 한다. 실질적인 jsp이동을 담당하고 있다.
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response);
    }

    // 우리가 직접 만든 model class가 담고 있는 데이터를 실제 Servlet의 request에 저장한다.
    private void modelToRequestAttribute(Map<String, Object> model, HttpServletRequest request) {
        //jsp는 request객체에서 데이터를 가져오기 떄문에, request에 데이터를 저장해야 한다.
        model.forEach((key, value) -> request.setAttribute(key, value));
    }


}
