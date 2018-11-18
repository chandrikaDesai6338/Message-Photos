package com.work.messagesphotos.models;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PhotoModel {

    @SerializedName("pugs")
    @Expose
    private List<String> pugs = null;

    public List<String> getPugs() {
        return pugs;
    }

    public void setPugs(List<String> pugs) {
        this.pugs = pugs;
    }

}