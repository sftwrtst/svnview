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
        <script src="../js/prettify.js" type="text/javascript"
                onerror="alert('Error: failed to load ' + this.src)"></script>
        <link rel="stylesheet" type="text/css" href="../css/prettify.css" />
        <link rel="stylesheet" href="../css/main.css" type="text/css">
    </head>
    <body onload="prettyPrint()" >
        <h2><svnview:SvnViewPathDisplayTag/></h2>
        <code class="prettyprint">${fileContents}</code>
    </body>
</html>    
