package org.espe.sigec.validacion;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

/**
 * @author roberto
 * 
 */
public class CedulaValidacion {
	private static final CedulaValidacion CEDULA_VALIDACION = new CedulaValidacion();

	public static CedulaValidacion getInstancia() {
		return CEDULA_VALIDACION;
	}

	public boolean validarCedula(String numero) throws Exception {
		try {
			validarInicial(numero, 10);
			validarCodigoProvincia(numero.substring(0, 2));
			validarTercerDigito(String.valueOf(numero.charAt(2)),
					DocumentTypeEnum.cedula);
			algoritmoModulo10(numero,
					Integer.parseInt(String.valueOf(numero.charAt(9))));
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	public boolean validarRucPersonaNatural(String numero) throws Exception {
		try {
			validarInicial(numero, 13);
			validarCodigoProvincia(numero.substring(0, 2));
			validarTercerDigito(String.valueOf(numero.charAt(2)),
					DocumentTypeEnum.ruc_natural);
			validarCodigoEstablecimiento(numero.substring(10, 13));
			algoritmoModulo10(numero.substring(0, 9),
					Integer.parseInt(String.valueOf(numero.charAt(9))));
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	public boolean validarRucSociedadPrivada(String numero) throws Exception {
		try {
			validarInicial(numero, 13);
			validarCodigoProvincia(numero.substring(0, 2));
			validarTercerDigito(String.valueOf(numero.charAt(2)),
					DocumentTypeEnum.ruc_privada);
			validarCodigoEstablecimiento(numero.substring(10, 13));
			algoritmoModulo11(numero.substring(0, 9),
					Integer.parseInt(String.valueOf(numero.charAt(9))),
					DocumentTypeEnum.ruc_privada);
		} catch (Exception e) {
			return false;
		}

		return true;
	}

	protected boolean validarInicial(String numero, int caracteres)
			throws Exception {
		if (StringUtils.isEmpty(numero)) {
			throw new Exception("Valor no puede estar vacio");
		}

		if (!NumberUtils.isDigits(numero)) {
			throw new Exception("Valor ingresado solo puede tener dígitos");
		}

		if (numero.length() != caracteres) {
			throw new Exception("Valor ingresado debe tener " + caracteres + " caracteres");
		}

		return true;
	}

	protected boolean validarCodigoProvincia(String numero) throws Exception {
		if ((Integer.parseInt(numero) < 0) || (Integer.parseInt(numero) > 24)) {
			throw new Exception(
					"Codigo de Provincia (dos primeros dígitos) no deben ser mayor a 24 ni menores a 0");
		}

		return true;
	}

	protected boolean validarTercerDigito(String numero, DocumentTypeEnum tipo)
			throws Exception {
		switch (tipo.getDocumentTypeEnumOrdinal()) {
		case 1:
		case 2:
			if ((Integer.parseInt(numero) < 0)
					|| (Integer.parseInt(numero) > 5)) {
				throw new Exception(
						"Tercer dígito debe ser mayor o igual a 0 y menor a 6 para cédulas y RUC de persona natural ... permitidos de 0 a 5");
			}
			break;
		case 3:
			if (Integer.parseInt(numero) != 9) {
				throw new Exception(
						"Tercer dígito debe ser igual a 9 para sociedades privadas");
			}

			break;
		case 4:
			if (Integer.parseInt(numero) != 6) {
				throw new Exception(
						"Tercer dígito debe ser igual a 6 para sociedades públicas");
			}
			break;
		default:
			throw new Exception("Tipo de Identificacion no existe.");
		}

		return true;
	}

	protected boolean algoritmoModulo10(String digitosIniciales,
			int digitoVerificador) throws Exception {
		Integer[] arrayCoeficientes = { Integer.valueOf(2), Integer.valueOf(1),
				Integer.valueOf(2), Integer.valueOf(1), Integer.valueOf(2),
				Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(1),
				Integer.valueOf(2) };

		Integer[] digitosInicialesTMP = new Integer[digitosIniciales.length()];
		int indice = 0;
		for (char valorPosicion : digitosIniciales.toCharArray()) {
			digitosInicialesTMP[indice] = NumberUtils.createInteger(String
					.valueOf(valorPosicion));
			indice++;
		}

		int total = 0;
		int key = 0;
		for (Integer valorPosicion : digitosInicialesTMP) {
			if (key < arrayCoeficientes.length) {
				valorPosicion = Integer.valueOf(digitosInicialesTMP[key]
						.intValue() * arrayCoeficientes[key].intValue());

				if (valorPosicion.intValue() >= 10) {
					char[] valorPosicionSplit = String.valueOf(valorPosicion)
							.toCharArray();
					valorPosicion = Integer.valueOf(Integer.parseInt(String
							.valueOf(valorPosicionSplit[0]))
							+ Integer.parseInt(String
									.valueOf(valorPosicionSplit[1])));
					System.out.println(valorPosicion);
				}
				total += valorPosicion.intValue();
			}

			key++;
		}
		int residuo = total % 10;
		int resultado;
		if (residuo == 0)
			resultado = 0;
		else {
			resultado = 10 - residuo;
		}

		if (resultado != digitoVerificador) {
			throw new Exception(
					"Dígitos iniciales no validan contra Dígito Idenficador");
		}

		return true;
	}

	protected boolean validarCodigoEstablecimiento(String numero)
			throws Exception {
		if (Integer.parseInt(numero) < 1) {
			throw new Exception("Código de establecimiento no puede ser 0");
		}
		return true;
	}

	protected boolean algoritmoModulo11(String digitosIniciales,
			int digitoVerificador, DocumentTypeEnum tipo) throws Exception {
		Integer[] arrayCoeficientes = (Integer[]) null;

		switch (tipo.getDocumentTypeEnumOrdinal()) {
		case 3:
			arrayCoeficientes = new Integer[] { Integer.valueOf(4),
					Integer.valueOf(3), Integer.valueOf(2), Integer.valueOf(7),
					Integer.valueOf(6), Integer.valueOf(5), Integer.valueOf(4),
					Integer.valueOf(3), Integer.valueOf(2) };
			break;
		case 4:
			arrayCoeficientes = new Integer[] { Integer.valueOf(3),
					Integer.valueOf(2), Integer.valueOf(7), Integer.valueOf(6),
					Integer.valueOf(5), Integer.valueOf(4), Integer.valueOf(3),
					Integer.valueOf(2) };
			break;
		default:
			throw new Exception("Tipo de Identificacion no existe.");
		}

		Integer[] digitosInicialesTMP = new Integer[digitosIniciales.length()];
		int indice = 0;
		for (char valorPosicion : digitosIniciales.toCharArray()) {
			digitosInicialesTMP[indice] = NumberUtils.createInteger(String
					.valueOf(valorPosicion));
			indice++;
		}

		int total = 0;
		int key = 0;
		for (Integer valorPosicion : digitosInicialesTMP) {
			if (key < arrayCoeficientes.length) {
				valorPosicion = Integer.valueOf(digitosInicialesTMP[key]
						.intValue() * arrayCoeficientes[key].intValue());

				if (valorPosicion.intValue() >= 10) {
					char[] valorPosicionSplit = String.valueOf(valorPosicion)
							.toCharArray();
					valorPosicion = Integer.valueOf(Integer.parseInt(String
							.valueOf(valorPosicionSplit[0]))
							+ Integer.parseInt(String
									.valueOf(valorPosicionSplit[1])));
					System.out.println(valorPosicion);
				}
				total += valorPosicion.intValue();
			}

			key++;
		}

		int residuo = total % 11;
		int resultado;
		if (residuo == 0)
			resultado = 0;
		else {
			resultado = 11 - residuo;
		}

		if (resultado != digitoVerificador) {
			throw new Exception(
					"Dígitos iniciales no validan contra Dígito Idenficador");
		}

		return true;
	}

}
