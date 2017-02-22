package catPOJO;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Category {

    @SerializedName("cat_id")
    @Expose
    private String catId;
    @SerializedName("parent_id")
    @Expose
    private String parentId;
    @SerializedName("cat_name")
    @Expose
    private String catName;
    @SerializedName("subcat_count")
    @Expose
    private String subcatCount;

    public String getCatId() {
        return catId;
    }

    public void setCatId(String catId) {
        this.catId = catId;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public String getSubcatCount() {
        return subcatCount;
    }

    public void setSubcatCount(String subcatCount) {
        this.subcatCount = subcatCount;
    }

}
