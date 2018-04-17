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
<title>Listar Chamados</title>
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/style.css" rel="stylesheet">

</head>

<body>
	<!-- Barra superior com os menus de navegação -->
	<c:import url="Menu.jsp" />
	<!-- Container Principal -->
	<div id="main" class="container">
		<h3 class="page-header">Chamado(s) da fila ${fila.nome }</h3>
		<c:if test="${not empty chamados }">
			<p>Selecione os chamados que quiser fechar.</p>
			<form class="form-inline" action="fechar_chamados" method="post">
				<div class="table-responsive col-md-12 col-lg-12">
					<table class="table table-striped table-borde red">
						<thead>
							<tr>
								<th scope="col">Fechar</th>
								<th scope="col">Número</th>
								<th scope="col">Descrição</th>
								<th scope="col">Abertura</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="chamado" items="${chamados }">
								<tr>
									<td scope="row"><input type="checkbox"
										name="${chamado.id}"></td>
									<td scope="row">${chamado.id }</td>
									<td>${chamado.descricao }</td>
									<td><fmt:formatDate value="${chamado.dt_abertura }"
											pattern="dd/MM/yyyy" /></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<div class="row">
					<div class="col-md-12">
						<button type="submit" class="btn btn-primary">Fechar
							Chamados</button>
						<a href="listar_filas_fechar_chamados" class="btn btn-default">Voltar</a>
					</div>
				</div>
			</form>
		</c:if>
		<c:if test="${empty chamados }">
			<div class="alert alert-info" role="alert">Não há chamados
				abertos nesta fila.</div>
			<div class="row">
				<div class="col-md-12">
					<a href="listar_filas_fechar_chamados" class="btn btn-default">Voltar</a>
				</div>
			</div>
		</c:if>
	</div>
	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
</body>

</html>