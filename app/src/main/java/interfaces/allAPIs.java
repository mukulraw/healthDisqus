package interfaces;


import android.support.v4.media.session.PlaybackStateCompat;

import bookmarkPOJO.addBean;
import catPOJO.catBean;
import loginPOJO.loginBean;
import registerPOJO.registerBean;
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
    Call<addBean> addBookmark(@Part("userid") String userId , @Part("topic") String topic);

}
