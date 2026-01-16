package scenarios;

import io.gatling.javaapi.core.FeederBuilder;
import requests.CreateProductApiRequest;
import requests.UserApiRequests;
import io.gatling.javaapi.core.ScenarioBuilder;

import static io.gatling.javaapi.core.CoreDsl.csv;
import static io.gatling.javaapi.core.CoreDsl.scenario;

public class UserApiScenario {
    private static final FeederBuilder.Batchable<String> userFeeder =
            csv("feeders/users.csv").circular();

    public static ScenarioBuilder userFlow =
            scenario("User API Flow")
                    .feed(userFeeder)
                    .exec(UserApiRequests.login)
                    .exitHereIfFailed()
                    .pause(1, 5)
                    .exec(UserApiRequests.getProfile)
                    .pause(1, 5);
}
