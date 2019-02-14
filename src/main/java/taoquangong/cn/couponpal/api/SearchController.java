package taoquangong.cn.couponpal.api;


import com.alibaba.druid.support.json.JSONParser;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.TbkDgMaterialOptionalRequest;
import com.taobao.api.request.WirelessShareTpwdQueryRequest;
import com.taobao.api.response.TbkDgMaterialOptionalResponse;
import com.taobao.api.response.WirelessShareTpwdQueryResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import taoquangong.cn.couponpal.config.Constants;
import taoquangong.cn.couponpal.model.MyTbkCoupon;
import taoquangong.cn.couponpal.services.SearchService;
import taoquangong.cn.couponpal.utils.QueryStringHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @author Brian
 */
@RestController
@RequestMapping(value = {"/api/search"})
public class SearchController {

    private final SearchService searchService;

    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @RequestMapping
    public List<MyTbkCoupon> search(@RequestBody String json) throws ApiException {
        JSONParser jsonParser = new JSONParser(json);
        Map data = jsonParser.parseMap();
        String q = (String) data.get("q");
        String query = QueryStringHandler.preHandle(q);
        System.out.println("查询词 ：" + query);
        return searchService.search(query, 1L);
    }

    @RequestMapping(value = {"/more/{pageNo}"})
    public List<MyTbkCoupon> loadMore(@RequestBody String json, @PathVariable Long pageNo) throws ApiException {
        JSONParser jsonParser = new JSONParser(json);
        Map data = jsonParser.parseMap();
        String q = (String) data.get("q");
        String query = QueryStringHandler.preHandle(q);
        System.out.println("查询词 ：" + query);
        System.out.println("pageNo:" + pageNo);
        return searchService.search(query, pageNo);
    }
}
