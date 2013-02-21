package org.espe.sigec.utils;

/**
 * @author roberto
 *
 */
public interface SigecConstantes {
	public static final String INVITACION_EMITIDA = SigecClientResourceBoundle.getString("doc_estado_invitacion_emitida");
	public static final String INVITACION_ACEPTADA = SigecClientResourceBoundle.getString("doc_estado_invitacion_aceptada");
	public static final String INVITACION_RECHAZADA = SigecClientResourceBoundle.getString("doc_estado_invitacion_rechazada");;
	
	public static final Integer MINIMO_ESTUDIANTES = SigecClientResourceBoundle.getInteger("acd_estudiante_minimo");
	public static final Integer MAXIMO_ESTUDIANTES = SigecClientResourceBoundle.getInteger("acd_estudiante_maximo");
	
	public static final String TIPO_CURSO_CORPORATIVO = SigecClientResourceBoundle.getString("acd_tipo_curso_corporativo");
	public static final String TIPO_CURSO_INDIVIDUAL = SigecClientResourceBoundle.getString("acd_tipo_curso_individual");
	
	public static final String PERFIL_PROFESOR = SigecClientResourceBoundle.getString("sg_perfil_profesor");
	public static final String PERFIL_ADMINISTRATIVO= SigecClientResourceBoundle.getString("sg_perfil_administrativo");
	public static final String PERFIL_ESTUDIANTE = SigecClientResourceBoundle.getString("sg_perfil_estudiante");
	public static final String PERFIL_INVITADO = SigecClientResourceBoundle.getString("sg_perfil_invitado");
	
	public static final String CATALOGO_COSTOS_GASTOS = SigecClientResourceBoundle.getString("acd_catalogo_costo_gastos");
	public static final String CATALOGO_DETALLE_MANUALES = SigecClientResourceBoundle.getString("acd_catalogo_detalle_mauales");
	public static final String CATALOGO_DETALLE_REFRIGERIOS = SigecClientResourceBoundle.getString("acd_catalogo_detalle_refrigerios");
	
	public static final String ESTADO_PAGADO = SigecClientResourceBoundle.getString("acd_estado_pagado");
	public static final String ESTADO_POR_PAGAR = SigecClientResourceBoundle.getString("acd_estado_debe");
	
	public static final String ESTADO_ACTIVO_BOOLEANO = SigecClientResourceBoundle.getString("acd_estado_activo_boolean");
	public static final String ESTADO_INACTIVO_BOOLEANO = SigecClientResourceBoundle.getString("acd_estado_inactivo_boolean");
}
