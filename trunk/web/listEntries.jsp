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
        <title>JSP SVN View : Browse Repository</title>
        <link rel="stylesheet" href="css/main.css" type="text/css">
    </head>
    <body>
        
        <h2><svnview:SvnViewPathDisplayTag/></h2>
        <a href="./AllLocks.do"><fmt:message key="show.all.locks"/></a>
        <table border="1">
            <thead>
                <tr>
                    <th width="150" align="left"><fmt:message key="name"/></th>
                    <th width="50" align="left"><fmt:message key="revision"/></th>
                    <th width="150" align="left"><fmt:message key="date"/></th>
                    <th width="100" align="left"><fmt:message key="author"/></th>
                    <th width="50" align="left"><fmt:message key="locked"/></th>
                    <th width="100" align="left"><fmt:message key="lock.owner"/></th>
                    <th width="50" align="left"><fmt:message key="needs.lock"/></th>
                    <th width="75" align="left"><fmt:message key="eol"/></th>
                    <th width="50" align="left"><fmt:message key="executable"/></th>
                    <th width="50" align="left"><fmt:message key="committed.revision"/></th>
                    <%--<th width="350" align="left"><fmt:message key="last.log.entry"/></th>--%>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="svnentry" items="${entries}">
                    <tr>
                        <td><svnview:EntryUrlTag entry="${svnentry}"/></td>
                        <td>${svnentry.revision}</td>
                        <td><fmt:formatDate value="${svnentry.date}" pattern="dd MMM yyyy, HH:mm" /></td>
                        <td>${svnentry.author}</td>
                        <td align="center"><c:choose>
                                <c:when test="${empty svnentry.lock}"><img src="../images/unlocked.JPG"></c:when>
                                <c:otherwise><img src="../images/locked.JPG"></c:otherwise>
                        </c:choose></td>
                        <td>${svnentry.lock.owner}</td>
                        <td>${entryProps[svnentry]["svn:needs-lock"]}</td>
                        <td>${entryProps[svnentry]["svn:eol-style"]}</td>
                        <td>${entryProps[svnentry]["svn:executable"]}</td>
                        <td>${entryProps[svnentry]["svn:entry:committed-rev"]}</td>
                    </tr>
                </c:forEach>  
            </tbody>
        </table>
        
    </body>
</html>
