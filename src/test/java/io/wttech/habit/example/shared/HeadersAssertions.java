package io.wttech.habit.example.shared;

import io.wttech.habit.client.requestgraph.assertion.response.ResponseHeadersAssertion;

public class HeadersAssertions {

  public static void assertHttpsResponseHeaders(ResponseHeadersAssertion responseHeaders) {
    responseHeaders.strictTransportSecurity()
        .value()
        .is("max-age=15768000");
  }

}
