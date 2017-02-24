package topicPOJO;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Topic {

    @SerializedName("topic_id")
    @Expose
    private String topicId;
    @SerializedName("cat_id")
    @Expose
    private String catId;
    @SerializedName("topic_title")
    @Expose
    private String topicTitle;
    @SerializedName("userid")
    @Expose
    private String userid;
    @SerializedName("total_views")
    @Expose
    private String totalViews;
    @SerializedName("user_name")
    @Expose
    private String userName;
    @SerializedName("topic_detail")
    @Expose
    private List<TopicDetail> topicDetail = null;
    @SerializedName("is_bookmark")
    @Expose
    private String isBookmark;

    public String getTopicId() {
        return topicId;
    }

    public void setTopicId(String topicId) {
        this.topicId = topicId;
    }

    public String getCatId() {
        return catId;
    }

    public void setCatId(String catId) {
        this.catId = catId;
    }

    public String getTopicTitle() {
        return topicTitle;
    }

    public void setTopicTitle(String topicTitle) {
        this.topicTitle = topicTitle;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getTotalViews() {
        return totalViews;
    }

    public void setTotalViews(String totalViews) {
        this.totalViews = totalViews;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<TopicDetail> getTopicDetail() {
        return topicDetail;
    }

    public void setTopicDetail(List<TopicDetail> topicDetail) {
        this.topicDetail = topicDetail;
    }

    public String getIsBookmark() {
        return isBookmark;
    }

    public void setIsBookmark(String isBookmark) {
        this.isBookmark = isBookmark;
    }

}
