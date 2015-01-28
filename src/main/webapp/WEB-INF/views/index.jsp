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

    <h1>List Quotes</h1>
    <hr/>

    <!-- Quote wrap -->
    <div class="b-quote-list-wrap">

        <!-- Quote navigation -->
        <c:if test="${!empty page.content}">
            <div class="b-quote-navigation-wrap">
                <form class="form-inline" action="/">
                    <div class="form-group">
                        <label for="${sortByCode}">Sort by</label>
                        <select class="form-control" name="${sortByCode}" id="${sortByCode}">
                            <c:forEach items="${quoteNavigation.sortByList}" var="sortByItem">
                                <option data-param-code="${sortByCode}" data-param-value="${sortByItem}" <c:if test="${sortByItem == pageRequest.sortBy}">selected</c:if> value="${sortByItem}">${sortByItem}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="form-group">
                        <label for="${sortDirCode}">Sort dir</label>
                        <select class="form-control" name="${sortDirCode}" id="${sortDirCode}">
                            <c:forEach items="${quoteNavigation.sortDirList}" var="sortDirItem">
                                <option data-param-code="${sortDirCode}" data-param-value="${sortDirItem}" <c:if test="${sortDirItem == pageRequest.sortDir}">selected</c:if> value="${sortDirItem}">${sortDirItem}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="form-group">
                        <label for="${pageSizeCode}">Page size</label>
                        <select class="form-control" name="${pageSizeCode}" id="${pageSizeCode}">
                            <c:forEach items="${quoteNavigation.pageSizeList}" var="pageSizeItem">
                                <option data-param-code="${pageSizeCode}" data-param-value="${pageSizeItem}" <c:if test="${pageSizeItem == pageRequest.pageSize}">selected</c:if> value="${pageSizeItem}">${pageSizeItem}</option>
                            </c:forEach>
                        </select>
                    </div>
                </form>
            </div>
            <hr>
        </c:if>

        <!-- Quote list -->
        <c:choose>
            <c:when test="${!empty page.content}">
                <c:forEach items="${page.content}" var="quote">
                    <div class="panel panel-info b-quote-item">
                        <div class="panel-heading b-quote-item-meta">
                            <h3 class="panel-title">
                                <span class="b-quote-item-meta-created_at">
                                    <fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${quote.createdAt}"/>
                                </span>
                                by
                                <span class="b-quote-item-meta-author"><c:out value="${quote.user.userName}"/></span>
                            </h3>
                        </div>
                        <div class="panel-body b-quote-item-content">
                            <c:out value="${quote.content}"/>
                        </div>
                    </div>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <div class="alert alert-warning" role="alert">Sorry, no quotes are available for this order at this
                    time.
                </div>
            </c:otherwise>
        </c:choose>
    </div>

    <!-- Pagination -->
    <c:if test="${(!empty page.content) && (page.totalPages gt 1)}">
        <c:url var="prevUrl" value="/?${pageNumberCode}=${page.pageNumber - 1}" />
        <c:url var="nextUrl" value="/?${pageNumberCode}=${page.pageNumber + 1}" />

        <nav class="b-quote-list-pagination">
            <div class="pagination">
                <ul class="pagination">
                    <!-- Previous -->
                    <c:choose>
                        <c:when test="${!page.hasPreviousPage()}">
                            <li class="disabled"><a href="#">&laquo;</a></li>
                        </c:when>
                        <c:otherwise>
                            <li><a data-param-code="${pageNumberCode}" data-param-value="${page.pageNumber - 1}" href="${prevUrl}">&laquo;</a></li>
                        </c:otherwise>
                    </c:choose>

                    <!-- List -->
                    <c:forEach var="i" begin="1" end="${page.totalPages}">
                        <c:url var="pageUrl" value="/?${pageNumberCode}=${i}" />
                        <c:choose>
                            <c:when test="${i == page.pageNumber}">
                                <li class="active"><a href="#">${i}</a></li>
                            </c:when>
                            <c:otherwise>
                                <li><a data-param-code="${pageNumberCode}" data-param-value="${i}" href="${pageUrl}">${i}</a></li>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>

                    <!-- Next -->
                    <c:choose>
                        <c:when test="${!page.hasNextPage()}">
                            <li class="disabled"><a href="#">&raquo;</a></li>
                        </c:when>
                        <c:otherwise>
                            <li><a data-param-code="${pageNumberCode}" data-param-value="${page.pageNumber + 1}" href="${nextUrl}">&raquo;</a></li>
                        </c:otherwise>
                    </c:choose>
                </ul>
            </div>
        </nav>
    </c:if>

</div>
<jsp:include page="layout/footer.jsp"/>
</body>
</html>
