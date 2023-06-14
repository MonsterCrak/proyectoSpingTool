//Scrip de procesos
let codigoTipo = -1;

//Asignar evento click a todos los botones con nombre de clase ".btn-editar"
$(document).on("click", ".btn-editar", function() {
	let cod;
	//obtener el codigo del medicamento actual según el boton que se presiono
	cod = $(this).parents("tr").find("td")[0].innerHTML;

	//trabajar con la funcion get para llmaar a la RUTA "buscar"
	$.get("/empleado/buscar?codigo=" + cod, function(response) {
		let puesto = response.puesto;
		$("#idCodigo").val(response.codigo).prop("readonly", true);
		//Imprimir en los controles el valor del JSON
		$("#idCodigo").val(response.codigo);
		$("#idNombre").val(response.nombre);
		$("#idApellido").val(response.apellido);
		$("#usr1").val(response.fecha_nacimiento);

		$("#idPuesto").val(puesto).change();

		$("#idSalario").val(response.salario);
		$("#idSucursal").val(response.sucursal.codigo);
		//llamar al evento change del combo idLaboratorio
		$("#idSucursal").trigger("change")
		console.log(response);
	})
})




//asignar evento clic al botón con ID btn-cerrar
$(document).on("click", "#btn-cerrar", function() {
	//resetear formulario
	$("#idRegistra").trigger("reset");
	//resetear validaciones
	$("#idRegistra").data("bootstrapValidator").resetForm(true);
})


//asignar evento change al select con ID idLaboratorio
$(document).on("change", "#idLaboratorio", function() {
	//obtener el código del laboratorio actual
	let codLab;
	codLab = $(this).val();
	//limpiar combo idTipo
	$("#idTipo").empty().append("<option value=' '>[Seleccione Tipo Medicamento]</option>");
	//get
	$.get("/medicamento/listarTipos?codigo=" + codLab, function(response) {
		//bucle
		$.each(response, function(index, item) {
			$("#idTipo").append("<option value='" + item.codigo + "'>" + item.nombre + "</option>");
		})
		//marcar el tipo de medicamento				
		$("#idTipo").val(codigoTipo);
	})
})



//Validación de BoostrapValidator

$(document).ready(function() {

	$('#idRegistra').bootstrapValidator({
		feedbackIcons: {
			valid: 'glyphicon glyphicon-ok',
			invalid: 'glyphicon glyphicon-remove',
			validating: 'glyphicon glyphicon-refresh'
		},
		fields: {

			Nombres: {
				selector: '#idNombre',
				validators: {
					notEmpty: {
						message: 'Ingrese nombre'
					}
				}
			},
			Fecha: {
				selector: '#usr1',
				validators: {
					notEmpty: {
						message: 'Seleccione fecha '
					},
					date: {
						format: 'YYYY-MM-DD',
						message: 'The format is dd/mm/yyyy'
					},
				}
			}
		}
	})
});

