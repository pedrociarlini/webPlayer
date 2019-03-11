<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="header.jsp" />

<jsp:useBean id="mainBuss" class="webPlayer.business.MainBusiness"></jsp:useBean>

<div id="body">
	<div id="diretorio">

		<form action="/diretorio?novoDiretorio=" method="post">
			Diretório atual: <em><c:out value="${mainBuss.diretorio}"></c:out></em> <br> Diretório: <input type="text"
				name="diretorio" value="/Users/pedrociarlini/Downloads"> <input type="submit" value="Enviar">
		</form>
		<c:if test="${not empty param.erro}">
			<c:out value="${param.erro}" />
		</c:if>
	</div>
	<div class="footer">
		<em>Feito por Pedro Ciarlini (C)</em>
	</div>

	<jsp:include page="footer.jsp"></jsp:include>
</div>