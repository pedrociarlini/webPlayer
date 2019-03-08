<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="utf-8">
<title>Diretorio :: WebPlayer :: 0.1 beta</title>
</head>
<div id="diretorio">
	<form action="/diretorio?novoDiretorio=" method="post">
		Diretório: <input type="text" name="diretorio" value="/Users/pedrociarlini/Downloads"> <input type="submit"
			value="Enviar">
	</form>
	<c:if test="${not empty param.erro}">
		<c:out value="${param.erro}" />
	</c:if>
</div>
<div class="footer">
	<em>Feito por Pedro Ciarlini (C)</em>
</div>
</html>