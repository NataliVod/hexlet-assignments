package exercise;

import com.github.javafaker.Company;
import io.javalin.Javalin;
import io.javalin.http.NotFoundResponse;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

// BEGIN

// END

public final class App {

    private static final List<Map<String, String>> COMPANIES = Data.getCompanies();

    public static Javalin getApp() {

        var app = Javalin.create(config -> {
            config.plugins.enableDevLogging();
        });

        // BEGIN

        app.get("/companies/{id}", ctx -> {
            var companyNumber = ctx.pathParamAsClass("id", String.class).getOrDefault(null);

            Map<String,String> company = COMPANIES.stream()
                    .filter(x -> x.get("id").equals(companyNumber))
                    .findAny()
                    .orElse(null);
            if (company == null) {
                throw new NotFoundResponse("Company not found");
            }

            ctx.json(company);
        });
        // END

        app.get("/companies", ctx -> {
            ctx.json(COMPANIES);
        });

        app.get("/", ctx -> {
            ctx.result("open something like (you can change id): /companies/5");
        });

        return app;

    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);

    }
}
