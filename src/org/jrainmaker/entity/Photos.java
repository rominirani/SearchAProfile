package org.jrainmaker.entity;

import com.google.gson.annotations.SerializedName;

public class Photos {

    /** Field description */
    @SerializedName("type")
    private String photoType;

    /** Field description */
    @SerializedName("url")
    private String photoUrl;

    /**
     * Method description
     *
     *
     * @return
     */
    public String getPhotoUrl() {
        return photoUrl;
    }

    /**
     * Method description
     *
     *
     * @param photoUrl
     */
    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public String getPhotoType() {
        return photoType;
    }

    /**
     * Method description
     *
     *
     * @param photoType
     */
    public void setPhotoType(String photoType) {
        this.photoType = photoType;
    }
}
