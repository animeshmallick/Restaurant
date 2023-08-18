package servlets.Kitchen;

import helpers.Kitchen.KitchenOrdersHelper;
import lombok.NonNull;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "/KitchenOrders", urlPatterns = "/Kitchen/KitchenOrders")
public class KitchenOrders extends HttpServlet {
    @Override
    protected void doGet(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response) {
        new KitchenOrdersHelper(request, response)
                .viewAllOrders();
    }
}
