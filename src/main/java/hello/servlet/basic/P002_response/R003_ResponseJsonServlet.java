package hello.servlet.basic.P002_response;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.servlet.basic.HelloData;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "r003_ResponseJsonServlet", urlPatterns = "/response-json")
public class R003_ResponseJsonServlet extends HttpServlet {


    ObjectMapper objectMapper=new ObjectMapper();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

            res.setContentType("application/json");
            res.setCharacterEncoding("utf-8");

            HelloData helloData = new HelloData();
            helloData.setUsername("정준일");
            helloData.setAge(33);

            String result= objectMapper.writeValueAsString(helloData);
            res.getWriter().write(result);


    }
}
