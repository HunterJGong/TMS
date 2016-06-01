<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<span class="pageTitle">
<strong><h2>Story: ${story.name}, ${story.points} Points<br/></h2></strong>
</span>
<strong>Description: </strong>${story.description}<br/>
<strong>Status: ${story.status.status}</strong>

<div class="row">
	<c:forEach items="${tasks}" var="task">
		<div class="trendy-box non-click col-xs-2 col-sm-2 col-md-3 col-lg-4 text-center">
			<div ><img src="${task.getUser().getImage() }" class="taskProfilePic"/></div>
			<span class="trendyBoxTitle"><h4>TASK : ${task.id}</h4></span>
			
			<div id="status${task.id}"></div>
			<div id="owner${task.id}"></div>
			
			<%-- ABILITY TO CHANGE/VIEW TASK STATUS --%>
			<c:choose>
				<%-- When your task but the story is done --%>
				<c:when test="${task.user.id==user.id && story.status.isDone()}">
					<label>Status:</label>${task.status.status}<br/>
				</c:when>
				<%-- When your task --%>
				<c:when test="${task.user.id==user.id}">
					<label>Status:</label>
					<select id="task${task.id}" onchange="taskClear(${task.id})">
						<c:forEach items="${statuses}" var="status">
							<c:choose>
								<c:when test="${status.id == task.status.id}">
									<option selected="selected" value="${status.id}">${status.status}</option>
								</c:when>
								<c:otherwise>
									<option value="${status.id}">${status.status}</option>
								</c:otherwise>
							</c:choose>
							
						</c:forEach>
					</select>
				</c:when>
				<%-- When not your task --%>
				<c:otherwise>
					<label>Status</label>${task.status.status}<br/>
				</c:otherwise>
			</c:choose>
			
			
			<%-- GRAB TASK --%>
			<c:choose>
				<%-- If task is already assigned--%>
				<c:when test="${task.user!= null}">
					<label>Owner:</label>${task.user.username}
					<c:if test="${task.user.id==user.id}">
						(you)
					</c:if>
				</c:when>
				<%-- If task is not assigned --%>
				<c:when test="${!story.status.isDone()}">
					<button id="grab${task.id}" onclick="grab(${task.id})" class="btn btn-default">Grab Task</button>
				</c:when>
				<%-- Story is done but nobody did task --%>
				<c:otherwise>
					<label>Owner:</label>None!
				</c:otherwise>
			</c:choose>			
			
			<%-- Updation logic --%>
			<c:choose>
				<c:when test="${!story.status.isDone()}">
					<%-- SHOW BUTTON LOGIC --%>
					<c:if test="${task.user!=null && task.user.id==user.id}">
						<div id="user${task.id}" value="${user.id}"></div>
						<button class="btn btn-default" onclick="updateTask('${task.id}', this)">update</button>
					</c:if>
				</c:when>
			</c:choose>
			<span id="msg${task.id}"></span>
			<br/>
			<c:if test="${empty task.getDescription() }">
				No description provided.
			</c:if>
			${task.getDescription()}
		</div>
	</c:forEach>
</div>
