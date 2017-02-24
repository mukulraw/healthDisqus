package commentPOJO;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AttachmentFile {

    @SerializedName("image")
    @Expose
    private String image;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


}
