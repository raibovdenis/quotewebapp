<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<div id="b-flash-message-wrap">
    <c:if test="${!empty flashMessageSuccess}">
        <div class="alert alert-success" role="alert">${flashMessageSuccess}</div>
    </c:if>

    <c:if test="${!empty flashMessageError}">
        <div class="alert alert-danger" role="alert">${flashMessageError}</div>
    </c:if>
</div>