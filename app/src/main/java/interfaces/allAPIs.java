package interfaces;


import android.support.v4.media.session.PlaybackStateCompat;

import addPostPOJO.addPodtBean;
import addTopicPOJO.addTopicBean;
import bookmarkPOJO.addBean;
import catPOJO.catBean;
import deleteBookPOJO.deleteBookBean;
import editPostPOJO.editPostBean;
import loginPOJO.loginBean;
import mBookmarkPOJO.manageBookmarkBean;
import registerPOJO.registerBean;
import removePOJO.removeBean;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import topicPOJO.topicBean;


public interface allAPIs {

    @Multipart
    @POST("hellthnu_app/category.php")
    Call<catBean> fetchCat(@Part("id") String email);

    @Multipart
    @POST("hellthnu_app/all_topics.php")
    Call<topicPOJO.topicBean> fetchTopics(@Part("catid") String email , @Part("userid") String userId);

    @Multipart
    @POST("hellthnu_app/register.php")
    Call<registerBean> register(@Part("username") String username , @Part("password") String password , @Part("email") String email , @Part("language") String language , @Part("time_zone") String timeZone);

    @Multipart
    @POST("hellthnu_app/login.php")
    Call<loginBean> login(@Part("username") String username , @Part("password") String password);

    @Multipart
    @POST("hellthnu_app/single_topic.php")
    Call<commentPOJO.topicBean> getComments(@Part("catid") String catId , @Part("topic_id") String topicId);

    @Multipart
    @POST("hellthnu_app/add_bookmark.php")
    Call<addBean> addBookmark(@Part("userid") String userId , @Part("topicid") String topic);

    @Multipart
    @POST("hellthnu_app/delete_bookmark.php")
    Call<removeBean> removeBookmark(@Part("userid") String userId , @Part("topicid") String topic);

    @Multipart
    @POST("hellthnu_app/add_topic.php")
    Call<addTopicBean> addTopic(@Part("catid") String catId , @Part("userid") String userId , @Part("subject") String subject , @Part("text") String text , @Part("topic_attchment") String attac , @Part("realfile_name") String name , @Part("physical_filename") String filename , @Part("extension") String ext , @Part("filesize") String size , @Part("mimetype") String mime , @Part("physical_filename") String phy_filename);

    @Multipart
    @POST("hellthnu_app/add_post.php")
    Call<addPodtBean> addPost(@Part("topicid") String topicId , @Part("catid") String catId , @Part("userid") String userId , @Part("subject") String subject , @Part("text") String text , @Part("topic_attchment") String attac , @Part("realfile_name") String name , @Part("physical_filename") String filename , @Part("extension") String ext , @Part("filesize") String size , @Part("mimetype") String mime , @Part("physical_filename") String phy_filename);

    @Multipart
    @POST("hellthnu_app/edit_post.php")
    Call<editPostBean> editPost(@Part("userid") String userId , @Part("subject") String subject , @Part("text") String text , @Part("topic_attchment") String attac , @Part("realfile_name") String name , @Part("extension") String ext , @Part("filesize") String size , @Part("mimetype") String mime , @Part("physical_filename") String phy_filename);

    @Multipart
    @POST("hellthnu_app/manage_bookmark.php")
    Call<manageBookmarkBean> bookmark(@Part("userid") String userId);

    @Multipart
    @POST("hellthnu_app/delete_bookmark.php")
    Call<deleteBookBean> deleteBook(@Part("userid") String userId , @Part("topicid") String topicId);

}
