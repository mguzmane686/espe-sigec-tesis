package org.espe.sigec.validacion;

/**
 * @author roberto
 * 
 */
public enum DocumentTypeEnum {
	cedula(1), ruc_natural(2), ruc_privada(3), ruc_publica(4);

	private int documentTypeEnumOrdinal;

	private DocumentTypeEnum(int ordinal) {
		setDocumentTypeEnumOrdinal(ordinal);
	}

	public int getDocumentTypeEnumOrdinal() {
		return this.documentTypeEnumOrdinal;
	}

	public void setDocumentTypeEnumOrdinal(int documentTypeEnumOrdinal) {
		this.documentTypeEnumOrdinal = documentTypeEnumOrdinal;
	}
}
