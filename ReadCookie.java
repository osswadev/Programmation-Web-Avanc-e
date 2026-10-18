
package ex1;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ReadCookie")
public class ReadCookie extends HttpServlet {

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        // Recuperer les cookies envoyes par le navigateur
        Cookie[] cookies = request.getCookies();

        out.println("<html><body>");
        out.println("<h2>Lecture des cookies</h2>");

        if (cookies != null) {

            for (Cookie cookie : cookies) {

                String name = cookie.getName();

                if (name.equals("firstName")
                        || name.equals("lastName")
                        || name.equals("birthDay")
                        || name.equals("sex")) {

                    out.println("<p>" + name + " : "
                            + cookie.getValue() + "</p>");
                }
            }

        } else {
            out.println("<p>Aucun cookie trouve.</p>");
        }

        out.println("<a href='index.html'>Retour au formulaire</a>");
        out.println("</body></html>");
    }
}
