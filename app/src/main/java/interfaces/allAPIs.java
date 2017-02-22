package interfaces;


import catPOJO.catBean;
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
    Call<topicBean> fetchTopics(@Part("catid") String email);

}
