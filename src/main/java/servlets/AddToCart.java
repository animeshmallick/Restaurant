package servlets;

import lombok.extern.log4j.Log4j;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Log4j
@WebServlet("/addToCart")
public class AddToCart extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] requestCookie = request.getCookies();
        for(Cookie cookie : requestCookie)
            log.info("Cookie Details=>" + cookie.getName() + ":" + cookie.getValue());
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        log.info("Posting");
    }
}
