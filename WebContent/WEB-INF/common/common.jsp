<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String context = request.getContextPath();
    pageContext.setAttribute("context", context);
%>

<script src="${context}/js/jquery/jquery-3.3.1.js"></script>
<script src="${context }/js/canvas-nest.js"></script>
<link href="../../css/bootstrap3.3.7/bootstrap.min.css" rel="stylesheet" type="text/css"></link>
<link href="../../css/bootstrap3.3.7/bootstrap-theme.min.css" rel="stylesheet" type="text/css"></link>
<script src="../../js/bootstrap3.3.7/bootstrap.min.js" type="text/javascript"></script> 

