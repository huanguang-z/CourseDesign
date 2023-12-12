package com.pony.model;

import java.io.Serializable;

/**
 *
 *评论类
 */

public class ReviewBean implements Serializable{


    private String reviewUserId;
    private String reviewTime;
    private String reviewContent;
    private String reviewUserName;
    private String reviewMessageId;
    private int reviewId;
    private String userImage;


    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    public String getReviewUserId() {
        return reviewUserId;
    }

    public void setReviewUserId(String reviewUserId) {
        this.reviewUserId = reviewUserId;
    }

    public String getReviewTime() {
        return reviewTime;
    }

    public void setReviewTime(String reviewTime) {
        this.reviewTime = reviewTime;
    }

    public String getReviewContent() {
        return reviewContent;
    }

    public void setReviewContent(String reviewContent) {
        this.reviewContent = reviewContent;
    }

    public String getReviewUserName() {
        return reviewUserName;
    }

    public void setReviewUserName(String reviewUserName) {
        this.reviewUserName = reviewUserName;
    }

    public String getReviewMessageId() {
        return reviewMessageId;
    }

    public void setReviewMessageId(String reviewMessageId) {
        this.reviewMessageId = reviewMessageId;
    }

    public int getReviewId() {
        return reviewId;
    }

    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }
}
