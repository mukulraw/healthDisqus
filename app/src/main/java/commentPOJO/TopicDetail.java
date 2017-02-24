package commentPOJO;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TopicDetail {

    @SerializedName("topic_id")
    @Expose
    private String topicId;
    @SerializedName("post_id")
    @Expose
    private String postId;
    @SerializedName("cat_id")
    @Expose
    private String catId;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("subject")
    @Expose
    private String subject;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("post_attachment")
    @Expose
    private String postAttachment;
    @SerializedName("post_time")
    @Expose
    private String postTime;
    @SerializedName("attachment_file")
    @Expose
    private List<AttachmentFile> attachmentFile = null;
    @SerializedName("user_detail")
    @Expose
    private List<UserDetail> userDetail = null;

    public String getTopicId() {
        return topicId;
    }

    public void setTopicId(String topicId) {
        this.topicId = topicId;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getCatId() {
        return catId;
    }

    public void setCatId(String catId) {
        this.catId = catId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPostAttachment() {
        return postAttachment;
    }

    public void setPostAttachment(String postAttachment) {
        this.postAttachment = postAttachment;
    }

    public String getPostTime() {
        return postTime;
    }

    public void setPostTime(String postTime) {
        this.postTime = postTime;
    }

    public List<AttachmentFile> getAttachmentFile() {
        return attachmentFile;
    }

    public void setAttachmentFile(List<AttachmentFile> attachmentFile) {
        this.attachmentFile = attachmentFile;
    }

    public List<UserDetail> getUserDetail() {
        return userDetail;
    }

    public void setUserDetail(List<UserDetail> userDetail) {
        this.userDetail = userDetail;
    }

}
