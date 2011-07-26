package com.thirdchimpanzee.rainmaker.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class AppUtils {
   public static String makeNetworkCall(String strServiceURL) throws Exception {
	   URL url = new URL(strServiceURL);
	   BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
       StringBuffer response = new StringBuffer();
       String line;

       while ((line = reader.readLine()) != null) {
           response.append(line);
       }
       reader.close();

       return response.toString();

   }
}
