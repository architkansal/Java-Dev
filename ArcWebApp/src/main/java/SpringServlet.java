package ArcWebApp;

import java.util.List;
import org.apache.ibatis.session.defaults.DefaultSqlSessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ArcWebApp.UserDAO;
import ArcWebApp.User;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class SpringServlet extends HttpServlet {
    private static final long serialVersionUID = -4751096228274971485L;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String s = MysqlConn.get_tuple();
        response.setContentType("text/html");
        response.getWriter().println("Hello ");
        response.getWriter().println(s);
         String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " +
         "transitional//en\">\n";
        response.getWriter().println(docType + "<html>\n" +
         "<head><title>" + "Sample" + "</title></head>\n" +
         "<body bgcolor=\"#f0f0f0\">\n" +
         "<h1 align=\"center\">" + "Login/Register(Spring)" + "</h1>\n" +
         "<ul>\n");

        response.getWriter().println(
            "<form action=\"\" method=\"GET\">" +
            "First Name: <input type=\"text\" name=\"first_name\">"+
            "<br />"+
            "Last Name: <input type=\"text\" name=\"last_name\" /> <br/>"+
            "<input type=\"submit\" value=\"Submit\" />"+
            "</form>  <br/> <h3> Get Request Data </h3>");


        response.getWriter().println("  <li><b>First Name</b>: "
         + request.getParameter("first_name") + "\n" +
         "  <li><b>Last Name</b>: "
         + request.getParameter("last_name") + "\n" +
         "</ul>\n" +
         "</body></html>");

        response.getWriter().println(
            "<form action=\"\" method=\"POST\">" +
            "First Name: <input type=\"text\" name=\"first_name_post\">"+
            "<br />"+
            "Last Name: <input type=\"text\" name=\"last_name_post\" /> <br/>"+
            "<input type=\"submit\" value=\"Submit\" />"+
            "</form>  <br/> <h3> Post Request Data </h3>");


         response.getWriter().println("  <li><b>First Name</b>: "
         + request.getParameter("first_name_post") + "\n" +
         "  <li><b>Last Name</b>: "
         + request.getParameter("last_name_post") + "\n" +
         "</ul>\n" +
         "</body></html>");



         // Laod spring-config.xml file
        ApplicationContext ctx = new ClassPathXmlApplicationContext("/spring-config.xml");
 
        //get sqlSessionFactory
                // sessionFactory should be cast as DefaultSqlSessionFactory!!!! 
        DefaultSqlSessionFactory sessionFactory  = (DefaultSqlSessionFactory) ctx.getBean("sqlSessionFactory");
 
        UserDAO userDAO = new UserDAO(sessionFactory);
 
        //create user bean to insert
        User user = new User();
        user.setName("User 1");
 
        //( 1 ) insert user 
        userDAO.insert(user);
 
        //**set name of user
        user.setName("User 2"); 
        //** insert another user
        int id = userDAO.insert(user);
 
        //( 2 ) select users by id
        userDAO.selectById(id);
 
        //( 3 ) select all
        List<User> users = userDAO.selectAll();
 
        //**set name of all users
        for(int i = 0; i < users.size(); i++){
            users.get(i).setName("User Name "+i);
            //( 4 ) update user
            userDAO.update(users.get(i));
        }
 
    }

    @Override       
    public void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
      doGet(request, response);
   }

    @Override
    public void init() throws ServletException {
        System.out.println("Servlet " + this.getServletName() + " has started");
    }
    @Override
    public void destroy() {
        System.out.println("Servlet " + this.getServletName() + " has stopped");
    }
}