<jsp:include page="header.jsp" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
  <div class="section top_bread clearfix">
    	<div class="container">
			<div class="row">
				<div class="col-md-12">
                	<p class="align-right"><span class="">Home</span>/<span class="yellow">Contact us</span></p>
                </div>
    		</div>
        </div>
    </div>
	<div class="section clearfix">
    	<div class="container bg-body">
			<div class="row  bottom-margin ">
				<div class="col-md-12">
                	<h2 class="col-md-12">CONTACT US</h2>
                </div>
    		</div>
        </div>
    </div>
    	<div class="section clearfix">
    	<div class="container bg-body">
   
    	<form  id="contact_form"  class="form-horizontal" method="post">
			<div class="row  bottom-margin ">
				<div class="col-md-12">
                	<div class="col-md-7">
                    	<div class="form-group">
                    		<div class="checkbox">
                    		<c:set var="count" value="1" scope="page" />
                    		 <c:forEach items="${categories}" var="category">  
                    		 <c:if test="${category.category_en!=''}">      
        						<div class="col-sm-6 pad0">
        							<div class="checkbox">
				                        <input class="checkbox" id="checkbox${count}" type="checkbox" name="tag_name" value="${category.category_en}"/>
				                        <label for="checkbox${count}">
				                            ${category.category_en}
				                        </label>
				                    </div>
        						
        						</div>
        						<c:set var="count" value="${count + 1}" scope="page"/>
                                </c:if>
                                </c:forEach>
                                				 		
   					 		 <input id="category" name="category" class="styled" value="" type="hidden">
           
   					 		</div>
                    	</div>
                        <div class="bottom-margin30">&nbsp;</div>
                        <div class="col-sm-12 pad0">
                        
                            <div class="form-group">
                                <label for="inputname" class="control-label col-xs-3">Name<sup>*</sup> :</label>
                                <div class="col-xs-9">
                                    <input type="text" name="name" class="form-control" id="name">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="inputName" class="control-label col-xs-3">Phone Number<sup>*</sup> :</label>
                                <div class="col-xs-9">
                                    <input type="text" name="phone_number"  class="form-control" id="phone_number">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="inputName" class="control-label col-xs-3">Email<sup>*</sup> :</label>
                                <div class="col-xs-9">
                                    <input type="text" class="form-control" name="email" id="email">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="inputName" class="control-label col-xs-3">Message<sup>*</sup> :</label>
                                <div class="col-xs-9">
                                    <textarea class="form-control"  rows="5" name=message id="message"></textarea>
                                </div>
                            </div>
                            <div class="form-group">
                            	<label for="inputName" class="control-label col-xs-3">&nbsp;</label>
                                <div class="col-xs-9">
                                     <button type="submit" class="btn btn-primary btn-submit">Submit</button>
                                </div>
                            </div>
                        </form>
                        
                        </div>
                    </div>
                    <div class="col-md-5">
                    	
                        <div class="col-sm-12">
                        <h4>LOCATION</h4>
                        <iframe src="${content.location}" width="100%" height="300" frameborder="0" style="border:0"></iframe>
                        </div>
                        <div class="col-sm-12">
						<div class="footer-widget contact-widget">
							<h4><span>CONTACT</span></h4>
							<p class="para16"><i class="glyphicon glyphicon-home"></i>${content.address}</p>
                            <p class="para16"><i class="fa fa-envelope"></i>${content.email}</p>
							<p class="para16"><i class="fa fa-phone"></i>${content.phone_number}</p>
                            <p class="para16"><i class="glyphicon glyphicon-globe"></i>${content.website}</p>
							
						</div>
					</div>
                    </div>
                </div>
    		</div>
        </div>
    </div>
  <jsp:include page="footer.jsp" />
  
  <script src="resources/front/js/jquery.validate.min.js"> </script>
  <script>
  $(document).ready(function(){
		$("#contact_form").validate({
          rules: {
        	  name: "required",
        	  phone_number: "required",
        	  email:{
						required: true,
						email: true
					},
			  message: "required"
          },
          messages: {
        	  name: "Please enter your name",
        	  phone_number: "Please enter phone number",
        	  email: {
				required:"Please enter  email address",
				email:"Please enter a valid email address"
			},
			  message: "Please enter message"
	      },
          submitHandler: function(form) {
              form.submit();
          }
      });
      

});
 /*Function for add category*/
 $(document).ready(function() {
$.each($('input[name*=tag_name]'),
    function () {
	$(this).click(function () {
				var txt = '';
                $.each($('input[name*=tag_name]:checked'),function () {
                      txt = txt + $(this).val() + ',';
                    });
                if (txt.length > 0)
                    txt = txt.substring(0, txt.length - 1);
                $('#category').val(txt);
            });
    });
          
     });
  
  </script>
  
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="resources/dashboard/dist/sweetalert-dev.js"></script>
<link rel="stylesheet" href="resources/dashboard/dist/sweetalert.css">
  <c:if test="${!empty success}">
<script>
swal(
		{
			title : "${success}",
			text : "",
			type : "success"
		}, function() {
			
		});
</script>

</c:if>