package removePOJO;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class removeBean {

    @SerializedName("deletebookmark")
    @Expose
    private List<Deletebookmark> deletebookmark = null;

    public List<Deletebookmark> getDeletebookmark() {
        return deletebookmark;
    }

    public void setDeletebookmark(List<Deletebookmark> deletebookmark) {
        this.deletebookmark = deletebookmark;
    }

}
