package taoquangong.cn.couponpal.utils;

import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.WirelessShareTpwdQueryRequest;
import com.taobao.api.response.WirelessShareTpwdQueryResponse;
import taoquangong.cn.couponpal.config.Constants;

/**
 * @author Brian
 */
public class QueryStringHandler {

    public static String preHandle(String input) throws ApiException {
        //淘口令输入
        String reg = ".*￥.*￥.*";
        String defaultQuery = "any";
        String output;
        if ("".equals(input) || defaultQuery.equals(input)) {
            output = "#";
        } else if (input.matches(reg)) {
            System.out.println("输入的是淘口令……");
            TaobaoClient client = new DefaultTaobaoClient(Constants.URL, Constants.APP_KEY, Constants.APP_SECRET);
            WirelessShareTpwdQueryRequest shareTpwdQueryRequest = new WirelessShareTpwdQueryRequest();
            shareTpwdQueryRequest.setPasswordContent(input);
            WirelessShareTpwdQueryResponse shareTpwdQueryResponse = client.execute(shareTpwdQueryRequest);
            output = shareTpwdQueryResponse.getContent();
        } else {
            output = input;
        }
        return output;
    }
}
