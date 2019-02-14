package taoquangong.cn.couponpal.api;

import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.TbkShopGetRequest;
import com.taobao.api.request.TbkTpwdCreateRequest;
import com.taobao.api.response.TbkShopGetResponse;
import com.taobao.api.response.TbkTpwdCreateResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import taoquangong.cn.couponpal.config.Constants;
import taoquangong.cn.couponpal.model.MyTbkCoupon;
import taoquangong.cn.couponpal.services.RedisService;

/**
 * @author Brian
 */
@RestController
@RequestMapping(value = {"api/"})
public class DetailController {

    private final RedisService redisService;

    public DetailController(RedisService redisService) {
        this.redisService = redisService;
    }

    @GetMapping("/detail/{id}")
    public MyTbkCoupon detail(@PathVariable String id) throws ApiException {
        TaobaoClient client = new DefaultTaobaoClient(Constants.URL, Constants.APP_KEY, Constants.APP_SECRET);
        TbkTpwdCreateRequest req = new TbkTpwdCreateRequest();

        MyTbkCoupon coupon = (MyTbkCoupon)redisService.getObj(id);
        //debug
        System.out.println(coupon.getTitle());

        String text = coupon.getTitle();
        String url = coupon.getCouponClickUrl();
        String logo = coupon.getPictUrl();

        req.setText(text);
        req.setUrl(url);
        req.setLogo(logo);

        TbkTpwdCreateResponse rsp = client.execute(req);
        String taokouling = rsp.getData().getModel();
        //debug
        System.out.println(taokouling);

        //get shop logo
        TbkShopGetRequest request = new TbkShopGetRequest();
        request.setFields("user_id, pict_url");
        request.setQ(coupon.getShopTitle());
        request.setPageSize(1L);
        TbkShopGetResponse response = client.execute(request);
        String shopLogo = response.getResults().get(0).getPictUrl();
        Long shopId = response.getResults().get(0).getUserId();

        coupon.setTaokouling(taokouling);
        if (shopId.equals(coupon.getSellerId())) {
            coupon.setShopLogo(shopLogo);
        }

        return coupon;
    }
}
