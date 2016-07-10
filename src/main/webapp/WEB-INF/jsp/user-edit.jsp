
<%@include file="include/header.jsp"%>

<div class="section" style="margin-top: 150px">
	<div class="container">
		<div class="row">

			<div class="col-md-12">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<h3 class="panel-title">Reset Password</h3>
					</div>
					<div class="panel-body">

						<form:form role="form" modelAttribute="userEditForm">

							<form:errors />

							<div class="form-group">
								<form:label path="name" class="control-label">Name</form:label>
								<form:input path="name" class="form-control" type="text" />
								<form:errors path="name" cssClass="text-danger" />
							</div>
							<sec:authorize access="hasRole('ROLE_ADMIN')">
								<div class="form-group">
									<form:label path="roles" class="control-label">Roles</form:label>
									<form:input path="roles" class="form-control" type="text" />
									<form:errors path="roles" cssClass="text-danger" />
								</div>
							</sec:authorize>
							<button type="submit" class="btn btn-default">Submit</button>

						</form:form>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>





<%@include file="include/footer.jsp"%>
