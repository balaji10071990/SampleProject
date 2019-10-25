package com.sampleproject.utilities;


public class SessionID {

	public static ThreadLocal<String> dr = new ThreadLocal<String>();

	public static String getSessionID() {

		return dr.get();

	}

	public static void setSessionID(String sessionID) {

		dr.set(sessionID);
	}

}
