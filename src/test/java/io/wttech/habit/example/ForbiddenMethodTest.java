package io.wttech.habit.example;

import io.wttech.habit.client.requestgraph.RequestGraph;
import io.wttech.habit.client.requestgraph.assertion.GraphAssertion;
import io.wttech.habit.example.shared.HeadersAssertions;
import io.wttech.habit.example.shared.Requests;
import io.wttech.habit.junit.HabitRequestDSL;
import io.wttech.habit.junit.HabitTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@HabitTest
public class ForbiddenMethodTest {

  private HabitRequestDSL habit;

  @BeforeEach
  private void beforeEach(HabitRequestDSL habit) {
    this.habit = habit;
  }

  @Test
  @DisplayName("DELETE request is forbidden")
  public void deleteRequest() {
    habit.request(Requests::frontDomainHttps)
        .delete()
        .path("/product-instructions/")
        .assertThat().exchange().response().code().isForbidden();
  }

  @Test
  @DisplayName("PATCH request is forbidden")
  public void patchRequest() {
    RequestGraph requestGraph = habit.request(Requests::frontDomainHttps)
        .patch()
        .path("/product-instructions/")
        .send()
        .getGraph();

    GraphAssertion graphAssertion = requestGraph.assertThat();

    graphAssertion.exchange().response().code().isForbidden();
    graphAssertion.exchange().response().headers().verify(HeadersAssertions::assertHttpsResponseHeaders);
  }

  @Test
  @DisplayName("PUT request is forbidden")
  public void putRequest() {
    RequestGraph requestGraph = habit.request(Requests::frontDomainHttps)
        .put()
        .path("/product-instructions/")
        .send()
        .getGraph();

    GraphAssertion graphAssertion = requestGraph.assertThat();

    graphAssertion.exchange().response().code().isForbidden();
    graphAssertion.exchange().response().headers().verify(HeadersAssertions::assertHttpsResponseHeaders);
  }
}
