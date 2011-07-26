package com.thirdchimpanzee.rainmaker;

import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.*;

import org.jrainmaker.RainMaker;
import org.jrainmaker.config.StatusCode;
import org.jrainmaker.entity.Chats;
import org.jrainmaker.entity.ContactInfo;
import org.jrainmaker.entity.Demographics;
import org.jrainmaker.entity.Organizations;
import org.jrainmaker.entity.RainMakerEntity;
import org.jrainmaker.entity.SocialProfiles;

import com.google.appengine.api.xmpp.JID;
import com.google.appengine.api.xmpp.Message;
import com.google.appengine.api.xmpp.MessageBuilder;
import com.google.appengine.api.xmpp.SendResponse;
import com.google.appengine.api.xmpp.XMPPService;
import com.google.appengine.api.xmpp.XMPPServiceFactory;
import com.thirdchimpanzee.rainmaker.utils.AppUtils;

@SuppressWarnings("serial")
public class RainmakerProfileServlet extends HttpServlet {
	public static final String API_KEY = "7cd7d138a03115f4";
	public static String APICall = "http://api.rainmaker.cc/v1/person.json?apiKey="+API_KEY+"&timeoutSeconds=30&email=";
	public static final Logger _log = Logger.getLogger(RainmakerProfileServlet.class.getName());
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		doGet(req,resp);
	}
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String strCallResult="";
		String strStatus="";
		resp.setContentType("text/plain");
		XMPPService xmpp = null;
		JID fromJid = null;
		try {
	
			//STEP 1 - Extract out the message and the Jabber Id of the user sending us the message via the Google Talk client
			xmpp = XMPPServiceFactory.getXMPPService();
			Message msg = xmpp.parseMessage(req);

			fromJid = msg.getFromJid();
			String body = msg.getBody();
			
			_log.info("Received a message from " + fromJid.getId() + " and body = " + body);
			String userId = fromJid.getId();
			//String emailId = fromJid.getId().substring(0,fromJid.getId().indexOf("/"));
			String emailId = fromJid.getId();
			
			//String strWord = req.getParameter("command");
			String strCommand = body;
			
			//Do validations here. Only basic ones i.e. cannot be null/empty
			if (strCommand == null) throw new Exception("You must give a command.");
			
			//Trim the stuff
			strCommand = strCommand.trim();
			if (strCommand.length() == 0) throw new Exception("You must give a command.");
			
			String[] words = strCommand.split(" ");
			if (words.length >= 2) {
				try {
					if (words.length == 2) {
						String command = words[0];
						String command_data = words[1];
						//Parse the stuff over here
						if (command.equalsIgnoreCase("search")) {
							RainMaker rm = new RainMaker(API_KEY);
							RainMakerEntity entity = rm.getPersonInformation(command_data,30);
							if (entity.getStatusCode() == StatusCode.REQ_SUCCESS) {
								//Get Contact Information
								StringBuffer strContactInfoResult = new StringBuffer();
								ContactInfo CI = entity.getContactInfo();
								strContactInfoResult.append("*Full Name:* " + CI.getFullName() + "\r\n");
								//Get Chat Handles
								List<Chats> _chats = CI.getChats();
								if (_chats != null) { 
									if (_chats.size() > 0) {
									strContactInfoResult.append("*Chat Handles:* ");
									int j = 1;
									for (Chats chats : _chats) {
										strContactInfoResult.append(chats.getChatClient() + "[" + chats.getChatHandle() + "]");
										if (j!= _chats.size()) { 
											strContactInfoResult.append(","); 
										}
										else {
											strContactInfoResult.append("." + "\r\n");
										}
										j++;
									}
									
								}
								}
								strCallResult+= strContactInfoResult;
								//Get Demographics
								StringBuffer strDemographicsResult = new StringBuffer();
								Demographics _dg = entity.getDemographics();
								if (_dg != null) {
									if ((_dg.getLocationGeneral() != null) || (_dg.getGender() != null) || (_dg.getAge() != null)) {
										strDemographicsResult.append("*Demographical Data:* ");
										if (_dg.getGender() != null) { strDemographicsResult.append(_dg.getGender());}
										if (_dg.getAge() != null) { strDemographicsResult.append(" Age: " + _dg.getAge());}
										if (_dg.getLocationGeneral() != null) { strDemographicsResult.append(" resides in " + _dg.getLocationGeneral());}
										strDemographicsResult.append("\r\n");
										strCallResult+= strDemographicsResult;
									}
								}
								
								//Get Organizations
								StringBuffer strOrganizationResult = new StringBuffer();
								
								List<Organizations> _orgs = entity.getOrganizations();
								if (_orgs != null) {
									if (_orgs.size() > 0) strOrganizationResult.append("*Employment History:* ");
									int i = 1;
									for (Organizations organizations : _orgs) {
										if (organizations.getOrganizationTitle() != null) {
											if (organizations.getOrganizationName() != null) {
												strOrganizationResult.append(organizations.getOrganizationName() + "[" + organizations.getOrganizationTitle()+"]");
												if (i != _orgs.size()) { 
													strOrganizationResult.append(","); 
												}
												else {
													strOrganizationResult.append("." + "\r\n");
												}
											}
										}
										else {
											if (organizations.getOrganizationName() != null) {
												strOrganizationResult.append(organizations.getOrganizationName());
												if (i != _orgs.size()) { 
													strOrganizationResult.append(","); 
												}
												else {
													strOrganizationResult.append("." + "\r\n");
												}
											}
										}
										
										i++;
									}
									strCallResult+= strOrganizationResult;
								}
								//Get Social Media footprint
								StringBuffer strSocialMediaResult = new StringBuffer();
								List<SocialProfiles> _SocialProfiles = entity.getSocialProfiles();
								if (_SocialProfiles != null) {
									if (_SocialProfiles.size() > 0) {
										for (SocialProfiles socialProfiles : _SocialProfiles) {
											if (socialProfiles != null) {
												if (socialProfiles.getProfileType() != null) {
													if (socialProfiles.getProfileType().equals("twitter")) {
														strSocialMediaResult.append("\r\n"+"*Twitter:* " + socialProfiles.getProfileUrl());
													}
													if (socialProfiles.getProfileType().equals("facebook")) {
														strSocialMediaResult.append("\r\n"+"*Facebook:* " + socialProfiles.getProfileUrl());
													}
													if (socialProfiles.getProfileType().equals("linkedin")) {
														strSocialMediaResult.append("\r\n"+"*LinkedIn:* " + socialProfiles.getProfileUrl());
													}
													if (socialProfiles.getProfileType().equals("google profile")) {
														strSocialMediaResult.append("\r\n"+"*Google Profile:* " + socialProfiles.getProfileUrl());
													}
													if (socialProfiles.getProfileType().equals("foursquare")) {
														strSocialMediaResult.append("\r\n"+"*Foursquare:* " + socialProfiles.getProfileUrl());
													}
													if (socialProfiles.getProfileType().equals("myspace")) {
														strSocialMediaResult.append("\r\n"+"*MySpace:* " + socialProfiles.getProfileUrl());
													}
													if (socialProfiles.getProfileType().equals("friendster")) {
														strSocialMediaResult.append("\r\n"+"*Friendster:* " + socialProfiles.getProfileUrl());
													}
												}
											}
										}
										strCallResult+= strSocialMediaResult;
									}
								}
							}
							else {
								strCallResult = "The information could not be found. Please try later.";
							}
						}
					}
				}
				catch (Exception ex) {
					strCallResult = ex.getMessage();
				}
			}			
			/**
			 * THESE are single word commands that we understand. Currently we understands help, about and list
			 * 
			 * 1. help : This prints out a mini user manual to help the user understand what commands the Bot can request. 
			 * 2. about : A brief message indicating who wrote this Bot or maybe some more details about the Bot
			 */
			else if (words.length == 1) {
				if (words[0].equalsIgnoreCase("help")) {
					//Print out help
					//strCallResult = "Help Text Over Here";
					StringBuffer SB = new StringBuffer();
					SB.append("\r\n***** Welcome to MySearchProfileBot *****");
					SB.append("\r\nI understand the following commands:");
					SB.append("\r\n1. Type help to get the list of commands.");
					SB.append("\r\n2. Type search [EmailAddress] to search for details about the Email Address. For e.g. search romin.k.irani@gmail.com will search for details about the email address (romin.k.irani@gmail.com).");
					SB.append("\r\n3. Type about to get more information about this Agent.");
					strCallResult = SB.toString();
					
				}
				else if (words[0].equalsIgnoreCase("about")) {
					strCallResult = "\r\nHello! I am the MySearchProfileBot version 1.0"+"\r\n"+"Developer: Romin Irani"+"\r\n"+"(http://searchaprofile.appspot.com)\r\n Powered by RainMaker API(http://api.rainmaker.cc/)";
				}
			}
			else {
				strCallResult = "Sorry! Could not understand your command.";
			}
			
			//Send out the Response message on the same XMPP channel. This will be delivered to the user via the Google Talk client.
	        Message replyMessage = new MessageBuilder().withRecipientJids(fromJid).withBody(strCallResult).build();
                
	        boolean messageSent = false;
	        //if (xmpp.getPresence(fromJid).isAvailable()) {
	        SendResponse status = xmpp.sendMessage(replyMessage);
	        messageSent = (status.getStatusMap().get(fromJid) == SendResponse.Status.SUCCESS);
	        //}
	        if (messageSent) {
	        	strStatus = "Message has been sent successfully";
	        }
	        else {
	        	strStatus = "Message could not be sent";
	        }
	        _log.info(strStatus);
		}
		catch (Exception ex) {
			
			//If there is an exception then we send back a generic message to the client i.e. MySearchProfileBot could not understand your command. Please
			//try again. We log the exception internally.
			_log.info("Something went wrong. Please try again!" + ex.getMessage());
	        Message replyMessage = new MessageBuilder()
            .withRecipientJids(fromJid)
            .withBody("MySearchProfileBot could not understand your command. Please try again.")
            .build();
                
	        boolean messageSent = false;
	        //The condition is commented out so that it can work over non Google Talk XMPP providers also.
	        //if (xmpp.getPresence(fromJid).isAvailable()) {
	        SendResponse status = xmpp.sendMessage(replyMessage);
	        messageSent = (status.getStatusMap().get(fromJid) == SendResponse.Status.SUCCESS);
	        //}
	        if (messageSent) {
	        	strStatus = "Message has been sent successfully";
	        }
	        else {
	        	strStatus = "Message could not be sent";
	        }
	        _log.info(strStatus);
		}
	}
}
