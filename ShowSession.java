
package ex1;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.MonthDay;
import java.time.temporal.ChronoUnit;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/ShowSession")
public class ShowSession extends HttpServlet {

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String fullName = (String) session.getAttribute("fullName");
        String birthDay = (String) session.getAttribute("birthDay");

        if (fullName == null || birthDay == null) {

            out.println("<h2>Welcome on my site!</h2>");
            out.println("<form action='ShowSession' method='POST'>");
            out.println("Full name: <input type='text' name='fullName' required><br>");
            out.println("Date of birth: <input type='date' name='birthDay' required><br>");
            out.println("<input type='submit' value='Save'>");
            out.println("</form>");

        } else {

            LocalDate birth = LocalDate.parse(birthDay);
            LocalDate today = LocalDate.now();

            LocalDate nextBirthday =
                MonthDay.from(birth).atYear(today.getYear());

            if (!nextBirthday.isAfter(today)) {
                nextBirthday = nextBirthday.plusYears(1);
            }

            long days = ChronoUnit.DAYS.between(today, nextBirthday);

            out.println("<h2>Welcome back!</h2>");
            out.println("<p>Hi, " + fullName + ".</p>");
            out.println("<p>There are " + days
                        + " days to your birthday.</p>");
        }
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        String fullName = request.getParameter("fullName");
        String birthDay = request.getParameter("birthDay");

        session.setAttribute("fullName", fullName);
        session.setAttribute("birthDay", birthDay);
        session.setAttribute("visits", 1);

        response.sendRedirect("ShowSession");
    }
}
