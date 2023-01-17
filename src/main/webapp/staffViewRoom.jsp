<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
    <title> View Room </title>
    <link rel="stylesheet" href="spaceHandler.css">
    <link href='https://unpkg.com/boxicons@2.0.7/css/boxicons.min.css' rel='stylesheet'>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.2/css/all.min.css"/>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
     <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
  <%
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    response.setHeader("Pragma", "no-cache");
    response.setHeader("Expires", "0");

    if(session.getAttribute("staffid")==null)
        response.sendRedirect("index.jsp");
  %>
<sql:setDataSource
        var="ic"
        driver="org.postgresql.Driver"
        url="jdbc:postgresql://containers-us-west-141.railway.app:7894/railway"
        user="postgres"
        password="ETymgiO6aGYvyXf5fkei"/>

<sql:query dataSource="${ic}" var="oc">
  SELECT * from room
</sql:query>

  <div class="sidebar">
    <div class="logo-details">
      <img src="logoWhite.png">
    </div>
      <ul class="nav-links">
        <li>
          <a class="main-menu" href="homepageStaff.jsp">
            <i class='bx bx-home'></i>
            <span class="links_name">LAMAN UTAMA</span>
          </a>
        </li>

        <li>
          <a class="main-menu" href="#">
            <i class='bx bx-box' ></i>
            <span class="links_name">RUANG</span>
          </a>
          <ul class="sub-menu">
            <li><a href="staffViewSpace.jsp">DEWAN</a></li>
            <li><a href="staffViewRoom.jsp">BILIK</a></li>
          </ul>
        </li>

        <li>
          <a class="main-menu" href="#">
            <i class='bx bx-bookmark'></i>
            <span class="links_name">TEMPAHAN</span>
          </a>
          <ul class="sub-menu">

            <li><a href="staffApproveBooking.jsp">SAHKAN TEMPAHAN</a></li>
          </ul>
        </li>

       <li>
          <a class="main-menu" href="staffViewAccount.jsp">
            <i class='bx bx-user'></i>
            <span class="links_name">AKAUN</span>
          </a>
        </li>
        
        <li class="log_out">
          <a class="main-menu" href="index.jsp">
            <i class='bx bx-log-out'></i>
            <span class="links_name">LOG KELUAR</span>
          </a>
        </li>
     </ul>
  </div>

<section class="home-section">
    <nav>
      <div class="sidebar-button">
        <span class="dashboard">SURAU AR-RAHMAN BANDAR PUCHONG JAYA</span>
      </div>
        <div class="media_icons">
          <a href="https://www.facebook.com/sarpuchongjaya/"><i class="fab fa-facebook-f"></i></a>
          <a href="https://www.instagram.com.surau_ar_rahman_pchg"><i class="fab fa-instagram"></i></a>
        </div>
    </nav>
  <div class="home-content">
    <div class="container">
    <a href="staffCreateRoom.jsp"><button class="add">TAMBAH BILIK</button></a>
      <header class="main_title" style="font-size: xx-large">SENARAI BILIK</header>
      	  <c:forEach var="room" items="${oc.rows}">
      	  <form action="" method="post">
      	  <input type="hidden" name="roomid" value="${room.roomid}">
          <section class="wrapper top">
                <div class="containerRoom">
                 <div class="text">
                    <h2>${room.roomname}</h2>
                      <div class="content">
                        <span>Kapasiti:</span>
                        <span>${room.roomcapacity}</span>
                        <span>Status:</span>
                        <span>${room.roomstatus}</span>
                        <span>Sistem Bunyi:</span>
                        <span>${room.roomsoundsystem}</span>
                        <span>Kuantiti Meja:</span>
                        <span>${room.tablequantity}</span>
                        <span>Kuantity Kerusi:</span>
                        <span>${room.chairquantity}</span>
                      </div>
                      <input type="hidden" name="action" value="deleteRoom">
                      <button class="update" onclick="form.action='staffUpdateRoom.jsp'">KEMASKINI</button>
                      <button class="delete" formaction="RoomServlet"
                      onclick="return confirm('Pasti padam <c:out value="${room.roomname}"/> ?');">PADAM</button>
                 </div>
                </div>
          </section>
  		  </form>
  		  </c:forEach>
  	</div>
  </div>
</section>
</body>
</html>