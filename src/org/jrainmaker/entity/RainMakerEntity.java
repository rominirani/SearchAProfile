package org.jrainmaker.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RainMakerEntity {

	/** Field description */
	@SerializedName("contactInfo")
	private ContactInfo contactInfo;
	
	/** Field description */
	@SerializedName("demographics")
	private Demographics demographics;

	/** Field description */
	@SerializedName("organizations")
	private List<Organizations> organizations;

	/** Field description */
	@SerializedName("photos")
	private List<Photos> photos;

	/** Field description */
	@SerializedName("socialProfiles")
	private List<SocialProfiles> socialProfiles;

	/** Field description */
	@SerializedName("status")
	private int statusCode;

	/**
	 * Method description
	 * 
	 * 
	 * @return
	 */
	public int getStatusCode() {
		return statusCode;
	}

	/**
	 * Method description
	 * 
	 * 
	 * @param statusCode
	 */
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	/**
	 * Method description
	 * 
	 * 
	 * @return
	 */
	public ContactInfo getContactInfo() {
		return contactInfo;
	}

	/**
	 * Method description
	 * 
	 * 
	 * @param contactInfo
	 */
	public void setContactInfo(ContactInfo contactInfo) {
		this.contactInfo = contactInfo;
	}

	/*
	 * public List<Interests> getInterests() { return interests; }
	 * 
	 * public void setInterests(List<Interests> interests) { this.interests =
	 * interests; }
	 */

	/**
	 * Method description
	 * 
	 * 
	 * @return
	 */
	public List<Organizations> getOrganizations() {
		return organizations;
	}

	/**
	 * Method description
	 * 
	 * 
	 * @param organizations
	 */
	public void setOrganizations(List<Organizations> organizations) {
		this.organizations = organizations;
	}

	/**
	 * Method description
	 * 
	 * 
	 * @return
	 */
	public List<SocialProfiles> getSocialProfiles() {
		return socialProfiles;
	}

	/**
	 * Method description
	 * 
	 * 
	 * @param socialProfiles
	 */
	public void setSocialProfiles(List<SocialProfiles> socialProfiles) {
		this.socialProfiles = socialProfiles;
	}

	/**
	 * Method description
	 * 
	 * 
	 * @return
	 */
	public List<Photos> getPhotos() {
		return photos;
	}

	/**
	 * Method description
	 * 
	 * 
	 * @param photos
	 */
	public void setPhotos(List<Photos> photos) {
		this.photos = photos;
	}

	/**
	 * @return the demographics
	 */
	public Demographics getDemographics() {
		return demographics;
	}

	/**
	 * @param demographics the demographics to set
	 */
	public void setDemographics(Demographics demographics) {
		this.demographics = demographics;
	}
	
	
}
