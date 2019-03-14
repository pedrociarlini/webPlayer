<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="header.jsp"></jsp:include>

<jsp:useBean id="mainBuss" class="webPlayer.business.MainBusiness" scope="application"></jsp:useBean>

<div id="body">
	<script type="text/javascript">
		document.title = "Tocando :: " + document.title;
	</script>
	<form action="/tocar" method="post">
		<div class="row" id="musicaTocando">
			<div class="col-sm-12">
				<c:if test="${empty mainBuss.musicaTocando.nome}" var="semMusica">
					Sem música tocando...
				</c:if>
				<c:if test="${!semMusica}">
					Tocando agora: <c:out value="${mainBuss.musicaTocando.nome}" />
				</c:if>
			</div>
		</div>
		<div class="row" id="musicaTocando">
			<div class="col-sm-12">
				<br> <br> Volume
				<button type="submit" value="Baixar volume" name="comando" class="btn btn-default btn-md">
					<span class="glyphicon glyphicon-volume-down" aria-hidden="true"></span>
				</button>
				<button type="submit" value="Aumentar volume" name="comando" class="btn btn-default btn-md">
					<span class="glyphicon glyphicon-volume-up" aria-hidden="true"></span>
				</button>
				<div class="progress" id="volume">
					<div class="progress-bar" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100"
						style="width: ${Math.round(mainBuss.volumeAtual * 100)}%;">${Math.round(mainBuss.volumeAtual * 100)}%</div>
				</div>
			</div>
		</div>
		<div class="row" id="musicaTocando">
			<div class="col-sm-12">
				<button type="submit" value="Música anterior" name="comando" class="btn btn-default btn-md">
					<span class="glyphicon glyphicon-fast-backward aria-hidden="true"></span>
				</button>
				<button type="submit" value="Play" name="comando" class="btn btn-default btn-md">
					<span class="glyphicon glyphicon-play" aria-hidden="true"></span>
				</button>
				<button type="submit" value="Pause" name="comando" class="btn btn-default btn-md">
					<span class="glyphicon glyphicon-pause" aria-hidden="true"></span>
				</button>
				<button type="submit" value="Próxima música" name="comando" class="btn btn-default btn-md">
					<span class="glyphicon glyphicon-fast-forward" aria-hidden="true"></span>
				</button>
			</div>
		</div>
	</form>
</div>
<jsp:include page="footer.jsp"></jsp:include>
