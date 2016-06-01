<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="sidebar" data-uid="${user.id}">
	<img src="${user.image}" id="editUser" class="btn-sidebar"/>
	<br/>
	<c:if test="${user.role.id==2}">
		<img src="/TMS/resources/img/plus-empty.png" id="newUser" class="btn-sidebar">
	</c:if>
	<div id="logout"><a href="/TMS/logout"><img src="/TMS/resources/img/logout-icon.png" width="50px" height="50px"/></a>
	</div>
</div>

<div class="modal fade" id="nav-modal" tabindex="-1" role="dialog">
  <div class="modal-dialog">
    <div class="modal-content modal-body text-center">	
    	<h2 id="modal-title"></h2>
    	<h4 id="modal-messages"></h4>
    	
    	<label>Username</label><input type="text" id="username" placeholder="Username" class="form-control"/>
    	<label>Password</label><input type="password" id="password1" placeholder="Password" class="form-control"/>
    	<label>Re-Type Password</label><input type="password" id="password2" placeholder="Password" class="form-control"/>
    	<label>Email</label><input type="email" id="email" placeholder="Email" class="form-control"/>
    	<label>Phone</label><input type="text" id="phone" placeholder="Phone" class="form-control"/>
    	<label>Bio</label><input type="text" id="bio" placeholder="Bio" class="form-control"/>

        <button class="btn btn-default" id="modal-submit">Submit</button>
    </div><!-- /.modal-content -->
    
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->