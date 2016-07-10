
<%@include file="include/header.jsp"%>

<div class="section" style="margin-top: 150px">
	<div class="container">
		<div class="row">

			<div class="col-md-4 col-md-offset-4">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<h3 class="panel-title">
							<spring:message code="signin" />
						</h3>
					</div>
					<div class="panel-body">

						<form:form role="form" modelAttribute="forgotPasswordForm">

							<form:errors  />

							<div class="form-group">
								<form:label path="email" class="control-label">Email address</form:label>
								<form:input path="email" class="form-control"	type="email" />
								<form:errors path="email" cssClass="text-danger"/>
							</div>
					
							<button type="submit" class="btn btn-default">	Reset password	</button>
							
						</form:form>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>





<%@include file="include/footer.jsp"%>
