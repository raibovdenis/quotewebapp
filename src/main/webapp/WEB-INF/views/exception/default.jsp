<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<%@ page session="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="../layout/head.jsp"/>
</head>
<body>
<jsp:include page="../layout/header.jsp"/>
<div class="container">
    <h1>Default exception handler</h1>
    <hr/>

    <div class="b-exception-wrap">
        <div class="b-exception-item">
            <h3>Message</h3>

            <div class="alert alert-danger" role="alert">
                <p>${exception.message}</p>
            </div>
        </div>
    </div>
</div>
<jsp:include page="../layout/footer.jsp"/>
</body>
</html>
