package helpers;

import lombok.extern.log4j.Log4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Log4j
public class BaseHelper {
    private String path = "/%s.jsp";
    protected void redirectToErrorPage(HttpServletRequest request, HttpServletResponse response, Exception ex) {
        try {
            String relativePath = String.format(path, "error");
            log.info("Setting Attribute Key:'error' and Value:" + ex);
            request.setAttribute("error", ex);
            log.info("Redirecting to : " + relativePath);
            request.getRequestDispatcher(relativePath).forward(request,response);
        } catch(Exception exception) {
            //Do nothing as the URL generated will never throw any error
        }
    }

    protected void redirectToErrorPage(HttpServletRequest request, HttpServletResponse response, String errorMessage) {
        try {
            String relativePath = String.format(path, "error");
            log.info("Setting Attribute Key:'error' and Value:" + errorMessage);
            request.setAttribute("error", errorMessage);
            log.info("Redirecting to : " + relativePath);
            request.getRequestDispatcher(relativePath).forward(request,response);
        } catch(Exception exception) {
            //Do nothing as the URL generated will never throw any error
        }
    }

    protected void redirectTo(HttpServletRequest request, HttpServletResponse response, String pageType) {
        try {
            String relativePath = String.format(path, pageType);
            log.info("Redirecting to : " + relativePath);
            request.getRequestDispatcher(relativePath).forward(request,response);
        } catch (Exception exception) {
            //Do nothing as the URL generated will never throw any error
        }
    }

    protected void redirectToStaticErrorPage(HttpServletRequest request, HttpServletResponse response) {
        try {
            String relativePath = String.format(path, "staticError");
            log.info("Redirecting to : " + relativePath);
            request.getRequestDispatcher(relativePath).forward(request,response);
        } catch (Exception exception) {
            //Do nothing as the URL generated will never throw any error
        }
    }
}
