package simulations;

import config.TestConfig;
import io.gatling.javaapi.http.HttpProtocolBuilder;
import scenarios.UserApiScenario;
import io.gatling.javaapi.core.OpenInjectionStep;
import io.gatling.javaapi.core.Simulation;


public class SmokeSimulation extends Simulation {

    {
        setUp(
                UserApiScenario.userFlow
                        .injectOpen(OpenInjectionStep.atOnceUsers(1))
        ).protocols(TestConfig.httpProtocol);
    }
}
