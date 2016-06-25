
<%@include file="include/header.jsp"%>

<div class="section">
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<h3 class="panel-title">Panel title</h3>
					</div>
					<div class="panel-body">
						<c:if test="${param.error != null}">
							<div class="alert alert-danger alert-dismissable">Invalid
								username and password</div>
						</c:if>
						<c:if test="${param.logout != null}">
							<div class="alert alert-danger alert-dismissable">You have
								been logged out</div>
						</c:if>
						<form:form role="form" method="post">
							<div class="form-group">
								<label class="control-label">Email address</label> <input
									name="username" class="form-control" id="username"
									placeholder="Enter email" type="email" />
								<p class="help-block">Enter your email address</p>
							</div>
							<div class="form-group">
								<label class="control-label">Password</label> <input
									class="form-control" name="password" id="password"
									placeholder="Password" type="password" />
								<p class="help-block">Enter your password</p>
								<form:errors cssClass="text-danger" path="password" />
							</div>
							<div class="form-group">								
									<div class="checkbox">
										<label> <input name="remember-me" type="checkbox">Remember
											me
										</label>
									</div>
							
							</div>							
								<button type="submit" class="btn btn-default">Login</button>
							</form:form>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>





<%@include file="include/footer.jsp"%>
