package com.example.tvs.healthdisqus;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class imageBean {

    @SerializedName("profile_pic")
    @Expose
    private String profilePic;

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

}
