<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
	</head>
	
	<body>
		<p>Привет ${userObj.name}!</p>
		<p>Вы ввели пароль ${userObj.password}</p>
		<p>Признак администра ${userObj.admin}</p>
	
		<form action="uploadFile" method="POST" enctype="multipart/form-data">
			Файл для загрузки (max 100 Kb): <input type="file" name="multipartFile">
			<input type="submit" value="Загрузить">
		</form>
	</body>
</html>