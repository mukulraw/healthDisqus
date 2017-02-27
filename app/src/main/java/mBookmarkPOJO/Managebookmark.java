package mBookmarkPOJO;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Managebookmark {

    @SerializedName("topic_id")
    @Expose
    private String topicId;
    @SerializedName("topic_title")
    @Expose
    private String topicTitle;
    @SerializedName("userid")
    @Expose
    private String userid;
    @SerializedName("user_name")
    @Expose
    private String userName;

    public String getTopicId() {
        return topicId;
    }

    public void setTopicId(String topicId) {
        this.topicId = topicId;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}
