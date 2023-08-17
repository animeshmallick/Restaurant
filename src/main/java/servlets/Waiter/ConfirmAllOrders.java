package servlets.Waiter;

import helpers.ConfirmAllOrdersHelper;
import lombok.NonNull;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ConfirmAllOrders")
public class ConfirmAllOrders extends HttpServlet {
    @Override
    protected void doGet(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response) {
        new ConfirmAllOrdersHelper(request, response)
                .confirmAllOrders();
    }
}
