package com.cs455.project2.clientside.api;

public interface IConnectionUtil {
	/**
	 * Connect to server and send provided request
	 * 
	 * @param requestType {@link RequestTypeEnum -see RequestType}
	 * @param request
	 * @return response from server
	 */
	String performRequest(String requestType, String request);
}
