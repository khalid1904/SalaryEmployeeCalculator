package com.java.EmpSalCalc;

import java.io.IOException; 
import java.io.PrintWriter; 
import java.sql.Connection; 
import java.sql.PreparedStatement;
import java.sql.ResultSet;


import javax.servlet.ServletException; 
import javax.servlet.annotation.WebServlet; 
import javax.servlet.http.HttpServlet; 
import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse;

import com.java.Dbconnect.DataBaseConnection;

/**
 * Servlet implementation class EmpSalCalcServlet
 */
@WebServlet("/InsertData")
public class EmpSalCalcServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    /*public EmpSalCalcServlet() {
        super();
        // TODO Auto-generated constructor stub
    }*/

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		 
		  
        // Try bock to catch exception/s 
		try { 
			  
			  
            Connection con = DataBaseConnection.initializeDatabase(); 
  
            int iddb = Integer.valueOf(request.getParameter("id"));
            String sql = "select * from employees where employees.Empid = " + iddb; 
            PreparedStatement p = con.prepareStatement(sql); 
            ResultSet rs = p.executeQuery(); 
  

            //System.out.println("Empid\t\tName\t\tAge\t\tMonthlyCTC");
            PrintWriter out = response.getWriter(); 
            out.println("<html><body >"
            		+ "<h3>The Employee Details For Given Id Is</h3>"
            		+ " <table > <tr>"
            		+ "    <th>Employee Id</th>"
            		+ "    <th>Employee Name</th>"
            		+ "    <th>Age</th>"
            		+ "    <th>Monthly Salary</th>"
            		+ "  </tr>" );
  
 
            while (rs.next()) { 
  
                int id = rs.getInt("Empid"); 
                String name = rs.getString("Name"); 
                int age = rs.getInt("Age");
                double mctc = rs.getDouble("MonthlyCTC"); 
                out.println("<tr>"
                		+ "    <td>"+id+"</td>"
                		+ "    <td>"+name+"</td>"
                		+ "    <td>"+age+"</td>"
                		+ "    <td>"+mctc+"</td>"
                		+ "  </tr>"
                		+ "</table></br>"
                		+ "<h5><a href= \"index.html\">Go To Home</a></h5>"
                		+"</body></html>");   
            }
            p.close(); 
            con.close(); 
  

             
        } 
        catch (Exception e) { 
            e.printStackTrace(); 
        } 
		//.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try { 
			  
  
            Connection con = DataBaseConnection.initializeDatabase(); 
  

            PreparedStatement st = con.prepareStatement("insert into employees values(?, ?, ?, ?)"); 
  
            
            st.setInt(1, Integer.valueOf(request.getParameter("id"))); 
 
            st.setString(2, request.getParameter("name")); 
            
            st.setInt(3, Integer.valueOf(request.getParameter("age")));
            
            st.setDouble(4, Double.valueOf(request.getParameter("msalary")));
  
 
            st.executeUpdate(); 
  
 
            st.close(); 
            con.close(); 
  

            PrintWriter out = response.getWriter(); 
            out.println("<html><body><b>Successfully Inserted </br>" 
            		+ "<h5><a href= \"index.html\">Go To Home</a></h5>"
            		+ "</b></body></html>"); 
        } 
        catch (Exception e) { 
            e.printStackTrace(); 
        }
		
		// TODO Auto-generated method stub
		//doGet(request, response);
	}

}
