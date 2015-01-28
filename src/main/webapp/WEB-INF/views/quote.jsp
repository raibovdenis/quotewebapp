<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="layout/head.jsp"/>
</head>
<body>
<jsp:include page="layout/header.jsp"/>
<div class="container">
    <jsp:include page="layout/flashMessage.jsp"/>

    <!-- Form -->
    <div class="b-quote-form-wrap">
        <h1>Quote add</h1>
        <hr/>

        <div class="panel panel-info">
            <div class="panel-heading">
                <h3 class="panel-title">Add new quote</h3>
            </div>
            <div class="panel-body">
                <fieldset>
                    <form:form class="form-horizontal" method="post" action="" commandName="quote">
                        <c:set var="userNameFormError"><form:errors path="user.userName"/></c:set>
                        <c:set var="contenFormtError"><form:errors path="content"/></c:set>

                        <div class="form-group required <c:if test="${!empty userNameFormError}">has-error</c:if>">
                            <label for="userName" class="col-sm-2 control-label">User Name</label>
                            <div class="col-sm-10">
                                <div class="row b-form-inline">
                                    <div class="col-sm-5">
                                        <form:input path="user.userName" id="userName" cssClass="form-control" placeholder="User name"/>
                                        <span class="help-block b-canceled-validate">Add new name</span>
                                    </div>
                                    <div class="col-sm-1 b-form-middle">or</div>
                                    <div class="col-sm-6">
                                        <select class="form-control b-canceled-validate" name="userList" id="userList" <c:if test="${empty userList}">disabled</c:if>>
                                            <option value="">Please select name</option>
                                            <c:if test="${!empty userList}">
                                                <c:forEach items="${userList}" var="user">
                                                    <option value="${user.id}">${user.userName}</option>
                                                </c:forEach>
                                            </c:if>
                                        </select>
                                        <span class="help-block b-canceled-validate">Select exists name</span>
                                    </div>
                                </div>
                                <c:if test="${!empty userNameFormError}">
                                    <form:errors path="user.userName" cssClass="help-block error-message" />
                                </c:if>
                            </div>
                        </div>

                        <div class="form-group required <c:if test="${!empty contenFormtError}">has-error</c:if>">
                            <label for="content" class="col-sm-2 control-label">Content</label>
                            <div class="col-sm-10">
                                <form:textarea path="content" id="content" cssClass="form-control" rows="10" placeholder="Content"/>
                                <c:if test="${!empty contenFormtError}">
                                    <form:errors path="content" cssClass="help-block error-message" />
                                </c:if>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-sm-offset-2 col-sm-10">
                                <button type="submit" class="btn btn-default">Submit</button>
                            </div>
                        </div>

                        <div class="form-group required">
                            <div class="col-sm-offset-2 col-sm-10">
                                <p><span class="control-label"></span> Required Fields</p>
                            </div>
                        </div>
                    </form:form>
                </fieldset>

            </div>
        </div>
    </div>
</div>
<jsp:include page="layout/footer.jsp"/>
</body>
</html>
