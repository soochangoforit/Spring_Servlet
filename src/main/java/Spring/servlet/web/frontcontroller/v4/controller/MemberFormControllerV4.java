package Spring.servlet.web.frontcontroller.v4.controller;

import Spring.servlet.web.frontcontroller.v4.ControllerV4;

import java.util.Map;

public class MemberFormControllerV4 implements ControllerV4 {

    // frontController에서 model를 만들어서 넘겨줄거다.
    // 반환값으로 논리 페이지 이름만 반환하면 된다.
    @Override
    public String process(Map<String, String> paramMap, Map<String, Object> model) {
        return "new-form";
    }
}
