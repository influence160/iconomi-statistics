<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>--%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
    <a href="<spring:url value='/query'  />" >query</a>
    <a href="<spring:url value='/prepared-query'  />" >prepared query</a>
</body>
</html>