package com.example.servlet_practice;


import javax.servlet.Registration;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.Enumeration;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

@WebServlet("/add-servlet")
public class AddServlet  extends HttpServlet {

    private static final String NUMBER_PATTERN = "^[0-9]+$";
    private static final Pattern REGEX = Pattern.compile(NUMBER_PATTERN);

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      BigInteger number1 = getParameter(request,"number1");
      BigInteger number2 = getParameter(request,"number2");
      RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");

      if(number1 == null || number2 == null){
          request.setAttribute("result","invalid input");
          response.setHeader("RESULT","invalid input");

      }else{
          BigInteger result = number1.add(number2);
          request.setAttribute("result",result);
          response.setHeader("RESULT",String.valueOf(result));
      }
      dispatcher.forward(request,response);
    }
    private BigInteger getParameter(HttpServletRequest request, String name){
        String param = request.getParameter(name);
        return checkIfNumber(param);
    }
    private static BigInteger checkIfNumber(String input){
        Matcher matcher = REGEX.matcher(input);
        return matcher.matches() ? new BigInteger(input) : null;

    }

    }

