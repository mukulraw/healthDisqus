package commentPOJO;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class topicBean {

    @SerializedName("topic_detail")
    @Expose
    private List<TopicDetail> topicDetail = null;

    public List<TopicDetail> getTopicDetail() {
        return topicDetail;
    }

    public void setTopicDetail(List<TopicDetail> topicDetail) {
        this.topicDetail = topicDetail;
    }

}
