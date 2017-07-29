<jsp:include page="header.jsp" />
<%@ page import="resources.Assets" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="col-md-6 col-sm-6 col-xs-12 m-side">
	<div class="col-sm-3 col-xs-12  user-icon">
		
                       <c:choose>
          <c:when test="${student.s_image_path!=''}"><td><img  src="<%=Assets.STUDENT_UPLOAD_PATH_DIS %>/${student.s_image_path}"/>
    	
    	</td>
          <br />
         </c:when>

         <c:otherwise><td><img src="resources/dashboard/Images/user_icon.png"/>
    	
    	</td>
         <br />
         </c:otherwise>
          </c:choose>
           <br />
             <a href="admin/viewAttendance?student_id=${student.student_id}"><button class="btn btn-primary btn-sm">View Attendance</button></a>        
	</div>
	
             <div class="col-sm-9 col-xs-12 new-student-form view_student_detail"> 
              <span class="success">${success}</span>
             <span class="error">${error}</span>
                <form class="form-horizontal" id="student" method="post">
                
                	
                  <div class="form-group">
                     <label class="col-sm-3 control-label">First Name :</label>
                       <div class="col-sm-9">
                       <label class="col-sm-12 control-label"> ${student.s_fname}</label>
                        </div>
                        
                   </div>
                   <div class="form-group">
                     <label class="col-sm-3 control-label">Last Name :</label>
                       <div class="col-sm-9">
                           <label class="col-sm-12 control-label">${student.s_lname}</label>
                        </div>
                   </div>
                     <div class="form-group">
                     <label class="col-sm-3 control-label">Email :</label>
                       <div class="col-sm-9">
                         <label class="col-sm-12 control-label">${student.s_email}</label>
                       </div>
                   </div>
                  <%--     <div class="form-group">
                     <label class="col-sm-3 control-label">Address :</label>
                       <div class="col-sm-9">
                         <label class="col-sm-12 control-label">${student.s_address}</label>
                       </div>
                   </div>
                      <div class="form-group">
                     <label class="col-sm-3 control-label">City:</label>
                       <div class="col-sm-9">
                         <label class="col-sm-12 control-label">${city.city_name}</label>
                       </div>
                   </div>
                   <div class="form-group">
                     <label class="col-sm-3 control-label">Country:</label>
                       <div class="col-sm-9">
                         <label class="col-sm-12 control-label">${country.c_name}</label>
                       </div>
                   </div> --%>
                     <div class="form-group">
                     <label class="col-sm-3 control-label">Contact Number:</label>
                       <div class="col-sm-9">
                         <label class="col-sm-12 control-label">${student.s_contact}</label>
                       </div>
                   </div>
                   <%--     <div class="form-group">
                     <label class="col-sm-3 control-label">Zip Code:</label>
                       <div class="col-sm-9">
                         <label class="col-sm-12 control-label">${student.s_zip}</label>
                       </div>
                   </div> --%>
                </form>
             </div> 
             
            
                         
<div class="col-md-12">
<script type="text/javascript">

    function PrintElem(elem)
    {
        Popup($(elem).html());
    }

    function Popup(data) 
    {
        var mywindow = window.open('', 'Trip Details', 'height=400,width=600');
        mywindow.document.write('<html><head><title>my div</title>');
        /*optional stylesheet*/ //mywindow.document.write('<link rel="stylesheet" href="main.css" type="text/css" />');
        mywindow.document.write('</head><body >');
        mywindow.document.write(data);
        mywindow.document.write('</body></html>');

        mywindow.document.close(); // necessary for IE >= 10
        mywindow.focus(); // necessary for IE >= 10

        mywindow.print();
        mywindow.close();

        return true;
    }
    

</script>
<div class="print_btn" style="margin-bottom: 25px;padding: 21px;">
<input style="float:right;" type="button" class="btn btn-primary btn-small" value="Print" onclick="PrintElem('#print')" /> 
</div>
	<table  id="print" width="500" border="0" align="center" cellpadding="5" cellspacing="0">
  <tr>
    <td align="left" valign="top" style="border-radius: 6px;padding: 6px; background-color:#F3BE4E !important; -webkit-print-color-adjust: exact;"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td align="left" valign="top" bgcolor="#FFFFFF" style="border-top-left-radius: 5px; border-top-right-radius: 5px;"><table width="100%" border="0" cellspacing="0" cellpadding="5">
          <tr>
          <td width="50%" align="left" valign="top" style="padding:8px;">
          <c:choose>
						<c:when test="${school_details.school_logo!=''}">
						<img src="<%=Assets.SCHOOL_UPLOAD_PATH %>${school_details.school_logo}" style="width:45px; height:auto;" />
						</c:when>
						<c:otherwise>
						<img class="img-responsive" id="" src="resources/dashboard/Images/s_logo_d.png" alt="image_path" style="width:45px; height:auto;" />
						 </c:otherwise>
						</c:choose>
          
          
            </td>
            <td width="50%" valign="top" align="right" style="vertical-align: middle; font-size: 19px; font-weight: 600;color: #4e8bc4;padding-right: 11px;">${school_details.school_name}</td>
          </tr>
        </table></td>
      </tr>
    </table>
    
      <table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;margin-bottom:10px;">
        <tr>
          <td><table width="100%" border="0" cellspacing="0" cellpadding="5">
            <tr>
              <td width="60%" align="left" valign="top"><table width="100%" border="0" cellspacing="0" cellpadding="5">
                <tr valign="top">
                  <td align="left" valign="top" style="padding: 5px 3px; font-size: 15px;"><strong>Student Name</strong></td>
                  <td align="left" valign="top" style="padding: 5px 3px;font-size: 15px;"><strong>:</strong></td>
                  <td align="left" style="padding: 5px 3px;font-size: 15px;"> ${student.s_fname}  ${student.s_lname}</td>
                </tr>
                
                <tr>
                  <td align="left" valign="top" style="padding: 5px 3px;font-size: 15px;"><strong>School address</strong></td>
                  <td align="left" valign="top" style="padding: 5px 3px;font-size: 15px;"><strong>:</strong></td>
                  <td align="left" valign="top" style="padding: 5px 3px;font-size: 15px;">${school_details.school_address}</td>
                </tr>
               
              </table></td>
              <td valign="top" align="center" width="20" style="vertical-align: middle;">
              
                <c:choose>
          <c:when test="${student.s_image_path!=''}">
          <img width="100" height="auto" src="<%=Assets.STUDENT_UPLOAD_PATH_DIS %>/${student.s_image_path}" style="border-radius: 15px; border: 2px solid rgb(255, 255, 255);">
         </c:when>

         <c:otherwise>
        	 <img width="150" height="150" src="resources/dashboard/Images/user_icon.png" style="border-radius: 15px; border: 2px solid rgb(255, 255, 255);">
         </c:otherwise>
          </c:choose>
              </td>
            </tr>
          </table></td>
        </tr>
      </table>
</td>
  </tr>
</table>
 
</div>  
             
          </div>
              <div class="col-md-3 col-sm-3 col-xs-12">
              
               <c:forEach items="${allParent}" var="parent">
              <div class="info-block"> 
                <h4><b>Parent - ${count + 1}</b> </h4>
                 <div class="info-group">
                    <div class="col-a"> Name : </div>
                    <div class="col-b">   ${parent.first_name} ${parent.last_name} </div> 
                 </div>
             
                 <div class="info-group">
                    <div class="col-a">Email  : </div>
                    <div class="col-b"> ${parent.user_email} </div> 
                 </div>
                 <div class="info-group">
                    <div class="col-a"> Contact Number : </div>
                    <div class="col-b">  ${parent.contact_number} </div> 
                 </div>
              </div> 
              <c:set var="count" value="${count + 1}" scope="page"/>
               </c:forEach> 
             
                 
          
        </div>
        
      <div class="col-sm-12 col-xs-12 new-student-form view_student_detail"> 
           <%--   <div class="form-group">
                     <label class="col-sm-3 control-label">Route:</label>
                       <div class="col-sm-3">
                      
                        <c:if test="${!empty routes}">
                         	<c:forEach items="${routes}" var="route">
                         	<c:if test="${route.route_id==latlng.route_id}">
                         		${route.route_name}
                         	</c:if>
                         	${route.route_name}
                         	</c:forEach>
                         </c:if>
                       
                      <select class="form-control" disabled="disabled" id="route_id" name="route_id" onchange="return getRouteMap1(this.value);"  >
                         <option value="">Select Route</option>
                         <c:if test="${!empty routes}">
                         	<c:forEach items="${routes}" var="route">
                         	<option
                         		<c:if test="${route.route_id==latlng.route_id}">
                         	<c:out value="selected"/>
                         	 </c:if>
                         	 value=<c:out value="${route.route_id}"/>><c:out value="${route.route_name}"/></option>
                         	</c:forEach>
                         </c:if>
                         </select> 
                     </div>
                </div> --%>
                <div style="clear:both;">&nbsp;</div><br></br>
                   <div class="form-group">
                   	<div class="col-sm-12">
                   		<div id="map_canvas" style="width: 100%; height: 500px;">
                   	</div>
                   </div>
                   </div>
             
             </div>    
          <script src="http://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false"></script>
<script type="text/javascript">+
$(document).ready(function(){
	getRouteMap("${latlng.route_id}");
});
var latiArr= [];
var longiArr= [];	
function init(a,b,latiLongi,sourceDesti) {
	 var infowindow = new google.maps.InfoWindow();
	
		latiArr= [];
		longiArr= [];
		latiArr.pop();
		longiArr.pop();
		var latStr='';	
		var lngStr='';
	   longiArr.push(sourceDesti.slng);
	   latiArr.push(sourceDesti.slat);
	   latiArr.push("${latlng.lat}");
	   longiArr.push("${latlng.lng}");
	   latiArr.push(sourceDesti.dlat);
	   longiArr.push(sourceDesti.dlng);

	  var sourceLat;
	  var sourceLng;
	  var destLat;
	  var destLng;
      var markers = [],
        segments = [],
        myOptions = {
            zoom: 13,
            center: new google.maps.LatLng(sourceDesti.slat, sourceDesti.slng),
            mapTypeId: google.maps.MapTypeId.ROADMAP,
            disableDoubleClickZoom: true,
            draggableCursor: "crosshair"
        },
        alphas = 'ABCDEFGHIJKLMNOPQRSTUVWXYZ'.split(''),
     //   alphas = 'ABCDEFGHIJKLMNOPQRSTUVWXYZ'.split(''),
        alphaIdx = 0,
        map = new google.maps.Map(document.getElementById("map_canvas"), myOptions),
        service = new google.maps.DirectionsService(),
        poly = new google.maps.Polyline({
            map: map,
            strokeColor: '#00FFFF',
            strokeOpacity: 0.6,
            strokeWeight: 5
    });

	

    window.resetMarkers = function () {
        for (var i = 0; i < markers.length; i++) {
            markers[i].setMap(null);
        }
        alphaIdx = 0;
        segments = [];
        markers = [];
        poly.setPath([]);
    };
    function getSegmentsPath() {
        var a, i,
            len = segments.length,
            arr = [];
        for (i = 0; i < len; i++) {
            a = segments[i];
            if (a && a.routes) {
                arr = arr.concat(a.routes[0].overview_path);
            }
        }
        return arr;
    }

    function addSegment(start, end, segIdx) {

        service.route(
            {
                origin: start,
                destination: end,
                travelMode: google.maps.DirectionsTravelMode.DRIVING
            },
            function (result, status) {
                if (status == google.maps.DirectionsStatus.OK) {
                    //store the entire result, as we may at some time want
                    //other data from it, such as the actual directions
                    segments[segIdx] = result;

                    poly.setPath(getSegmentsPath());
                    
                }
   
            }
        );
       
         //addLatLong(start);
    }


    for(var p=0;p<latiArr.length;p++)
     { 
    	var ic=''
    		var dr='';
    	if(p==1)
      	{
    		ic="resources/dashboard/Images/markerS.png";
    		dr=false;
      	}
    	if(p==0)
      	{
    		ic="resources/dashboard/Images/source_icon.png";
    		dr=false;
      	}
    	if(p==2)
      	{
    		ic="resources/dashboard/Images/desti.png";
    		dr=false;
      	}
    	
    	
      if (alphaIdx > latiArr.length+1) {
            return;
        }
      
      
       
        var evtPos =new google.maps.LatLng(latiArr[p],longiArr[p]) ,
            c = alphas[alphaIdx++],
           
            marker = new google.maps.Marker({
                map: map,
                position: evtPos,
                draggable: dr,
                icon: ic
        });
     	 marker.segmentIndex = markers.length - 1;
         marker.iconChar = c;//just storing this for good measure, may want at some time
     
        function updateSegments() {
            var start, end, inserts, i,
                idx = this.segmentIndex,
                segLen = segments.length, //segLen will always be 1 shorter than markers.length
                myPos = this.getPosition();
            if (segLen === 0) { //nothing to do, this is the only marker
                return;
            }
            if (idx == -1) { //this is the first marker
                start = [myPos];
                end = [markers[1].getPosition()];
                inserts = [0];
               
            } else if (idx == segLen - 1) { //this is the last marker
                start = [markers[markers.length - 2].getPosition()];
                end = [myPos];
                inserts = [idx];
            } else {
            //there are markers both behind and ahead of this one in the 'markers' array
                start = [markers[idx].getPosition(), myPos];
                end = [myPos, markers[idx + 2].getPosition()];
                inserts = [idx, idx + 1];
            }
          
          
           latiArr.splice(idx+1,1);
           latiArr.splice(idx+1, 0,markers[idx+1].getPosition().lat());
           longiArr.splice(idx+1,1);
           longiArr.splice(idx+1, 0,markers[idx+1].getPosition().lng());
           console.log(start.length);
            for (i = 0; i < start.length; i++) {
                addSegment(start[i], end[i], inserts[i]);
            }
        }

    
        //google.maps.event.addListener(marker, 'drag', updateSegments);
       

        google.maps.event.addListener(marker, 'dragend', updateSegments);
        markers.push(marker);

        if (markers.length > 1) {

             var infowindow = new google.maps.InfoWindow({
             content: ""
  });

            addSegment(markers[markers.length - 2].getPosition(), evtPos, marker.segmentIndex);
          // infowindow.open(map,markers[markers.length - 2]);
        }
   }

  
}

function addLatLong(start)
{
  
}
function add()
{
	
    $("#lat").val(latiArr);
    $("#lng").val(longiArr);
  
    	
    var lat= $("#lat").val();
    var lng=  $("#lng").val();
   
    
    
  document.getElementById("student").submit();
 
}   


function getRouteMap(id)
{
	
	
	$.ajax({
        url : 'getRouteSourceDestination.html',
        type:'post',
        data:{"route_id":id},
        success : function(data) {
        	var sourceDesti = $.parseJSON(data);
        	
        	$.ajax({
                url : 'getRouteLatLng.html',
                type:'post',
                data:{"route_id":id},
                success : function(data) {
              	  
              	var latiLongi = $.parseJSON(data);
              	//init(json);
            /*   	if(latiLongi.latlng.length===0)
  	       		{
  	       			$("#map_canvas").html("No Route Defined");
  	       		}else
  	  			{ */
  	       		    var latStr='';
  	           	   var lngStr='';
  	           		for(var i=0;i<latiLongi.latlng.length;i++)
  	           			{
  	           			   if(i==parseInt(latiLongi.length)-1)
  	           			   {
  	           				   latStr +=latiLongi.latlng[i].lat;
  	           				   lngStr +=latiLongi.latlng[i].lng;
  	           			   }
  	           			   else
  	           			   {
  	           				   latStr +=latiLongi.latlng[i].lat+",";
  	           				   lngStr +=latiLongi.latlng[i].lng+",";
  	           			   }
  	           			   
  	           			}
  	           		
  	           		
  	           		
  	           		
  	  			//}
  	           	init(latStr,lngStr,latiLongi,sourceDesti);
             
                }
            });
        	
        	
        	
        }
    });
	
	
	 
		 return false; 
	
	   
}

            
            
         
            </script>     
       <jsp:include page="footer.jsp" />      