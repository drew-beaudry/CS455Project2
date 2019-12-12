package com.cs455.project2.shared;

/**
 * Indicates the type of request being transmitted
 *
 * <p>GET requests should be used when retrieving a menu of options<br>
 * POST requests should be used when responding to the menu with data
 *
 * @author dbeaudry
 */
public enum RequestTypeEnum {
  GET_MAIN_MENU("GET_MAIN_MENU"),
  POST_MAIN_MENU("POST_MAIN_MENU"),
  GET_REGISTRATION_MENU("GET_REG_MENU"),
  POST_REGISTRATIN_MENU("POST_REG_MENU"),
  GET_PASSWORD_CHANGE_MENU("GET_PASS_MENU"),
  POST_PASSWORD_CHANGE_MENU("POST_PASS_MENU"),
  GET_LOGIN_MENU("GET_LOGIN_MENU"),
  POST_LOGIN_MENU("POST_LOGIN_MENU");

  private String requestType;

  private RequestTypeEnum(String requestType) {
    this.requestType = requestType;
  }

  public String getRequestType() {
    return requestType;
  }
}
