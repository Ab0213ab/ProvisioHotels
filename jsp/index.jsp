<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Provisio Home</title> 
<link rel="stylesheet" type="text/css" href="styles.css"/>
</head>

<body>

    <div class="container">    
        <div id="mySidebar" class="sidebar">
            <div class="closebtn" onclick="closeNav()"><strong>×</strong></div>
            <h2 id="nav-bar-top">Login/Register</h2>
            <div class="nav-item"><a href="#">Home</a></div>
            <div class="nav-item"><a href="#">About Us</div>
            <div class="nav-item"><a href="#">Locations</div>
            <div class="nav-item"><a href="/Provisio/src/main/webapp/ReservationBooking.jsp">Book Your Vacation</div>
            <div class="nav-item"><a href="#">Lookup Your Vacation</div>
            <div class="nav-item"><a href="#">Provisio Points</div>
        </div>

        <div id="main">
            <button class="openbtn" onclick="openNav()">
                <strong>&gt</strong>
            </button>
            <div class='top-bar'>
                <div class="header-content" id="brand"><h1>Provisio Hotels</h1></div>
                <div class="header-content" id="slogan"><h1>"Your Grand Getaway Awaits!"</h1></div>
            </div>
            <div id="welcome-message"><strong>Welcome to Provisio Hotels where you are sure to get exceptional 
            service in amazing locations for an unbelievable price.</strong></div>
            <div class="slideshow">
                <div class="myslides fade">
                    <div class="numbertext">1 / 3</div>
                    <img src="images/img1.png" style="width:100%">
                    <div class="text">Lake Garda, Italy</div>
                  </div>
                
                  <div class="myslides fade">
                    <div class="numbertext">2 / 3</div>
                    <img src="images/img2.png" style="width:100%;">
                    <div class="text">Marrakesh, Morocco</div>
                  </div>
                
                  <div class="myslides fade">
                    <div class="numbertext">3 / 3</div>
                    <img src="images/img3.png" style="width:100%;">
                    <div class="text">Rio de Janeiro, Brazil</div>
                  </div>
                
                  <!-- Next and previous buttons -->
                  <a class="prev" onclick="plusSlides(-1)">&#10094;</a>
                  <a class="next" onclick="plusSlides(1)">&#10095;</a>
                  <br>

                  <div style="text-align:center">
                    <span class="dot" onclick="currentSlide(1)"></span>
                    <span class="dot" onclick="currentSlide(2)"></span>
                    <span class="dot" onclick="currentSlide(3)"></span>
                  </div>
            </div>
        </div>
             <div id="quote"><h3>“Travel is the only<h3>
                            <h3>thing you buy that</h3> 
                            <h3>makes you richer.”</h3> 
                            <h3>- Anonymous</h3>
            </div>
        
    </div>

    <!-- Functions for collapsing side nav bar -->
    <script>
        function openNav() {
            document.getElementById("mySidebar").style.width = "250px";
            document.getElementById("main").style.marginLeft = "250px";
        }
        
        function closeNav() {
            document.getElementById("mySidebar").style.width = "0";
            document.getElementById("main").style.marginLeft= "0";
        }
        openNav();


        //slideshow
        let slideshowIndex = 1;
        showSlides(slideshowIndex);

        //next/prev controls
        function plusSlides(n) {
            showSlides(slideshowIndex += n);
        }

        //thumbnail image control
        function currentSlide(n) {
            showSlides(slideshowIndex = n);
        }

        function showSlides(n) {
            let i;
            let slides = document.getElementsByClassName("myslides");

            let dots = document.getElementsByClassName("dot");

            if(n > slides.length) {slideshowIndex = 1}
            if(n < 1) {slideshowIndex = slides.length}
            for(i = 0; i < slides.length; i++) {
                slides[i].style.display = "none";
            }
            for(i = 0; i < dots.length; i++) {
                dots[i].className = dots[i].className.replace(" active", "");
            }
            slides[slideshowIndex-1].style.display = "block";
            dots[slideshowIndex-1].className += " active";
        }
    </script>
</body>
</html>
