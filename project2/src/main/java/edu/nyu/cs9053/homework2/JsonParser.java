package edu.nyu.cs9053.homework2;

import edu.nyu.cs9053.homework2.model.*;
import java.util.Arrays;

/**
 * User: blangel
 *
 * @see {@literal https://www.json.org/}
 * @see {@literal https://en.wikipedia.org/wiki/JSON}
 */
public class JsonParser {

    /**
     * @param alert to serialize into {@literal JSON}
     * @implNote a null value should be an {@linkplain IllegalArgumentException}; i.e. {@code throw new IllegalArgumentException}
     * @return the serialized {@literal JSON} representation of {@code alert}
     */
    public static String toJson(EngineLightAlert alert) {
	
	if (alert == null) {
	    throw new IllegalArgumentException("Input includes null");
	}
	
	String returnedJson = "{";

	if ( alert.getVehicleId() != null ) {
	     returnedJson += "\"vehicleId\":\"" 
			     + alert.getVehicleId().replaceAll("\\\"", "\\\\\\\"")
			     + "\",";
	}
	
	returnedJson += "\"dateTime\":" + String.valueOf(alert.getDateTime());
	
	if ( alert.getCodes() != null && alert.getCodes().length != 0 ) {
	    returnedJson += ",[";

	    for (DiagnosticTroubleCode code : alert.getCodes()) {
		if (code != null) {
		   if ( code.getCode() != null ) {
		        returnedJson += "{\"code\":\"" + code.getCode() + "\"},";
		   } else {
			returnedJson += "{},";
		   }
				
		}
	    }

	    returnedJson = returnedJson.substring(0, returnedJson.length() - 1) + "]}";
	    
	} else {
	    returnedJson += "}";
	}
	
	return returnedJson;
    }

}
