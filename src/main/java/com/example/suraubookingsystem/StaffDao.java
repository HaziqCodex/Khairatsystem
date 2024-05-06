package com.example.suraubookingsystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import static java.lang.System.out;

public class StaffDao {

    String dbURL = "jdbc:postgresql://postgres:xVWAUtUsmPncHKGsYVYdMjcuCeYovkOw@viaduct.proxy.rlwy.net:18234/railway";
    String user = "postgres";
    String pass = "xVWAUtUsmPncHKGsYVYdMjcuCeYovkOw";
  
  protected Connection getConnection()
  {
    Connection connection = null;
    try {
        Class.forName("org.postgresql.Driver");
        connection = DriverManager.getConnection(dbURL, user, pass);

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
    PreparedStatement preparedStatement = connection.prepareStatement("insert into staff(staffrole,staffname,staffic,staffaddress,staffphone,staffemail,staffdateofbirth,staffusername,staffpassword) values(?,?,?,?,?,?,?,?,?)");)
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

        out.println(preparedStatement);
        preparedStatement.executeUpdate();
      } catch (SQLException e) {
          printSQLException(e);
      }
    }
  
    public boolean updateStaff(Staff staff) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement("UPDATE staff set staffrole=?,staffname=?,staffic=?,staffaddress=?,staffphone=?,staffemail=?,staffdateofbirth=?,staffusername=?,staffpassword=? where staffid=?");)
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
          statement.setInt(10, staff.getStaffid());

          rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }

    public boolean deleteStaff(int staffid) throws SQLException {
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