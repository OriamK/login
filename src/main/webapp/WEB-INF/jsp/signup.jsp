
	<%@include file="include/header.jsp"%>	
		
		
		
		
	<div class="section">
      <div class="container">
      
      
        <div class="panel panel-primary">
          <div class="panel-heading">
            <h3 class="panel-title">Sign Up</h3>
          </div>
          
          <div class="panel-body">
            <form:form role="form" modelAttribute="signupForm">
              <div class="form-group">
                <form:label path="email" class="control-label" >Email address</form:label>
                <form:input path="email" class="form-control" placeholder="Enter email" type="email"/>
                <form:errors path="email" cssClass="text-danger"/>
              </div> 
              <div class="form-group">
                <form:label path="name" class="control-label" >Name</form:label>
                <form:input path="name" class="form-control" placeholder="Name" type="text"/>
                <form:errors path="name" cssClass="text-danger"/>
              </div>
              <div class="form-group">
                <form:label path="password" class="control-label" >Password</form:label>
                <form:input path="password" class="form-control" placeholder="Password" type="password"/>
                <form:errors path="password" cssClass="text-danger"/>
              </div>
              <button type="submit" class="btn btn-default">Sign up</button>
            </form:form>
          </div>
        </div>
        
      </div>
    </div>	
	
	
	
	<%@include file="include/footer.jsp"%>	