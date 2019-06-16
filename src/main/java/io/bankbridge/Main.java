package io.bankbridge;

import static spark.Spark.get;
import static spark.Spark.port;

public class Main {

    public static void main(String[] args) { // removed throws Exception which is never thrown
        port(getPort());

        get("/rbb", (request, response) -> "{\n" +
                "\"bic\":\"1234\",\n" +
                "\"countryCode\":\"GB\",\n" +
                "\"auth\":\"OAUTH\"\n" +
                "}");
        get("/cs", (request, response) -> "{\n" +
                "\"bic\":\"5678\",\n" +
                "\"countryCode\":\"CH\",\n" +
                "\"auth\":\"OpenID\"\n" +
                "}");
        /*
         The last bank doesn't contain a bic, only the name - intentional or not?
         Setting correct bic so that the Mock response matches the README api description
          */
        get("/bes", (request, response) -> "{\n" +
                "\"name\":\"Banco de espiritu santo\",\n" +
                "\"countryCode\":\"PT\",\n" +
                "\"auth\":\"SSL\"\n" +
                "}");
    }

    static int getPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 1234; //return default port if heroku-port isn't set (i.e. on localhost)
    }
}