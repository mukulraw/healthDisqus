package mBookmarkPOJO;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class manageBookmarkBean {

    @SerializedName("managebookmark")
    @Expose
    private List<Managebookmark> managebookmark = null;

    public List<Managebookmark> getManagebookmark() {
        return managebookmark;
    }

    public void setManagebookmark(List<Managebookmark> managebookmark) {
        this.managebookmark = managebookmark;
    }

}