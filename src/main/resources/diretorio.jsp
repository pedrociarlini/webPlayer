<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="header.jsp" />

<jsp:useBean id="mainBuss" class="webPlayer.business.MainBusiness" scope="application" />

<div id="body">
	<form action="/diretorio?novoDiretorio=" method="post">
		<div class="row">
			<div class="col-sm-12">
				Diretório atual: <br> <input class="form-control" type="text" readonly="readonly" value="${mainBuss.diretorio}">
			</div>
		</div>
		<div class="row">
			<div class="col-sm-12">
				Novo Diretório:<br> <input type="text" name="diretorio" class="form-control"
					value="/Users/pedrociarlini/Downloads"> <input type="submit" value="Enviar" class="btn btn-default btn-md">
			</div>
		</div>
	</form>
</div>
<jsp:include page="footer.jsp"></jsp:include>
