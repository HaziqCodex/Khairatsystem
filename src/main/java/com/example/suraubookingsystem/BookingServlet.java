package com.example.suraubookingsystem;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.sql.Date;
import java.sql.SQLException;
/**
 * Servlet implementation class BookingServlet
 */
@WebServlet(name = "BookingServlet", value = "/BookingServlet")
public class BookingServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private BookingDao bd;

    public void init() {
        bd = new BookingDao();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        String action = request.getParameter("action");

        try {
            switch (action) {
                case "staffcreatebooking":
                    staffcreatebooking(request, response);
                    break;
                case "staffcancelbooking":
                    staffcancelbooking(request, response);
                    break;
                case "staffapprovedbooking":
                    staffapprovedbooking(request, response);
                    break;
                case "staffrejectbooking":
                    staffrejectbooking(request, response);
                    break;

            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /*######################################################( staffCreateBooking )#############################################################*/

    private void staffcreatebooking(HttpServletRequest request, HttpServletResponse response)throws Exception {

        HttpSession session = request.getSession();

        int staffid = Integer.parseInt(request.getParameter("staffid"));
        int spaceid = Integer.parseInt(request.getParameter("spaceid"));
        Date eventdate = Date.valueOf(request.getParameter("eventdate"));
        String bookingdescription = request.getParameter("bookingdescription");


        Staff staff = new Staff();
        Space space = new Space();
        Booking booking = new Booking();

        staff.setStaffid(staffid);
        space.setSpaceid(spaceid);
        booking.setEventdate(eventdate);
        booking.setBookingdescription(bookingdescription);

        bd.staffCreateBooking(staff, space, booking);
        response.sendRedirect("staffViewBooking.jsp");
    }
    /*######################################################( CANCEL )#############################################################*/

    private void staffcancelbooking(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int bookingid = Integer.parseInt(request.getParameter("bookingid"));
        bd.staffcancelbooking(bookingid);
        response.sendRedirect("staffViewBooking.jsp");
    }
    /*######################################################( STAFF APPROVE BOOKING )#############################################################*/

    private void staffapprovedbooking(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        int bookingid = Integer.parseInt(request.getParameter("bookingid"));
        int spaceid = Integer.parseInt(request.getParameter("spaceid"));
        bd.staffapprovedbooking(bookingid,spaceid);


       /* out.println("<script type=\"text/javascript\">");
        out.println("alert('You succesfully approved this booking form!');");
        out.println("location='landlord-displayBookingList.jsp';");
        out.println("</script>");*/
    }
    /*######################################################( STAFF REJECT)#############################################################*/

    private void staffrejectbooking(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        int bookingid = Integer.parseInt(request.getParameter("bookingid"));
        bd.staffrejectbooking(bookingid);

        /*out.println("<script type=\"text/javascript\">");
        out.println("alert('You succesfully reject this agreement and deposit!');");
        out.println("location='landlord-displayBookingList.jsp';");
        out.println("</script>");*/
    }
}