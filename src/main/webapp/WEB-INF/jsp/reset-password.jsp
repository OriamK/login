
<%@include file="include/header.jsp"%>

<div class="section" style="margin-top: 150px">
	<div class="container">
		<div class="row">

			<div class="col-md-4 col-md-offset-4">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<h3 class="panel-title">
							Reset Password
						</h3>
					</div>
					<div class="panel-body">

						<form:form role="form" modelAttribute="resetPasswordForm">

							<form:errors  />

							<div class="form-group">
								<form:label path="password" class="control-label">Type new password</form:label>
								<form:input path="password"  class="form-control" 	type="password" />
								<form:errors path="password" cssClass="text-danger"/>
							</div>
							
							<div class="form-group">
								<form:label path="retypePassword" class="control-label">Retype new password</form:label>
								<form:input path="retypePassword"  class="form-control"	type="password" />
								<form:errors path="retypePassword" cssClass="text-danger"/>
							</div>
					
							<button type="submit" class="btn btn-default">	Submit	</button>
							
						</form:form>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>





<%@include file="include/footer.jsp"%>
