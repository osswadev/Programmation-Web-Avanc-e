
package ex1;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/ListItems")
public class ListItems extends HttpServlet {

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        HashMap<String, Integer> items =
            (HashMap<String, Integer>) session.getAttribute("items");

        if (items == null) {
            items = new HashMap<String, Integer>();
        }

        String newItem = request.getParameter("newItem");

        if (newItem != null && !newItem.trim().isEmpty()) {

            newItem = newItem.trim();

            int count = items.getOrDefault(newItem, 0);

            items.put(newItem, count + 1);
        }

        session.setAttribute("items", items);

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.println("<html><body>");
        out.println("<h2>Items and their counts</h2>");
        out.println("<table border='1'>");
        out.println("<tr><th>Item</th><th>Count</th></tr>");

        for (Map.Entry<String, Integer> entry : items.entrySet()) {
            out.println("<tr><td>" + entry.getKey()
                + "</td><td>" + entry.getValue()
                + "</td></tr>");
        }

        out.println("</table>");
        out.println("<p><a href='items.html'>Add another item</a></p>");
        out.println("</body></html>");
    }
}
