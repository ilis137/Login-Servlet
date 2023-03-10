package com.FirstServlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Pattern;

@WebServlet(
        description = "login Servlet Testing",
        urlPatterns = {"/LoginServlet"},
        initParams = {
                @WebInitParam(name = "user", value = "Iliyas"),
                @WebInitParam(name = "password", value = "root")
        }
)
public class LoginServlet  extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // Declaring name and password pattern
        String NAME_PATTERN = "^[A-Z][a-z]{2,}$";
        String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";

        //get request parameters for userId and password
        String user = req.getParameter("user");
        String pwd = req.getParameter("pwd");
        //get servlet config init params
        String userID = getServletConfig().getInitParameter("user");
        String password  = getServletConfig().getInitParameter("password");


        if((userID.equals(user) && password.equals(pwd))&&Pattern.matches(NAME_PATTERN, userID)&& Pattern.matches(PASSWORD_PATTERN, password)) {
            req.setAttribute("user", user);
            req.getRequestDispatcher("LoginSuccess.jsp").forward(req, resp);
        } else {
            RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/login.html");
            PrintWriter out = resp.getWriter();
            out.println("<font color=red> Either username or password is invalid.</font>");
            requestDispatcher.include(req, resp);
        }
    }


}
