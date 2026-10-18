
package ex1;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class FirstServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        // 1. Recuperer les valeurs du formulaire
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String birthDay = request.getParameter("birthDay");
        String sex = request.getParameter("sex");

        // 2. Creer les cookies
        Cookie c1 = new Cookie("firstName", firstName);
        Cookie c2 = new Cookie("lastName", lastName);
        Cookie c3 = new Cookie("birthDay", birthDay);
        Cookie c4 = new Cookie("sex", sex);

        // 3. Envoyer les cookies au navigateur
        response.addCookie(c1);
        response.addCookie(c2);
        response.addCookie(c3);
        response.addCookie(c4);

        // 4. Afficher les informations
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.println("<html><body>");
        out.println("<h2>Informations recues</h2>");
        out.println("<p>Prenom : " + firstName + "</p>");
        out.println("<p>Nom : " + lastName + "</p>");
        out.println("<p>Date de naissance : " + birthDay + "</p>");
        out.println("<p>Sexe : " + sex + "</p>");
        out.println("<a href='ReadCookie'>Lire les cookies</a>");
        out.println("</body></html>");
    }
}
