package requests;

import io.gatling.javaapi.http.HttpRequestActionBuilder;

import static io.gatling.javaapi.core.CoreDsl.ElFileBody;
import static io.gatling.javaapi.http.HttpDsl.http;
import static io.gatling.javaapi.http.HttpDsl.status;
import static utils.Headers.jsonHeaders;

public class CreateProductApiRequest {

    public static HttpRequestActionBuilder createProduct =
            http("Create Product")
                    .post("/products/add")
                    .headers(jsonHeaders)
                    .body(ElFileBody("bodies/create-product.json")).asJson()
                    .check(status().is(201));
}
