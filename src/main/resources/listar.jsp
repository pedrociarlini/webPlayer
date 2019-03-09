<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="header.jsp"></jsp:include>

<jsp:useBean id="mainBuss" class="webPlayer.business.MainBusiness"></jsp:useBean>

<body>
<script type="text/javascript">
	document.title = "Músicas :: " + document.title;  
</script>
	<div id="musicas">
		<form action="/tocar" method="post">
			<c:forEach items="${mainBuss.listarTodasAsMusicas()}" var="musica">
				${musica.nome} - ${musica.caminhoCompleto}
			</c:forEach>
		</form>

	</div>

	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>