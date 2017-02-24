package bookmarkPOJO;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class addBean {

    @SerializedName("addbookmark")
    @Expose
    private List<Addbookmark> addbookmark = null;

    public List<Addbookmark> getAddbookmark() {
        return addbookmark;
    }

    public void setAddbookmark(List<Addbookmark> addbookmark) {
        this.addbookmark = addbookmark;
    }

}
