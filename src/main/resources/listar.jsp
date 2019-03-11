<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="header.jsp"></jsp:include>

<jsp:useBean id="mainBuss" class="webPlayer.business.MainBusiness"></jsp:useBean>

<div id="body">
	<script type="text/javascript">
		document.title = "Músicas :: " + document.title;
	</script>
	<form action="/tocar" method="post">
		<input type="hidden" name="comando" value="tocar">
		<div id="musicas">
			<c:if test="${empty mainBuss.diretorio}" var="semMusica">
				Diretório ainda não configurado.
			</c:if>
			<c:if test="${!semMusica}">
				<c:forEach items="${mainBuss.listarTodasAsMusicas()}" var="musica">
					<span class="musica">${musica.nome} <input type="submit" name="caminhoCompleto"
						value="${musica.caminhoCompleto}">
					</span>
				</c:forEach>
			</c:if>


		</div>
	</form>
</div>
<jsp:include page="footer.jsp"></jsp:include>
