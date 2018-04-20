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
<title>Listar Filas</title>
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/style.css" rel="stylesheet">

</head>

<body>
	<!-- Barra superior com os menus de navegação -->
	<c:import url="Menu.jsp" />
	<!-- Container Principal -->
	<div id="main" class="container">
		<h3 class="page-header">Listar Filas</h3>
		<c:if test="${not empty filas }">
			<div class="table-responsive col-md-12 col-lg-12">
				<table class="table table-striped table-bordered">
					<thead>
						<tr>
							<th scope="col">Imagem</th>
							<th scope="col">ID</th>
							<th scope="col">Nome</th>
							<th scope="col">Ações</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="fila" items="${filas }">
							<tr>
								<td style="vertical-align: middle;"><img alt="?"
									src="img/${fila.imagem }" height="48" width="48"
									class="img-circle"></td>
								<td style="vertical-align: middle;" scope="row">${fila.id }</td>
								<td style="vertical-align: middle;">${fila.nome }</td>
								<td style="vertical-align: middle;" class="actions"><a
									class="btn btn-success btn-xs"
									href="mostrar_filal?id=${fila.id }">Exibir</a> <a
									class="btn btn-warning btn-xs"
									href="alterar_fila?id=${fila.id }">Alterar</a> <a
									class="btn btn-danger btn-xs"
									href="excluir_fila?id=${fila.id }">Excluir</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</c:if>
		<c:if test="${empty filas }">
			<div class="alert alert-info" role="alert">Não há nenhuma fila.</div>
		</c:if>
		<div class="row">
			<div class="col-md-12">
				<a href="index" class="btn btn-default">Voltar</a>
			</div>
		</div>
	</div>
	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
</body>

</html>