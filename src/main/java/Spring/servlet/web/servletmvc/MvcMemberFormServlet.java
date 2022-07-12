package Spring.servlet.web.servletmvc;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// MVC패턴에서 Controller에 해당하는 부분이다.
@WebServlet(name = "mvcMemberFormServlet", urlPatterns = "/servlet-mvc/members/new-form")
public class MvcMemberFormServlet extends HttpServlet {

    //MVC패턴을 통해 form을 보여주고 싶다.
    // MVC 패턴에서는 항상 Controller를 거쳐서 view로 들어가야 한다.
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //단순히 폼을 반환하는것이기 때문에 , 따른 로직이 필요없고 폼을 보여줄 jsp로 이동하면 된다.
        String viewPath = "/WEB-INF/views/new-form.jsp";

        // servlet에서 controller에서 view로 이동할때 사용되는 dispatcher
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        // servlet에서 jsp를 호춯할 수 있다.
        // 서버 내부에서 한번더 jsp 파일 폼을 얻기 위한 요청을 한다고 생각하면 된다.
        // 다른 서블릿이나 JSP로 이동할 수 있는 기능이다. 서버 내부에서 다시 호출이 발생한다. , Client로 다시 이동하고 하는게 아니다. (서버내부)
        dispatcher.forward(request, response);

        // JSP 사용시 /WEB-INF란 이 경로 안에 JSP가 있으면 외부에서 직접 JSP를 호출할 수 없다. 우리가 기대하는것은 항상 컨트롤러를 통해서 JSP를
        // 호출하는 것이다.


        /*
         * redirect vs forward
         *
         * 리다이렉트는 실제 클라이언트(웹 브라우저)에 응답이 나갔다가, 클라이언트가 redirect 경로로 다시
         * 요청한다. 따라서 클라이언트가 인지할 수 있고, URL 경로도 실제로 변경된다. 반면에 포워드는 서버
         * 내부에서 일어나는 호출이기 때문에 클라이언트가 전혀 인지하지 못한다
         */

    }

}
