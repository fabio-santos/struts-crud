<%@taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<title>Exames - Listagem</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.2/css/all.css" integrity="sha384-oS3vJWv+0UjzBfQzYUhtDYW+Pj2yciDJxpsK1OYPAYjqT085Qq/1cq5FLXAZQ7Ay" crossorigin="anonymous">	
<style>
	#saveExame table, #saveExame td, #saveExame th {
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
					Exames 
					<button data-toggle="modal" data-target="#new-exame-modal" class="btn btn-outline-success"><i class="fas fa-plus"></i></button>
				</h2>
			</div>
		</div>
	</div>
	
	<br><br>
	<div class="container">
		<div class="row">
			<div class="col">
				<s:if test="examesList.size() == 0">
					<div class="alert alert-info text-center" role="alert">
						Nenhum Registro Encontrado
					</div>
				</s:if>
				
				<s:if test="examesList.size() > 0">
					<table class="table table-condensed">
						<thead>
							<tr>
								<th>ID</th>
								<th>Tipo de Exame</th>
								<th>Nome Pessoa</th>
								<th>Médico</th>
								<th>Criado em</th>
								<th>Editar</th>
								<th>Excluir</th>
							</tr>
						</thead>
						<tbody>
							<s:iterator value="examesList">
								<tr>
									<td><s:property value="id" /></td>
									<td><s:property value="tipoExame" /></td>
									<td><s:property value="nomePessoa" /></td>
									<td><s:property value="medico" /></td>
									<td><s:date name="createdAt" format="dd/MM/yyyy" /></td>
									<td>
				                		<a class="btn btn-outline-warning" href="editExame.action?id=<s:property value="id"/>"><i class="fas fa-edit"></i></a>
				                	</td>
									<td>
						                <a class="btn btn-outline-danger remove-item" exameid="<s:property value="id"/>" href="javascript:void(0)"><i class="fas fa-minus"></i></a>
									</td>
								</tr>
							</s:iterator>
						</tbody>
					</table>
				</s:if>
			</div>
		</div>
	</div>

	<div class="modal fade" id="new-exame-modal" tabindex="-1"
		role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Novo Exame</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<s:form action="saveExame" method="post">
						<div class="row">
							<div class="col">
								<label>Tipo de Exame</label>
								<input class="form-control" name="exame.tipoExame"/>
							</div>
						</div>
						<div class="row">
							<div class="col">
								<label>Médico</label>
								<input class="form-control" name="exame.medico"/>
							</div>
						</div>
						<div class="row">
							<div class="col">
								<label>Nome Pessoa</label>
								<input class="form-control" name="exame.nomePessoa"/>
							</div>
						</div>
					</s:form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-outline-secondary" data-dismiss="modal">Fechar</button>
					<button type="button" class="btn btn-outline-success" id="save-exame-btn">Salvar</button>
				</div>
			</div>
		</div>
	</div>
	
	<div class="modal fade" id="delete-exame-modal" tabindex="-1"
		role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Confirmação</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					Tem certeza que deseja excluir este exame?
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-outline-secondary" data-dismiss="modal">Não</button>
					<a class="btn btn-outline-danger" href="" id="delete-exame-btn">Sim</a>
				</div>
			</div>
		</div>
	</div>
	
	<script>
		$(document).ready( function() {
			$("#save-exame-btn").click( function() {
				$("form").submit();
			});
			
			$(".remove-item").click( function() {
				$("#delete-exame-btn").attr("href", "deleteExame.action?id=" + $(this).attr("exameid"));
				$("#delete-exame-modal").modal();
			});
		});
	</script>

</body>
</html>