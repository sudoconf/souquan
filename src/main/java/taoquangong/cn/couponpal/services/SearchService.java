package taoquangong.cn.couponpal.services;

import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.TbkDgMaterialOptionalRequest;
import com.taobao.api.response.TbkDgMaterialOptionalResponse;
import org.springframework.stereotype.Service;
import taoquangong.cn.couponpal.config.Constants;
import taoquangong.cn.couponpal.model.MyTbkCoupon;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Brian
 */
@Service
public class SearchService {

    private final RedisService redisService;
    private final TaobaoClient client = new DefaultTaobaoClient(Constants.URL, Constants.APP_KEY, Constants.APP_SECRET);

    public SearchService(RedisService redisService) {
        this.redisService = redisService;
    }
    public List<MyTbkCoupon> search(String query, Long pageNo) throws ApiException {
        TbkDgMaterialOptionalRequest req = new TbkDgMaterialOptionalRequest();
        req.setAdzoneId(Constants.ADZONE_ID);
        req.setQ(query);
        req.setPageNo(pageNo);
        req.setHasCoupon(true);
        req.setSort("total_sales_des");
        TbkDgMaterialOptionalResponse rsp = client.execute(req);
        List<MyTbkCoupon> coupons = new ArrayList<>();
        for (TbkDgMaterialOptionalResponse.MapData data : rsp.getResultList()) {
            MyTbkCoupon temp = new MyTbkCoupon(data);
            coupons.add(temp);
            redisService.setObj(temp.getNumIid().toString(), temp, 24L);
        }
        return coupons;
    }
}
