<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:if test="${not empty param.erro}">
	<c:out value="${param.erro}" />
</c:if>

<div class="footer">
	<em>Feito por Pedro Ciarlini (C)</em>
</div>
