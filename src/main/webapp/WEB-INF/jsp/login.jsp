
<%@include file="include/header.jsp"%>

<div class="section"  style="margin-top: 150px">
	<div class="container">
		<div class="row">
			<div class="col-md-4 col-md-offset-4">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<h3 class="panel-title"><spring:message code="signin" /></h3>
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
								<label class="control-label"><spring:message code="email" /></label> <input
									name="username" class="form-control" id="username"
									type="email" />
							<!-- 	<p class="help-block">Ingresa tu direccion de correo</p> -->
							</div>
							<div class="form-group">
								<label class="control-label"><spring:message code="password"/></label> <input
									class="form-control" name="password" id="password" type="password" />
								<!-- <p class="help-block">Ingresa tu contraseña</p> -->
								<form:errors cssClass="text-danger" path="password" />
							</div>
							<div class="form-group">								
									<div class="checkbox">
										<label> <input name="remember-me" type="checkbox">Remember
											me
										</label>
									</div>
							
							</div>							
								<button type="submit" class="btn btn-default"><spring:message code="signin" /></button>
								<a href="/forgot-password" class="btn btn-default">Forgot password</a>
							</form:form>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>





<%@include file="include/footer.jsp"%>
