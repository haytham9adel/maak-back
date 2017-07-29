<jsp:include page="header.jsp" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="section top_bread clearfix">
   	<div class="container">
		<div class="row">
			<div class="col-md-12">
               	<p class="align-right"><span class="">Home</span>/<span  class="yellow">${heading}</span></p>
               </div>
   		</div>
       </div>
   </div>
<div class="section clearfix">
   	<div class="container  bg-body">
		<div class="row  bottom-margin30">
			<div class="col-md-12">
               	<h2 class="bottom-margin col-sm-12" >${heading}</h2>
                   <div class="col-sm-3">
                   <!-- vertical tab -->
               <ul class="ver-inline-menu tabbable">
	
	<li class="active">
		<a data-toggle="tab" href="#tab_1">
			<i class="fa fa-group"></i> General
		</a>
	</li>
	<li >
		<a data-toggle="tab" href="#tab_2">
			<i class="fa fa-leaf"></i>Functionality
		</a>
	</li>
	<li class="">
		<a data-toggle="tab" href="#tab_3">
			<i class="fa fa-info-circle"></i> Technology
		</a>
	</li>

	<li class="">
		<a data-toggle="tab" href="#tab_4">
			<i class="fa fa-plus-square"></i> Other questions
		</a>
	</li>
</ul>
               <!-- end vertical tab -->
              </div>
              <div class="col-sm-9 faq_acc">
              <!-- toggle faq -->
              <div class="tab-content">
<div id="tab_1" class="tab-pane active">
	<div id="accordion1" class="panel-group">
		<c:set var="count" value="1" scope="page" />
		<c:forEach items="${faq}" var="faq">  
		<c:if test="${faq.category=='General'}">  
		<div class="panel panel-default">
			<div class="panel-heading">
				<h4 class="panel-title">
				<a onclick="set_plus_minus(<c:out value='${count}'/>);" class="accordion-toggle" data-toggle="collapse" data-parent="#accordion<c:out value='${count}'/>" href="#accordion1_<c:out value='${count}'/>">
					 <c:out value="${count}"/>-	${faq.question}
					 <c:if test='${count==1}'>
					 <i id="cola_<c:out value='${count}'/>" class="fa fa-minus-square pull-right migrate"></i>
					 </c:if>
					 <c:if test='${count!=1}'>
					 <i id="cola_<c:out value='${count}'/>" class="fa fa-plus-square pull-right migrate"></i>
					 </c:if>
				</a>
				</h4>
			</div>
			<div id="accordion1_<c:out value='${count}'/>" class="panel-collapse <c:if test='${count==1}'>in</c:if> <c:if test='${count!=1}'>collapse</c:if>">
				<div class="panel-body">
					<p>${faq.answer}</p>
				</div>
			</div>
		</div>
		 <c:set var="count" value="${count + 1}" scope="page"/>
		</c:if>
		
		</c:forEach>
		
		
	</div>
</div>

<div id="tab_2" class="tab-pane ">
	<div id="accordion2" class="panel-group">
		<c:set var="count" value="1" scope="page" />
		<c:forEach items="${faq}" var="faq">  
		<c:if test="${faq.category=='Functionality'}">  
		<div class="panel panel-default">
			<div class="panel-heading">
				<h4 class="panel-title">
				<a onclick="set_plus_minus1(<c:out value='${count}'/>);" class="accordion-toggle" data-toggle="collapse" data-parent="#accordion<c:out value='${count}'/>" href="#accordion2_<c:out value='${count}'/>">
					 <c:out value="${count}"/>-	${faq.question}
					  <c:if test='${count==1}'>
					 <i id="colaa_<c:out value='${count}'/>" class="fa fa-minus-square pull-right migrate"></i>
					 </c:if>
					 <c:if test='${count!=1}'>
					 <i id="colaa_<c:out value='${count}'/>" class="fa fa-plus-square pull-right migrate"></i>
					 </c:if>
				</a>
				</h4>
			</div>
			<div id="accordion2_<c:out value='${count}'/>" class="panel-collapse <c:if test='${count==1}'>in</c:if> <c:if test='${count!=1}'>collapse</c:if>">
				<div class="panel-body">
					<p>${faq.answer}</p>
				</div>
			</div>
		</div>
		 <c:set var="count" value="${count + 1}" scope="page"/>
		</c:if>
		
		</c:forEach>
	</div>
						</div>
						<div id="tab_3" class="tab-pane">
							<div id="accordion3" class="panel-group">
								
								<c:set var="count" value="1" scope="page" />
		<c:forEach items="${faq}" var="faq">  
		<c:if test="${faq.category=='Technology'}">  
		<div class="panel panel-default">
			<div class="panel-heading">
				<h4 class="panel-title">
				<a onclick="set_plus_minus2(<c:out value='${count}'/>);" class="accordion-toggle" data-toggle="collapse" data-parent="#accordion<c:out value='${count}'/>" href="#accordion3_<c:out value='${count}'/>">
					 <c:out value="${count}"/>-	${faq.question}
					  <c:if test='${count==1}'>
					 <i id="colaaa_<c:out value='${count}'/>" class="fa fa-minus-square pull-right migrate"></i>
					 </c:if>
					 <c:if test='${count!=1}'>
					 <i id="colaaa_<c:out value='${count}'/>" class="fa fa-plus-square pull-right migrate"></i>
					 </c:if>
				</a>
				</h4>
			</div>
			<div id="accordion3_<c:out value='${count}'/>" class="panel-collapse <c:if test='${count==1}'>in</c:if> <c:if test='${count!=1}'>collapse</c:if>">
				<div class="panel-body">
					<p>${faq.answer}</p>
				</div>
			</div>
		</div>
		 <c:set var="count" value="${count + 1}" scope="page"/>
		</c:if>
		
		</c:forEach>
							</div>
						</div>
							  <div id="tab_4" class="tab-pane">
							<div id="accordion4" class="panel-group">
								<c:set var="count" value="1" scope="page" />
		<c:forEach items="${faq}" var="faq">  
		<c:if test="${faq.category=='Other questions'}">  
		<div class="panel panel-default">
			<div class="panel-heading">
				<h4 class="panel-title">
				<a onclick="set_plus_minus3(<c:out value='${count}'/>);" class="accordion-toggle" data-toggle="collapse" data-parent="#accordion<c:out value='${count}'/>" href="#accordion4_<c:out value='${count}'/>">
					 <c:out value="${count}"/>-	${faq.question}
					  <c:if test='${count==1}'>
					 <i id="colaaaa_<c:out value='${count}'/>" class="fa fa-minus-square pull-right migrate"></i>
					 </c:if>
					 <c:if test='${count!=1}'>
					 <i id="colaaaa_<c:out value='${count}'/>" class="fa fa-plus-square pull-right migrate"></i>
					 </c:if>
				</a>
				</h4>
			</div>
			<div id="accordion4_<c:out value='${count}'/>" class="panel-collapse <c:if test='${count==1}'>in</c:if> <c:if test='${count!=1}'>collapse</c:if>">
				<div class="panel-body">
					<p>${faq.answer}</p>
				</div>
			</div>
		</div>
		 <c:set var="count" value="${count + 1}" scope="page"/>
		</c:if>
		
		</c:forEach>
							</div>
						</div> 
						<!-- <div id="tab_5" class="tab-pane">
							<div id="accordion2" class="panel-group">
								<div class="panel panel-warning">
									<div class="panel-heading">
										<h4 class="panel-title">
										<a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion2" href="#accordion2_1_1">
											 1. Aiquet bulla venenatis inser pede aliquet sit?
										</a>
										</h4>
									</div>
									<div id="accordion2_1_1" class="panel-collapse collapse in">
										<div class="panel-body">
											<p>
												 At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum deleniti atque corrupti quos dolores et quas molestias excepturi vehicula sem ut volutpat. Ut non libero magna fusce condimentum eleifend enim a feugiat corrupti quos.
											</p>
											
										</div>
									</div>
								</div>
								<div class="panel panel-danger">
									<div class="panel-heading">
										<h4 class="panel-title">
										<a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion2" href="#accordion2_2_2">
											 2.Aiquet bulla venenatis inser pede aliquet sit?
										</a>
										</h4>
									</div>
									<div id="accordion2_2_2" class="panel-collapse collapse">
										<div class="panel-body">
										<p>
											At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum deleniti atque corrupti quos dolores et quas molestias excepturi vehicula sem ut volutpat. Ut non libero magna fusce condimentum eleifend enim a feugiat corrupti quos.
										</p>
										
										</div>
									</div>
								</div>
								<div class="panel panel-success">
									<div class="panel-heading">
										<h4 class="panel-title">
										<a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion2" href="#accordion2_3_3">
											 3. Aiquet bulla venenatis inser pede aliquet sit?
										</a>
										</h4>
									</div>
									<div id="accordion2_3_3" class="panel-collapse collapse">
										<div class="panel-body">
											  At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum deleniti atque corrupti quos dolores et quas molestias excepturi vehicula sem ut volutpat. Ut non libero magna fusce condimentum eleifend enim a feugiat corrupti quos.  
										</div>
									</div>
								</div>
								<div class="panel panel-default">
									<div class="panel-heading">
										<h4 class="panel-title">
										<a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion2" href="#accordion2_4_4">
											 4.  Aiquet bulla venenatis inser pede aliquet sit?
										</a>
										</h4>
									</div>
									<div id="accordion2_4_4" class="panel-collapse collapse">
										<div class="panel-body">
											At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum deleniti atque corrupti quos dolores et quas molestias excepturi vehicula sem ut volutpat. Ut non libero magna fusce condimentum eleifend enim a feugiat corrupti quos.
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
-->                    <!-- end toggle faq -->
                </div>
            </div>
		</div>
    </div>
</div>
<script>
function set_plus_minus(id)
{
	if($("#accordion1_"+id).hasClass("in"))
		{
			
			$("#cola_"+id).addClass("fa-plus-square");
			$("#cola_"+id).removeClass("fa-minus-square");
		}else
		{
			$("#cola_"+id).removeClass("fa-plus-square");
			$("#cola_"+id).addClass("fa-minus-square");
			
		}
}
function set_plus_minus1(id)
{
	if($("#accordion2_"+id).hasClass("in"))
		{
			
			$("#colaa_"+id).addClass("fa-plus-square");
			$("#colaa_"+id).removeClass("fa-minus-square");
		}else
		{
			$("#colaa_"+id).removeClass("fa-plus-square");
			$("#colaa_"+id).addClass("fa-minus-square");
			
		}
}
function set_plus_minus2(id)
{
	if($("#accordion3_"+id).hasClass("in"))
		{
			
			$("#colaaa_"+id).addClass("fa-plus");
			$("#colaaa_"+id).removeClass("fa-minus");
		}else
		{
			$("#colaaa_"+id).removeClass("fa-plus");
			$("#colaaa_"+id).addClass("fa-minus");
			
		}
}
function set_plus_minus3(id)
{
	if($("#accordion4_"+id).hasClass("in"))
		{
			
			$("#colaaaa_"+id).addClass("fa-plus");
			$("#colaaaa_"+id).removeClass("fa-minus");
		}else
		{
			$("#colaaaa_"+id).removeClass("fa-plus");
			$("#colaaaa_"+id).addClass("fa-minus");
			
		}
}
</script>
   <jsp:include page="footer.jsp" />