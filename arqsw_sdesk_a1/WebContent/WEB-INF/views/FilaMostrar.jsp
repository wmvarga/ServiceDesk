<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Mostrar Fila</title>
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/style.css" rel="stylesheet">

</head>

<body>
	<!-- Barra superior com os menus de navegação -->
	<c:import url="Menu.jsp" />
	<!-- Container Principal -->
	<div id="main" class="container">
		<h3 class="page-header">Mostrar Fila</h3>
		<div class="row">
			<div class="col-md-4 text-center">
				<img alt="?" src="img/${fila.imagem}" class="img-responsive rounded">
			</div>
			<div class="col-md-4">
				<div class="row">
					<h4><strong>ID:</strong> ${fila.id }</h4>
				</div>
				<div class="row">
					<h4><strong>Nome:</strong> ${fila.nome }</h4>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-12">
				<br>
				<a href="listar_filas" class="btn btn-default">Voltar</a>
			</div>
		</div>
	</div>
	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
</body>

</html>