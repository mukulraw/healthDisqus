package editPostPOJO;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class editPostBean {

    @SerializedName("updatepost")
    @Expose
    private List<Updatepost> updatepost = null;

    public List<Updatepost> getUpdatepost() {
        return updatepost;
    }

    public void setUpdatepost(List<Updatepost> updatepost) {
        this.updatepost = updatepost;
    }

}
