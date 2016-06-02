<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<span class="pageTitle">
<strong>Story:</strong> ${story.name}, ${story.points} Points<br/>
</span>
<strong>Description:</strong> ${story.description}<br/>
<strong>Status:</strong>

<!-- Mark Story Complete BTN -->
<c:choose>
	<c:when test="${!story.status.isDone()}">
		Incomplete
		<form action="/app/manager/story/complete" method="GET">
			<input type="hidden" name="id" value="${story.id}"/>
			<input class="btn btn-default" type="submit" value="Mark Story Complete"/>
		</form>
		<hr/>
		<div class="task-box">
			<form action="/app/manager/story/createTask" method="POST">
				<label>Submit a New Task</label>
				<br/>
				<span class="err-msg">${task_message}</span>
				<input type="hidden" name="id" value="${story.id}"> 
				<input type="text" name="description" class="form-control" placeholder="Task Description"/>
				<input type="submit" class="btn btn-default task-submit"/>
			</form>
		</div>		
	</c:when>
	
	<c:otherwise>
		Completed
	</c:otherwise>
</c:choose>
<!-- End of Mark Story Complete BTN -->
<!-- TODO TASKS -->
<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
  <div class="panel panel-default">
  	<a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
    <div class="panel-heading" role="tab" id="headingOne">
      <h4 class="panel-title">
          TODO: 
      </h4>
    </div>
    </a>
    <div id="collapseOne" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne">
      <div class="panel-body">
		<c:forEach items="${tasks}" var="task">
			<c:if test="${task.status.status.equalsIgnoreCase(\"TODO\")}">
				<div class="trendy-box non-click col-xs-2 col-sm-2 col-md-3 col-lg-4 text-center">
					<div ><img src="${task.getUser().getImage() }" class="taskProfilePic" /></div>
					<span class="trendyBoxTitle"><h4>TASK : ${task.id}</h4></span>
					
					<label>Status</label>${task.status.status}<br/>
					<c:choose>
						<c:when test="${task.user.username != null}">
							<label>Assigned</label>${task.user.username}
						</c:when>
						<c:otherwise>
							<label>Unassigned</label>
						</c:otherwise>
					</c:choose>
					<br/><br/>
					<c:if test="${empty task.getDescription() }">
						No description provided.
					</c:if>
					${task.getDescription()}
				</div>
			</c:if>
		</c:forEach>
	  </div>
	 </div>
<!-- IN PROGRESS TASKS -->
  <div class="panel panel-default">
	  	<a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
	    <div class="panel-heading" role="tab" id="headingTwo">
	      <h4 class="panel-title">
	       
	          IN PROGRESS:
	   
	      </h4>
	    </div>
	    </a>
	    <div id="collapseTwo" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwo">
	      <div class="panel-body">		
			<c:forEach items="${tasks}" var="task">
				<c:if test="${task.status.status.equalsIgnoreCase(\"IN PROGRESS\")}">
					<div class="trendy-box non-click col-xs-2 col-sm-2 col-md-3 col-lg-4 text-center">
						<div ><img src="${task.getUser().getImage() }" class="taskProfilePic" /></div>
						<span class="trendyBoxTitle"><h4>TASK : ${task.id}</h4></span>
						
						<label>Status</label>${task.status.status}<br/>
						<c:choose>
							<c:when test="${task.user.username != null}">
								<label>Assigned</label>${task.user.username}
							</c:when>
							<c:otherwise>
								<label>Unassigned</label>
							</c:otherwise>
						</c:choose>
						<br/><br/>
						<c:if test="${empty task.getDescription() }">
							No description provided.
						</c:if>
						${task.getDescription()}
					</div>
				</c:if>
				</c:forEach>
			</div>
		</div>
	 </div>
  </div>
  <!-- TESTING TASKS -->
  <div class="panel panel-default">
	  	<a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
	    <div class="panel-heading" role="tab" id="headingThree">
	      <h4 class="panel-title">
	       
	          TESTING:
	   
	      </h4>
	    </div>
	    </a>
	    <div id="collapseThree" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingThree">
	      <div class="panel-body">		
			<c:forEach items="${tasks}" var="task">
				<c:if test="${task.status.status.equalsIgnoreCase(\"testing\")}">
					<div class="trendy-box non-click col-xs-2 col-sm-2 col-md-3 col-lg-4 text-center">
						<div ><img src="${task.getUser().getImage() }" class="taskProfilePic" /></div>
						<span class="trendyBoxTitle"><h4>TASK : ${task.id}</h4></span>
						
						<label>Status</label>${task.status.status}<br/>
						<c:choose>
							<c:when test="${task.user.username != null}">
								<label>Assigned</label>${task.user.username}
							</c:when>
							<c:otherwise>
								<label>Unassigned</label>
							</c:otherwise>
						</c:choose>
						<br/><br/>
						<c:if test="${empty task.getDescription() }">
							No description provided.
						</c:if>
						${task.getDescription()}
					</div>
				</c:if>
				</c:forEach>
			</div>
		</div>
	 </div>
  <!-- TO VERIFY TASKS -->
  <div class="panel panel-default">
	  	<a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseFour" aria-expanded="false" aria-controls="collapseFour">
	    <div class="panel-heading" role="tab" id="headingFour">
	      <h4 class="panel-title">
	       
	          TO VERIFY:
	   
	      </h4>
	    </div>
	    </a>
	    <div id="collapseFour" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingFour">
	      <div class="panel-body">	
	      
			<c:forEach items="${tasks}" var="task">
				<c:if test="${task.status.status.equalsIgnoreCase(\"TO VERIFY\")}">
					<div class="trendy-box non-click col-xs-2 col-sm-2 col-md-3 col-lg-4 text-center">
						<div >
							<c:if test="${empty task.getUser().getImage() }">
								<img src="http://www.gravatar.com/avatar/be51905497e293f3474c81d9aa1e1353" class="taskProfilePic" />
							</c:if>
							<c:if test="${not empty task.getUser().getImage() }" > 
								<img src="${task.getUser().getImage() }" class="taskProfilePic" /></div>
							</c:if>
						<span class="trendyBoxTitle"><h4>TASK : ${task.id}</h4></span>
						
						<label>Status</label>${task.status.status}<br/>
						<c:choose>
							<c:when test="${task.user.username != null}">
								<label>Assigned</label>${task.user.username}
							</c:when>
							<c:otherwise>
								<label>Unassigned</label>
							</c:otherwise>
						</c:choose>
						<%-- SHOW BUTTON LOGIC --%>
							<div id="user${task.id}" value="${user.id}"></div>
							<button id="doneBtn" class="btn btn-default" onclick="doneTask('${task.id}', this)">Mark Done</button>
							<span id="msg${task.id}"></span>
						<br/><br/>
						<c:if test="${empty task.getDescription() }">
							No description provided.
						</c:if>
						${task.getDescription()}
					</div>
				</c:if>
				</c:forEach>
			</div>
		</div>
	 </div>
  <!-- DONE TASKS -->
  <div class="panel panel-default">
	  	<a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseFive" aria-expanded="false" aria-controls="collapseFive">
	    <div class="panel-heading" role="tab" id="headingFive">
	      <h4 class="panel-title">
	       
	          DONE:
	   
	      </h4>
	    </div>
	    </a>
	    <div id="collapseFive" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingFive">
	      <div class="panel-body">	
	      
			<c:forEach items="${tasks}" var="task">
				<c:if test="${task.status.status.equalsIgnoreCase(\"done\")}">
					<div class="trendy-box non-click col-xs-2 col-sm-2 col-md-3 col-lg-4 text-center">
						<div ><img src="${task.getUser().getImage() }" class="taskProfilePic" /></div>
						<span class="trendyBoxTitle"><h4>TASK : ${task.id}</h4></span>
						
						<label>Status</label>${task.status.status}<br/>
						<c:choose>
							<c:when test="${task.user.username != null}">
								<label>Assigned</label>${task.user.username}
							</c:when>
							<c:otherwise>
								<label>Unassigned</label>
							</c:otherwise>
						</c:choose>
						<br/><br/>
						<c:if test="${empty task.getDescription() }">
							No description provided.
						</c:if>
						${task.getDescription()}
					</div>
				</c:if>
				</c:forEach>
			</div>
		</div>
	 </div>
</div>
