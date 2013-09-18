package org.espe.sigec.web.reportes;

import java.io.IOException;

import javax.faces.context.FacesContext;

import org.apache.poi.xwpf.usermodel.BreakType;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.espe.sigec.model.entities.ContratoProfesor;
import org.espe.sigec.web.utils.FacesUtils;
import org.espe.sigec.web.utils.ReportesUtil;

public class ReporteFlujoContrato {
	private String fecha_de_aceptacion;
	private XWPFDocument xwpfDocumentFlujoContra;

	public ReporteFlujoContrato() {
		xwpfDocumentFlujoContra = new XWPFDocument();
	}
	
	public ReporteFlujoContrato(ContratoProfesor contratoProfesor){
		xwpfDocumentFlujoContra = new XWPFDocument();
	}

	public void reporteContratoP1() {
		XWPFParagraph paragraphOne =  xwpfDocumentFlujoContra.createParagraph();
		XWPFRun paragraphOneRunOne = paragraphOne.createRun();
		ReportesUtil.getInstance().agregarEstilos(paragraphOneRunOne);

		ReportesUtil.getInstance().addBreakSpace(paragraphOneRunOne, 6);
		
		ReportesUtil.getInstance().addText(paragraphOneRunOne,"Sangolquí, ${fecha_de_invitacion}");
		ReportesUtil.getInstance().addText(paragraphOneRunOne,"Oficio No. ${${N_invitacion}");
		ReportesUtil.getInstance().addText(paragraphOneRunOne,"Señor (a)");
		ReportesUtil.getInstance().addText(paragraphOneRunOne,"${nombre_del_proveedor}");
		ReportesUtil.getInstance().addText(paragraphOneRunOne,"Presente.-");

		ReportesUtil.getInstance().addBreakSpace(paragraphOneRunOne, 1);
		ReportesUtil.getInstance().addText(paragraphOneRunOne,"De mi consideración:");
		
		paragraphOne = xwpfDocumentFlujoContra.createParagraph();
		paragraphOneRunOne = paragraphOne.createRun();
		paragraphOne.setAlignment(ParagraphAlignment.BOTH);
		
		ReportesUtil.getInstance().addText(paragraphOneRunOne,"Luego de expresar a usted un cordial saludo deseo extender la invitación para que participe como facilitador en el curso de  “${NOMBRE_DEL_CURSO}”,  del ${fecha_curso}, con una duración de ${n_horas} horas,  a impartirse en el ${lugar_a_dictarse},  por este servicio de capacitación se le reconocerá el valor total de $   ${valor_a_pagar} ${pp} menos retenciones de Ley.");
		ReportesUtil.getInstance().addBreakSpace(paragraphOneRunOne, 1);
		ReportesUtil.getInstance().addText(paragraphOneRunOne,"El contenido del curso deberá ser propuesto por el facilitador en función de los lineamientos proporcionados por la Unidad de Educación Continua, así como también el original del material de apoyo para los estudiantes el cual será previamente revisado y aprobado.");
		ReportesUtil.getInstance().addBreakSpace(paragraphOneRunOne, 1);
		ReportesUtil.getInstance().addText(paragraphOneRunOne,"El instructor se sujetará al Reglamento de la ESPE, en lo que corresponda al régimen disciplinario, deberes y derechos.");
		ReportesUtil.getInstance().agregarEstilos(paragraphOneRunOne);
		

		paragraphOne = xwpfDocumentFlujoContra.createParagraph();
		paragraphOneRunOne = paragraphOne.createRun();
		paragraphOne.setAlignment(ParagraphAlignment.LEFT);
		
		ReportesUtil.getInstance().addBreakSpace(paragraphOneRunOne, 2);
		paragraphOneRunOne.setText("Atentamente,");
		ReportesUtil.getInstance().addBreakSpace(paragraphOneRunOne, 4);
		ReportesUtil.getInstance().addText(paragraphOneRunOne,"Ing. Karla Benavides");
		paragraphOneRunOne.setText("DIRECTORA UNIDAD DE EDUCACIÓN CONTINUA");
		
		ReportesUtil.getInstance().agregarEstilos(paragraphOneRunOne);
		paragraphOneRunOne.addBreak(BreakType.PAGE);
	}
	
	public void reporteContratoP2() {
		XWPFParagraph paragraphOne =  xwpfDocumentFlujoContra.createParagraph();
		XWPFRun paragraphOneRunOne = paragraphOne.createRun();
		paragraphOneRunOne.setItalic(Boolean.TRUE);
		paragraphOneRunOne.setFontFamily("Bookman Old Style");
		paragraphOneRunOne.setFontSize(12);

		ReportesUtil.getInstance().addBreakSpace(paragraphOneRunOne, 8);
		
		paragraphOneRunOne.setText("Sangolquí, ${fecha_de_aceptacion}");
		
		ReportesUtil.getInstance().addBreakSpace(paragraphOneRunOne, 2);
		
		ReportesUtil.getInstance().addText(paragraphOneRunOne,"Sres.");
		ReportesUtil.getInstance().addText(paragraphOneRunOne,"ESCUELA POLITÉCNICA DEL EJÉRCITO");
		ReportesUtil.getInstance().addText(paragraphOneRunOne,"Presente.-");
		ReportesUtil.getInstance().addBreakSpace(paragraphOneRunOne, 2);
		ReportesUtil.getInstance().addText(paragraphOneRunOne,"De mi consideración:");
		ReportesUtil.getInstance().addBreakSpace(paragraphOneRunOne, 2);
		ReportesUtil.getInstance().addText(paragraphOneRunOne,"En referencia al oficio No. ${N_inivitacion} mediante el cual me invita a participar como facilitador en el cursode ${NOMBRE_DEL_CURSO}, cuya fecha de ejecución está prevista del ${fecha_curso}, con una duración de ${n_horas} horas, por medio del presente deseo dejar constancia de mi agradecimiento y aceptación a la propuesta.");
		ReportesUtil.getInstance().addBreakSpace(paragraphOneRunOne, 4);
		ReportesUtil.getInstance().addText(paragraphOneRunOne,"Atentamente,");
		ReportesUtil.getInstance().addBreakSpace(paragraphOneRunOne, 4);
		ReportesUtil.getInstance().addText(paragraphOneRunOne,"${nombre_del_proveedor}");
		paragraphOneRunOne.setText("${cedula}");
		ReportesUtil.getInstance().addBreakSpace(paragraphOneRunOne, 3);
		paragraphOneRunOne.addBreak(BreakType.PAGE);
		// document.write(FacesUtils.getTempletaDescargaReporte("reportepoi.doc"));
	}
	
	public void reporteContratoP3(){

		XWPFParagraph paragraphOne =  xwpfDocumentFlujoContra.createParagraph();
		XWPFRun paragraphOneRunOne = paragraphOne.createRun();
		paragraphOneRunOne.setText("CONTRATO No.${numero_de_contrato} -SC-ESPE-UEC");
		paragraphOneRunOne.setFontFamily("Bookman Old Style");
		
		paragraphOneRunOne.addBreak();
		paragraphOneRunOne.setText("CONTRATO DE PRESTACIÓN DE SERVICIOS PROFESIONALES DE EDUCACIÓN CONTINUA A CELEBRARSE ENTRE LA ESCUELA POLITÉCNICA DEL EJÉRCITO Y EL (LA) ${nombre_del_proveedor}");
		
		ReportesUtil.getInstance().addBreakSpace(paragraphOneRunOne, 3);
		paragraphOneRunOne.setText("Intervienen en la celebración del presente contrato, por una parte, la Escuela Politécnica del Ejército, legalmente representada por el señor Cnel. E.M.C.,${nombre_sr_Vicerrector} en calidad de Vicerrector Académico;  y delegado del rector representante legal de la ESPE; y, de otro lado, el (la) ,${nombre_del_proveedor} , de nacionalidad ecuatoriana,  quien en lo posterior se denominará el (la) contratado (a), partes legalmente capaces para contraer obligaciones, que convienen en celebrar el presente contrato, al tenor de las siguientes cláusulas:");
		ReportesUtil.getInstance().addBreakSpace(paragraphOneRunOne, 3);

		paragraphOneRunOne.setText("PRIMERA.- ANTECEDENTES:");
		ReportesUtil.getInstance().addBreakSpace(paragraphOneRunOne, 3);

		paragraphOneRunOne.setText("1.	Invitación formulada mediante oficio No.${N_invitacion} ");
		paragraphOneRunOne.addBreak();
		paragraphOneRunOne.setText("2.	Aceptación de los contratistas");
		paragraphOneRunOne.addBreak();
		paragraphOneRunOne.setText("3.	Certificación Presupuestaria No. ,${N_certificacion}  del, ${fecha_de_certificacion} mediante la cual se informa que existe disponibilidad económica en la Partida Presupuestaria No.${NOMBRE_D_ELA_PP_}, por el valor de U.S.D. $ ${valor} ${F9} para financiar el pago de la contratación al personal de instructores que participarán en la capacitación continua.");
		paragraphOneRunOne.addBreak();
		

		paragraphOneRunOne.setText("SEGUNDA.- DOCUMENTOS HABILITANTES:");
		paragraphOneRunOne.addBreak();
		paragraphOneRunOne.setText("1.	Certificación Presupuestaria No. ${N_certificacion}");
		paragraphOneRunOne.addBreak();
		paragraphOneRunOne.setText("2.	Los demás que constan en la cláusula Antecedentes; y, ");
		paragraphOneRunOne.addBreak();
		paragraphOneRunOne.setText("3.	Documentos del contratista");
		paragraphOneRunOne.addBreak();


		paragraphOneRunOne.setText("TERCERA.- OBJETO:");
		paragraphOneRunOne.addBreak();
		paragraphOneRunOne.setText("Con los antecedentes expuestos, la ESPE contrata los servicios del (la) ${nombre_del_proveedor}, en calidad de instructor para que dicte la materia de, ${NOMBRE_DEL_CURSO} en el ${lugar_a_dictarse}");
		paragraphOneRunOne.addBreak();

		paragraphOneRunOne.setText("CUARTA.- PRECIO:");
		paragraphOneRunOne.addBreak();
		paragraphOneRunOne.setText("Por los servicios de instructor que se contrata, la Escuela Politécnica del Ejército pagará al (la) Contratado (a), el valor único de U.S.D. ${valor_a_pagar} ${pp} sujeto a retenciones de Ley.  Este valor se pagará al finalizar la capacitación previo informe del Coordinador de Unidad de Educación Continua y la Orden de Pago del Vicerrector Académico.");
		paragraphOneRunOne.addBreak();

		paragraphOneRunOne.setText("QUINTA.- DURACIÓN:");
		paragraphOneRunOne.addBreak();
		paragraphOneRunOne.setText("La capacitación  se desarrollará a partir del ${fecha_curso} con una duración de ${n_horas} horas, tiempo en el cual el (la) contratado (a) se compromete a ejecutar las actividades a él (ella) encomendadas.");
		paragraphOneRunOne.addBreak();
		paragraphOneRunOne.setText("La duración puede ser prorrogada por parte de la ESPE, de considerar que han existido causas no imputables al (la) contratado (a), en el cumplimiento del plazo, tales como caso fortuito y fuerza mayor de acuerdo a los términos del Art. 30 del Código Civil."); 
		paragraphOneRunOne.addBreak();
		paragraphOneRunOne.setText("La Unidad de Educación Continua, en virtud de este contrato, resolverá sobre las peticiones de ampliación o prórroga de plazo.");
		paragraphOneRunOne.addBreak();

		paragraphOneRunOne.setText("SEXTA: CONSTANCIA.");
		paragraphOneRunOne.addBreak();
		paragraphOneRunOne.setText("Conste por el presente, que la contratación se realiza por la capacidad y experiencia del contratado (a).  Por la naturaleza del contrato, no existe dependencia laboral entre las partes.");
		paragraphOneRunOne.addBreak();

		paragraphOneRunOne.setText("SÉPTIMA.- TERMINACIÓN:");
		paragraphOneRunOne.addBreak();
		paragraphOneRunOne.setText("El contrato en condiciones normales terminará por el cumplimiento cabal de las obligaciones contractuales; para el efecto el Director de la Unidad de Educación Continua presentará un informe final de cumplimiento del objeto del contrato."); 
		paragraphOneRunOne.addBreak();
		paragraphOneRunOne.setText("Adicionalmente el contrato podrá terminar por las siguientes causas:");
		paragraphOneRunOne.addBreak();
		paragraphOneRunOne.setText("-	Por renuncia del contratista.");
		paragraphOneRunOne.addBreak();
		paragraphOneRunOne.setText("-	Por mutuo acuerdo de las partes.");
		paragraphOneRunOne.addBreak();
		paragraphOneRunOne.setText("-	Por muerte súbita, caso fortuito o calamidad doméstica.");
		paragraphOneRunOne.addBreak();
		paragraphOneRunOne.setText("-	Por inconformidad con su desempeño académico ");
		paragraphOneRunOne.addBreak();
		paragraphOneRunOne.setText("-	Por las causales determinadas en la Ley.");
		paragraphOneRunOne.addBreak();

		paragraphOneRunOne.setText("La ESPE podrá también dar por terminado este contrato unilateralmente, si el (la) contratado (a),  no cumpliere a satisfacción las actividades a él (ella) asignadas, resultantes de este instrumento, o por incumplimiento de la Ley, para cuyo efecto deberá preceder el trámite correspondiente.");
		paragraphOneRunOne.addBreak();

		paragraphOneRunOne.setText("OCTAVA.- OBLIGACIONES TRIBUTARIAS:");
		paragraphOneRunOne.addBreak();
		paragraphOneRunOne.setText("El (la) contratado (a) se obliga a cumplir todas las obligaciones tributarias que las leyes ecuatorianas imponen, así como las que devengan del presente instrumento y la ESPE, actuará como agente de retención en los casos y montos que se determinen en la Ley y reglamentos correspondientes.");
		paragraphOneRunOne.addBreak();

		paragraphOneRunOne.setText("NOVENA.- DEFINICIÓN E interpretación DE TÉRMINOS:");
		paragraphOneRunOne.addBreak();
		paragraphOneRunOne.setText("La intención de los términos contenidos en este contrato o en cualquier documento o instrumento relativo a él (la), es el cumplimiento y ejecución del objeto del contrato.");
		paragraphOneRunOne.addBreak();
		paragraphOneRunOne.setText("La interpretación de términos será al tenor de la Ley y a falta de definición legal se estará al significado técnico de los mismos y al significado natural y obvio, de conformidad con el objeto contractual y la intención de los contratantes.");
		paragraphOneRunOne.addBreak();

		paragraphOneRunOne.setText("DÉCIMA.- DIVERGENCIAS");
		paragraphOneRunOne.addBreak();
		paragraphOneRunOne.setText("En caso de suscitarse controversias en la aplicación e interpretación de este contrato, las partes convienen en sujetar toda controversia, a la solución mediante trato directo, para el caso de no llegar a un acuerdo en el término de quince días, se someterán a lo dispuesto en la Ley de Arbitraje y Mediación, para lo cual la partes se obligan a buscar una solución con la intervención del Centro de Mediación de la Procuraduría General del Estado. En el caso de que las partes no lleguen a un acuerdo acudirán a la vía judicial, para tal efecto se sujetan a los jueces de la ciudad de Quito.");
		paragraphOneRunOne.addBreak();

		paragraphOneRunOne.setText("DÉCIMO PRIMERA.- NOTIFICACIONES:");
		paragraphOneRunOne.addBreak();
		paragraphOneRunOne.setText("Las notificaciones que sean necesarias realizar entre las partes durante la ejecución de este contrato se realizarán por escrito, al contratado, en el lugar donde se desarrolla el objeto del contrato; y, a la ESPE, en las oficinas de la Dirección de la Unidad de Educación Continua.");
		paragraphOneRunOne.addBreak();

		paragraphOneRunOne.setText("DÉCIMO SEGUNDA.- RATIFICACIÓN:");
		paragraphOneRunOne.addBreak();
		paragraphOneRunOne.setText("Para constancia de todo cuanto queda estipulado, en fe de aceptación y conformidad, las partes suscriben el presente contrato, en dos ejemplares de igual tenor y valor, en Sangolquí, a ${fecha_contrato}");
		paragraphOneRunOne.addBreak();
 
		paragraphOneRunOne.setText("CRNL. EMC. ${nombre_sr_Vicerrector}");
		paragraphOneRunOne.addBreak();
		paragraphOneRunOne.setText("VICERRECTOR ACADÉMICO ESPE");
		paragraphOneRunOne.addBreak();

		paragraphOneRunOne.setText("${nombre_del_proveedor}");
		paragraphOneRunOne.addBreak();
		paragraphOneRunOne.setText("${cedula} ");
		paragraphOneRunOne.addBreak(BreakType.PAGE);

	}
	
	
	public void reporteContratoP4(){
		XWPFParagraph paragraphOne =  xwpfDocumentFlujoContra.createParagraph();
		XWPFRun paragraphOneRunOne = paragraphOne.createRun();
		paragraphOneRunOne.setFontFamily("Bookman Old Style");
		
		paragraphOneRunOne.setText("UNIDAD DE EDUCACION CONTINUA");
		ReportesUtil.getInstance().addBreakSpace(paragraphOneRunOne, 2);

		paragraphOneRunOne.setText("No. ${n_de_memo_legalizacion_contrato}");
		ReportesUtil.getInstance().addBreakSpace(paragraphOneRunOne, 2);

		paragraphOneRunOne.setText("Sangolquí, ${fecha_contrato}");
		ReportesUtil.getInstance().addBreakSpace(paragraphOneRunOne, 2);

		paragraphOneRunOne.setText("PARA:    Sr. CRNL. E.M.C ${nombre_sr_Vicerrector}");
		ReportesUtil.getInstance().addBreakSpace(paragraphOneRunOne, 1);
		paragraphOneRunOne.setText("	VICERRECTOR ACADEMICO");
		ReportesUtil.getInstance().addBreakSpace(paragraphOneRunOne, 2);

		paragraphOneRunOne.setText("ASUNTO  Legalización de contrato.");
		paragraphOneRunOne.addBreak();
		paragraphOneRunOne.setText("Para la respectiva legalización me permito remitir a usted, el contrato N° ${numero_de_contrato}del  ${nombre_del_proveedor} que dictará  el curso de \"${NOMBRE_DEL_CURSO}\", del ${fecha_curso}.");
		ReportesUtil.getInstance().addBreakSpace(paragraphOneRunOne, 5);




		paragraphOneRunOne.setText("Atentamente,");
		ReportesUtil.getInstance().addBreakSpace(paragraphOneRunOne, 2);

		paragraphOneRunOne.setText("_______________________");
		paragraphOneRunOne.addBreak();
		paragraphOneRunOne.setText("Ing. Karla Benavides MBA");
		paragraphOneRunOne.addBreak();
		paragraphOneRunOne.setText("DIRECTORA UEC	");
		paragraphOneRunOne.addBreak();
		paragraphOneRunOne.addBreak(BreakType.PAGE);
	}
	
	
	public void reporteContratoP5(){
		XWPFParagraph paragraphOne =  xwpfDocumentFlujoContra.createParagraph();
		XWPFRun paragraphOneRunOne = paragraphOne.createRun();

		paragraphOneRunOne.setFontFamily("Bookman Old Style");
		
		paragraphOneRunOne.setText("UNIDAD DE EDUCACION CONTINUA");
		ReportesUtil.getInstance().addBreakSpace(paragraphOneRunOne, 2);
		paragraphOneRunOne.setText("No.${numero_de_contrato}");
		ReportesUtil.getInstance().addBreakSpace(paragraphOneRunOne, 2);
		paragraphOneRunOne.setText("Sangolquí, ${fecha_memmo_imprenta}");
		ReportesUtil.getInstance().addBreakSpace(paragraphOneRunOne, 2);
		paragraphOneRunOne.setText("PARA:  	Ing. Byron Tamayo");
		ReportesUtil.getInstance().addBreakSpace(paragraphOneRunOne, 2);
		paragraphOneRunOne.setText("Coordinador de la EDITORIAL");
		ReportesUtil.getInstance().addBreakSpace(paragraphOneRunOne, 2);
		paragraphOneRunOne.setText("ASUNTO: Reproducción de material evento de capacitación");
		ReportesUtil.getInstance().addBreakSpace(paragraphOneRunOne, 2);
		paragraphOneRunOne.setText("Agradeceré a usted señor Ingeniero, disponer la reproducción de ${NUMERO_DE_EJEMPLARES} folletos, para el curso de ${NOMBRE_DEL_CURSO}");
		ReportesUtil.getInstance().addBreakSpace(paragraphOneRunOne, 2);
		paragraphOneRunOne.setText("Adjunto copia de la certificación presupuestaria Nº.${N_partida_presupuestria_imprenta}, de fecha  ${fecha_memmo_imprenta} por el valor de ${valor_imprenta}");
		ReportesUtil.getInstance().addBreakSpace(paragraphOneRunOne, 2);





		paragraphOneRunOne.setText("Atentamente,");
		ReportesUtil.getInstance().addBreakSpace(paragraphOneRunOne, 2);

		paragraphOneRunOne.setText("_______________________");
		ReportesUtil.getInstance().addBreakSpace(paragraphOneRunOne, 2);
		paragraphOneRunOne.setText("Ing. Karla Benavides MBA");
		ReportesUtil.getInstance().addBreakSpace(paragraphOneRunOne, 2);
		paragraphOneRunOne.setText("Directora de la UEC");
		paragraphOneRunOne.addBreak(BreakType.PAGE);

	}
	
	public void reporteContratoP6(){
		XWPFParagraph paragraphOne =  xwpfDocumentFlujoContra.createParagraph();
		XWPFRun paragraphOneRunOne = paragraphOne.createRun();
		
		paragraphOneRunOne.setFontFamily("Bookman Old Style");
		
		paragraphOneRunOne.setText("UNIDAD DE EDUCACIÓN CONTINUA");
		ReportesUtil.getInstance().addBreakSpace(paragraphOneRunOne, 1);
		paragraphOneRunOne.setText("INFORME QUE PRESENTA LA ${NOMBRE_COORDINADOR}, COORDINADOR (A) DE EVENTOS DE LA UNIDAD EDUCACIÒN CONTINUA, SOBRE  LA  PRESTACIÓN DE SERVICIOS PROFESIONALES DE LA ${NOMBRE_DEL_PROVEEDOR}");
		ReportesUtil.getInstance().addBreakSpace(paragraphOneRunOne, 2);
		paragraphOneRunOne.setText("ANTECEDENTES");
		ReportesUtil.getInstance().addBreakSpace(paragraphOneRunOne, 3);

		paragraphOneRunOne.setText("${actividades}");
		ReportesUtil.getInstance().addBreakSpace(paragraphOneRunOne, 1);
		paragraphOneRunOne.setText("La ejecución del evento de capacitación de \"${NOMBRE_DEL_CURSO}\" se ejecutó en la semana del ${fecha_curso}");
		ReportesUtil.getInstance().addBreakSpace(paragraphOneRunOne, 3);


		paragraphOneRunOne.setText("DESARROLLO ");
		ReportesUtil.getInstance().addBreakSpace(paragraphOneRunOne, 2);
		paragraphOneRunOne.setText("La  Unidad de Educación Continua dentro del Plan de capacitación realizó el curso de ${NOMBRE_DEL_CURSO} realizado en ${lugar_a_dictarse},  el mismo que se ejecutó con normalidad del ${fecha_curso} con una duración de ${n_horas} horas en la que la instrucción estuvo a cargo de él (la) ${nombre_del_proveedor}");
		ReportesUtil.getInstance().addBreakSpace(paragraphOneRunOne, 1);
		paragraphOneRunOne.setText("El proceso de selección del Facilitador se realizó a través de la revisión de perfiles de docentes que constan en la Base de datos de la Unidad a fin de identificar los candidatos que reúnan el perfil y sean especializados en la temática, con los que se tomó contacto,  para verificar la disponibilidad de tiempo y el interés para dictar el curso, siendo seleccionado por su formación y experiencia en el tema de \"${NOMBRE_DEL_CURSO}\"  al (la) ${nombre_del_proveedor} con un resultado  de ${M__terna}, siendo el más alto por lo que se procedió a realizar el contrato N° ${numero_de_contrato},"); 
		ReportesUtil.getInstance().addBreakSpace(paragraphOneRunOne, 1);
		paragraphOneRunOne.setText("Se adjunta los siguientes documentos.");
		ReportesUtil.getInstance().addBreakSpace(paragraphOneRunOne, 2);
		
		paragraphOneRunOne.setText("CONCLUSIONES");
		ReportesUtil.getInstance().addBreakSpace(paragraphOneRunOne, 2);
		paragraphOneRunOne.setText("Después de realizar el análisis de la documentación entrega por el Facilitador (a)  y las coordinaciones pertinentes por el personal responsable de la Unidad de Educación Continua de la ESPE ${nombre_coordinador} de dicho proceso, concluye que el profesional ${nombre_del_proveedor} ha cumplido satisfactoriamente con el Desarrollo del curso ${NOMBRE_DEL_CURSO} en el horario de ${HORARIO_DE_CLASES} el mismo que fue programado por la ESPE, objeto del contrato ${numero_de_contrato}");

		ReportesUtil.getInstance().addBreakSpace(paragraphOneRunOne, 1);
		paragraphOneRunOne.setText("RECOMENDACIONES");
		ReportesUtil.getInstance().addBreakSpace(paragraphOneRunOne, 2);
		paragraphOneRunOne.setText("Una vez devengado el servicio de capacitación del curso \"${NOMBRE_DEL_CURSO}\", en las fechas estipuladas y contando con la Certificación Presupuestaria Nº ${N_certificacion} y  toda la documentación solicitada por la Dirección Financiera se recomienda se proceda con el tramite respectivo.");
		ReportesUtil.getInstance().addBreakSpace(paragraphOneRunOne, 2);
		paragraphOneRunOne.setText("Sangolquí, ${fecha_carta_de_pago}");
		ReportesUtil.getInstance().addBreakSpace(paragraphOneRunOne, 3);
		paragraphOneRunOne.setText("____________________________________");
		ReportesUtil.getInstance().addBreakSpace(paragraphOneRunOne, 1);
		paragraphOneRunOne.setText("${nombre_coordinador}");
		ReportesUtil.getInstance().addBreakSpace(paragraphOneRunOne, 1);
		paragraphOneRunOne.setText("Coordinador (a) de Capacitación UEC");
		ReportesUtil.getInstance().addBreakSpace(paragraphOneRunOne, 3);
		paragraphOneRunOne.setText("Después del informe presentado por la Coordinadora de la Capacitación, ${nombre_coordinador} y la revisión de los documentos  por el Ing. Edison Cabezas,   yo como Directora de la Unidad de Educación Continua, recomiendo que se proceda con los trámites correspondientes.");
		ReportesUtil.getInstance().addBreakSpace(paragraphOneRunOne, 3);
		paragraphOneRunOne.setText("_____________________");
		ReportesUtil.getInstance().addBreakSpace(paragraphOneRunOne, 1);
		paragraphOneRunOne.setText("Ing. Karla Benavides ");
		ReportesUtil.getInstance().addBreakSpace(paragraphOneRunOne, 1);
		paragraphOneRunOne.setText("Directora UEC");


		paragraphOneRunOne.addBreak(BreakType.PAGE);

	}
	public void reporteContratoP7(){
		XWPFParagraph paragraphOne =  xwpfDocumentFlujoContra.createParagraph();
		XWPFRun paragraphOneRunOne = paragraphOne.createRun();
		
		paragraphOneRunOne.setFontFamily("Bookman Old Style");
		
		ReportesUtil.getInstance().addText(paragraphOneRunOne,"VICERRECTORADO ACADEMICO");
		ReportesUtil.getInstance().addText(paragraphOneRunOne,"UNIDAD DE EDUCACIÓN CONTINUA");
		ReportesUtil.getInstance().addText(paragraphOneRunOne,"MEMORANDO");
	
		ReportesUtil.getInstance().addBreakSpace(paragraphOneRunOne, 1);
		ReportesUtil.getInstance().addText(paragraphOneRunOne,"No. -${N_MEMO}");
		ReportesUtil.getInstance().addBreakSpace(paragraphOneRunOne, 1);
		
		ReportesUtil.getInstance().addText(paragraphOneRunOne,"Sangolquí ${fecha_carta_de_pago}");
		ReportesUtil.getInstance().addBreakSpace(paragraphOneRunOne, 1);
		ReportesUtil.getInstance().addText(paragraphOneRunOne,"DE:	CRNL. E.M.C. ${nombre_sr_Vicerrector}");
		ReportesUtil.getInstance().addText(paragraphOneRunOne,"	VICERRECTOR ACADEMICO");
		ReportesUtil.getInstance().addText(paragraphOneRunOne,"PARA:	SRA. ING. GABRIELA CORDOVA");
		ReportesUtil.getInstance().addText(paragraphOneRunOne,"	DIRECTORA FINANCIERA");
		ReportesUtil.getInstance().addText(paragraphOneRunOne,"En:  	      Su despacho.-");
		ReportesUtil.getInstance().addBreakSpace(paragraphOneRunOne, 1);
		ReportesUtil.getInstance().addText(paragraphOneRunOne,"Mucho agradeceré a usted, señora Directora, ordenar y disponer a quien corresponda el pago por servicios de capacitación, de acuerdo al siguiente detalle:");
		ReportesUtil.getInstance().addBreakSpace(paragraphOneRunOne, 1);
		ReportesUtil.getInstance().addText(paragraphOneRunOne,"Instructor:	${nombre_del_proveedor}");

		ReportesUtil.getInstance().addText(paragraphOneRunOne,"Función:	${tipo_relacion_loboral}");

		ReportesUtil.getInstance().addText(paragraphOneRunOne,"Curso:	${NOMBRE_DEL_CURSO}");

		ReportesUtil.getInstance().addText(paragraphOneRunOne,"Lugar:	${lugar_a_dictarse}");

		ReportesUtil.getInstance().addText(paragraphOneRunOne,"Contrato N°:	${numero_de_contrato}");

		ReportesUtil.getInstance().addText(paragraphOneRunOne,"Fechas:	${fecha_curso}");

		ReportesUtil.getInstance().addText(paragraphOneRunOne,"RUC	${cedula}");

		ReportesUtil.getInstance().addText(paragraphOneRunOne,"Horas del curso:	${n_horas} horas");

		ReportesUtil.getInstance().addText(paragraphOneRunOne,"Valor hora clase:	${V_HORA_CLASE}");

		ReportesUtil.getInstance().addText(paragraphOneRunOne,"Total a pagar:");
		ReportesUtil.getInstance().addText(paragraphOneRunOne,"Banco:");
		ReportesUtil.getInstance().addText(paragraphOneRunOne,"No. de Cuentas:	$ ${valor_a_pagar},oo ${pp}");
		ReportesUtil.getInstance().addText(paragraphOneRunOne,"${Banco}");
		ReportesUtil.getInstance().addText(paragraphOneRunOne,"${N_cta}");

		ReportesUtil.getInstance().addText(paragraphOneRunOne,"No. Ticket de avión:	");
		ReportesUtil.getInstance().addText(paragraphOneRunOne,"${plan}");
		ReportesUtil.getInstance().addBreakSpace(paragraphOneRunOne, 1);
		ReportesUtil.getInstance().addText(paragraphOneRunOne,"Para el efecto adjunto la Certificación de Fondos No.${N_certificacion} de  fecha ${fecha_de_certificacion} Además, informo que cumplió la capacitación con total satisfacción del cliente.");     

		ReportesUtil.getInstance().addBreakSpace(paragraphOneRunOne, 2);
		ReportesUtil.getInstance().addText(paragraphOneRunOne,"DIOS, PATRIA Y LIBERTAD,");

		ReportesUtil.getInstance().addBreakSpace(paragraphOneRunOne, 2);
		ReportesUtil.getInstance().addText(paragraphOneRunOne,"____________________________________");
		ReportesUtil.getInstance().addText(paragraphOneRunOne,"${nombre_sr_Vicerrector}");
		ReportesUtil.getInstance().addText(paragraphOneRunOne,"CRNL. E.M.C.");
		ReportesUtil.getInstance().addText(paragraphOneRunOne,"                           VICERRECTOR ACADEMICO");
		ReportesUtil.getInstance().addBreakSpace(paragraphOneRunOne, 2);
		ReportesUtil.getInstance().addText(paragraphOneRunOne,"Adjunto:   documentación");
		ReportesUtil.getInstance().addText(paragraphOneRunOne,"Elaborado por: Ing. E. Cabezas");
		ReportesUtil.getInstance().addText(paragraphOneRunOne,"Revisado por: Ing. K. Benavides");

		
	}
	
	public void generarContrato(String filename) throws IOException{
		
		
		xwpfDocumentFlujoContra.write(FacesUtils.getTempletaDescargaReporte(filename));
	    FacesContext facesContext = FacesContext.getCurrentInstance();
	    facesContext.responseComplete();
	    
	}

}
