<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> -->
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>Prajina Academy</title>
	</head>
	<body>
		<form:form id="submitForm" name="submitForm" method="POST">
			<div align="center">
				<div class="form-group">
					<label class="text" for="userName">User Name</label>
					<input type="text" 
						id="userName" 
						name="userName"
						class="form-control"
						placeholder="User Name" />
					
					<label class="text" for="password">Password</label>
					<input type="password" 
						id="password"
						name="password"
						class="form-control"
						placeholder="Password" />
					
					<input type="submit" id="loginBtn" value="Login" />
				</div>
				<div id="errorDiv" style="color: red">
					<h4 id="errorMsg">${error}</h4>
				</div>
			</div>
		</form:form>
	</body>
</html>