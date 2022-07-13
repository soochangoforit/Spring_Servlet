package Spring.servlet.web.frontcontroller.v3.controller;

import Spring.servlet.web.frontcontroller.ModelView;
import Spring.servlet.web.frontcontroller.v3.ControllerV3;

import java.util.Map;

public class MemberFormControllerV3 implements ControllerV3 {

    @Override
    public ModelView process(Map<String, String> paramMap) {
        // jsp에 대한 경로를 지정하지 않고 논리적인 이름의 view만 처리하도록 하자.
        return new ModelView("new-form");
    }

}
