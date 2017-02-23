<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title tiles:fragment="title">Messages : Create</title>
<script type="text/javascript">
/* 	alert('@{/login}'); */
</script>
</head>
<body>

	<div tiles:fragment="content">
		<form name="f" th:action="@{/login}" method="post">
			<fieldset>
				<legend>Please Login</legend>
				<div th:if="${param.error}" class="alert alert-error">Invalid
					username and password.</div>
				<div th:if="${param.logout}" class="alert alert-success">You
					have been logged out.</div>
				<label for="username">Username</label> <input type="text"
					id="username" name="username" /> <label for="password">Password</label>
				<input type="password" id="password" name="password" />
				<div class="form-actions">
					<button type="submit" class="btn">Log in</button>
				</div>
			</fieldset>
		</form>
	</div>
	
</body>
</html>