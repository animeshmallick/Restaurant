package helpers;

import lombok.experimental.UtilityClass;
import lombok.extern.log4j.Log4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@UtilityClass
@Log4j
public class CommonHelper {
    private final String path = "webpage/%s.jsp";
    public void redirectToErrorPage(HttpServletRequest request, HttpServletResponse response, Exception ex) {
        try {
            String relativePath = String.format(path, "error");
            request.getRequestDispatcher(relativePath).forward(request,response);
        } catch(Exception exception) {
            //Do nothing as the URL generated will never throw any error
        }
    }

    public void redirectToErrorPage(HttpServletRequest request, HttpServletResponse response, String errorMessage) {
        try {
            String relativePath = String.format(path, "error");
            request.getRequestDispatcher(relativePath).forward(request,response);
        } catch(Exception exception) {
            //Do nothing as the URL generated will never throw any error
        }
    }

    public void redirectTo(HttpServletRequest request, HttpServletResponse response, String pageType) {
        try {
            String relativePath = String.format(path, pageType);
            request.getRequestDispatcher(relativePath).forward(request,response);
        } catch (Exception exception) {
            //Do nothing as the URL generated will never throw any error
        }
    }

    public static void redirectToStaticErrorPage(HttpServletRequest request, HttpServletResponse response) {
        try {
            String relativePath = String.format(path, "staticError");
            request.getRequestDispatcher(relativePath).forward(request,response);
        } catch (Exception exception) {
            //Do nothing as the URL generated will never throw any error
        }
    }
}
