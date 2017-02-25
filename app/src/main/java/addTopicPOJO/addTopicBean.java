package addTopicPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class addTopicBean {

    @SerializedName("addtopic")
    @Expose
    private List<Addtopic> addtopic = null;

    public List<Addtopic> getAddtopic() {
        return addtopic;
    }

    public void setAddtopic(List<Addtopic> addtopic) {
        this.addtopic = addtopic;
    }

}
