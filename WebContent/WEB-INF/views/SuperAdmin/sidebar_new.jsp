<div class="col-md-3 col-sm-3 col-xs-12 side">
            <div class="l-side">
              <div id='cssmenu'>
         
              <ul class="list-unstyled">

                 <li> <a class="list" href="adminDashboard"><i class="fa fa-home"></i> Home
                 </a> </li>
                <!--  <li class='has-sub'><a href="javascript:void(0);"><i class="fa fa-building "></i> School 
                    <i class="fa fa-angle-right pull-right"></i> </a>
                    <ul class="list-unstyled">
                      <li><a href="admin/addSchool/">Add  School
                       </a></li>
                      <li><a href="admin/manageSchools">View School
                    </i> </a></li>
                   </ul>
                </li> -->
                 <% if((Integer)session.getAttribute("permission")==1) {%>
                 <li><a href="admin/addSchool/"><i class="icon icon-school-318-23393 "></i> Add New School
                       </a></li>
                     <% } %>
                      <li><a href="admin/manageSchools"><i class="fa fa-eye"></i> View All Schools
                    </i> </a></li>
                    
                
               <li class='has-sub'><a href="javascript:void(0);"><i class="fa fa-beer"></i> Holiday 
				<i class="fa fa-angle-right pull-right"></i> </a>
				<ul class="list-unstyled">
				 <li><a href="admin/viewAdminCalendar">Calendar</i> </a></li>
				  <li><a href="admin/manageAdminHoliday">All Holidays
				</i> </a></li>
				<% if((Integer)session.getAttribute("permission")==1) {%>
				    <!-- <li><a href="admin/addAdminHoliday">Add Holiday
				       </a></li> -->
				   <% } %>  
				   </ul>
				</li>
                    
                    
                <!--  <li class='has-sub'><a href="javascript:void(0);">Students 
                    <i class="fa fa-angle-right pull-right"></i> </a>
                    <ul class="list-unstyled">
                      
                      <li><a href="admin/manageStudents">All Student
                    </i> </a></li>
                    <li><a href="admin/addStudent">Add Students
                       </a></li>
                      
                   </ul>
                </li> -->
                <li> <a class="list" href="admin/enterToSchool"><i class="fa fa-building-o"></i> Manage Schools  </a> </li>
              
                <li class='has-sub'><a href="javascript:void(0);"><i class="fa fa-globe"></i> Manage Website Content 
                    <i class="fa fa-angle-right pull-right"></i> </a>
                    <ul class="list-unstyled">
                      
                      <li  class='has-sub'>
                      <a href="javascript:void(0);">HomePage
                       <i class="fa fa-angle-right pull-right"></i>
                       </a>
                        <ul class="list-unstyled">
                        <li><a href="admin/manage_slider">Slider</a></li>
                        <!-- <li><a href="admin/homepage">Home</a></li> -->
                      	<li><a href="admin/manage_home_section_two">Section-2</a></li>
                      	<li><a href="admin/manage_home_section_three">Section-3</a></li>
                      	<li><a href="admin/manage_home_section_four">Section-4</a></li>
						<li><a href="admin/manage_home_section_five">Section-5</a></li>
                        <li><a href="admin/manage_video">Video</a></li> 
                        
                    </ul>
                    
                    
                    
                    </li>
                     <li><a href="admin/aboutus">About us
                    </a></li>
                      <li><a href="admin/manageFeatures">Features
                    </a></li>
                      <li><a href="admin/manageFaq">FAQ
                    </a></li>
                    <li  class='has-sub'>
                      <a href="javascript:void(0);">Contact Page
                       <i class="fa fa-angle-right pull-right"></i>
                       </a>
                        <ul class="list-unstyled">
                        <li><a href="admin/manage_contact_category">Category</a></li>
                         <li><a href="admin/manage_contact_page">Contact Page</a></li>
                        </ul>
                        
                     </li> 
                     </li>
                      <li><a href="admin/social_link">Social link</a></li> 
                      <li><a href="admin/play_icon">Play Icon</a></li>  
                   </ul>
                </li>
                <li class=''><a href="admin/manageSuperAdmin"><i class="fa fa-user-secret"></i> Super Admin 
                    <!-- <i class="fa fa-angle-right pull-right"></i> --> </a>
                    <%-- <ul class="list-unstyled">
                     <li><a href="admin/manageSuperAdmin">Manage Super Admin
                    </a></li>
                       <% if((Integer)session.getAttribute("permission")==1) {%>
                      <!-- <li><a href="admin/addSuperAdmin">Add Super Admin
                       </a></li> -->
                    	<% } %> 
                   </ul> --%>
                </li>
                <li class='has-sub'><a href="javascript:void(0);"><i class="fa fa-plane"></i> Manage 
                    <i class="fa fa-angle-right pull-right"></i> </a>
                    <ul class="list-unstyled">
                     <li><a href="admin/manageCountry">All Country
                    </a></li>
                      <% if((Integer)session.getAttribute("permission")==1) {%>
                      <!-- <li><a href="admin/addCountry">Add New Country
                       </a></li> -->
                       <% } %> 
                     <li><a href="admin/manageTown">All Town
                    </a></li>
                       <% if((Integer)session.getAttribute("permission")==1) {%>
                        <!-- <li><a href="admin/addTown">Add New Town
                       </a></li> -->
                    	<% } %> 
                    	 <li><a href="admin/manageNationlities">Nationalities</a></li>
                    	 <li><a href="admin/manageRelationship">Relationship</a></li>
                    	 <li><a href="admin/manageSubscrubers">Subscribers</a></li>
                    	 <li><a href="admin/adminSetup">Email Setup</a></li>
                   </ul>
                </li>
                <li> <a class="list" href="admin/chattingSocket" target="_blank"><i class="fa fa-envelope"></i> Message </a> </li>
              	<!-- <li> <a class="list" href="admin/chattingSocket"><i class="fa fa-envelope"></i> Message Socket</a> </li> --> 
                 <!-- <li class='has-sub'><a href="javascript:void(0);"><i class="fa fa-flag"></i> Manage Nationalities 
                    <i class="fa fa-angle-right pull-right"></i> </a>
                    <ul class="list-unstyled">
                     <li><a href="admin/manageNationlities">Nationalities
                    </a></li>
                     <li><a href="admin/addNationlities">Add Nationality
                    </a></li>
                 </ul>
                 
                 </li>  -->  
                
                
              <!--   <li> <a class="list" href="admin/manageNationlities"><i class="fa fa-flag" aria-hidden="true"></i> Nationalities </a> </li>
               -->  
                 <!--
                <li class='has-sub'><a href="javascript:void(0);">Parents 
                    <i class="fa fa-angle-right pull-right"></i> </a>
                    <ul class="list-unstyled">
                      
                      <li><a href="admin/manageParents">All Parents
                    </i> </a></li>
                    <li><a href="admin/addParent">Add Parents
                       </a></li>
                     
                   </ul>
                </li>
                <li class='has-sub'><a href="javascript:void(0);">Drivers 
                    <i class="fa fa-angle-right pull-right"></i> </a>
                    <ul class="list-unstyled">
                      
                      <li><a href="admin/manageDrivers">All Driver
                    </i> </a></li>
                    <li><a href="admin/addDriver">Add Driver
                       </a></li>
                     
                   </ul>
                </li>
                <li class='has-sub'><a href="javascript:void(0);">Vehicles 
                    <i class="fa fa-angle-right pull-right"></i> </a>
                    <ul class="list-unstyled">
                      
                      <li><a href="admin/manageVehicle">All Vehicles
                    </i> </a></li>
                    <li><a href="admin/addVehicle">Add Vehicle
                       </a></li>
                     
                   </ul>
                </li>
                <li> <a class="list" href="#">Profile setting
                 <i class="fa fa-angle-right pull-right"></i> </a> </li>
                <li> <a class="list" href="admin/manageSchools">School information 
                 <i class="fa fa-angle-right pull-right"></i></a> </li>
                <li> <a class="list" href="#"> History log 
               <i class="fa fa-angle-right pull-right"></i></a> </li>
             </ul>
               </li> -->
                
</ul>
</div><!-- 
              <div class="route-search">
                <h4>search by route id</h4>
                <form class="form-search" id="custom-search-form">
                <div class="input-group">
                    <input type="text" placeholder="Search" class="form-control">
                    <button class="btn-search" type="submit"><i class="fa fa-search"></i></button>
                </div>
            </form>
              </div> -->
            </div> 
          </div>
          <script>
          $(document).ready(function(){
        	  $('.has-sub').click(function(){
        		 var element = $(this);
        		 if(element.hasClass("open")){
        			 element.find(".fa-angle-right").removeClass("fa-angle-right").addClass("fa-angle-down");
        		 }else{
        			 element.find(".fa-angle-down").removeClass("fa-angle-down").addClass("fa-angle-right");
        		 }
        	  });
          });
          </script>