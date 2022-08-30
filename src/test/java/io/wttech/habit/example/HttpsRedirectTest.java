package io.wttech.habit.example;

import io.wttech.habit.client.requestgraph.assertion.GraphAssertion;
import io.wttech.habit.example.shared.HeadersAssertions;
import io.wttech.habit.example.shared.Requests;
import io.wttech.habit.junit.HabitRequestDSL;
import io.wttech.habit.junit.HabitTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@HabitTest
public class HttpsRedirectTest {

  private HabitRequestDSL habit;

  @BeforeEach
  private void beforeEach(HabitRequestDSL habit) {
    this.habit = habit;
  }

  @Test
  @DisplayName("HTTPS redirect works on /resources/")
  public void httpsRedirect() {
    GraphAssertion graphAssertion = habit.request(Requests::frontDomainHttp)
        .get()
        .path("/resources/")
        .send()
        .getGraph()
        .assertThat();

    graphAssertion.exchange().response().code().isOk();
    graphAssertion.exchange().response().headers().verify(HeadersAssertions::assertHttpsResponseHeaders);

    GraphAssertion redirectSubgraph = graphAssertion.firstSubgraph();
    redirectSubgraph.exchange().response().code().isMovedPermanently();
    redirectSubgraph.exchange().response()
        .headers()
        .location()
        .value()
        .is("https://front.domain.com/resources/");
  }

}
