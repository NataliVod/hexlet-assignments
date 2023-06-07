package exercise.servlet;

import exercise.Data;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.stream.Collectors;

public class CompaniesServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
                throws IOException, ServletException {

        // BEGIN
        PrintWriter out = response.getWriter();
        List<String> companies = Data.getCompanies();
        String search = request.getParameter("search");
        if (search != null && !search.isEmpty()) {
            companies = companies
                    .stream()
                    .filter(x -> x.contains(search))
                    .collect(Collectors.toList());
        }
        if (companies.isEmpty()){
            out.println("Companies not found");
        } else {
            companies.forEach(str -> out.println(str));
        }
        // END
    }
}
