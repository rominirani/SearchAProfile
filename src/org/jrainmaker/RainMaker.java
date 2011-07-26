package org.jrainmaker;

import java.text.MessageFormat;

import org.jrainmaker.config.Constants;
import org.jrainmaker.entity.RainMakerEntity;
import org.jrainmaker.http.RainMakerHttpRequest;

import com.google.gson.Gson;

/**
 * 
 * @author Sachin Handiekar
 * @version 1.0
 */
public class RainMaker {

	/** Field description */
	private String apiKey;

	public RainMaker(String apiKey) {
		if (apiKey == null) {
			throw new IllegalArgumentException("apiKey cannot be null.");
		}

		if (apiKey.trim().length() == 0) {
			throw new IllegalArgumentException("apiKey cannot be empty.");
		}

		this.apiKey = apiKey;
	}

	/**
	 * Method description
	 * 
	 * 
	 * @param email
	 * 
	 * @return
	 * @throws RainMakerException
	 */
	public RainMakerEntity getPersonInformation(String email)
			throws RainMakerException {

		String requestParams = MessageFormat.format(Constants.emailFormat,
				email)
				+ "&"
				+ MessageFormat.format(Constants.apiKeyFormat, apiKey);
		RainMakerEntity message = null;

		Gson gson = new Gson();

		String response = RainMakerHttpRequest.sendRequest(requestParams);

		message = gson.fromJson(response, RainMakerEntity.class);

		return message;
	}

	/**
	 * Method description
	 * 
	 * 
	 * @param email
	 * 
	 * @return
	 * @throws RainMakerException
	 */
	public RainMakerEntity getPersonInformation(String email, int timeoutSeconds)
			throws RainMakerException {

		String requestParams = MessageFormat.format(Constants.emailFormat,
				email)
				+ "&"
				+ MessageFormat.format(Constants.apiKeyFormat, apiKey)
				+ "&"
				+ MessageFormat.format(Constants.timeoutSecondsFormat,
						timeoutSeconds);
		RainMakerEntity message = null;

		Gson gson = new Gson();

		String response = RainMakerHttpRequest.sendRequest(requestParams);

		message = gson.fromJson(response, RainMakerEntity.class);

		return message;
	}
}
