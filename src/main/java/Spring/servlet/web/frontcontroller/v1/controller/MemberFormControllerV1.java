package Spring.servlet.web.frontcontroller.v1.controller;

import Spring.servlet.web.frontcontroller.v1.ControllerV1;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MemberFormControllerV1 implements ControllerV1 {

    // Servlet에서 다뤘던 service 메소드랑 같은 역할을 하고 있다. Servlet를 직접 사용할때는 implements를 HttpSevlet으로 해줬지만
    // 지금은 우리가 만든 Interface인 ControllerV1을 implements하고 process를 Override하여 사용한다.
    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String viewPath = "/WEB-INF/views/new-form.jsp";

        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response);

    }


}
