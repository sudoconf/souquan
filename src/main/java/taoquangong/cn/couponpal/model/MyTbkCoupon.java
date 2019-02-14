package taoquangong.cn.couponpal.model;

import com.taobao.api.response.TbkDgItemCouponGetResponse;
import com.taobao.api.response.TbkDgMaterialOptionalResponse;

import java.text.NumberFormat;

import java.util.List;

/**
 * @author Brian
 */
public class MyTbkCoupon implements java.io.Serializable {

    private static NumberFormat nbf = NumberFormat.getInstance();

    private static Long calculateCouponValue(String str) {
        //数据转换
        String couponInfo = str.trim();
        int jian_index = couponInfo.indexOf("减");
        String couponInfoTemp = couponInfo.substring(jian_index + 1);
        int yuan_index = couponInfoTemp.indexOf("元");
        String quan = couponInfoTemp.substring(0, yuan_index);
        return Long.parseLong(quan);
    }

    /**
     * shop infos
     */
    private Long sellerId;
    private String shopTitle;
    private Long userType;
    private String shopLogo;

    private List smallImages;
    private String zkFinalPrice;
    private String title;
    private Long volume;
    /**
     * 主图
     */
    private String pictUrl;
    private String itemUrl;
    private Long couponTotalCount;
    private String commissionRate;
    private String couponInfo;
    private Long category;
    private Long numIid;
    private Long couponRemainCount;
    private String couponStartTime;
    private String couponEndTime;
    private String couponClickUrl;
    //private String itemDescription;

    /**
     * 返现金额
     */
    private String rebate;
    /**
     * 优惠券额度
     */
    private Long couponValue;
    /**
     * 券后价格
     */
    private String priceAfterCoupon;

    /**
     * 淘口令
     */
    private String taokouling;

    public MyTbkCoupon() {

    }

    public MyTbkCoupon(TbkDgItemCouponGetResponse.TbkCoupon origin) {
        this.smallImages = origin.getSmallImages();
        this.category = origin.getCategory();
        this.commissionRate = origin.getCommissionRate();
        this.couponClickUrl = origin.getCouponClickUrl();
        this.couponEndTime = origin.getCouponEndTime();
        this.couponInfo = origin.getCouponInfo();
        this.couponRemainCount = origin.getCouponRemainCount();
        this.couponStartTime = origin.getCouponStartTime();
        this.couponTotalCount = origin.getCouponTotalCount();
        this.itemUrl = origin.getItemUrl();
        this.numIid = origin.getNumIid();
        this.pictUrl = origin.getPictUrl();
        this.sellerId = origin.getSellerId();
        this.title = origin.getTitle();
        this.shopTitle = origin.getShopTitle();
        this.userType = origin.getUserType();
        this.volume = origin.getVolume();
        this.zkFinalPrice = origin.getZkFinalPrice();

        this.couponValue = calculateCouponValue(origin.getCouponInfo());
        Double priceAfterCouponNum = Double.parseDouble(getZkFinalPrice()) - couponValue;
        Double rebateNum = priceAfterCouponNum
                * Double.parseDouble(origin.getCommissionRate())
                / 100
                / 2;

        nbf.setMaximumFractionDigits(2);
        this.priceAfterCoupon = nbf.format(priceAfterCouponNum);
        this.rebate = nbf.format(rebateNum);
    }

    public MyTbkCoupon(TbkDgMaterialOptionalResponse.MapData data) {
        this.smallImages = data.getSmallImages();
        this.category = data.getCategoryId();
        this.commissionRate = data.getCommissionRate();
        this.couponClickUrl = "https:" + data.getCouponShareUrl();
        this.couponEndTime = data.getCouponEndTime();
        this.couponInfo = data.getCouponInfo();
        this.couponRemainCount = data.getCouponRemainCount();
        this.couponStartTime = data.getCouponStartTime();
        this.couponTotalCount = data.getCouponTotalCount();
        this.itemUrl = data.getItemUrl();
        this.numIid = data.getNumIid();
        this.pictUrl = data.getPictUrl();
        this.sellerId = data.getSellerId();
        this.title = data.getTitle();
        this.shopTitle = data.getShopTitle();
        this.userType = data.getUserType();
        this.volume = data.getVolume();
        this.zkFinalPrice = data.getZkFinalPrice();

        this.couponValue = calculateCouponValue(data.getCouponInfo());
        Double priceAfterCouponNum = Double.parseDouble(getZkFinalPrice()) - couponValue;
        Double rebateNum = priceAfterCouponNum
                * Double.parseDouble(data.getCommissionRate())
                / 10000
                / 2;

        nbf.setMaximumFractionDigits(2);
        this.priceAfterCoupon = nbf.format(priceAfterCouponNum);
        this.rebate = nbf.format(rebateNum);
    }

    public String getShopLogo() {
        return shopLogo;
    }

    public void setShopLogo(String shopLogo) {
        this.shopLogo = shopLogo;
    }

    public String getTaokouling() {
        return taokouling;
    }

    public void setTaokouling(String taokouling) {
        this.taokouling = taokouling;
    }

    public List getSmallImages() {
        return smallImages;
    }

    public void setSmallImages(List smallImages) {
        this.smallImages = smallImages;
    }

    public String getShopTitle() {
        return shopTitle;
    }

    public void setShopTitle(String shopTitle) {
        this.shopTitle = shopTitle;
    }

    public Long getUserType() {
        return userType;
    }

    public void setUserType(Long userType) {
        this.userType = userType;
    }

    public String getZkFinalPrice() {
        return zkFinalPrice;
    }

    public void setZkFinalPrice(String zkFinalPrice) {
        this.zkFinalPrice = zkFinalPrice;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

    public Long getVolume() {
        return volume;
    }

    public void setVolume(Long volume) {
        this.volume = volume;
    }

    public String getPictUrl() {
        return pictUrl;
    }

    public void setPictUrl(String pictUrl) {
        this.pictUrl = pictUrl;
    }

    public String getItemUrl() {
        return itemUrl;
    }

    public void setItemUrl(String itemUrl) {
        this.itemUrl = itemUrl;
    }

    public Long getCouponTotalCount() {
        return couponTotalCount;
    }

    public void setCouponTotalCount(Long couponTotalCount) {
        this.couponTotalCount = couponTotalCount;
    }

    public String getCommissionRate() {
        return commissionRate;
    }

    public void setCommissionRate(String commissionRate) {
        this.commissionRate = commissionRate;
    }

    public String getCouponInfo() {
        return couponInfo;
    }

    public void setCouponInfo(String couponInfo) {
        this.couponInfo = couponInfo;
    }

    public Long getCategory() {
        return category;
    }

    public void setCategory(Long category) {
        this.category = category;
    }

    public Long getNumIid() {
        return numIid;
    }

    public void setNumIid(Long numIid) {
        this.numIid = numIid;
    }

    public Long getCouponRemainCount() {
        return couponRemainCount;
    }

    public void setCouponRemainCount(Long couponRemainCount) {
        this.couponRemainCount = couponRemainCount;
    }

    public String getCouponStartTime() {
        return couponStartTime;
    }

    public void setCouponStartTime(String couponStartTime) {
        this.couponStartTime = couponStartTime;
    }

    public String getCouponEndTime() {
        return couponEndTime;
    }

    public void setCouponEndTime(String couponEndTime) {
        this.couponEndTime = couponEndTime;
    }

    public String getCouponClickUrl() {
        return couponClickUrl;
    }

    public void setCouponClickUrl(String couponClickUrl) {
        this.couponClickUrl = couponClickUrl;
    }


    public String getRebate() {
        return rebate;
    }

    public void setRebate(String rebate) {
        this.rebate = rebate;
    }

    public Long getCouponValue() {
        return couponValue;
    }

    public void setCouponValue(Long couponValue) {
        this.couponValue = couponValue;
    }

    public String getPriceAfterCoupon() {
        return priceAfterCoupon;
    }

    public void setPriceAfterCoupon(String priceAfterCoupon) {
        this.priceAfterCoupon = priceAfterCoupon;
    }
}
