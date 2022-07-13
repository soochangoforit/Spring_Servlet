package Spring.servlet.web.frontcontroller.v2;

import Spring.servlet.web.frontcontroller.MyView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface ControllerV2 {

    //현재 Controller에서 view를 바로 return 하기 때문에 return 값을 MyView로 하도록 한다.
    MyView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

}
