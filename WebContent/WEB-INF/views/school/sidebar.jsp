<div id="small_map_div" class="col-md-3">
            <div class="l-side">
              <div id='cssmenu'>
              <ul class="list-unstyled">
                <!--  <li> <a class="list" href="javascript:void(0);">Live trackng
<i class="fa fa-angle-right pull-right"></i>  </a> </li> -->
  <li> <a class="list" href="schoolDashboard.html"><i class="fa fa-backward"></i> Dashboard
</a> </li> 
<li class=''><a href="school/allRoute"><i class="fa fa-bus"></i> Bus Route 
   <!-- <i class="fa fa-angle-right pull-right"></i> --> </a>
   <ul class="list-unstyled">
     
     <li><a href="school/allRoute">All Route 
    
    </a></li>
 <% if((Integer)session.getAttribute("permission")==1) {%>
<!--  <li><a href="school/addRoute">Add Route
     </a></li> -->
<% } %>
<!--  <li><a href="school/studentRoute">Assign Student Route
     </a></li> 
  <li><a href="school/editStudentRoute">Edit Student Route
     </a></li>     -->     
      
      </ul>
   </li>

<li class=''><a href="school/manageDrivers"><i class="fa fa-taxi"></i> Drivers 
      <!--  <i class="fa fa-angle-right pull-right"></i> --> </a>
       <ul class="list-unstyled">
         
         <li><a href="school/manageDrivers">All Drivers
       </a></li>
       <% if((Integer)session.getAttribute("permission")==1) {%>
<!-- <li><a href="school/addDriver">Add Driver
   </a></li> -->
     <% } %>
            </ul>
         </li>     
       	<li class=''><a href="school/manageVehicle"><i class="fa fa-car"></i> Vehicles 
<!-- <i class="fa fa-angle-right pull-right"></i> --> </a>
<ul class="list-unstyled">
<li><a href="school/manageVehicle">All Vehicles

 <%--  <% if(request.getAttribute("notification")!=null && (Integer)request.getAttribute("notification")!=0){ %>
  <span class="notify">
  <%=request.getAttribute("notification") %>
  
  
  </span>
  <% } %> --%>
                 
</a></li>
  <% if((Integer)session.getAttribute("permission")==1) {%>
<!--   <li><a href="school/addVechile">Add Vehicle
     </a></li> -->
<% } %>
    </ul>
</li> 
<li class='has-sub'><a href="javascript:void(0);"><i class="fa fa-power-off"></i> Holiday 
    <i class="fa fa-angle-right pull-right"></i> </a>
    <ul class="list-unstyled">
    
    <li><a href="school/viewCalendar">View Calender
	 </a></li>
    <li><a href="school/manageHoliday">All Holiday
    </a></li>
     <% if((Integer)session.getAttribute("permission")==1) {%>
<!-- <li><a href="school/addHoliday">Add Holiday
 </a></li> -->
<% } %>
    </ul>
</li> 
         <li class=''><a href="school/manageParents"> <i class="fa fa-male"></i> Parents 
                 <!-- <i class="fa fa-angle-right pull-right"></i> --> </a>
                 <ul class="list-unstyled">
                   
                   <li><a href="school/manageParents">All Parents
         	  </a></li>
         	     <% if((Integer)session.getAttribute("permission")==1) {%>

<!--  <li><a href="school/addParent">Add Parents
    </a></li> -->
  <% } %>
   </ul>
</li>

 


 <li class=''><a href="school/manageStudents"><i class="fa fa-graduation-cap"></i>  Students 
  <!--   <i class="fa fa-angle-right pull-right"></i> --> </a>
    <ul class="list-unstyled">
      
      <li><a href="school/manageStudents">All Student
     </a></li>
      <% if((Integer)session.getAttribute("permission")==1) {%>
<!-- <li><a href="school/addStudent">Add Students</a></li> -->
    <% } %>
    </ul>
 </li>
<li class=''><a href="school/manageSchoolsAdmin"><i class="fa fa-users"></i> School Admins 
     <!-- <i class="fa fa-angle-right pull-right"></i> --> </a>
     <ul class="list-unstyled">
      <li><a href="school/manageSchoolsAdmin">Manage School Admin
      <% if((Integer)session.getAttribute("permission")==1) {%>
 <!--  <li><a href="school/addSchoolAdmin">Add School Admin
   </a></li> -->
   <% } %>
     
    </i> </a></li>
   </ul>
</li>
  <li class=''><a href="school/manageClasses"><i class="fa fa-users"></i> Manage Classes
   <!--  <i class="fa fa-angle-right pull-right"></i> --> </a>
<ul class="list-unstyled">
 <!--  <li><a href="school/manageClasses">All Classes</a></li> -->
  <% if((Integer)session.getAttribute("permission")==1) {%>
  <!-- <li><a href="school/addClass">Add Class</a></li> -->
  <% } %>
               	
               </ul>
           </li>
           <li class='has-sub'>
<a href="javascript:void(0);" ><i class="fa fa-envelope"></i> Messages
               <i class="fa fa-angle-right pull-right"></i> </a>
               <ul class="list-unstyled">
                <li><a class="list" href="school/chattingSocket" target="_blank">SuperAdmin Message </a> </li>
                 <% if((Integer)session.getAttribute("main_admin")==1) {%>
<li><a class="list" href="school/chattingToParentSocket" target="_blank">Parent Message </a> </li>
<li><a class="list" href="school/chattingToDriverSocket" target="_blank">Driver Message </a> </li>
 <% } %>
                    </ul>
			   
               </li>
                
</ul>
</div>
         <!--      <div class="route-search">
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