<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div id="errorMsg">
	<c:if test="${not empty param.erro}">
		<c:out value="${param.erro}" />
	</c:if>
</div>

<%--Essa div abre no header --%>
</div>
<div class="panel-footer footer text-center">
	<em>Feito por Pedro Ciarlini (C)</em>
</div>
</div>
</div>
</body>
</html>