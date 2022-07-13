package Spring.servlet.web.springmvc.v1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Component : 컴포넌트 스캔을 통해 스프링 빈으로 등록
 * @RequestMapping : RequestMappingHandlerMapping에서 찾을 수 있는 컨트롤러의 대상이 된다.
 *
 * 해당 클래스는 컴포넌트 스캔과 동시에 HandlerMapping에서 controller로 찾을 수 있는 대상이 될려면
 * 클래스 위에 2개다 선언하면 된다.
 *
 * 하지만 간단히 @Controller 만을 통해서 컴포넌트 스캔과 동시에 RequestMappingHandlerMapping에서 찾을 수 있는 컨트롤러의 대상이
 * 되도록 만들 수 있다.
 *
 *  RequestMappingHandlerAdapter으로 부터 실행이 가능하다. handle()메소드 실행가능 */
@Controller
public class SpringMemberFormControllerV1 {

    @RequestMapping("/springmvc/v1/members/new-form")
    public ModelAndView process() {
        return new ModelAndView("new-form");
    }

}
