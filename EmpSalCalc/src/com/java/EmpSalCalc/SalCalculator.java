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
 * Servlet implementation class SalCalculator
 */
@WebServlet("/SalCalculator")
public class SalCalculator extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SalCalculator() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try { 
			  
			  
            Connection con = DataBaseConnection.initializeDatabase(); 
  
            int iddb = Integer.valueOf(request.getParameter("id"));
            String sql = "select * from employees where employees.Empid = " + iddb; 
            PreparedStatement p = con.prepareStatement(sql);
            
            ResultSet rs = p.executeQuery();
            
            

            
            while (rs.next()) { 
            	  
                int id = rs.getInt("Empid"); 
                String name = rs.getString("Name");
                int age = rs.getInt("Age");
                double mctc = rs.getDouble("MonthlyCTC"); 
                
                double actc = mctc * 12.0;
                double deductions = 50000;
                double ctax = actc - deductions;
                
                double ntax = 0;
                if( ctax > 250000 && ctax <= 500000) {
                	ntax = 0.05 * (ctax - 250000);
                }else if( ctax > 500000 && ctax <= 1000000){
                	ntax = 12500 + (0.2 * (ctax - 500000));
                }else if( ctax > 1000000) {
                	ntax = 112500 + (0.3 * (ctax - 1000000));
                }
                
                
                double surcharge = 0;
                
                if( ctax >= 5000000 && ctax <= 1000000) {
                	surcharge = 0.1 * ntax;
                }else if( ctax > 10000000 && ctax <= 20000000) {
                	surcharge = 0.15 * ntax;
                }else if( ctax > 20000000 && ctax <= 50000000) {
                	surcharge = 0.25 * ntax;
                }else if( ctax > 50000000 ) {
                	surcharge = 0.37 * ntax;
                }
                
                double cess=0;
                
                if( ctax > 500000) {
                	cess = 0.04 * ntax;
                }
                
                //  double netptax = (ctax > 500000) ? ntax+surcharge+cess : 0; //withtax rebate
                
                double netptax = ntax+surcharge+cess; //without rebate
                
                double sal = ctax - (netptax);
                
                double monthlysal = ctax/12 - netptax/12;
                
                
                PreparedStatement st = con.prepareStatement("insert into taxes values(?, ?, ?, ?, ?, ?, ?, ?)"); 
                
                
                st.setInt(1, id); 
     
                st.setDouble(2, actc); 
                
                st.setDouble(3, deductions);
                
                st.setDouble(4, ctax);
                
                st.setDouble(5, surcharge);
                
                st.setDouble(6, cess);
                
                st.setDouble(7, netptax);
                
                st.setDouble(8, sal);
      
     
                st.executeUpdate();
                
                
                
                PrintWriter out = response.getWriter();
                out.println("<html><body>" 
        		+ "<h3> Hi "+ name + " Your Calculated Salary Details Are Follows"
        		+ " <table > <tr>"
        		+ "    <th>Categories</th>"
        		+ "    <th>Amounts</th>"
        		+ "  </tr>"
        		+ " <tr>"
        		+ "    <th>AnnualCTC :</th>"
        		+ "    <th>"+actc+"</th>"
        		+ "  </tr>"
        		+ " <tr>"
        		+ "    <th>Deductions :</th>"
        		+ "    <th>"+deductions+"</th>"
        		+ "  </tr>"
        		+ " <tr>"
        		+ "    <th>Taxable Income :</th>"
        		+ "    <th>"+ctax+"</th>"
        		+ "  </tr>"
        		+ " <tr>"
        		+ "    <th>Surcharge :</th>"
        		+ "    <th>"+surcharge+"</th>"
        		+ "  </tr>"
        		+ " <tr>"
        		+ "    <th>Health And Education Cess :</th>"
        		+ "    <th>"+cess+"</th>"
        		+ "  </tr>"
        		+ " <tr>"
        		+ "    <th>Net Payable Tax :</th>"
        		+ "    <th>"+netptax+"</th>"
        		+ "  </tr>"
        		+ " <tr>"
        		+ "    <th>Annual Take Home Salary :</th>"
        		+ "    <th>"+sal+"</th>"
        		+ "  </tr></br>"
        		+ " <tr>"
        		+ "    <th>Monthly Take Home Salary (First Month):</th>"
        		+ "    <th>"+monthlysal+"</th>"
        		+ "  </tr></br>"
        		+ "<footer><h5><a href= \"index.html\">Go To Home</a></h5></footer>"
                + "</body></html>");
                
                st.close();
            }
            
            p.close(); 
            con.close(); 
  

             
        } 
        catch (Exception e) { 
            e.printStackTrace(); 
        }
		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		//doGet(request, response);
	}

}
