package io.wttech.habit.example.shared;

import io.wttech.habit.client.request.specification.RequestDefinitionDSL;

public final class Requests {

  private Requests() {
  }

  public static void frontDomainHttp(RequestDefinitionDSL request) {
    request
        .http()
        .host(Domains.FRONT_DOMAIN);
  }

  public static void frontDomainHttps(RequestDefinitionDSL request) {
    request
        .https()
        .host(Domains.FRONT_DOMAIN);
  }

}
