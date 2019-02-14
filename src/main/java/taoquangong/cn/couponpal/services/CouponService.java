package taoquangong.cn.couponpal.services;

import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.TbkDgItemCouponGetRequest;
import com.taobao.api.response.TbkDgItemCouponGetResponse;
import org.springframework.stereotype.Service;
import taoquangong.cn.couponpal.config.Constants;
import taoquangong.cn.couponpal.model.MyTbkCoupon;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Brian
 */
@Service
public class CouponService {

    private final RedisService redisService;

    private final TaobaoClient client = new DefaultTaobaoClient(Constants.URL, Constants.APP_KEY, Constants.APP_SECRET);

    public CouponService(RedisService redisService) {
        this.redisService = redisService;
    }

    public List<MyTbkCoupon> getCoupon(Long pageNo) throws ApiException {
        TbkDgItemCouponGetRequest req = new TbkDgItemCouponGetRequest();
        req.setAdzoneId(Constants.ADZONE_ID);
        req.setPageSize(20L);
        req.setPageNo(pageNo);
        TbkDgItemCouponGetResponse rsp = client.execute(req);
        List<TbkDgItemCouponGetResponse.TbkCoupon> couponList = rsp.getResults();
        List<MyTbkCoupon> coupons = new ArrayList<>();
        for(TbkDgItemCouponGetResponse.TbkCoupon coupon : couponList) {
            MyTbkCoupon temp = new MyTbkCoupon(coupon);
            coupons.add(temp);
            redisService.setObj(temp.getNumIid().toString(), temp, 24L);
        }
        return coupons;
    }

    public List<MyTbkCoupon> getCoupon(String q, Long pageNo) throws ApiException {
        TbkDgItemCouponGetRequest req = new TbkDgItemCouponGetRequest();
        req.setAdzoneId(Constants.ADZONE_ID);
        req.setPageSize(20L);
        req.setQ(q);
        req.setPageNo(pageNo);
        TbkDgItemCouponGetResponse rsp = client.execute(req);
        List<TbkDgItemCouponGetResponse.TbkCoupon> couponList = rsp.getResults();
        List<MyTbkCoupon> coupons = new ArrayList<>();
        for(TbkDgItemCouponGetResponse.TbkCoupon coupon : couponList) {
            MyTbkCoupon temp = new MyTbkCoupon(coupon);
            coupons.add(temp);
            redisService.setObj(temp.getNumIid().toString(), temp, 24L);
        }
        return coupons;
    }
}
