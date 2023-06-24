package exercise.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.Map;

import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.Files;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.commons.lang3.ArrayUtils;

public class UsersServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
                throws IOException, ServletException {

        String pathInfo = request.getPathInfo();

        if (pathInfo == null) {
            showUsers(request, response);
            return;
        }

        String[] pathParts = pathInfo.split("/");
        String id = ArrayUtils.get(pathParts, 1, "");
        showUser(request, response, id);
    }

    private List getUsers() throws JsonProcessingException, IOException {
        // BEGIN
        String filePath = "src/main/resources/users.json";
        String content = Files.readString(Paths.get(filePath));
        ObjectMapper mapper = new ObjectMapper();
        List<Map<String, String>> users = mapper.readValue(content, List.class);
        return users;
        // END
    }

    private void showUsers(HttpServletRequest request,
                          HttpServletResponse response)
                throws IOException {

        // BEGIN
        StringBuilder body = new StringBuilder("""
                <!DOCTYPE html>
                <html lang=\\"ru\\">
                  <head>
                    <meta charset=\\"UTF-8\\">
                    <title>Example application | Users</title>
                    <link rel=\\"stylesheet\\" href=\\"mysite.css\\">
                  </head>
                  <body>
                  <table>
                  <tr>
                """);

        List<Map<String, String>> users = getUsers();
        String htmlUsers = users.stream().map(user -> "<td>" + user.get("id") + "</td>\n" +
                "<td>\n"
                + "<a href=/users/" + user.get("id") + ">"
                + user.get("firstName") + " " + user.get("lastName") + "</a>\n" +
                "<td>\n").collect(Collectors.joining("\n"));

        body.append(htmlUsers);
        body.append("""
                  </tr>
                </table>
                """);

        PrintWriter out = response.getWriter();
        out.println(body.toString());
        // END
    }

    private void showUser(HttpServletRequest request,
                         HttpServletResponse response,
                         String id)
                 throws IOException {

        // BEGIN
        StringBuilder body = new StringBuilder("""
                <!DOCTYPE html>
                <html lang=\\"ru\\">
                  <head>
                    <meta charset=\\"UTF-8\\">
                    <title>Example application | Users</title>
                    <link rel=\\"stylesheet\\" href=\\"mysite.css\\">
                  </head>
                  <body>
                  <table>
                  <tr>
                """);

        if (id.equals("")) {
            response.sendError(404);
            return;
        }
        int index = Integer.parseInt(id);
        List<Map<String, String>> users = getUsers();
        Map<String, String> user = users.stream().filter(u -> u.get("id").equals(id)).findAny().orElse(null);
        if (user == null) {
            response.sendError(404);
            return;
        }

        body.append("<td>" + user.get("id") + "</td>\n")
                .append("<td>\n"
                        + "<a href=/users/"+ user.get("id") + ">"
                        + user.get("firstName") + " " + user.get("lastName") + "</a>\n")
                .append("<td>" + user.get("email") + "</td>")
                .append("<td>\n");

        body.append("""
                  </tr>
                </table>
                """);
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(body.toString());

        // END
    }
}
