package ArcWebApp;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class SimpleServlet extends HttpServlet {
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
         "<h1 align=\"center\">" + "Login/Register" + "</h1>\n" +
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
        //ApplicationContext ctx = new ClassPathXmlApplicationContext("com/hmkcode/config/spring-config.xml");
 
        //get jdbcTemplatePersonDAO
        UserDAO personDAO = new UserDAO(MyBatisConnectionFactory.getSqlSessionFactory());

        if(personDAO.isSessionNull()==1)
        {
            response.getWriter().println(" Session Factory Error ");
        }
        
        //create person bean to insert
        User person = new User();
        person.setName("Person 1");
        
        //( 1 ) insert person   
        personDAO.insert(person);
 
        //**set name of person
        person.setName("Person 2"); 
        //** insert another person
        int id = personDAO.insert(person);
 
        //( 2 ) select persons by id
        personDAO.selectById(id);
 
        //( 3 ) select all
        List<User> persons = personDAO.selectAll();
 
        //**set name of all persons
        for(int i = 0; i < persons.size(); i++){
            persons.get(i).setName("Person Name "+i);
            //( 4 ) update person
            personDAO.update(persons.get(i));
            response.getWriter().println(persons.get(i).toString() + "<br/>");
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