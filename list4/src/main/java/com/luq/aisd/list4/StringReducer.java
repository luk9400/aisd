package com.luq.aisd.list4;

public class StringReducer {
  public static String reduceString(String str) {

    if (str.equals("")) {
      return str;
    }
    if (!isValid(str.charAt(0))) {
      return reduceString(str.substring(1));
    }
    if (!isValid(str.charAt(str.length() - 1))) {
      return reduceString(str.substring(0, str.length() - 1));
    }

    return str;
  }

  private static boolean isValid(char c) {
    return (c >= 65 && c <= 90) || (c >= 97 && c <= 122);
  }
}
