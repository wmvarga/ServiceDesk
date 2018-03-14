 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <nav class="navbar navbar-default navbar-fixed-top">
        <div class="container-fluid">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="index">Sistema de Chamados (RA: 81612389)</a>
            </div>
            <div id="navbar" class="navbar-collapse collapse">
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="criar_novo_chamado">Novo Chamado</a>
                    </li> 
                    <li><a href="listar_filas_exibir">Consultar Chamados</a>
                    </li>
                    <c:if test="${not empty usuarioLogado}">
	                    <li><a href="efetuar_logout">Logout</a>
	                    </li>
                    </c:if>
                    <c:if test="${empty usuarioLogado}">
	                    <li><strong>${usuarioLogado.username }</strong></a>
	                    </li>
	                    <li><a href="loginForm">Login</a>
	                    </li>
                    </c:if>
                </ul>
            </div>
        </div>
    </nav>