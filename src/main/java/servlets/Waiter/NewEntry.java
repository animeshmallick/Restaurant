package servlets.Waiter;

import helpers.Waiter.NewEntryHelper;
import lombok.NonNull;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "/NewEntry", urlPatterns = "/Waiter/NewEntry")
public class NewEntry extends HttpServlet {
    @Override
    protected void doGet(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response) {
        new NewEntryHelper(request, response)
                .validateEntry();
    }
}
