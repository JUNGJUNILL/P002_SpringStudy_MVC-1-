package hello.servlet.basic.P001_request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "r002_RequestParamServlet", urlPatterns = "/request-param")
public class R002_RequestParamServlet extends HttpServlet {


    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        System.out.println("[전체 파라메터 조회]-start");
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName->
                        System.out.println("key="+paramName+ " value="+request.getParameter(paramName)));
        System.out.println("[전체 파라메터 조회]-end");


        System.out.println("[단일 파라미터 조회]");
        String username = request.getParameter("username");
        System.out.println("request.getParameter(username) = " + username);


        System.out.println("[이름이 같은 복수 파라미터 조회]");
        //?username=hello&username=world
        System.out.println("request.getParameterValues(username)");
        String[] usernames = request.getParameterValues("username");
        for (String name : usernames) {
            System.out.println("username=" + name);
        }
        response.getWriter().write("ok");
    }
}
