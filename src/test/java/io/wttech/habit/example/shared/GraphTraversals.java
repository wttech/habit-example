package io.wttech.habit.example.shared;

import io.wttech.habit.client.requestgraph.assertion.GraphAssertion;

public final class GraphTraversals {

  private GraphTraversals() {}

  public static GraphAssertion getBackDomainSubgraph(GraphAssertion graphAssertion) {
    GraphAssertion backDomainSubgraph = graphAssertion.firstSubgraph().firstSubgraph();
    backDomainSubgraph.exchange().request().host().is(Domains.BACK_DOMAIN);
    return backDomainSubgraph;
  }

}
