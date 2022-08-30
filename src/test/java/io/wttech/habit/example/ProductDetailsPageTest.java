package io.wttech.habit.example;

import io.wttech.habit.client.requestgraph.assertion.GraphAssertion;
import io.wttech.habit.example.shared.GraphTraversals;
import io.wttech.habit.example.shared.HeadersAssertions;
import io.wttech.habit.example.shared.Requests;
import io.wttech.habit.junit.HabitRequestDSL;
import io.wttech.habit.junit.HabitTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@HabitTest
public class ProductDetailsPageTest {

  private HabitRequestDSL habit;

  @BeforeEach
  private void beforeEach(HabitRequestDSL habit) {
    this.habit = habit;
  }

  @Test
  public void productDetailsPage() {
    GraphAssertion graphAssertion = habit.request(Requests::frontDomainHttps)
        .get()
        .path("/products/product1/")
        .send()
        .getGraph()
        .assertThat();

    graphAssertion.exchange().response().code().isOk();
    graphAssertion.exchange().response().headers().verify(HeadersAssertions::assertHttpsResponseHeaders);

    GraphAssertion backDomainSubgraph = graphAssertion.traverse(GraphTraversals::getBackDomainSubgraph);
    backDomainSubgraph.exchange().response().code().isOk();
    backDomainSubgraph.exchange().request().path().is("/templates/pdp/");
    backDomainSubgraph.exchange().request().query()
        .parameter("id")
        .values().containOnly("product1");
  }

  @Test
  public void directProductDetailsPageTemplateAccess() {
    GraphAssertion graphAssertion = habit.request(Requests::frontDomainHttps)
        .get()
        .path("/templates/pdp/")
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
        .is("https://front.domain.com/products/");
  }
}
