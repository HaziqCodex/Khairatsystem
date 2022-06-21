package com.example.suraubookingsystem;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.http.Part;

@MultipartConfig
@WebServlet(name = "RoomServlet", value = "/RoomServlet")
public class RoomServlet extends HttpServlet {

	private RoomDao rm;
    public void init() {
        rm = new RoomDao();    
    }

    @Override
     protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
        response.setContentType("text/html");
        
        String action = request.getParameter("action");

        try {
            switch (action) {
                case "createroom":
                    createroom(request, response);
                    break;
                case "deleteroom":
                    deleteroom(request, response);
                    break;
                case "updateroom":
                    updateroom(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }


    }   
        
        /*######################################################( CREATE ROOM )#############################################################*/

        private void createroom(HttpServletRequest request, HttpServletResponse response)throws SQLException, IOException, ServletException {
        
        int roomid = Integer.parseInt(request.getParameter("roomid"));
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        request.setAttribute("thiss", "surau-ar-rahman.herokuapp.com");
        String appPath = getServletContext().getRealPath("");
        Part f = request.getPart("hPic");
        String host = request.getScheme()+ "://" + request.getAttribute("thiss")+"/";
        String imageFileName = f.getSubmittedFileName();
        String urlPathforDB=host + "pic/" + imageFileName;
        String savePath = appPath + "pic" + File.separator + imageFileName;
        new File(appPath + "pic").mkdir();
        f.write(savePath);

        try {

    		String roomname = request.getParameter("roomname"); 
    		int roomcapacity = Integer.parseInt(request.getParameter("roomcapacity"));
    		boolean roomstatus = Boolean.parseBoolean(request.getParameter("roomstatus"));
    		String soundsystem = request.getParameter("soundsystem");

            Room rooms = new Room();

            rooms.setRoomname(roomname);
            rooms.setRoomcapacity(roomcapacity);
            rooms.setRoomstatus(roomstatus);
            rooms.setSoundsystem(soundsystem); 
    		
    		rm.createroom(rooms,imageFileName,urlPathforDB);
            response.sendRedirect("displayRoomList.jsp");
		    out.println("<script type=\"text/javascript\">");
            out.println("alert('Your details succesfully create a room.');");
            out.println("location='displayRoomList.jsp';");
            out.println("</script>");

        } catch (Exception e) {
            e.printStackTrace();
        }

	}
        
        /*######################################################( UPDATE )#############################################################*/


        private void updateroom(HttpServletRequest request, HttpServletResponse response)throws SQLException, IOException, ServletException {

        	response.setContentType("text/html");
            PrintWriter out = response.getWriter();

            request.setAttribute("thiss", "surau-ar-rahman.herokuapp.com");
            String appPath = getServletContext().getRealPath("");
            Part f = request.getPart("hPic");
            String host = request.getScheme()+ "://" + request.getAttribute("thiss")+"/";
            String imageFileName = f.getSubmittedFileName();
            String urlPathforDB=host + "pic/" + imageFileName;
            String savePath = appPath + "pic" + File.separator + imageFileName;
            new File(appPath + "pic").mkdir();
            f.write(savePath);

            /*int roomid = Integer.parseInt(request.getParameter("roomid"));*/

              try{
                	int roomid = Integer.parseInt(request.getParameter("roomid"));
                	String roomname = request.getParameter("roomname"); 
            		int roomcapacity = Integer.parseInt(request.getParameter("roomcapacity"));
            		boolean roomstatus = Boolean.parseBoolean(request.getParameter("roomstatus"));
            		String soundsystem = request.getParameter("soundsystem");
            		
                    Room rooms = new Room();
                    
                    rooms.setRoomid(roomid);
                    rooms.setRoomname(roomname);
                    rooms.setRoomcapacity(roomcapacity);
                    rooms.setRoomstatus(roomstatus);
                    rooms.setSoundsystem(soundsystem); 

                    
                    rm.updateroom(rooms,imageFileName,urlPathforDB);
                    response.sendRedirect("displayRoomList.jsp");
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('Your room succesfully updated.');");
                    out.println("location='displayRoomList.jsp';");
                    out.println("</script>");
                
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }




        /*######################################################( DELETE )#############################################################*/

	    private void deleteroom(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
	        int roomid = Integer.parseInt(request.getParameter("roomid"));
	        rm.deleteroom(roomid);
	        response.sendRedirect("homepage.jsp");
	    }



}