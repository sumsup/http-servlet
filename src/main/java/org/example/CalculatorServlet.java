package org.example;

import org.example.calculator.domain.Calculator;
import org.example.calculator.domain.PositiveNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/calculate")
public class CalculatorServlet extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(CalculatorServlet.class);
    //    private ServletConfig servletConfig;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("hi service");
        int operand1 = Integer.parseInt(request.getParameter("operand1"));
        String operator = request.getParameter("operator");
        int operand2 = Integer.parseInt(request.getParameter("operand2"));

        int result = Calculator.calculate(new PositiveNumber(operand1), operator, new PositiveNumber(operand2));

        PrintWriter writer = response.getWriter();
        writer.println(result);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }



    //    @Override
//    public void init() throws ServletException {
//        log.info("init");
//        this.servletConfig = servletConfig;
//
//    }

//    @Override
//    public void service(ServletRequest request, ServletResponse response) throws IOException {
//        log.info("hi service");
//        int operand1 = Integer.parseInt(request.getParameter("operand1"));
//        String operator = request.getParameter("operator");
//        int operand2 = Integer.parseInt(request.getParameter("operand2"));
//
//        int result = Calculator.calculate(new PositiveNumber(operand1), operator, new PositiveNumber(operand2));
//
//        PrintWriter writer = response.getWriter();
//        writer.println(result);
//    }

//    @Override
//    public ServletConfig getServletConfig() {
//        return this.servletConfig;
//    }
//
//    @Override
//    public void destroy() {
//        // resource close
//    }
}
