package taoquangong.cn.couponpal.model;

/**
 * @author Brian
 */
public class Category implements java.io.Serializable {

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String businessId;
    private String iconUrl;
    private String name;
}
