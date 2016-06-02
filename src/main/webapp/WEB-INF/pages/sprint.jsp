<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script src="https://code.highcharts.com/highcharts.js"></script>
<script src="https://code.highcharts.com/modules/exporting.js"></script>
<span class="pageTitle">
<h2>Sprint Started - <fmt:formatDate type="date" dateStyle="medium" value="${sprint.start}"/></h2>
</span>
<br/>
<button class="btn btn-default" id="burndown">View Burndown</button>
<button class="btn btn-default" id="lanes">View Lanes</button>

<c:if test="${user.role.id==2}">
	<div class="task-box">
		<form action="/app/manager/sprint/createStory" method="POST">
			<label>Submit a New Story</label>
			<c:forEach items="${errors}" var="error">
				<br/><span class="err-msg">${error}</span>
			</c:forEach>
			<input type="text" name="name" class="form-control" placeholder="Name"/>
			<input type="text" name="description" class="form-control" placeholder="Description"/>
			<input type="text" name="points" class="form-control points-box" placeholder="# Points"/>
			<input type="submit" class="btn btn-default task-submit"/>
		</form>
	</div>
</c:if>

<div class="row">
<div>
	<span class="pageTitle"><h2>Stories:</h2></span>
</div>
	<c:forEach items="${stories}" var="story">
		<div class="col-sm-4 trendy-box text-center" onclick="nav('story',${story.id}, ${user.role.id})" >
			<span class="trendyBoxTitle">${story.name}</span><br/>
			${story.points} Points<br/>
			<c:choose>
				<c:when test="${story.status.isDone()}">
					${story.status.status}
				</c:when>
				<c:otherwise>
					In progress
				</c:otherwise>
			</c:choose>
			<hr/>
			${story.description}<br/>
		</div>
	</c:forEach>
</div>



<!-- Burndown -->
<div class="modal fade" id="burndown-modal" tabindex="-1" role="dialog">
  <div class="chart-dialog">
    <div class="modal-content chart-modal-body text-center">	
    	<h2 id="modal-title"></h2>
    	<h4 id="modal-messages"></h4>
    	<br/>
		<div id="burndown-container"></div>
    </div>
  </div>
</div>

<!-- Lanes -->
<div class="modal fade" id="lanes-modal" tabindex="-1" role="dialog">
  <div class="chart-dialog">
    <div class="modal-content chart-modal-body text-center">	
    	<h2 id="modal-title"></h2>
    	<h4 id="modal-messages"></h4>
    	<br/>
    	<div id="lane-container"></div>
    </div>
  </div>
</div>