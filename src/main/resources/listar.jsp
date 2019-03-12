<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="header.jsp"></jsp:include>

<jsp:useBean id="mainBuss" class="webPlayer.business.MainBusiness" scope="application"></jsp:useBean>
<script type="text/javascript">
	document.title = "Músicas :: " + document.title;
</script>

<div id="body">
	<form action="/tocar" method="post">
		<div class="row">
			<div class="col-sm-12">

				<input type="hidden" name="comando" value="tocar">
				<div id="musicas">
					<c:if test="${empty mainBuss.diretorio}" var="semMusica">
						Diretório ainda não configurado.
					</c:if>
					<c:if test="${!semMusica}">
						<c:forEach items="${mainBuss.listarTodasAsMusicas()}" var="musica">
							<span class="musica">${musica.caminhoCompleto}
								<button type="submit" name="caminhoCompleto" class="btn btn-default btn-sm" value="${musica.caminhoCompleto}">
									<span class="glyphicon glyphicon-play" aria-hidden="true"></span>
								</button>
							</span>
						</c:forEach>
					</c:if>


				</div>
			</div>
		</div>
	</form>
</div>
<jsp:include page="footer.jsp"></jsp:include>
