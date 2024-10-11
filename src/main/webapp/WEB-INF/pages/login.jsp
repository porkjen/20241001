<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title></title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">

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
	<div style="margin-top: 56px;">
		<h1 class="text-success" style="text-align: center;">登入</h1>
		
		<%String hint = (String)request.getSession().getAttribute("hint"); %>
		<%if( hint != null ) {%>
			<div class="alert alert-danger d-flex align-items-center" role="alert">
			  <%= hint%>
			</div>
			<% request.getSession().removeAttribute("hint"); %>
		<%} %>
		<form action="login" method="post" >
			<table style="text-align: left; margin: auto">
				<tbody>
					<tr>
						<td>帳號</td>
						<td><input type="text" name="accNum" id="accNum" required></td>
					</tr>
					<tr>
						<td>密碼</td>
						<td><input type="password" name="password" id="password" required></td>
					</tr>
					<tr>
						<td></td>
						<td style="text-align: right">
							
							<button class="btn btn-outline-success btn-sm" type="submit" >登入</button>
							<button class="btn btn-outline-success btn-sm" id="ajaxbtn" type="button" >AJAX 登入</button>
						</td>
						
					</tr>
				</tbody>
			</table>
		</form>
	</div>
	<script>
    	(function () {
            var httpRequest;
            document
              .getElementById("ajaxbtn")
              .addEventListener("click", makeRequest);

            function makeRequest() {
                httpRequest = new XMLHttpRequest();

                if (!httpRequest) {
                    alert("Giving up :( Cannot create an XMLHTTP instance");
                    return false;
                }
                httpRequest.onreadystatechange = alertContents;
                httpRequest.open("POST", "/demo/ajaxlogin");
                httpRequest.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
                let accNumValue = document.getElementById("accNum").value;
                let passwordValue = document.getElementById("password").value;
                httpRequest.send("accNum=" + encodeURIComponent(accNumValue) + "&password=" + encodeURIComponent(passwordValue));
            }

            function alertContents() {
                if (httpRequest.readyState === XMLHttpRequest.DONE) {
                    if (httpRequest.status === 200) {
                        window.location.href = "/demo/main";
                    }
                    else if(httpRequest.status === 404 ||
                            httpRequest.status === 400 ||
                            httpRequest.status === 401){
                        window.location.href = "/demo/login.jsp";
                    }
                    else {
                      alert("There was a problem with the request.");
                    }
                }
            }
        })();
    </script>
</body>
</html>