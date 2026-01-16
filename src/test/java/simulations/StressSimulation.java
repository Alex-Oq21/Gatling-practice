package simulations;

import config.StressConfig;
import config.TestConfig;
import io.gatling.javaapi.core.Simulation;
import scenarios.CreateProductScenario;
import scenarios.UserApiScenario;

import java.time.Duration;

import static io.gatling.javaapi.core.CoreDsl.*;

public class StressSimulation extends Simulation {
    {
        setUp(
                UserApiScenario.userFlow
                        .injectOpen(
                                incrementUsersPerSec(1)
                                        .times(StressConfig.STEPS)
                                        .eachLevelLasting(StressConfig.DURATION)
                                        .separatedByRampsLasting(10)
                                        .startingFrom(StressConfig.USERS)
                        ),
                CreateProductScenario.productFlow
                        .injectOpen(
                        incrementUsersPerSec(1)
                                .times(StressConfig.STEPS)
                                .eachLevelLasting(StressConfig.DURATION)
                                .separatedByRampsLasting(10)
                                .startingFrom(StressConfig.USERS)
                        )
        ).protocols(TestConfig.httpProtocol)
                .assertions(
                        global().responseTime().percentile(95)
                                .lt(TestConfig.MAX_RESPONSE_TIME_P95),
                        global().failedRequests().percent()
                                .lt(TestConfig.MAX_ERROR_PERCENTAGE)
                );
    }
}
