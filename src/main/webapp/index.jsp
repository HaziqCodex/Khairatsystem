<!DOCTYPE html>
<html lang="en" dir="ltr">
  <head>
    <meta charset="UTF-8">
    <title> The Login Account Form </title>
    <link rel="stylesheet" href="loginAccount.css">
    <link href='https://unpkg.com/boxicons@2.0.7/css/boxicons.min.css' rel='stylesheet'>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.2/css/all.min.css"/>
     <meta name="viewport" content="width=device-width, initial-scale=1.0">
   </head>

<body>
  <div class="sidebar">
    <div class="logo-details">
      <img src="logoWhite.png">
    </div>
  </div>
  <section class="home-section">
    <nav>
      <div class="sidebar-button">
        <span class="dashboard">SURAU AR-RAHMAN BANDAR PUCHONG JAYA</span>
      </div>
      <div class="media_icons">
          <a href="https://www.facebook.com/sarpuchongjaya/"><i class="fab fa-facebook-f"></i></a>
          <a href="https://www.instagram.com/surau_ar_rahman_pchg/"><i class="fab fa-instagram"></i></a>
        </div>
    </nav>
    <div class="home-content">
        <div class="hero">
          <div class="form-box">
          <div class="button-box">
            <div id="btn"></div>
            <button type="button" class="toggle-btn" onclick="staffLogin()">STAF</button>
            <button type="button" class="toggle-btn" onclick="applicantLogin()">PEMOHON</button>
          </div>
          <div class="logo">
            <img src="logoBlack.png">
          </div>
          <form id="staff" class="input-group" action="StaffServlet" method="POST">
            <input type="text" class="input-field" name="staffusername" placeholder="Nombor Staf" required>
            <input type="password" class="input-field" name="staffpassword" placeholder="Kata Laluan" required>
            <div class="input-box button">

              <input type="hidden" name="action" value="loginStaff">

              <input type="submit" name="submit" value="Log Masuk">
            </div>
            <div class="login-signup">
                <span class="text">Tiada Akaun?
                  <a href="staffCreateAccount.jsp" class="text signup-link">Daftar Segera!</a>
                </span>
            </div>
          </form>
          <form id="applicant" class="input-group" action="ApplicantServlet" method="POST">
            <input type="text" class="input-field" name="applicantusername"  placeholder="Nama Pengguna" required>
            <input type="password" class="input-field" name="applicantpassword" placeholder="Kata Laluan" required>
            <div class="input-box button">

               <input type="hidden" name="action" value="loginApplicant">

              <input type="submit" name="submit" value="Log Masuk">
            </div>
            <div class="login-signup">
              <span class="text">Tiada Akaun?
                <a href="applicantCreateAccount.jsp" class="text signup-link">Daftar Segera!</a>
              </span>
            </div>
          </form>
        </div>
      </div>
    </div>
  </section>

  <script>

    var x = document.getElementById("staff");
    var y = document.getElementById("applicant");
    var z = document.getElementById("btn");

    function applicantLogin(){
      x.style.left = "-400px";
      y.style.left = "50px";
      z.style.left = "110px";
    }

    function staffLogin(){
      x.style.left = "50px";
      y.style.left = "450px";
      z.style.left = "0";
    }
 </script>

</body>
</html>

