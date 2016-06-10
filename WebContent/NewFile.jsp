<%-- 
    Document   : validate
    Created on : 28 Feb, 2015, 8:50:26 AM
    Author     : Lahaul Seth
--%>

<%@ page import ="java.sql.*" %>
<%

	String username = "   ";
    try{
        username = request.getParameter("username");   
        String password = request.getParameter("password");
    	String url = "jdbc:db2://192.86.32.54:5040/DALLASB:retrieveMessagesFromServerOnGetMessage=true;emulateParameterMetaDataForZCalls=1;";
        Class.forName("com.ibm.db2.jcc.DB2Driver");  // DB2 database connection
        Connection conn = DriverManager.getConnection(url, "DTU22", "FAGP2016");    
        PreparedStatement pst = conn.prepareStatement
        		("SELECT client_id, client_password FROM client WHERE client_id=? and client_password=?");
        pst.setString(1, username);
        pst.setString(2, password);
        ResultSet rs = pst.executeQuery();                        
        if(rs.next())           
           out.println("Valid login credentials");        
        else
           out.println("Invalid login credentials");            
   }
   catch(Exception e){       
       out.println("Something went wrong !! Please try again");       
   }      
%>