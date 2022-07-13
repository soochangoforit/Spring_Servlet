package Spring.servlet.web.frontcontroller.v3;

import Spring.servlet.web.frontcontroller.ModelView;

import java.util.Map;

public interface ControllerV3 {

    // 각각의 구현 controller들은 frontController에서 model처럼 받은 데이터를 처리해주기만
    // 하면 되기때문에 따로, Servlet 기술을 사용하지 않는다.
    ModelView process(Map<String, String> paramMap);
}
