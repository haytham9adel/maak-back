<jsp:include page="header.jsp" />
<%@ page import="resources.Assets" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<style>
.kya {
    clear: both;
}
.slide_boxes {
    overflow: hidden;
}


.slide_boxes .col-md-3:nth-child(5),.slide_boxes .col-md-3:nth-child(9),.slide_boxes .col-md-3:nth-child(13),.slide_boxes .col-md-3:nth-child(17) {
    display: table-cell;
    clear: both;
}
</style>
<div class="section top_bread clearfix">
   	<div class="container">
		<div class="row">
			<div class="col-md-12">
               	<p class="align-right"><span class="">Home</span>/<span  class="yellow">Features</span></p>
               </div>
   		</div>
       </div>
   </div>
<div class="section clearfix">
   	<div class="container bg-body">
		<div class="row  bottom-margin ">
			<div class="col-md-12">
               	<h2>FEATURES</h2>
               </div>
   		</div>
       </div>
       
   </div>
   <div class="section clearfix">
   	<div class="container bg-body pad0">
		<ul class="nav nav-tabs featured-ul">
  		    <li class="ftrd-tab active"><a class="featured-active" data-toggle="tab" href="#home">All</a></li>
   			<li class="ftrd-tab"><a data-toggle="tab" href="#menu1">Parents</a></li>
   			<li class="ftrd-tab"><a data-toggle="tab" href="#menu2">School</a></li>
   			<li class="ftrd-tab"><a data-toggle="tab" href="#menu3">College Student</a></li>
 			</ul>
           <div class="tab-content top-margin slide_boxes">
   			<div id="home" class="tab-pane fade in clr_both active ">
     				<!-- box -->
<section class="margin-bottom">
    <div class="kya">
    <c:forEach items="${features}" var="features">    
            <div class="col-md-3 col-sm-6 bshad">
                <div class="content-box box-default">
					<c:choose>
						<c:when test="${features.image_path!=''}">
							<img width="100%"  src="<%=Assets.FEATURES_IMAGE %>${features.image_path}"/>
						</c:when>
						<c:otherwise>
							<img width="100%" src="resources/dashboard/Images/no.jpg"/>
						</c:otherwise>
					</c:choose>
               	  <p class="box-head top-margin">${features.title}</p>
                    <p>${features.content}</p>
                </div>
            </div>
   </c:forEach>         
    </div>

</section>
                    <!-- end box -->
			</div>
			<div id="menu1" class="tab-pane fade clr_both">
  <div class="kya">
  		  
	    <c:forEach items="${features}" var="features">    
	    <c:set var="theString" value="${features.category_type}"/>
	    	<c:if test="${fn:contains(theString, '1')}">
            <div class="col-md-3 col-sm-6">
                <div class="content-box box-default">
					<c:choose>
						<c:when test="${features.image_path!=''}">
							<img width="100%"  src="<%=Assets.FEATURES_IMAGE %>${features.image_path}"/>
						</c:when>
						<c:otherwise>
							<img width="100%" src="resources/dashboard/Images/no.jpg"/>
						</c:otherwise>
					</c:choose>
               	  <p class="box-head top-margin">${features.title}</p>
                    <p>${features.content}</p>
                </div>
             
            </div>
		</c:if>
	
   </c:forEach>          
</div>
			</div>
			<div id="menu2" class="tab-pane fade clr_both">
  				 <div class="kya">
    					
					<c:forEach items="${features}" var="features">    
					<c:set var="theString" value="${features.category_type}"/>
					<c:if test="${fn:contains(theString, '2')}">
					<div class="col-md-3 col-sm-6">
					    <div class="content-box box-default">
					<c:choose>
					<c:when test="${features.image_path!=''}">
						<img width="100%"  src="<%=Assets.FEATURES_IMAGE %>${features.image_path}"/>
					</c:when>
					<c:otherwise>
						<img width="100%" src="resources/dashboard/Images/no.jpg"/>
					</c:otherwise>
					</c:choose>
					<p class="box-head top-margin">${features.title}</p>
					 <p>${features.content}</p>
					    </div>
					 
					</div>
					</c:if>
					
					</c:forEach>  
    					
				 </div>
			</div>
			<div id="menu3" class="tab-pane fade clr_both">
				<div class="kya">
    					
					<c:forEach items="${features}" var="features">    
					<c:set var="theString" value="${features.category_type}"/>
					<c:if test="${fn:contains(theString, '3')}">
					<div class="col-md-3 col-sm-6">
					    <div class="content-box box-default">
					<c:choose>
					<c:when test="${features.image_path!=''}">
						<img width="100%"  src="<%=Assets.FEATURES_IMAGE %>${features.image_path}"/>
					</c:when>
					<c:otherwise>
						<img width="100%" src="resources/dashboard/Images/no.jpg"/>
					</c:otherwise>
					</c:choose>
					<p class="box-head top-margin">${features.title}</p>
					 <p>${features.content}</p>
					    </div>
					 
					</div>
					</c:if>
					
					</c:forEach>  
    					
				 </div>
			</div>
	</div>
    </div>
</div>
   <jsp:include page="footer.jsp" />