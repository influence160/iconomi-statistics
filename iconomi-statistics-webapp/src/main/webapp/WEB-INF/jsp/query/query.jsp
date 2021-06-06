<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link href="/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
	<link rel="stylesheet" type="text/css" href="/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="/vendor/sb-admin-2/sb-admin-2.css">
	<link rel="stylesheet" type="text/css" href="/css/core.css">
	<script src="/vendor/jquery/jquery.min.js"></script>
<title>Title</title>

</head>
<body id="page-top">

	<!-- Page Wrapper -->
	<div id="wrapper">
		<!-- End of Sidebar -->

		<!-- Content Wrapper -->
		<div id="content-wrapper" class="d-flex flex-column">

			<!-- Main Content -->
			<div id="content">

				<!-- Topbar -->
				<nav
					class="navbar navbar-expand navbar-light bg-white topbar m-5 static-top shadow">

					<ul class="navbar-nav">
						<li class="nav-item dropdown no-arrow mx-1"></li>
						<li class="nav-item dropdown no-arrow mx-1"><a
							href='<spring:url value="/"></spring:url>'>Home</a> <a
							href='<spring:url value="/query/query"></spring:url>'>Query</a></li>
					</ul>
					<!-- 				   		<div class="input-group"> -->
					<%-- 				   			<form:label path = "selectedQuery">Select a query </form:label> --%>
					<!-- 				   			<div class="input-group-append"> -->
					<%-- 				                  <form:select path = "selectedQuery" onchange="selectedQueryChange();"> --%>
					<%-- 				                     <form:option value = "value" label = ""/> --%>
					<%-- 				                     <form:options items = "${builtInQueries}" itemLabel="label" itemValue="value" /> --%>
					<%-- 				                  </form:select>  --%>
					<!-- 							</div> -->
					<!-- 						</div> -->
				</nav>

				<!-- End of Topbar -->

				<!-- Begin Page Content -->

				<div class="container-fluid">
					<div class="card shadow m-4">
						<div class="card-body">
							<form:form method="POST" action="/query" modelAttribute="query">
								<div class="form-group">
									<form:label path="selectedQuery">Select a query </form:label>
									<form:select
										class="custom-select custom-select-sm form-control form-control-sm"
										path="selectedQuery" onchange="selectedQueryChange();">
										<form:option value="value" label="" />
										<form:options items="${builtInQueries}" itemLabel="label"
											itemValue="value" />
									</form:select>
									<c:forEach items="${builtInQueries}" var="xx" varStatus="i">
										<div class="d-none">
											<span id="q_desciption_${i.index}">${builtInQueries[i.index].description}</span>
										</div>
									</c:forEach>
								</div>
								<div class="form-group">
									<form:label path="queryString">Query</form:label>
									<form:textarea cssClass="form-control form-text"
										path="queryString" cssStyle="width: 1500px; height: 100px;" />
								</div>
								<input class="btn btn-primary" type="submit" value="Execute" />
								<p class="mb-4 mt-4" id="query_desciption"></p>
							</form:form>

							<h1 class="h3 mb-2 text-gray-800">Result</h1>
							Size = ${query.result.size()}

							<div class="table-responsive">
								<table class="table table-bordered" id="dataTable" width="100%"
									cellspacing="0">
									<thead>
										<c:if test="${query.result.size() != 0}">
											<c:forEach items="${query.result.get(0).keySet()}" var="key">
												<th>${key}</th>
											</c:forEach>
										</c:if>
									</thead>
									<c:forEach items="${query.result}" var="row">
										<tr>
											<c:forEach items="${row.entrySet()}" var="entry">
												<td>${entry.value}</td>
											</c:forEach>
										</tr>
									</c:forEach>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<script type="text/javascript">
		function selectedQueryChange() {
			$("textarea[name='queryString']").val(
					$("select[name='selectedQuery']").val());
			updateDescription();
		}
		function updateDescription() {
			var selectedIndex=$("select[name='selectedQuery']")[0].selectedIndex - 1;
			$("#query_desciption").text($("#q_desciption_"+selectedIndex).text());
		}

		$(document).ready(function(){
			updateDescription();
		});
	</script>
</body>
</html>