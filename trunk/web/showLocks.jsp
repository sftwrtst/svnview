<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/SvnView.tld" prefix="svnview" %>
<fmt:setBundle basename="org.pittjug.svnview.ApplicationResource"/>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP SVN View : All Locks</title>
        <link rel="stylesheet" href="../css/main.css" type="text/css">
        <meta http-equiv="refresh" content="180">
    </head>
    <body>
        
        <h2><fmt:message key="all.locks"/></h2>
        <!--<a href="./ShowEntry.do?path=">Browse</a>--> <a href="./AllLocks.do?orderBy=${param.orderBy}&asc=${param.asc}"><img src="../images/refresh.jpg" border="0" alt="Refresh"></a>
        <table border="1">
            <thead>
                <tr>
                    <th><fmt:message key="file"/> <svnview:LockSortArrowsTag type="name" asc="${param.asc}" orderBy="${param.orderBy}"/></th>
                    <th><fmt:message key="date.locked"/> <svnview:LockSortArrowsTag type="date" asc="${param.asc}" orderBy="${param.orderBy}"/></th>
                    <th><fmt:message key="lock.owner"/> <svnview:LockSortArrowsTag type="owner" asc="${param.asc}" orderBy="${param.orderBy}"/></th>
                    <th><fmt:message key="comment"/></th>
                </tr>
            </thead>
            <tbody>
                
                <c:forEach var="lock" items="${allLocks}">
                    <tr>
                        <td>
                            ${lock.path}
                        </td>                
                        <td>
                            <fmt:formatDate value="${lock.creationDate}" pattern="dd MMM yyyy, HH:mm" />
                        </td>
                        <td>
                            ${lock.owner}
                        </td>
                        <td>
                            ${lock.comment}
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>   
    </body>
</html>
