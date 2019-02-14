package taoquangong.cn.couponpal.api;

import com.taobao.api.ApiException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import taoquangong.cn.couponpal.model.MyTbkCoupon;
import taoquangong.cn.couponpal.services.CouponService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Brian
 */
@RestController
@RequestMapping(value = { "/api" })
public class IndexController {
    private static final Map<String, String> CATEGORIES;
    static {
        CATEGORIES = new HashMap<>();
        CATEGORIES.put("女装", "../images/category/nvzhuang.png");
        CATEGORIES.put("家居家装", "../images/category/jiazhuang.png");
        CATEGORIES.put("母婴", "../images/category/muying.png");
        CATEGORIES.put("数码家电", "../images/category/jiadian-1.png");
        CATEGORIES.put("食品", "../images/category/shengxian.png");
        CATEGORIES.put("鞋包配饰", "../images/category/xiangbao.png");
        CATEGORIES.put("美妆个护", "../images/category/gehu.png");
        CATEGORIES.put("男装", "../images/category/nanzhuang.png");
        CATEGORIES.put("内衣", "../images/category/neiyi.png");
        CATEGORIES.put("运动户外", "../images/category/huwaiyundong.png");
    }

    private final CouponService couponService;

    public IndexController(CouponService couponService) {
        this.couponService = couponService;
    }

    @RequestMapping(value = "/")
    public Map<String, Object> index() throws ApiException {
        System.out.println("coming");
        Map<String, Object> data = new HashMap<>();
        data.put("category", CATEGORIES);

        List<MyTbkCoupon> coupons = couponService.getCoupon(1L);
        data.put("coupons", coupons);
        return data;
    }

    @RequestMapping(value = "/more/{pageNo}")
    public List<MyTbkCoupon> addMore(@PathVariable Long pageNo) throws ApiException {
        System.out.println("addMore...");
        System.out.println("pageNo:" + pageNo);
        return couponService.getCoupon(pageNo);
    }
}
