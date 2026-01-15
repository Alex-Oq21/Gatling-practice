package scenarios;

import io.gatling.javaapi.core.ScenarioBuilder;
import requests.CreateProductApiRequest;

import static io.gatling.javaapi.core.CoreDsl.scenario;

public class CreateProductScenario {

    public static ScenarioBuilder productFlow =
            scenario("Create Product API Flow")
                    .exec(CreateProductApiRequest.createProduct)
                    .exitHereIfFailed()
                    .pause(1);
}
