package servlets.Waiter;

import helpers.Waiter.TableAllotmentHelper;
import lombok.NonNull;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "/TableAllotment", urlPatterns = "/Waiter/TableAllotment")
public class TableAllotment extends HttpServlet {
    @Override
    protected void doPost(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response) {
        new TableAllotmentHelper(request, response)
                .allotTable();
    }
}
