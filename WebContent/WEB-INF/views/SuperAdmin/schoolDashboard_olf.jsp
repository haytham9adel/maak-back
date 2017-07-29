<jsp:include page="header.jsp" />
<div class="container  bg-body top-margin30">
    	<div class="col-sm-12">
        <!-- sidebar -->
        	<div class="col-sm-3 col-xs-12 ybg">
            	<div class="sidebar" role="navigation">
                <div class="sidebar-nav navbar-collapse">
                    <ul class="nav sm" id="side-menu">
                    <div class="gap10"></div>
                        <li>
                            <a href="#"><i class="fa fa-dashboard fa-fw"></i> Dashboard</a>
                        </li>
                        <li class="active">
                            <a href="#"><i class="fa fa-bar-chart-o fa-fw"></i> Live Tracking<span class="fa arrow"></span></a>
                        </li>
                        <li>
                            <a href="#"><i class="fa fa-table fa-fw"></i> Staff</a>
                        </li>
                        <li>
                            <a href="#"><i class="fa fa-edit fa-fw"></i> Parents</a>
                        </li>
                        <li>
                            <a href="#"><i class="fa fa-wrench fa-fw"></i> Vehicle<span class="fa arrow"></span></a>
                        </li>
                        <li>
                            <a href="#"><i class="fa fa-sitemap fa-fw"></i> Profile Setting<span class="fa arrow"></span></a>
                        </li>
                        <li>
                            <a href="#"><i class="fa fa-files-o fa-fw"></i> School Information<span class="fa arrow"></span></a>
                      </li>
                       <li>
                            <a href="#"><i class="fa fa-files-o fa-fw"></i> History Log<span class="fa arrow"></span></a>
                      </li>
                      <li class="sidebar-search top-margin">
                      <label for="searchbox" style="color:#fff;">Search By Route</label>
                            <div class="input-group custom-search-form">
                                <input type="text" class="form-control" placeholder="Search by route id">
                                <span class="input-group-btn">
                                <button class="btn btn-default" type="button">
                                    <i class="fa fa-search"></i>
                                </button>
                            </span>
                            </div>
                        </li>
                        <li>
                            <form action="#" method="post">
                            <section class="searchBx2">
                            <div class="gap10"></div>
                            <select name="type_id" class="form-control">                
                                <option value="">Select</option>
                                                        <option value="1">Option1</option>
                                                        <option value="4">Option2</option>
                                                </select>
                             <div class="gap10"></div>
                            <select name="s_location" class="form-control">                
                                <option value="">Select2</option>
                               
                                                        <option value="a">Option1</option>
                                                        <option value="a">Option2</option>
                                                </select>
                            <div class="gap10"></div>
                            <select name="d_location" class="form-control">                
                            <option value="">Select3</option>
                                                        <option value="India">Option1</option>
                                                        <option value="India">Option2</option>
                            </select>
                            <div class="gap10"></div>
                            <p align="right"><input type="submit" class="form-control" value="Search"></p>
                            </section>
                            </form>
                        </li>
                    </ul>
                </div>
            </div>
            </div>
        <!-- end sidebar -->
            <div class="col-sm-7 col-xs-12">
            <p><iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d7591307.962503819!2d80.47178829191273!3d21.714630321205462!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x30635ff06b92b791%3A0xd78c4fa1854213a6!2sIndia!5e0!3m2!1sen!2sin!4v1431078382311" width="100%" height="560" frameborder="0" style="border:0"></iframe></p>
            </div>
            <!--  Right bar -->
            <div class="col-sm-2 col-xs-12">
             	<h4 class="hbold">Bus Detail</h4>
                <span class="margin8">Bus Number : </span>123456
                <span class="margin8">Driver Name : </span>123456
                <span class="margin8">Source : </span><span>123456
                <span class="margin8">Destination : </span>123456
                <span class="margin8">Trave Time : </span>123456
                <span class="margin8">Bus Stop : </span>123456
                <h4 class="hbold">Current Location</h4>
                <span class="margin8">Current stop Name : </span>123456
                <span class="margin8">Next stop Name : </span>123456
                <span class="margin8">Previous stop Name : </span><span>123456
                <h4 class="hbold">Staff info</h4>
                <span class="margin8">Staff member name1 : </span><span>123456
                <span class="margin8">Staff member name2 : </span><span>123456
                <h4 class="hbold">Student info</h4>
                <span class="margin8">no. of student : </span><span>123456
                <span class="margin8">Extra student : </span><span>123456
            </div>
            <!-- end right bar -->
        </div>
    
    </div>
    <jsp:include page="footer.jsp" />