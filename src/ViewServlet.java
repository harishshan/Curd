

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/ViewServlet")
public class ViewServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		out.println("<a href='index.html'>Add New Employee</a>");
		out.println("<h1>Employees List</h1>");
		
		List<Student> list=StudentDao.getAllEmployees();
		
		out.print("<table border='1' width='100%'");
		out.print("<tr><th>Sno</th><th>Name</th><th>Edit</th><th>Delete</th></tr>");
		for(Student s:list){
			out.print("<tr><td>"+s.getSno()+"</td><td>"+s.getName()+"</td><td><a href='EditServlet?sno="+s.getSno()+"'>edit</a></td><td><a href='DeleteServlet?sno="+s.getSno()+"'>delete</a></td></tr>");
		}
		out.print("</table>");
		
		out.close();
	}
}
