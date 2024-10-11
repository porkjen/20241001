<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Lottery</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<style>
        .content {
            margin-top: 100px; //navbar
        }
    </style>
</head>
<body class="p-3 mb-2 bg-success-subtle text-success-emphasis">
	<nav class="navbar fixed-top navbar-expand-lg bg-body-tertiary" >
	  <div class="container-fluid">
	    <a class="navbar-brand" href="index.jsp">Lottery</a>
	  </div>
	  <div class="d-flex">
	    <a class="btn btn-success" href="logout" role="button">logout</a>
	  </div>
	</nav>
	<div class="content">
		<p style="text-align:center">
			<a class="btn btn-success" href="registerForm" role="button">註冊</a>
			<a class="btn btn-success" href="loginForm" role="button">登入</a>
			<a class="btn btn-success" href="main" role="button">開始</a>
		</p>
	</div>
	
</body>
</html>