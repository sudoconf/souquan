package taoquangong.cn.couponpal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Brian
 */
//@Controller
public class IndexController {

    @RequestMapping(value = {"/**/*"})
    public String index() {
        return "index";
    }
}
