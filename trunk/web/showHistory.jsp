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
        <link rel="stylesheet" href="../css/main.css" type="text/css">
    </head>
    <body>
        
        <h2><svnview:SvnViewPathDisplayTag/></h2>

        <c:forEach var="historyItem" items="${logEntries}">
            <p><hr><br>
            ${historyItem.revision} <fmt:formatDate value="${historyItem.date}" pattern="dd MMM yyyy, HH:mm" /> ${historyItem.author}
            <a href="ViewFile.do?path=${PathForm.path}&version=${historyItem.revision}">[View]</a><br>
            <pre>${historyItem.message}</pre>
        </c:forEach>
        <hr>
    </body>
</html>
