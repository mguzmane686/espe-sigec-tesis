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
		
		ReportesUtil.getInstance().addText(paragraphOneRunOne,"Sangolqu�, ${fecha_de_invitacion}");
		ReportesUtil.getInstance().addText(paragraphOneRunOne,"Oficio No. ${${N_invitacion}");
		ReportesUtil.getInstance().addText(paragraphOneRunOne,"Se�or (a)");
		ReportesUtil.getInstance().addText(paragraphOneRunOne,"${nombre_del_proveedor}");
		ReportesUtil.getInstance().addText(paragraphOneRunOne,"Presente.-");

		ReportesUtil.getInstance().addBreakSpace(paragraphOneRunOne, 1);
		ReportesUtil.getInstance().addText(paragraphOneRunOne,"De mi consideraci�n:");
		
		paragraphOne = xwpfDocumentFlujoContra.createParagraph();
		paragraphOneRunOne = paragraphOne.createRun();
		paragraphOne.setAlignment(ParagraphAlignment.BOTH);
		
		ReportesUtil.getInstance().addText(paragraphOneRunOne,"Luego de expresar a usted un cordial saludo deseo extender la invitaci�n para que participe como facilitador en el curso de  �${NOMBRE_DEL_CURSO}�,  del ${fecha_curso}, con una duraci�n de ${n_horas} horas,  a impartirse en el ${lugar_a_dictarse},  por este servicio de capacitaci�n se le reconocer� el valor total de $   ${valor_a_pagar} ${pp} menos retenciones de Ley.");
		ReportesUtil.getInstance().addBreakSpace(paragraphOneRunOne, 1);
		ReportesUtil.getInstance().addText(paragraphOneRunOne,"El contenido del curso deber� ser propuesto por el facilitador en funci�n de los lineamientos proporcionados por la Unidad de Educaci�n Continua, as� como tambi�n el original del material de apoyo para los estudiantes el cual ser� previamente revisado y aprobado.");
		ReportesUtil.getInstance().addBreakSpace(paragraphOneRunOne, 1);
		ReportesUtil.getInstance().addText(paragraphOneRunOne,"El instructor se sujetar� al Reglamento de la ESPE, en lo que corresponda al r�gimen disciplinario, deberes y derechos.");
		ReportesUtil.getInstance().agregarEstilos(paragraphOneRunOne);
		

		paragraphOne = xwpfDocumentFlujoContra.createParagraph();
		paragraphOneRunOne = paragraphOne.createRun();
		paragraphOne.setAlignment(ParagraphAlignment.LEFT);
		
		ReportesUtil.getInstance().addBreakSpace(paragraphOneRunOne, 2);
		paragraphOneRunOne.setText("Atentamente,");
		ReportesUtil.getInstance().addBreakSpace(paragraphOneRunOne, 4);
		ReportesUtil.getInstance().addText(paragraphOneRunOne,"Ing. Karla Benavides");
		paragraphOneRunOne.setText("DIRECTORA UNIDAD DE EDUCACI�N CONTINUA");
		
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
		
		paragraphOneRunOne.setText("Sangolqu�, ${fecha_de_aceptacion}");
		
		ReportesUtil.getInstance().addBreakSpace(paragraphOneRunOne, 2);
		
		ReportesUtil.getInstance().addText(paragraphOneRunOne,"Sres.");
		ReportesUtil.getInstance().addText(paragraphOneRunOne,"ESCUELA POLIT�CNICA DEL EJ�RCITO");
		ReportesUtil.getInstance().addText(paragraphOneRunOne,"Presente.-");
		ReportesUtil.getInstance().addBreakSpace(paragraphOneRunOne, 2);
		ReportesUtil.getInstance().addText(paragraphOneRunOne,"De mi consideraci�n:");
		ReportesUtil.getInstance().addBreakSpace(paragraphOneRunOne, 2);
		ReportesUtil.getInstance().addText(paragraphOneRunOne,"En referencia al oficio No. ${N_inivitacion} mediante el cual me invita a participar como facilitador en el cursode ${NOMBRE_DEL_CURSO}, cuya fecha de ejecuci�n est� prevista del ${fecha_curso}, con una duraci�n de ${n_horas} horas, por medio del presente deseo dejar constancia de mi agradecimiento y aceptaci�n a la propuesta.");
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
		paragraphOneRunOne.setText("CONTRATO DE PRESTACI�N DE SERVICIOS PROFESIONALES DE EDUCACI�N CONTINUA A CELEBRARSE ENTRE LA ESCUELA POLIT�CNICA DEL EJ�RCITO Y EL (LA) ${nombre_del_proveedor}");
		
		ReportesUtil.getInstance().addBreakSpace(paragraphOneRunOne, 3);
		paragraphOneRunOne.setText("Intervienen en la celebraci�n del presente contrato, por una parte, la Escuela Polit�cnica del Ej�rcito, legalmente representada por el se�or Cnel. E.M.C.,${nombre_sr_Vicerrector} en calidad de Vicerrector Acad�mico;  y delegado del rector representante legal de la ESPE; y, de otro lado, el (la) ,${nombre_del_proveedor} , de nacionalidad ecuatoriana,  quien en lo posterior se denominar� el (la) contratado (a), partes legalmente capaces para contraer obligaciones, que convienen en celebrar el presente contrato, al tenor de las siguientes cl�usulas:");
		ReportesUtil.getInstance().addBreakSpace(paragraphOneRunOne, 3);

		paragraphOneRunOne.setText("PRIMERA.- ANTECEDENTES:");
		ReportesUtil.getInstance().addBreakSpace(paragraphOneRunOne, 3);

		paragraphOneRunOne.setText("1.	Invitaci�n formulada mediante oficio No.${N_invitacion} ");
		paragraphOneRunOne.addBreak();
		paragraphOneRunOne.setText("2.	Aceptaci�n de los contratistas");
		paragraphOneRunOne.addBreak();
		paragraphOneRunOne.setText("3.	Certificaci�n Presupuestaria No. ,${N_certificacion}  del, ${fecha_de_certificacion} mediante la cual se informa que existe disponibilidad econ�mica en la Partida Presupuestaria No.${NOMBRE_D_ELA_PP_}, por el valor de U.S.D. $ ${valor} ${F9} para financiar el pago de la contrataci�n al personal de instructores que participar�n en la capacitaci�n continua.");
		paragraphOneRunOne.addBreak();
		

		paragraphOneRunOne.setText("SEGUNDA.- DOCUMENTOS HABILITANTES:");
		paragraphOneRunOne.addBreak();
		paragraphOneRunOne.setText("1.	Certificaci�n Presupuestaria No. ${N_certificacion}");
		paragraphOneRunOne.addBreak();
		paragraphOneRunOne.setText("2.	Los dem�s que constan en la cl�usula Antecedentes; y, ");
		paragraphOneRunOne.addBreak();
		paragraphOneRunOne.setText("3.	Documentos del contratista");
		paragraphOneRunOne.addBreak();


		paragraphOneRunOne.setText("TERCERA.- OBJETO:");
		paragraphOneRunOne.addBreak();
		paragraphOneRunOne.setText("Con los antecedentes expuestos, la ESPE contrata los servicios del (la) ${nombre_del_proveedor}, en calidad de instructor para que dicte la materia de, ${NOMBRE_DEL_CURSO} en el ${lugar_a_dictarse}");
		paragraphOneRunOne.addBreak();

		paragraphOneRunOne.setText("CUARTA.- PRECIO:");
		paragraphOneRunOne.addBreak();
		paragraphOneRunOne.setText("Por los servicios de instructor que se contrata, la Escuela Polit�cnica del Ej�rcito pagar� al (la) Contratado (a), el valor �nico de U.S.D. ${valor_a_pagar} ${pp} sujeto a retenciones de Ley.  Este valor se pagar� al finalizar la capacitaci�n previo informe del Coordinador de Unidad de Educaci�n Continua y la Orden de Pago del Vicerrector Acad�mico.");
		paragraphOneRunOne.addBreak();

		paragraphOneRunOne.setText("QUINTA.- DURACI�N:");
		paragraphOneRunOne.addBreak();
		paragraphOneRunOne.setText("La capacitaci�n  se desarrollar� a partir del ${fecha_curso} con una duraci�n de ${n_horas} horas, tiempo en el cual el (la) contratado (a) se compromete a ejecutar las actividades a �l (ella) encomendadas.");
		paragraphOneRunOne.addBreak();
		paragraphOneRunOne.setText("La duraci�n puede ser prorrogada por parte de la ESPE, de considerar que han existido causas no imputables al (la) contratado (a), en el cumplimiento del plazo, tales como caso fortuito y fuerza mayor de acuerdo a los t�rminos del Art. 30 del C�digo Civil."); 
		paragraphOneRunOne.addBreak();
		paragraphOneRunOne.setText("La Unidad de Educaci�n Continua, en virtud de este contrato, resolver� sobre las peticiones de ampliaci�n o pr�rroga de plazo.");
		paragraphOneRunOne.addBreak();

		paragraphOneRunOne.setText("SEXTA: CONSTANCIA.");
		paragraphOneRunOne.addBreak();
		paragraphOneRunOne.setText("Conste por el presente, que la contrataci�n se realiza por la capacidad y experiencia del contratado (a).  Por la naturaleza del contrato, no existe dependencia laboral entre las partes.");
		paragraphOneRunOne.addBreak();

		paragraphOneRunOne.setText("S�PTIMA.- TERMINACI�N:");
		paragraphOneRunOne.addBreak();
		paragraphOneRunOne.setText("El contrato en condiciones normales terminar� por el cumplimiento cabal de las obligaciones contractuales; para el efecto el Director de la Unidad de Educaci�n Continua presentar� un informe final de cumplimiento del objeto del contrato."); 
		paragraphOneRunOne.addBreak();
		paragraphOneRunOne.setText("Adicionalmente el contrato podr� terminar por las siguientes causas:");
		paragraphOneRunOne.addBreak();
		paragraphOneRunOne.setText("-	Por renuncia del contratista.");
		paragraphOneRunOne.addBreak();
		paragraphOneRunOne.setText("-	Por mutuo acuerdo de las partes.");
		paragraphOneRunOne.addBreak();
		paragraphOneRunOne.setText("-	Por muerte s�bita, caso fortuito o calamidad dom�stica.");
		paragraphOneRunOne.addBreak();
		paragraphOneRunOne.setText("-	Por inconformidad con su desempe�o acad�mico ");
		paragraphOneRunOne.addBreak();
		paragraphOneRunOne.setText("-	Por las causales determinadas en la Ley.");
		paragraphOneRunOne.addBreak();

		paragraphOneRunOne.setText("La ESPE podr� tambi�n dar por terminado este contrato unilateralmente, si el (la) contratado (a),  no cumpliere a satisfacci�n las actividades a �l (ella) asignadas, resultantes de este instrumento, o por incumplimiento de la Ley, para cuyo efecto deber� preceder el tr�mite correspondiente.");
		paragraphOneRunOne.addBreak();

		paragraphOneRunOne.setText("OCTAVA.- OBLIGACIONES TRIBUTARIAS:");
		paragraphOneRunOne.addBreak();
		paragraphOneRunOne.setText("El (la) contratado (a) se obliga a cumplir todas las obligaciones tributarias que las leyes ecuatorianas imponen, as� como las que devengan del presente instrumento y la ESPE, actuar� como agente de retenci�n en los casos y montos que se determinen en la Ley y reglamentos correspondientes.");
		paragraphOneRunOne.addBreak();

		paragraphOneRunOne.setText("NOVENA.- DEFINICI�N E interpretaci�n DE T�RMINOS:");
		paragraphOneRunOne.addBreak();
		paragraphOneRunOne.setText("La intenci�n de los t�rminos contenidos en este contrato o en cualquier documento o instrumento relativo a �l (la), es el cumplimiento y ejecuci�n del objeto del contrato.");
		paragraphOneRunOne.addBreak();
		paragraphOneRunOne.setText("La interpretaci�n de t�rminos ser� al tenor de la Ley y a falta de definici�n legal se estar� al significado t�cnico de los mismos y al significado natural y obvio, de conformidad con el objeto contractual y la intenci�n de los contratantes.");
		paragraphOneRunOne.addBreak();

		paragraphOneRunOne.setText("D�CIMA.- DIVERGENCIAS");
		paragraphOneRunOne.addBreak();
		paragraphOneRunOne.setText("En caso de suscitarse controversias en la aplicaci�n e interpretaci�n de este contrato, las partes convienen en sujetar toda controversia, a la soluci�n mediante trato directo, para el caso de no llegar a un acuerdo en el t�rmino de quince d�as, se someter�n a lo dispuesto en la Ley de Arbitraje y Mediaci�n, para lo cual la partes se obligan a buscar una soluci�n con la intervenci�n del Centro de Mediaci�n de la Procuradur�a General del Estado. En el caso de que las partes no lleguen a un acuerdo acudir�n a la v�a judicial, para tal efecto se sujetan a los jueces de la ciudad de Quito.");
		paragraphOneRunOne.addBreak();

		paragraphOneRunOne.setText("D�CIMO PRIMERA.- NOTIFICACIONES:");
		paragraphOneRunOne.addBreak();
		paragraphOneRunOne.setText("Las notificaciones que sean necesarias realizar entre las partes durante la ejecuci�n de este contrato se realizar�n por escrito, al contratado, en el lugar donde se desarrolla el objeto del contrato; y, a la ESPE, en las oficinas de la Direcci�n de la Unidad de Educaci�n Continua.");
		paragraphOneRunOne.addBreak();

		paragraphOneRunOne.setText("D�CIMO SEGUNDA.- RATIFICACI�N:");
		paragraphOneRunOne.addBreak();
		paragraphOneRunOne.setText("Para constancia de todo cuanto queda estipulado, en fe de aceptaci�n y conformidad, las partes suscriben el presente contrato, en dos ejemplares de igual tenor y valor, en Sangolqu�, a ${fecha_contrato}");
		paragraphOneRunOne.addBreak();
 
		paragraphOneRunOne.setText("CRNL. EMC. ${nombre_sr_Vicerrector}");
		paragraphOneRunOne.addBreak();
		paragraphOneRunOne.setText("VICERRECTOR ACAD�MICO ESPE");
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

		paragraphOneRunOne.setText("Sangolqu�, ${fecha_contrato}");
		ReportesUtil.getInstance().addBreakSpace(paragraphOneRunOne, 2);

		paragraphOneRunOne.setText("PARA:    Sr. CRNL. E.M.C ${nombre_sr_Vicerrector}");
		ReportesUtil.getInstance().addBreakSpace(paragraphOneRunOne, 1);
		paragraphOneRunOne.setText("	VICERRECTOR ACADEMICO");
		ReportesUtil.getInstance().addBreakSpace(paragraphOneRunOne, 2);

		paragraphOneRunOne.setText("ASUNTO  Legalizaci�n de contrato.");
		paragraphOneRunOne.addBreak();
		paragraphOneRunOne.setText("Para la respectiva legalizaci�n me permito remitir a usted, el contrato N� ${numero_de_contrato}del  ${nombre_del_proveedor} que dictar�  el curso de \"${NOMBRE_DEL_CURSO}\", del ${fecha_curso}.");
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
		paragraphOneRunOne.setText("Sangolqu�, ${fecha_memmo_imprenta}");
		ReportesUtil.getInstance().addBreakSpace(paragraphOneRunOne, 2);
		paragraphOneRunOne.setText("PARA:  	Ing. Byron Tamayo");
		ReportesUtil.getInstance().addBreakSpace(paragraphOneRunOne, 2);
		paragraphOneRunOne.setText("Coordinador de la EDITORIAL");
		ReportesUtil.getInstance().addBreakSpace(paragraphOneRunOne, 2);
		paragraphOneRunOne.setText("ASUNTO: Reproducci�n de material evento de capacitaci�n");
		ReportesUtil.getInstance().addBreakSpace(paragraphOneRunOne, 2);
		paragraphOneRunOne.setText("Agradecer� a usted se�or Ingeniero, disponer la reproducci�n de ${NUMERO_DE_EJEMPLARES} folletos, para el curso de ${NOMBRE_DEL_CURSO}");
		ReportesUtil.getInstance().addBreakSpace(paragraphOneRunOne, 2);
		paragraphOneRunOne.setText("Adjunto copia de la certificaci�n presupuestaria N�.${N_partida_presupuestria_imprenta}, de fecha  ${fecha_memmo_imprenta} por el valor de ${valor_imprenta}");
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
		
		paragraphOneRunOne.setText("UNIDAD DE EDUCACI�N CONTINUA");
		ReportesUtil.getInstance().addBreakSpace(paragraphOneRunOne, 1);
		paragraphOneRunOne.setText("INFORME QUE PRESENTA LA ${NOMBRE_COORDINADOR}, COORDINADOR (A) DE EVENTOS DE LA UNIDAD EDUCACI�N CONTINUA, SOBRE  LA  PRESTACI�N DE SERVICIOS PROFESIONALES DE LA ${NOMBRE_DEL_PROVEEDOR}");
		ReportesUtil.getInstance().addBreakSpace(paragraphOneRunOne, 2);
		paragraphOneRunOne.setText("ANTECEDENTES");
		ReportesUtil.getInstance().addBreakSpace(paragraphOneRunOne, 3);

		paragraphOneRunOne.setText("${actividades}");
		ReportesUtil.getInstance().addBreakSpace(paragraphOneRunOne, 1);
		paragraphOneRunOne.setText("La ejecuci�n del evento de capacitaci�n de \"${NOMBRE_DEL_CURSO}\" se ejecut� en la semana del ${fecha_curso}");
		ReportesUtil.getInstance().addBreakSpace(paragraphOneRunOne, 3);


		paragraphOneRunOne.setText("DESARROLLO ");
		ReportesUtil.getInstance().addBreakSpace(paragraphOneRunOne, 2);
		paragraphOneRunOne.setText("La  Unidad de Educaci�n Continua dentro del Plan de capacitaci�n realiz� el curso de ${NOMBRE_DEL_CURSO} realizado en ${lugar_a_dictarse},  el mismo que se ejecut� con normalidad del ${fecha_curso} con una duraci�n de ${n_horas} horas en la que la instrucci�n estuvo a cargo de �l (la) ${nombre_del_proveedor}");
		ReportesUtil.getInstance().addBreakSpace(paragraphOneRunOne, 1);
		paragraphOneRunOne.setText("El proceso de selecci�n del Facilitador se realiz� a trav�s de la revisi�n de perfiles de docentes que constan en la Base de datos de la Unidad a fin de identificar los candidatos que re�nan el perfil y sean especializados en la tem�tica, con los que se tom� contacto,  para verificar la disponibilidad de tiempo y el inter�s para dictar el curso, siendo seleccionado por su formaci�n y experiencia en el tema de \"${NOMBRE_DEL_CURSO}\"  al (la) ${nombre_del_proveedor} con un resultado  de ${M__terna}, siendo el m�s alto por lo que se procedi� a realizar el contrato N� ${numero_de_contrato},"); 
		ReportesUtil.getInstance().addBreakSpace(paragraphOneRunOne, 1);
		paragraphOneRunOne.setText("Se adjunta los siguientes documentos.");
		ReportesUtil.getInstance().addBreakSpace(paragraphOneRunOne, 2);
		
		paragraphOneRunOne.setText("CONCLUSIONES");
		ReportesUtil.getInstance().addBreakSpace(paragraphOneRunOne, 2);
		paragraphOneRunOne.setText("Despu�s de realizar el an�lisis de la documentaci�n entrega por el Facilitador (a)  y las coordinaciones pertinentes por el personal responsable de la Unidad de Educaci�n Continua de la ESPE ${nombre_coordinador} de dicho proceso, concluye que el profesional ${nombre_del_proveedor} ha cumplido satisfactoriamente con el Desarrollo del curso ${NOMBRE_DEL_CURSO} en el horario de ${HORARIO_DE_CLASES} el mismo que fue programado por la ESPE, objeto del contrato ${numero_de_contrato}");

		ReportesUtil.getInstance().addBreakSpace(paragraphOneRunOne, 1);
		paragraphOneRunOne.setText("RECOMENDACIONES");
		ReportesUtil.getInstance().addBreakSpace(paragraphOneRunOne, 2);
		paragraphOneRunOne.setText("Una vez devengado el servicio de capacitaci�n del curso \"${NOMBRE_DEL_CURSO}\", en las fechas estipuladas y contando con la Certificaci�n Presupuestaria N� ${N_certificacion} y  toda la documentaci�n solicitada por la Direcci�n Financiera se recomienda se proceda con el tramite respectivo.");
		ReportesUtil.getInstance().addBreakSpace(paragraphOneRunOne, 2);
		paragraphOneRunOne.setText("Sangolqu�, ${fecha_carta_de_pago}");
		ReportesUtil.getInstance().addBreakSpace(paragraphOneRunOne, 3);
		paragraphOneRunOne.setText("____________________________________");
		ReportesUtil.getInstance().addBreakSpace(paragraphOneRunOne, 1);
		paragraphOneRunOne.setText("${nombre_coordinador}");
		ReportesUtil.getInstance().addBreakSpace(paragraphOneRunOne, 1);
		paragraphOneRunOne.setText("Coordinador (a) de Capacitaci�n UEC");
		ReportesUtil.getInstance().addBreakSpace(paragraphOneRunOne, 3);
		paragraphOneRunOne.setText("Despu�s del informe presentado por la Coordinadora de la Capacitaci�n, ${nombre_coordinador} y la revisi�n de los documentos  por el Ing. Edison Cabezas,   yo como Directora de la Unidad de Educaci�n Continua, recomiendo que se proceda con los tr�mites correspondientes.");
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
		ReportesUtil.getInstance().addText(paragraphOneRunOne,"UNIDAD DE EDUCACI�N CONTINUA");
		ReportesUtil.getInstance().addText(paragraphOneRunOne,"MEMORANDO");
	
		ReportesUtil.getInstance().addBreakSpace(paragraphOneRunOne, 1);
		ReportesUtil.getInstance().addText(paragraphOneRunOne,"No. -${N_MEMO}");
		ReportesUtil.getInstance().addBreakSpace(paragraphOneRunOne, 1);
		
		ReportesUtil.getInstance().addText(paragraphOneRunOne,"Sangolqu� ${fecha_carta_de_pago}");
		ReportesUtil.getInstance().addBreakSpace(paragraphOneRunOne, 1);
		ReportesUtil.getInstance().addText(paragraphOneRunOne,"DE:	CRNL. E.M.C. ${nombre_sr_Vicerrector}");
		ReportesUtil.getInstance().addText(paragraphOneRunOne,"	VICERRECTOR ACADEMICO");
		ReportesUtil.getInstance().addText(paragraphOneRunOne,"PARA:	SRA. ING. GABRIELA CORDOVA");
		ReportesUtil.getInstance().addText(paragraphOneRunOne,"	DIRECTORA FINANCIERA");
		ReportesUtil.getInstance().addText(paragraphOneRunOne,"En:  	      Su despacho.-");
		ReportesUtil.getInstance().addBreakSpace(paragraphOneRunOne, 1);
		ReportesUtil.getInstance().addText(paragraphOneRunOne,"Mucho agradecer� a usted, se�ora Directora, ordenar y disponer a quien corresponda el pago por servicios de capacitaci�n, de acuerdo al siguiente detalle:");
		ReportesUtil.getInstance().addBreakSpace(paragraphOneRunOne, 1);
		ReportesUtil.getInstance().addText(paragraphOneRunOne,"Instructor:	${nombre_del_proveedor}");

		ReportesUtil.getInstance().addText(paragraphOneRunOne,"Funci�n:	${tipo_relacion_loboral}");

		ReportesUtil.getInstance().addText(paragraphOneRunOne,"Curso:	${NOMBRE_DEL_CURSO}");

		ReportesUtil.getInstance().addText(paragraphOneRunOne,"Lugar:	${lugar_a_dictarse}");

		ReportesUtil.getInstance().addText(paragraphOneRunOne,"Contrato N�:	${numero_de_contrato}");

		ReportesUtil.getInstance().addText(paragraphOneRunOne,"Fechas:	${fecha_curso}");

		ReportesUtil.getInstance().addText(paragraphOneRunOne,"RUC	${cedula}");

		ReportesUtil.getInstance().addText(paragraphOneRunOne,"Horas del curso:	${n_horas} horas");

		ReportesUtil.getInstance().addText(paragraphOneRunOne,"Valor hora clase:	${V_HORA_CLASE}");

		ReportesUtil.getInstance().addText(paragraphOneRunOne,"Total a pagar:");
		ReportesUtil.getInstance().addText(paragraphOneRunOne,"Banco:");
		ReportesUtil.getInstance().addText(paragraphOneRunOne,"No. de Cuentas:	$ ${valor_a_pagar},oo ${pp}");
		ReportesUtil.getInstance().addText(paragraphOneRunOne,"${Banco}");
		ReportesUtil.getInstance().addText(paragraphOneRunOne,"${N_cta}");

		ReportesUtil.getInstance().addText(paragraphOneRunOne,"No. Ticket de avi�n:	");
		ReportesUtil.getInstance().addText(paragraphOneRunOne,"${plan}");
		ReportesUtil.getInstance().addBreakSpace(paragraphOneRunOne, 1);
		ReportesUtil.getInstance().addText(paragraphOneRunOne,"Para el efecto adjunto la Certificaci�n de Fondos No.${N_certificacion} de  fecha ${fecha_de_certificacion} Adem�s, informo que cumpli� la capacitaci�n con total satisfacci�n del cliente.");     

		ReportesUtil.getInstance().addBreakSpace(paragraphOneRunOne, 2);
		ReportesUtil.getInstance().addText(paragraphOneRunOne,"DIOS, PATRIA Y LIBERTAD,");

		ReportesUtil.getInstance().addBreakSpace(paragraphOneRunOne, 2);
		ReportesUtil.getInstance().addText(paragraphOneRunOne,"____________________________________");
		ReportesUtil.getInstance().addText(paragraphOneRunOne,"${nombre_sr_Vicerrector}");
		ReportesUtil.getInstance().addText(paragraphOneRunOne,"CRNL. E.M.C.");
		ReportesUtil.getInstance().addText(paragraphOneRunOne,"                           VICERRECTOR ACADEMICO");
		ReportesUtil.getInstance().addBreakSpace(paragraphOneRunOne, 2);
		ReportesUtil.getInstance().addText(paragraphOneRunOne,"Adjunto:   documentaci�n");
		ReportesUtil.getInstance().addText(paragraphOneRunOne,"Elaborado por: Ing. E. Cabezas");
		ReportesUtil.getInstance().addText(paragraphOneRunOne,"Revisado por: Ing. K. Benavides");

		
	}
	
	public void generarContrato(String filename) throws IOException{
		
		
		xwpfDocumentFlujoContra.write(FacesUtils.getTempletaDescargaReporte(filename));
	    FacesContext facesContext = FacesContext.getCurrentInstance();
	    facesContext.responseComplete();
	    
	}

}
