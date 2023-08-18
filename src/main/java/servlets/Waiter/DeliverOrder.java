package servlets.Waiter;

import helpers.Waiter.DeliverOrderHelper;
import lombok.NonNull;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "/DeliverOrder", urlPatterns = "/Waiter/DeliverOrder")
public class DeliverOrder extends HttpServlet {
    @Override
    protected void doGet(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response) {
        new DeliverOrderHelper(request, response)
                .deliverOrder();
    }
}
