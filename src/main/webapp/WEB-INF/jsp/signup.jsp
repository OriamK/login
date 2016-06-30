
<%@include file="include/header.jsp"%>


<div class="section" style="margin-top: 100px">
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<div class="well">
					<h1>
						<spring:message code="signup" />
					</h1>
					<div class="panel panel-primary">
						<!--           <div class="panel-heading"> -->
						<%--             <h3 class="panel-title"><spring:message code="signup" /></h3> --%>
						<!--           </div>           -->
						<div class="panel-body">
							<form:form role="form" modelAttribute="signupForm">

								<form:errors />
								<div class="form-group">
									<form:label path="email" class="control-label">
										<spring:message code="email" />
									</form:label>
									<form:input path="email" class="form-control" type="email" />
									<form:errors path="email" cssClass="text-danger" />
								</div>
								<div class="form-group">
									<form:label path="name" class="control-label">
										<spring:message code="name" />
									</form:label>
									<form:input path="name" class="form-control" type="text" />
									<form:errors path="name" cssClass="text-danger" />
								</div>
								<div class="form-group">
									<form:label path="password" class="control-label">
										<spring:message code="password" />
									</form:label>
									<form:input path="password" class="form-control"
										type="password" />
									<form:errors path="password" cssClass="text-danger" />
								</div>
								<button type="submit" class="btn btn-default">
									<spring:message code="signup" />
								</button>
							</form:form>
						</div>
					</div>
				</div>
			</div>

		</div>


	</div>
</div>



<%@include file="include/footer.jsp"%>
