package com.example.suraubookingsystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import static java.lang.System.out;

public class StaffDao {
  
  String dbUrl = "jdbc:postgresql://ec2-52-72-56-59.compute-1.amazonaws.com";
  String user = "dnzxqagexabepj";
  String pass = "edb330e6fe55ed3bb6d1ee1eb3c1f995e6b205eb5d464bee634abc3345b2d294";
  
  protected Connection getConnection()
  {
    Connection connection = null;
    try {
      Class.forName("org.postgresql.Driver");
      connection = DriverManager.getConnection(dbUrl, user, pass);
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (ClassNotFoundException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
    return connection;
  }
  
  
  public void signupStaff (Staff staff) throws SQLException {
    
    // try-with-resource statement will auto close the connection.
    try (Connection connection = getConnection();
    PreparedStatement preparedStatement = connection.prepareStatement("insert into staff(staffrole,staffname,staffic,staffaddress,staffphone,staffemail,staffdateofbirth,staffusername,staffpassword,supervisorid) values(?,?,?,?,?,?,?,?,?,?)");)
    {
    	  preparedStatement.setString(1, staff.getStaffrole());
    	  preparedStatement.setString(2, staff.getStaffname());
        preparedStatement.setString(3, staff.getStaffic());
        preparedStatement.setString(4, staff.getStaffaddress());
        preparedStatement.setString(5, staff.getStaffphone());
        preparedStatement.setString(6, staff.getStaffemail());
        preparedStatement.setDate(7, staff.getStaffdateofbirth());
        preparedStatement.setString(8, staff.getStaffusername());
        preparedStatement.setString(9, staff.getStaffpassword());
        preparedStatement.setLong(10, staff.getSupervisorid());

        out.println(preparedStatement);
        preparedStatement.executeUpdate();
      } catch (SQLException e) {
          printSQLException(e);
      }
    }
  
    public boolean updateStaff(Staff staff) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement("UPDATE staff set staffrole=?,staffname=?,staffic=?,staffaddress=?,staffphone=?,staffemail=?,staffdateofbirth=?,staffusername=?,staffpassword=?,supervisorid=? where staffid=?");)
        {
          statement.setString(1, staff.getStaffrole());
          statement.setString(2, staff.getStaffname());
          statement.setString(3, staff.getStaffic());
          statement.setString(4, staff.getStaffaddress());
          statement.setString(5, staff.getStaffphone());
          statement.setString(6, staff.getStaffemail());
          statement.setDate(7, staff.getStaffdateofbirth());
          statement.setString(8, staff.getStaffusername());
          statement.setString(9, staff.getStaffpassword());
          statement.setLong(10, staff.getSupervisorid());
          statement.setInt(11, staff.getStaffid());

          rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }

    public boolean deleteUser(int staffid) throws SQLException {
            boolean rowDeleted;
            try (Connection connection = getConnection();
                 PreparedStatement statement = connection.prepareStatement("delete from staff where staffid=?");) {
                statement.setInt(1, staffid);
                rowDeleted = statement.executeUpdate() > 0;
            }
            return rowDeleted;
        }
    
    
    
    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }  
	    
	}