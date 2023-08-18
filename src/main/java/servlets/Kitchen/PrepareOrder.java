package servlets.Kitchen;

import helpers.Kitchen.PrepareOrderHelper;
import lombok.NonNull;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "/PrepareOrder", urlPatterns = "/Kitchen/PrepareOrder")
public class PrepareOrder extends HttpServlet {
    @Override
    protected void doGet(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response) {
        new PrepareOrderHelper(request, response)
                .prepareOrder();
    }
}
