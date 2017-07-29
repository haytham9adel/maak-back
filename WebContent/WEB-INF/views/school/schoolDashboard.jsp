<jsp:include page="header.jsp" />
<style>
.tile {
    background-color: #f3be4e;
    border-radius: 3px;
    color: #ffffff;
    margin-bottom: 15px;
    transition: all 1s ease 0s;
}

.tile-heading {
   background-color: #cf9a2a;
    color: #fff;
    padding: 5px 8px;
    text-transform: uppercase;
}

.tile-body {
    color: #ffffff;
    line-height: 48px;
    padding: 15px;
}

.tile-footer {
    background-color: #cf9a2a;
    padding: 5px 8px;
}

.tile .tile-heading .pull-right {
    opacity: 0.7;
    transition: all 1s ease 0s;
}

.tile .tile-body i {
    font-size: 50px;
    opacity: 0.3;
    transition: all 1s ease 0s;
}

.tile .tile-body h2 {
    font-size: 42px;
    margin-top: 0;
}

.tile a {
    color: #ffffff;
}
.tile:hover .tile-body i {
    color: #ffffff;
    opacity: 1;
}

</style>
 		  <div class="col-md-9 m-side">
 		      <h3>Dashboard</h3>
 		        <div class="row" id="statics">
          	<div class="col-lg-4 col-md-4 col-sm-6">
          	<div class="tile">
  <div class="tile-heading">${Heading_box} <span class="pull-right">
        </span></div>
  <div class="tile-body"><i class="fa fa-building "></i>
    <h2 class="pull-right">${total_schools}</h2>
  </div>
  <div class="tile-footer"><a href="school/manageSchoolsAdmin">View more...</a></div>
</div>
</div>

<div class="col-lg-4 col-md-4 col-sm-6"><div class="tile">
  <div class="tile-heading">Total Buses <span class="pull-right">
       </span></div>
  <div class="tile-body"><i class="fa fa-bus"></i>
    <h2 class="pull-right">${total_buses}</h2>
  </div>
  <div class="tile-footer"><a href="school/manageVehicle">View more...</a></div>
</div>
</div>


<div class="col-lg-4 col-md-4 col-sm-6"><div class="tile">
  <div class="tile-heading">Total Students <span class="pull-right">
        </span></div>
  <div class="tile-body"><i class="fa fa-graduation-cap"></i>
    <h2 class="pull-right">${total_student}</h2>
  </div>
  <div class="tile-footer"><a href="school/manageStudents">View more...</a></div>
</div>
</div>


<div class="col-lg-4 col-md-4 col-sm-6"><div class="tile">
  <div class="tile-heading">Total Bus Capacity <span class="pull-right">
       </span></div>
  <div class="tile-body"><i class="fa fa-bus"></i>
    <h2 class="pull-right">${schoolModel.total_bus}</h2>
  </div>
  <div class="tile-footer"><a href="javascript:void(0);">&nbsp;</a></div>
</div>
</div>

<div class="col-lg-4 col-md-4 col-sm-6"><div class="tile">
  <div class="tile-heading">Total Student Capacity <span class="pull-right">
       </span></div>
  <div class="tile-body"><i class="fa fa-bus"></i>
    <h2 class="pull-right">${schoolModel.total_students}</h2>
  </div>
  <div class="tile-footer"><a href="javascript:void(0);">&nbsp;</a></div>
</div>
</div>
<div class="clearfix"></div>

<div class="col-lg-4 col-md-4 col-sm-6 col-xs-offset-4">
	<a href="school/trackDrivers" style="text-decoration:none;">
	<div class="btn-track-bus">
		<img src="resources/dashboard/Images/track_bus.png" /> Track Drivers
	</div>
	</a>
</div>
          
          </div> 
          </div>
         
    <jsp:include page="footer.jsp" />