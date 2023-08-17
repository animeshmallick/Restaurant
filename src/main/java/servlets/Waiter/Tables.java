package servlets.Waiter;

import helpers.TablesHelper;
import lombok.NonNull;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Tables")
public class Tables extends HttpServlet {
    @Override
    protected void doGet(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response) {
        new TablesHelper(request, response)
                .getAllTables()
                .formatTableStatus()
                .displayAllTables();
    }
}
