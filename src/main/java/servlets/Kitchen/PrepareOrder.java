package servlets.Kitchen;

import helpers.PrepareOrderHelper;
import lombok.NonNull;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/PrepareOrder")
public class PrepareOrder extends HttpServlet {
    @Override
    protected void doGet(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response) {
        new PrepareOrderHelper(request, response)
                .prepareOrder();
    }
}
