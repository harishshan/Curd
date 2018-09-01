import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StudentDao {

	public static Connection getConnection(){
		Connection con=null;
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","12345678");
		}catch(Exception e){System.out.println(e);}
		return con;
	}
	public static int save(Student s){
		int status=0;
		try{
			Connection con=StudentDao.getConnection();
			Statement cs = con.createStatement();
			int noOfRecords = cs.executeUpdate("insert into student(sno,name) values ('"+s.getSno()+"','"+s.getName()+"')");
			status = 1;
			con.close();
		}catch(Exception ex){ex.printStackTrace();}
		return status;
	}
	public static int update(Student s){
		int status=0;
		try{
			Connection con=StudentDao.getConnection();
			Statement cs = con.createStatement();
			int noOfRecords = cs.executeUpdate("update Student set name='"+s.getName()+"' where sno='"+s.getSno()+"'");
			con.close();
		}catch(Exception ex){ex.printStackTrace();}
		
		return status;
	}
	public static int delete(String sno){
		int status=0;
		try{
			Connection con=StudentDao.getConnection();
			Statement cs = con.createStatement();
			int noOfRecords = cs.executeUpdate("delete from Student where sno='"+sno+"'");
			con.close();
		}catch(Exception e){e.printStackTrace();}
		return status;
	}
	public static Student getStudentBySno(String sno){
		Student s=new Student();
		
		try{
			Connection con=StudentDao.getConnection();
			Statement cs = con.createStatement();
			ResultSet rs=cs.executeQuery("select sno,name from Student where sno='"+sno+"'");
			if(rs.next()){
				s.setSno(rs.getString("sno"));
				s.setName(rs.getString("name"));				
			}
			con.close();
		}catch(Exception ex){ex.printStackTrace();}
		return s;
	}
	public static List<Student> getAllEmployees(){
		List<Student> list=new ArrayList<Student>();
		
		try{
			Connection con=StudentDao.getConnection();
			PreparedStatement ps=con.prepareStatement("select sno,name from Student");
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				Student s=new Student();
				s.setSno(rs.getString("sno"));
				s.setName(rs.getString("name"));
				list.add(s);
			}
			con.close();
		}catch(Exception e){e.printStackTrace();}
		
		return list;
	}
}
