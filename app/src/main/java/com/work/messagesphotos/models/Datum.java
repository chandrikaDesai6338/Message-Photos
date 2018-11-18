package com.work.messagesphotos.models;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("social_type")
    @Expose
    private String socialType;
    @SerializedName("tweetId")
    @Expose
    private String tweetId;
    @SerializedName("screenName")
    @Expose
    private String screenName;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("tweet")
    @Expose
    private String tweet;
    @SerializedName("Original_Post_DateTime")
    @Expose
    private String originalPostDateTime;
    @SerializedName("Post_Keywords")
    @Expose
    private String postKeywords;
    @SerializedName("Post_Status")
    @Expose
    private String postStatus;
    @SerializedName("User_Photo_Url")
    @Expose
    private String userPhotoUrl;
    @SerializedName("User_Profile_Url")
    @Expose
    private String userProfileUrl;
    @SerializedName("Original_Post_Media_URL")
    @Expose
    private String originalPostMediaURL;
    @SerializedName("Location_Name")
    @Expose
    private String locationName;
    @SerializedName("isVisible")
    @Expose
    private Object isVisible;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSocialType() {
        return socialType;
    }

    public void setSocialType(String socialType) {
        this.socialType = socialType;
    }

    public String getTweetId() {
        return tweetId;
    }

    public void setTweetId(String tweetId) {
        this.tweetId = tweetId;
    }

    public String getScreenName() {
        return screenName;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTweet() {
        return tweet;
    }

    public void setTweet(String tweet) {
        this.tweet = tweet;
    }

    public String getOriginalPostDateTime() {
        return originalPostDateTime;
    }

    public void setOriginalPostDateTime(String originalPostDateTime) {
        this.originalPostDateTime = originalPostDateTime;
    }

    public String getPostKeywords() {
        return postKeywords;
    }

    public void setPostKeywords(String postKeywords) {
        this.postKeywords = postKeywords;
    }

    public String getPostStatus() {
        return postStatus;
    }

    public void setPostStatus(String postStatus) {
        this.postStatus = postStatus;
    }

    public String getUserPhotoUrl() {
        return userPhotoUrl;
    }

    public void setUserPhotoUrl(String userPhotoUrl) {
        this.userPhotoUrl = userPhotoUrl;
    }

    public String getUserProfileUrl() {
        return userProfileUrl;
    }

    public void setUserProfileUrl(String userProfileUrl) {
        this.userProfileUrl = userProfileUrl;
    }

    public String getOriginalPostMediaURL() {
        return originalPostMediaURL;
    }

    public void setOriginalPostMediaURL(String originalPostMediaURL) {
        this.originalPostMediaURL = originalPostMediaURL;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public Object getIsVisible() {
        return isVisible;
    }

    public void setIsVisible(Object isVisible) {
        this.isVisible = isVisible;
    }

}