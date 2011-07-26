package org.jrainmaker.entity;

import com.google.gson.annotations.SerializedName;

public class Organizations {

    /** Field description */
    @SerializedName("name")
    private String organizationName;

    /** Field description */
    @SerializedName("startDate")
    private String organizationStartDate;

    /** Field description */
    @SerializedName("title")
    private String organizationTitle;

    /** Field description */
    @SerializedName("isPrimary")
    private boolean primary;

    /**
     * Method description
     *
     *
     * @return
     */
    public String getOrganizationName() {
        return organizationName;
    }

    /**
     * Method description
     *
     *
     * @param organizationName
     */
    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public String getOrganizationTitle() {
        return organizationTitle;
    }

    /**
     * Method description
     *
     *
     * @param organizationTitle
     */
    public void setOrganizationTitle(String organizationTitle) {
        this.organizationTitle = organizationTitle;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public String getOrganizationStartDate() {
        return organizationStartDate;
    }

    /**
     * Method description
     *
     *
     * @param organizationStartDate
     */
    public void setOrganizationStartDate(String organizationStartDate) {
        this.organizationStartDate = organizationStartDate;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public boolean isPrimary() {
        return primary;
    }

    /**
     * Method description
     *
     *
     * @param primary
     */
    public void setPrimary(boolean primary) {
        this.primary = primary;
    }
}
