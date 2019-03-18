package com.recoveryenhancementsolutions.volition.utilities;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * A utility method for converting stack traces from exceptions to strings for logging.
 */
public class ExceptionLoggingUtility {

  /**
   * Gets the stack trace from an exception in string format.
   *
   * @param e Exception to extract stack trace from
   * @return Stack trace string
   */
  public String getStackTraceString(final Exception e) {
    final StringWriter sw = new StringWriter();
    e.printStackTrace(new PrintWriter(sw));
    return sw.toString();
  }
}
