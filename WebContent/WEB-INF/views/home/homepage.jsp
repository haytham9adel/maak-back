<jsp:include page="header.jsp" />
<%@ page import="resources.Assets" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*" %> 
<%@ page import="org.apache.commons.codec.binary.Base64" %> 
<style>
.owl-controls div span {
    width: 10px;
    height: 10px;
    background: #ff4b64;
    float: left;
    border-radius: 50%;
    margin-right: 11px;
}

.owl-controls div.active span {
    background: #000;
}

.owl-pagination {
    text-align: center;
    display: block;
    overflow: hidden;
    width: auto;
    margin: 0 auto;
}

.owl-page {
    overflow: hidden;
    display: inline-block;
}
div#owl-demo img {
    width: 268px !important;
    height: 268px;
}

.three-block-wrap p {
    padding: 0 37px;
    font-family: 'proxima_novalight';
    color: #282828;
    font-size: 15px;
    line-height: 25px;
    text-align: left;
    margin: 10px 0;
}

section.h-video {
    overflow: hidden;
    height: 500px !important;
}

section.h-video iframe {
    width: 100% !important;
    height: 500px !important;
}

</style>
    <section class="slider"> 
      <ul class="bxslider">
       <c:forEach items="${sliders}" var="slider">    
		<c:if test="${slider.slider_type==0}">
		<c:choose>
			<c:when test="${slider.slider_image!=''}">
	         	<li> <img src="<%=Assets.SLIDER_IMAGES %>/${slider.slider_image}"/></li>
	         </c:when>
	    	<c:otherwise>
	        	 <li><img src="resources/dashboard/Images/no.jpg"/></li>
	    	</c:otherwise>	
	    </c:choose>
	   	</c:if>
 		</c:forEach>
		  <!--<li><img src="resources/front/Images/pic-2.jpg" /></li>
          <li><img src="resources/front/Images/pic-3.jpg" /></li> -->
      </ul>
    </section>
    <section content-block>
      <div class="container white-bg">
        <div class="row">
          <div class="col-md-4 col-sm-4 col-xs-12"> 
              <div class="three-block-wrap"> 
                 <img class="img-responsive" src="resources/front/Images/parents.jpg">
                 <div class="h_text">
                ${pages[2].page_desc}
		         </div>
				<c:set var="h_id" value="${pages[2].page_id}"/>
				<%
				String str=pageContext.getAttribute("h_id").toString();
				byte[] encodedBytes = Base64.encodeBase64(str.getBytes());
				%>
                  <a role="button" href="page.html?q=<%=new String(encodedBytes)%>" class="btn btn-default">Learn More</a>
              </div>
           </div>
          <div class="col-md-4 col-sm-4 col-xs-12">
           <div class="three-block-wrap"> 
                <img class="img-responsive" src="resources/front/Images/School.jpg">
                <div class="h_text"> ${pages[1].page_desc}   
				</div>
				<c:set var="h_id1" value="${pages[1].page_id}"/>
				<%
				String str1=pageContext.getAttribute("h_id1").toString();
				byte[] encodedBytes1 = Base64.encodeBase64(str1.getBytes());
				%>
                  <a role="button" href="page.html?q=<%=new String(encodedBytes1)%>" class="btn btn-default">Learn More</a>
              </div> 
           </div>
          <div class="col-md-4 col-sm-4 col-xs-12"> 
            <div class="three-block-wrap"> 
                <img class="img-responsive" src="resources/front/Images/student.jpg">
                <div class="h_text">${pages[0].page_desc} </div>
                <c:set var="h_id2" value="${pages[0].page_id}"/>
				<%
				String str2=pageContext.getAttribute("h_id2").toString();
				byte[] encodedBytes2 = Base64.encodeBase64(str2.getBytes());
				%>
                  <a role="button" href="page.html?q=<%=new String(encodedBytes2)%>" class="btn btn-default">Learn More</a>
              </div>
           </div> 
        </div>
        
       </div> 
    </section>
    <c:if test="${video.p_name!=''}">
    <section class="h-video">
    	<div class="ea-video">
    		<div style="position:relative;height:0;padding-bottom:56.25%"><iframe src="https://www.youtube.com/embed/${video.p_name}?rel=0&amp;controls=0&amp;showinfo=0?ecver=2" width="100%" height="100%" frameborder="0" style="position:absolute;width:100%;height:100%;left:0" allowfullscreen></iframe></div>
    	</div>
    </section>
    </c:if>
    <section class="wwr">
    	<div class="container">
    		<div class="row who-we-are-block" style="margin-top:23px;">
          <div class="col-md-3 col-sm-3 col-xs-12">
         <c:choose>
	          <c:when test="${pages_1[0].page_title!=''}">
	          
	    	<img alt="" src="<%=Assets.SLIDER_IMAGES %>${pages_1[0].page_title}" class="img-responsive">
	    	  </c:when>
	
	         <c:otherwise><img alt="" src="resources/dashboard/Images/we_are.jpg" class="img-responsive"> 
	       
	         </c:otherwise>
          </c:choose>
          
          
          </div> 
          <div class="col-md-8 col-sm-9 col-xs-12">
            <h1>${pages_1[0].page_name} </h1>
            
           ${pages_1[0].page_desc}
           
           <c:set var="h_id1" value="${pages_1[0].page_id}"/>
				<%
				String str3=pageContext.getAttribute("h_id1").toString();
				byte[] encodedBytes3 = Base64.encodeBase64(str3.getBytes());
				%>
				
				 <a role="button" href="about.html" class="read_more">Read More</a>
              <%--  <a role="button" href="page.html?q=<%=new String(encodedBytes3)%>" class="read_more">Read More</a> --%>
               
           </div>
        </div>
    	</div>
    </section>
    
  <style>
  .slider-text-bottom p {
    font-size: 17px;
    text-align: center;
    position: relative;
    top: 5px;
    font-weight: bold;
}
  </style>  
    <section class="tracking-system-blcok">
      <div class="container"> 
        <h2> ${pages_2.page_name}</h2>
           <div class="customNavigation"> <a class="btn prev"> </a> 
           <a class="btn next"> </a> </div>
          <div id="owl-demo" class="owl-carousel">
               	<c:set var="count" value="1" scope="page" /> 
				<c:forEach items="${sliders}" var="slider">    
				<c:if test="${slider.slider_type==1}">
				<c:choose>
				<c:when test="${slider.slider_image!=''}">
				  <div class="item item-cover slider-text-bottom">
				   <img   class="img-responsive"  src="<%=Assets.SLIDER_IMAGES %>/${slider.slider_image}"/> 
				  	<c:if test="${count==1 }">
				  	 <p>&nbsp;</p>
				    </c:if>
				    <c:if test="${count==2 }">
				  	 <p>Real Time Tracking</p>
				    </c:if>
				    <c:if test="${count==3 }">
				  	 <p>In Bus Attendance</p>
				    </c:if>
				    <c:if test="${count==4 }">
				  	 <p>Pick/ Drop SMS</p>
				    </c:if>
				    <c:if test="${count==5 }">
				  	 <p>Geo Fencing</p>
				    </c:if>
				    <c:if test="${count==6 }">
				  	 <p>Reports</p>
				    </c:if>
				    
                 </div>
				</c:when>
				<c:otherwise>
				<li><img src="resources/dashboard/Images/no.jpg"/></li>
				</c:otherwise>	
				</c:choose>
				</c:if>
				
				<c:set var="count" value="${count + 1}" scope="page"/>
				</c:forEach>
              
          
               
              </div>
      </div>
    </section>
    
    <section class="service-f-block">
        <div class=" container white-bg">
          <div class="col-md-4 col-sm-4 col-xs-12">
            <div class="counting-visual"> 
               <span> 1 </span>
               <div class="visual-content clearfix ">
                 <div class="service-icon"> <img src="resources/front/Images/icon-a.png">  </div>
                 <div class="desc">
                   <h3>${pages_3[2].page_name}</h3>
                   <p> ${pages_3[2].page_desc}</p> 
                  </div>
                </div>
            </div> 
          </div>
          <div class="col-md-4 col-sm-4 col-xs-12"> 
          <div class="counting-visual "> 
               <span> 2 </span>
               <div class="visual-content clearfix">
                 <div class="service-icon"> <img src="resources/front/Images/icon-b.png">  </div>
                 <div class="desc">
                   <h3>${pages_3[1].page_name}</h3>
                   <p> ${pages_3[1].page_desc}</p> 
                  </div>
                </div>
            </div> 
          </div> 
          <div class="col-md-4 col-sm-4 col-xs-12"> 
          <div class="counting-visual"> 
               <span> 3 </span>
               <div class="visual-content clearfix">
                 <div class="service-icon"><img src="resources/front/Images/icon-c.png">  </div>
                 <div class="desc">
                   <h3>${pages_3[0].page_name}</h3>
                   <p>${pages_3[0].page_desc}</p> 
                  </div>
                </div>
            </div> 
          </div> 
          <div class="col-md-12 col-sm-12 col-xs-12 text-center"> <a role="button" href="contact.html" class="read_more">Contact us</a></div> 
        </div>
    </section>
    <jsp:include page="footer.jsp" />
    