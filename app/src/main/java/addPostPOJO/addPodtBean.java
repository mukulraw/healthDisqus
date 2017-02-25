package addPostPOJO;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class addPodtBean {

    @SerializedName("addpost")
    @Expose
    private List<Addpost> addpost = null;

    public List<Addpost> getAddpost() {
        return addpost;
    }

    public void setAddpost(List<Addpost> addpost) {
        this.addpost = addpost;
    }

}
