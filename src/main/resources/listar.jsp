<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="header.jsp"></jsp:include>

<jsp:useBean id="playlistBuss" class="webPlayer.business.PlaylistBusiness" scope="application"></jsp:useBean>
<script type="text/javascript">
	document.title = "M�sicas :: " + document.title;
</script>

<div id="body">
	<form action="/tocar" method="post">
		<div class="row">
			<div class="col-sm-12">

				<input type="hidden" name="comando" value="tocar">
				<div id="musicas">
					<c:if test="${empty playlistBuss.listMusicas()}" var="semMusica">
						Diret�rio ainda n�o configurado.
					</c:if>
					<c:if test="${!semMusica}">
						<c:set var="qtdeMusicas" value="-1" />
						<c:forEach items="${playlistBuss.listMusicas()}" var="musica">
							<c:set var="qtdeMusicas" value="${qtdeMusicas + 1}"></c:set>
							<span class="musica">${qtdeMusicas} - ${musica.caminhoCompleto}
								<button type="submit" name="numeroMusica" class="btn btn-default btn-sm" value="${qtdeMusicas}">
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
