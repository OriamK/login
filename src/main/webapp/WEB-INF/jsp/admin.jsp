<%@include file="include/header.jsp"%>

<div class="section">
      <div class="container-fluid">
        <div class="row">
          <div class="col-md-2 text-center">
            <ul class="nav nav-pills nav-stacked">
              <li class="active">
                <a href="#">Home</a>
              </li>
              <li>
                <a href="#">Profile</a>
              </li>
              <li>
                <a href="#">Messages</a>
              </li>
            </ul>
          </div>
          <div class="col-md-3">
            <table class="table table-bordered table-hover">           
              <thead>
                <tr>
                  <th>#</th>
                  <th>Name</th>
                  <th>Email</th>
                  <th>Role</th>                  
                </tr>
              </thead>
              <tbody>
               <c:forEach items="${users}"  var="user">      			     
                <tr>
                  <td>${user.id }</td>
                  <td>${user.name}</td>
                  <td>${user.email}</td>
                  <td>${user.roles }</td>
                  <td> <a class="btn btn-primary" href='<c:url value="admin/${user.id}/delete"/> '>Eliminar</a></td>
                </tr>
                  </c:forEach>                
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </div>

<%@include file="include/footer.jsp"%>