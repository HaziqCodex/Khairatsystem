<!DOCTYPE html>
<html lang="en" dir="ltr">
<head>
	<meta charset="UTF-8">
	<title>Create Room Form</title>
	<link rel="stylesheet" href="createspace.css">
	<link href='https://unpkg.com/boxicons@2.0.7/css/boxicons.min.css'
		rel='stylesheet'>
	<link rel="stylesheet"
		href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.2/css/all.min.css" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
<sql:setDataSource
        var="ic"
        driver="org.postgresql.Driver"
        url="jdbc:postgresql://containers-us-west-141.railway.app:7894/railway"
        user="postgres"
        password="ETymgiO6aGYvyXf5fkei"/>
<sql:query dataSource="${ic}" var="oc">
    SELECT roomid,roomname from room
</sql:query>
<%
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    response.setHeader("Pragma", "no-cache");
    response.setHeader("Expires", "0");

    if(session.getAttribute("staffid")==null)
        response.sendRedirect("index.jsp");
  %>
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
					<i class='bx bx-box'></i>
					<span class="links_name min">RUANG</span>
				</a>
				<ul class="sub-menu">
		            <li><a href="staffViewSpace.jsp">DEWAN</a></li>
		            <li><a href="staffViewRoom.jsp">BILIK</a></li>
	            </ul>
			</li>

			<li>
				<a class="main-menu" href="#"> 
					<i class='bx bx-bookmark'></i>
					<span class="links_name min">TEMPAHAN</span>
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
				<a href="https://www.facebook.com/sarpuchongjaya/"><i
					class="fab fa-facebook-f"></i></a> <a
					href="https://www.instagram.com.surau_ar_rahman_pchg"><i
					class="fab fa-instagram"></i></a>
			</div>
		</nav>

		<div class="home-content">
			<div class="container">
				<header class="main_title">PENAMBAHAN BILIK</header>
				<form method="post">
					<div class="form first">
						<div class="details room">
							<span class="title">INFORMASI BILIK</span>
							<div class="fields">
								<div class="input-field input-box">
									<label class="details">Nama</label> 
									<input type="text" name="roomname" required>
								</div>
								<div class="input-field input-box">
									<label class="details">Kapasiti</label>
									<input type="text" name="roomcapacity" pattern="[0-9]+" title="Kapasiti hanya nombor sahaja" required>
								</div>
								<div class="input-field input-box">
									<label class="details">Status</label>
									<select	name="roomstatus" required>
										<option disabled selected>Pilih Status</option>
										<option value="Boleh Digunakan">Boleh Digunakan</option>
										<option value="Sedang Diselenggara">Sedang Diselenggara</option>
									</select>
								</div>
								<div class="input-field input-box">
									<label class="details">Sistem Bunyi</label>
									<input type="text" name="roomsoundsystem" required>
								</div>
								<div class="input-field input-box">
									<label class="details">Kuantiti Meja</label>
									<input type="text" name="tablequantity" pattern="[0-9]+" title="Kuantiti meja hanya nombor sahaja" required>
								</div>
								
								<div class="input-field input-box">
									<label class="details">Kuantiti Kerusi</label>
									<input type="text" name="chairquantity" pattern="[0-9]+" title="Kuantiti kerusi hanya nombor sahaja" required>
								</div>
							</div>
							<input type="hidden" name="action" value="createRoom">
							<div class="button staff">
								<input type="submit" value="TAMBAH" formaction="RoomServlet">
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</section>
</body>
</html>

