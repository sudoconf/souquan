package taoquangong.cn.couponpal.api;

import com.taobao.api.ApiException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import taoquangong.cn.couponpal.model.MyTbkCoupon;
import taoquangong.cn.couponpal.services.CouponService;

import java.util.List;

/**
 * @author Brian
 */
@RestController
@RequestMapping(value = {"/api/category"})
public class CategoryController {

    private final CouponService couponService;
    public CategoryController(CouponService couponService) {
        this.couponService = couponService;
    }

    @RequestMapping("/{label}")
    public List<MyTbkCoupon> index(@PathVariable String label) throws ApiException {
        String q = label;
        int len = 2;
        if (label.length() > len) {
            String q1 = label.substring(0, 2);
            String q2 = label.substring(2);
            q = q1 + " " + q2;
        }

        //debug
        System.out.println(q);
        return couponService.getCoupon(q, 1L);
    }

    @RequestMapping("/{label}/{pageNo}")
    public List<MyTbkCoupon> loadMore(@PathVariable String label, @PathVariable Long pageNo) throws ApiException {
        String q = label;
        int len = 2;
        if (label.length() > len) {
            String q1 = label.substring(0, 2);
            String q2 = label.substring(2);
            q = q1 + " " + q2;
        }
        return couponService.getCoupon(q, pageNo);
    }
}
