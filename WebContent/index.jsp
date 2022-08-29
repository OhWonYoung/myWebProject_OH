<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="css/index.css" />
<meta charset="UTF-8">
<title>메인페이지</title>
</head>
<body>
	<div id="wrap">
			<video loop autoplay muted>
				<source src="video/bg2.mp4" type="video/mp4">
			</video>
			<div id="inner-wrap">
			<%@include file="common/header.jsp"%>
			<%@include file="container/indexContainer.jsp"%>
			<%@include file="common/footer.jsp"%>
	</div>
	</div>
</body>
</html>