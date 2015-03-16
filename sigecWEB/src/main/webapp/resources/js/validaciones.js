/**
 * @author roberto
 * Valida que los valores ingresados en las cajas de texto sean solo numericos
 * */
function validateNumPP(e) {
	tecla = (document.all) ? e.keyCode : e.which;
	if (tecla == 8) {
		return true;
	} // Tecla de retroceso (para poder borrar)

	patron = /\d/;

	te = String.fromCharCode(tecla);
	return patron.test(te);
}