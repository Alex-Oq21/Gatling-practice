package simulations;

import config.TestConfig;
import io.gatling.javaapi.core.Simulation;
import scenarios.CreateProductScenario;
import scenarios.UserApiScenario;

import static io.gatling.javaapi.core.CoreDsl.*;

public class LoadSimulation extends Simulation {
    {
        setUp(
                UserApiScenario.userFlow
                        .injectOpen(
                                rampUsers(TestConfig.USERS)
                                        .during(TestConfig.RAMP_UP),
                                constantUsersPerSec(20)
                                        .during(TestConfig.DURATION)
                        ),
                CreateProductScenario.productFlow
                        .injectOpen(
                                rampUsers(TestConfig.USERS)
                                        .during(TestConfig.RAMP_UP),
                                constantUsersPerSec(20)
                                        .during(TestConfig.DURATION)
                        )



        )
                .protocols(TestConfig.httpProtocol)
                .assertions(
                        global().responseTime().percentile(95)
                                .lt(TestConfig.MAX_RESPONSE_TIME_P95),
                        global().failedRequests().percent()
                                .lt(TestConfig.MAX_ERROR_PERCENTAGE)
                );
    }
}
