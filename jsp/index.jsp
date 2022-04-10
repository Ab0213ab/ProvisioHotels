<%@ 
    page 
    language="java" 
    contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"
%>
<%@ 
    taglib 
    uri="http://java.sun.com/jsp/jstl/core" 
    prefix="c"
%>

<%@
    taglib 
    prefix="t" 
    tagdir="/WEB-INF/tags" 
%>

<t:header>
    <jsp:attribute name="title">
        Provisio Home
    </jsp:attribute>
    <jsp:attribute name="styles">
        <!-- Any styles, cdns, or scripts go here -->
        <link rel="stylesheet" type="text/css" href="/Provisio/jsp/index.css">
    </jsp:attribute>
    <jsp:attribute name="header_title">
        Provisio Hotels
    </jsp:attribute>
    <jsp:attribute name="page_title">
        "Your Grand Getaway Awaits!"
    </jsp:attribute>
    <jsp:attribute name="body">
        <div id="welcome-message"><strong>Welcome to Provisio Hotels where you are sure to get exceptional 
            service in amazing locations for an unbelievable price.</strong></div>

        <div class="flex-div">
            <div class="flex-item-lg">
                <div class="slideshow">
                    <div class="myslides fade">
                        <div class="numbertext">1 / 3</div>
                        <img src="/Provisio/images/img1.png" style="width:100%">
                        <div class="text">Lake Garda, Italy</div>
                      </div>
                    
                      <div class="myslides fade">
                        <div class="numbertext">2 / 3</div>
                        <img src="/Provisio/images/img2.png" style="width:100%;">
                        <div class="text">Marrakesh, Morocco</div>
                      </div>
                    
                      <div class="myslides fade">
                        <div class="numbertext">3 / 3</div>
                        <img src="/Provisio/images/img3.png" style="width:100%;">
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

            <div id="quote" class="flex-item-sm">
                <h3>"Travel is the only</h3>
                <h3>thing you buy that</h3> 
                <h3>makes you richer."</h3> 
                <h3>- Anonymous</h3>
            </div>
        </div>

        <script type="text/javascript">
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
    </jsp:attribute>
</t:header>