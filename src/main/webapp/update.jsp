<%@taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<title>Exames - Atualização</title>
<script src="js/jquery-3.3.1.slim.min.js"></script>
<script src="js/popper.min.js"></script>
<link rel="stylesheet" href="css/bootstrap.min.css">
<script src="js/bootstrap.min.js"></script>
<link rel="stylesheet" href="css/fontawesome/all.min.css">	
<style>
	#updateExame table, #updateExame td, #updateExame th {
		border:none !important;
	}
</style>
</head>
<body>
	
	<br><br>
	<div class="container">
		<div class="row">
			<div class="col">
				<h2 class="text-center">
					Exame - Edição
					<a href="index.action" class="btn btn-outline-warning"><i class="fas fa-home"></i></a>
				</h2>
			</div>
		</div>
	</div>

	<div class="container">
		<s:form action="updateExame" method="post">
			<s:hidden name="exame.id" label="ID" />
			<div class="row">
				<div class="col-4">
					<s:textfield name="exame.nomePessoa" label="Nome Pessoa" />
				</div>
				<div class="col-4">
					<s:textfield name="exame.medico" label="Médico" />
				</div>
				<div class="col-4">
					<s:textfield name="exame.tipoExame" label="Tipo Exame" />
				</div>
			</div>
			<div class="row">
				<div class="col">
					<s:submit cssClass="btn btn-outline-success" value="Atualizar" />
				</div>
			</div>
		</s:form>
	</div>

</body>
</html>