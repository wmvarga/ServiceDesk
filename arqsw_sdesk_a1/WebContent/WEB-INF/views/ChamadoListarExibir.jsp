<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

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
	        <div class="table-responsive col-md-12 col-lg-12">
		        <table class="table table-striped table-bordered">
					<thead>
						<tr>
				    		<th scope="col">Número</th>
						    <th scope="col">Descrição</th>
						    <th scope="col">Abertura</th>
						    <th scope="col">Fechamento</th>
						    <th scope="col">Status</th>
						    <th scope="col">Tempo</th>
				   		</tr>
				  	</thead>
					<tbody>
				    	<c:forEach var="chamado" items="${chamados }">
					    	<tr>
					      		<th scope="row">${chamado.id }</th>
					      		<td>${chamado.descricao }</td>
					      		<td>${chamado.dt_abertura }</td>
					      		<td>@${chamado.dt_fechamento }</td>
					      		<td>@${chamado.status }</td>
					    	</tr>
				    	</c:forEach>
				  	</tbody>
				</table>
			</div>
        </c:if>
        <c:if test="${empty chamados }">
        	<div class="alert alert-info" role="alert">Não há chamados nesta fila.</div>
        </c:if>
        <div class="row">
            <div class="col-md-12">
            	<a href="listar_filas_exibir" class="btn btn-default">Voltar</a>
			</div>
        </div>
    </div>
    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
</body>

</html>