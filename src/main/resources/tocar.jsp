<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="header.jsp"></jsp:include>

<jsp:useBean id="mainBuss" class="webPlayer.business.MainBusiness"></jsp:useBean>

<div id="body">
	<script type="text/javascript">
		document.title = "Tocando :: " + document.title;
	</script>
	<form action="/tocar" method="post">
		<div id="musicaTocando">
			<c:if test="${empty mainBuss.musicaTocando}" var="semMusica">
				Sem música tocando...
			</c:if>
			<c:if test="${!semMusica}">
			Tocando agora: <c:out value="${mainBuss.musicaTocando}" />
				<br>
				<br>
				<input type="submit" value="Pause" name="comando">
				<input type="submit" value="Play" name="comando">
				<input type="submit" value="Baixar volume" name="comando">
				<input type="submit" value="Aumentar volume" name="comando">
			</c:if>
		</div>
	</form>
</div>
<jsp:include page="footer.jsp"></jsp:include>
