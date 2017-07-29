<div id="small_map_div" class="col-md-3 col-sm-3 col-xs-12 side">
            <div class="l-side">
              <div id='cssmenu'>
              <ul class="list-unstyled">
                 <!-- <li> <a class="list" href="#">Live trackng
                 <i class="fa fa-angle-right pull-right"></i>  </a> </li>
                 
 -->              
 				  <li> <a class="list" href="adminDashboard"><i class="fa fa-backward"></i> Back to Super Admin
                 </a> </li>  
 				 <li> <a class="list" href="admin/schoolDashboard"><i class="fa fa-home"></i> Dashboard
                 </a> </li>   
                <li class=''><a href="admin/allRoute"><i class="fa fa-bus"></i> Bus Route
                    <!-- <i class="fa fa-angle-right pull-right"></i> --> </a>
                    <%-- <ul class="list-unstyled">
                      
                      <li><a href="admin/allRoute">All Route
                    </a></li>
                 	<% if((Integer)session.getAttribute("permission")==1) {%>
                    <!-- <li><a href="admin/addRoute">Add Route
                       </a></li> -->
                       <% } %>
                   </ul> --%>
                </li>
                  <li class=''><a href="admin/manageDrivers"><i class="fa fa-taxi"></i> Drivers 
                    <!-- <i class="fa fa-angle-right pull-right"></i> --> </a>
                    <ul class="list-unstyled">
                     <!-- <li><a href="admin/trackDrivers">Track Driver
                    </i> </a></li> -->
                     <!--  <li><a href="admin/manageDrivers">All Drivers
                    </i> </a></li> -->
                    <% if((Integer)session.getAttribute("permission")==1) {%>
                    <!-- <li><a href="admin/addDriver">Add Driver
                       </a></li> -->
                     <% } %>
                   </ul>
                </li>
                 <li class=''><a href="admin/manageVehicle"><i class="fa fa-car"></i> Vehicles 
                    <!-- <i class="fa fa-angle-right pull-right"></i>  --></a>
                   <%--  <ul class="list-unstyled">
                      
                      <li><a href="admin/manageVehicle">All Vehicles
                    </i> </a></li>
                    <% if((Integer)session.getAttribute("permission")==1) {%>
                    <!-- <li><a href="admin/addVehicle">Add Vehicles
                       </a></li> -->
                     <% } %>
                   </ul> --%>
                </li>
                <li class='has-sub'><a href="javascript:void(0);"><i class="fa fa-power-off"></i> Holiday 
				<i class="fa fa-angle-right pull-right"></i> </a>
				<ul class="list-unstyled">
				 <li><a href="admin/viewCalendar">Calendar
				</i> </a></li>
				  <li><a href="admin/manageHoliday">All Holidays
				</i> </a></li>
				<% if((Integer)session.getAttribute("permission")==1) {%>
				    <!-- <li><a href="admin/addHoliday">Add Holiday
				       </a></li> -->
				   <% } %>  
				   </ul>
				</li>
                  <li class='has-sub'><a href="admin/manageParents"><i class="fa fa-male"></i> Parents 
                    <!-- <i class="fa fa-angle-right pull-right"></i>  --></a>
                    <%-- <ul class="list-unstyled">
                      
                      <li><a href="admin/manageParents">All Parents
                    </i> </a></li>
                    <% if((Integer)session.getAttribute("permission")==1) {%>
                    <!-- <li><a href="admin/addParent">Add Parents
                       </a></li> -->
                     <% } %>
                   </ul> --%>
                </li>
             
				   <li class=''><a href="admin/manageStudents"><i class="fa fa-graduation-cap"></i> Students 
                    <!-- <i class="fa fa-angle-right pull-right"></i> --> </a>
                    <%-- <ul class="list-unstyled">
                      
                      <li><a href="admin/manageStudents">All Student
                    </i> </a></li>
                    <% if((Integer)session.getAttribute("permission")==1) {%>
                    <!-- <li><a href="admin/addStudent">Add Students
                       </a></li> -->
                      <% } %>
                   </ul> --%>
                </li>
                 
                 <li class='has-sub'><a href="admin/manageSchoolsAdmin"><i class="fa fa-users"></i> School Admins 
                    <!-- <i class="fa fa-angle-right pull-right"></i> --> </a>
                    <%-- <ul class="list-unstyled">
	                     <li><a href="admin/manageSchoolsAdmin">Manage School Admin</a></li>
	                     <% if((Integer)session.getAttribute("permission")==1) {%>
	                     <!-- <li><a href="admin/addSchoolAdmin">Add School Admin</a></li> -->
	                     <% } %>
                    </ul> --%>
                </li>
                  <li class=''><a href="admin/manageClasses"><i class="fa fa-users"></i> Manage Classes
                    <!-- <i class="fa fa-angle-right pull-right"></i> --> </a>
                    <ul class="list-unstyled">
	                    <!--  <li><a href="admin/manageClasses">All Classes</a></li> -->
	                     <% if((Integer)session.getAttribute("permission")==1) {%>
	                     <!-- <li><a href="admin/addClass">Add Class</a></li> -->
	                     <% } %>
	                 <!--     <li><a href="javascript:void(0);">Nationalities</a></li> -->
	                      
                    </ul>
                </li>
                 
                <!-- 
                <li> <a class="list" href="admin/enterToSchool"><i class="fa fa-share"></i> Go to School<i class="fa fa-angle-right pull-right"></i>  </a> </li>
              <li class='has-sub'><a href="javascript:void(0);">Staff 
                    <i class="fa fa-angle-right pull-right"></i> </a>
                    <ul class="list-unstyled">
                      
                      <li><a href="admin/manageStaff">All Staff
                    </i> </a></li>
                    <li><a href="admin/addStaff">Add Staff
                       </a></li>
                   
                   </ul>
                </li> -->
               
            
              
                <!-- <li> <a class="list" href="#">Profile setting
                 <i class="fa fa-angle-right pull-right"></i> </a> </li> -->
             <!--    <li> <a class="list" href="admin/manageSchools">School information 
                 <i class="fa fa-angle-right pull-right"></i></a> </li> -->
              <!--   <li> <a class="list" href="#"> History log 
               <i class="fa fa-angle-right pull-right"></i></a> </li> -->
             </ul>
               </li>
                
</ul>
</div>
              <!-- <div class="route-search">
                <h4>search by route id</h4>
                <form class="form-search" id="custom-search-form">
                <div class="input-group">
                    <input type="text" placeholder="Search" class="form-control">
                    <button class="btn-search" type="button"><i class="fa fa-search"></i></button>
                </div>
            </form>
              </div> -->
            </div> 
          </div>