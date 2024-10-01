<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Set"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Lottery Result</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body class="p-3 mb-2 bg-success-subtle text-success-emphasis">
	<nav class="navbar fixed-top navbar-expand-lg bg-body-tertiary" >
	  <div class="container-fluid">
	    <a class="navbar-brand" href="index.jsp">Lottery</a>
	  </div>
	</nav>
	<div style="margin-top: 56px; text-align: center">
		<h2 class="text-success" style="text-align:center">樂透號碼產生結果</h2>
		<table border="0" style="margin:auto; text-align: center; width: 50%" class="table">
			<tbody>
				<% ArrayList<Set<Integer>> results = (ArrayList<Set<Integer>>)request.getAttribute("results");%>
				<% for(Set<Integer> s : results){ %>
					<tr>
					<% for(int i : s){%>
						<td><%=i%></td>
					<% }%>
					</tr>
				<% }%>
			</tbody>
		</table>
		<br/>
		<a class="btn btn-success" href="index.jsp" role="button">回首頁</a>
	</div>
</body>
</html>