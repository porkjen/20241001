<%@page import="java.util.LinkedList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Lottery</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body class="p-3 mb-2 bg-success-subtle text-success-emphasis">
	<nav class="navbar fixed-top navbar-expand-lg bg-body-tertiary" >
	  <div class="container-fluid">
	    <a class="navbar-brand" href="index.jsp">Lottery</a>
	  </div>
	</nav>
	<div style="margin-top: 56px;">
		<h1 class="text-success" style="text-align:center">樂透選號</h1>
 
		<%LinkedList<String> errors = (LinkedList<String>)request.getAttribute("errors"); %>
			<%if(errors != null){ %>
			<ul style="color:red;font-size:0.8em">
			<table style="margin:auto;text-align:left">
				<%for(String error:errors){ %>
					<tr><td><li><%=error %></li></td></tr>
				<%} %>
			</table>
				
			</ul>
		<%} %>

		<form action="checkLottery" method="post">
			<table style="margin:auto">
				<tbody>
				<tr>
					<td>組數</td><td><input type="text" id="group" name="group" value="${param.group}"></td>
				</tr>
				<tr></tr><tr></tr>
				<tr>
					<td>排除數字</td><td><input type="text" id="exclude" name="exclude" value="${param.exclude}"></td>
				</tr>
				<tr>
					<td></td><td style="font-size:0.5em">以空格間隔!</td>
				</tr>
				<tr>
					<td></td>
					<td>
						<button class="btn btn-outline-success btn-sm" type="submit" >確認</button>
					</td>
				</tr>
				</tbody>
			</table>
		</form>
	</div>

</body>
</html>