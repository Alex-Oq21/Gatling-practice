package requests;

import io.gatling.javaapi.http.HttpRequestActionBuilder;

import static utils.Headers.jsonHeaders;
import static io.gatling.javaapi.core.CoreDsl.StringBody;
import static io.gatling.javaapi.core.CoreDsl.jsonPath;
import static io.gatling.javaapi.http.HttpDsl.http;
import static io.gatling.javaapi.http.HttpDsl.status;

public class UserApiRequests {

    public static HttpRequestActionBuilder login =
            http("Login")
                    .post("/auth/login")
                    .headers(jsonHeaders)
                    .body(StringBody("""
                        {
                          "username": "#{username}",
                          "password": "#{password}"
                        }
                    """)).asJson()
                    .check(status().in(200, 201))
                    .check(jsonPath("$.accessToken").exists())
                    .check(jsonPath("$.accessToken").saveAs("authToken"));

    public static HttpRequestActionBuilder getProfile =
            http("Get User Profile")
                    .get("/auth/me")
                    .header("Authorization", "Bearer #{authToken}")
                    .check(status().is(200));
}
