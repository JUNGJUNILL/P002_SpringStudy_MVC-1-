package hello.servlet.web.frontController.v3;


import hello.servlet.web.frontController.ModelView;
import hello.servlet.web.frontController.MyView;
import hello.servlet.web.frontController.v3.controller.MemberFormControllerV3;
import hello.servlet.web.frontController.v3.controller.MemberListControllerV3;
import hello.servlet.web.frontController.v3.controller.MemberSaveControllerV3;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name="frontControllerServletV3" , urlPatterns ="/front-controller/v3/*")
public class FrontControllerServletV3 extends HttpServlet {


    private Map<String, ControllerV3> controllerMap=new HashMap<>();
    public FrontControllerServletV3(){
        controllerMap.put("/front-controller/v3/members/new-form",new MemberFormControllerV3());
        controllerMap.put("/front-controller/v3/members/save",new MemberSaveControllerV3());
        controllerMap.put("/front-controller/v3/members",new MemberListControllerV3());
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        System.out.println("front-controller-v3");
        String url = req.getRequestURI();
        System.out.println("url="+url);
        ControllerV3 controller = controllerMap.get(url);

        if(controller==null){
            res.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        Map<String,String> paramMap=new HashMap<>();

        req.getParameterNames().asIterator().forEachRemaining(paramName->paramMap.put(paramName,req.getParameter(paramName)));

        ModelView mv = controller.process(paramMap);
        String viewName=mv.getViewName();

        MyView view = new MyView("/WEB-INF/views/"+viewName+".jsp");
        view.render(mv.getModel(), req,res);



    }
}
