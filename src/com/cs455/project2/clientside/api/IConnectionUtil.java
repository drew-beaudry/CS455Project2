package com.cs455.project2.clientside.api;

import com.cs455.project2.shared.RequestTypeEnum;

public interface IConnectionUtil {
	/**
	 * Connect to server and send provided request
	 * 
	 * @param requestType {@link RequestTypeEnum -see RequestType}
	 * @param request
	 * @return response from server
	 */
	String performRequest(RequestTypeEnum requestType, String request);
}
