package com.cs455.project2.clientside.api;

import com.cs455.project2.shared.RequestType;

public interface IConnectionUtil {
	/**
	 * Connect to server and send provided request
	 * 
	 * @param requestType {@link RequestType -see RequestType}
	 * @param request
	 * @return response from server
	 */
	String performRequest(RequestType requestType, String request);
}
