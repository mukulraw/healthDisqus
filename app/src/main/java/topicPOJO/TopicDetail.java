package topicPOJO;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TopicDetail {

    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("total_reply")
    @Expose
    private String totalReply;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTotalReply() {
        return totalReply;
    }

    public void setTotalReply(String totalReply) {
        this.totalReply = totalReply;
    }

}
