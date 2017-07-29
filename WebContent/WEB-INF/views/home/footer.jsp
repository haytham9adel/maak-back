<style>
span#sub-msg-prt {
    color: #fff;
    font-size: 14px;
    padding: 3px 1px;
}
</style>
     <footer>
        <div class="container">
          <div class="col-md-5 col-sm-5 col-xs-12">
          	<div class="headline"><h4 style="border:0;">&nbsp;</h2></div>
             <div class="foot-logo sep-verti"> 
               <img class="img-responsive" src="resources/front/Images/footer-logo.jpg">
             </div>
           </div> 
           
          <div class="col-md-7 col-sm-7 col-xs-12"> 
            <div class="col-md-4 col-sm-4 col-xs-12">
              <div class="headline"><h4>Quick Links</h2></div>
               <ul class="list-unstyled">
                 <li><a href="index.html"> Home </a> </li>
                 <li><a href="about.html"> About us </a> </li>
                 <li><a href="features.html"> Features </a> </li>
                 <li><a href="how_it_works.html"> How it works </a> </li> 
                 <li><a href="faq.html">FAQ</a> </li>
                 <li><a href="contact.html"> Contact us </a> </li> 
                 
               </ul> 
            </div>
            <div class="col-md-4 col-sm-4 col-xs-12">
             <!--  <div class="headline"><h4>Stuff</h2></div>
               <ul class="list-unstyled">
                 <li><a href="#"> Privacy </a> </li>
                 <li><a href="#"> Terms </a> </li>
                 <li><a href="#"> Advertise </a> </li>
               </ul>  -->
            </div>
            
            <div class="col-md-5 col-sm-5 col-xs-12 pull-right">
              <div class="headline"><h4>Subscribe to our newsletter</h2></div>
              <span id="sub-msg-prt"></span>
                <div class="subscribe-block">
                   <div class="input-group">
                  <input type="text" id="sub_email" placeholder="E-mail" class="form-control">
                     <span class="input-group-btn">
                        <button id="submit-form-subscriber" type="button" class="btn-u">
                        <i class="fa fa-angle-right"></i></button>
                           </span>
                 </div> 
                </div>
               <ul class="list-inline social_links">
               	 <li><a target="_blank" href="${fb_link}"> <i class="fa fa-facebook"></i> </a> </li>
                 <li><a target="_blank" href="${tw_link}"> <i class="fa fa-twitter"></i> </a> </li>
                 <li><a target="_blank" href="${li_link}"> <i class="fa fa-linkedin"></i></a> </li>
               </ul>
               <ul class="list-inline app_links">
                <li> 
                  <h3>Driver<br />App</h3>
                  <div class="app_usr_img" ><img src="resources/front/Images/driver.png" class="img-responsive" /></div>
                  <div class="links-applications">
                     <a target="_blank" alt="Android Driver App" href="${android_driver_app}"> <i class="fa fa-android" aria-hidden="true"></i> </a>
                  </div>
                </li>
                <li> 
                  <h3>School Admin App</h3>
                  <div class="app_usr_img"><img src="resources/front/Images/admin.png" class="img-responsive" /></div>
                  <div class="links-applications">
                    <a target="_blank" alt="Android School Admin App" href="${android_school_app}"> <i class="fa fa-android" aria-hidden="true"></i> </a> 
                    <a target="_blank" alt="IOS School Admin App" href="${ios_school_app}"> <i class="fa fa-apple" aria-hidden="true"></i> </a>
                  </div>
                </li>
                <li>
                  <h3>Parent<br />App</h3>
                  <div class="app_usr_img"><img src="resources/front/Images/parent.png" class="img-responsive" /></div>
                  <div class="links-applications">
                    <a target="_blank" alt="Android parents App" href="${android_parent_app}"> <i class="fa fa-android" aria-hidden="true"></i> </a> 
                    <a target="_blank" alt="IOS Parents App" href="${ios_parent_app}"> <i class="fa fa-apple" aria-hidden="true"></i> </a>
                  </div>
                </li>
               </ul>
               <script type="text/javascript">
                $(function () {
                  $('[data-toggle="tooltip"]').tooltip()
                })
               </script>
               <!--<ul class="list-inline social_links">
               	 <li><a target="_blank" alt="Android parents App" href="${android_parent_app}"> <i class="fa fa-play"></i> </a> </li>
                 <li><a target="_blank" alt="IOS Parents App" href="${ios_parent_app}"> <i class="fa fa-apple"></i> </a> </li>

                 <li><a target="_blank" alt="Android Driver App" href="${android_driver_app}"> <i class="fa fa-play"></i></a> </li>

                 <li><a target="_blank" alt="Android School Admin App" href="${android_school_app}"> <i class="fa fa-play"></i></a> </li>
                 <li><a target="_blank" alt="IOS School Admin App" href="${ios_school_app}"> <i class="fa fa-apple"></i></a> </li>
               </ul> -->
                
            </div>
          </div> 
        </div>
        <div class="clearfix"> </div>
        <div class="copyright">
          <div class="container"> Copyright © 2017 M3aak school bus tracking. 
          All rights reserved </div>
        </div>
     </footer>
     
     </div><!--=========wrapper-div===========--->
 <script>
 	$(document).ready(function(){
 			$("#submit-form-subscriber").click(function(){
 				$.ajax({
 					url:"subscribe.html",
 					data:{email:$("#sub_email").val()},
 					type:"post",
 					success:function(response)
 					{
 						var obj=$.parseJSON(response)
 						$("#sub-msg-prt").html(obj.msg);
 					}
 					
 				});
 			});
 	});
 	
 </script>    
     
     
     
<body>
</body>
</html>
