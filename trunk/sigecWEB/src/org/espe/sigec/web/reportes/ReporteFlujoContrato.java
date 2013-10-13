package org.espe.sigec.web.reportes;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.faces.context.FacesContext;

import org.apache.poi.xwpf.usermodel.BreakType;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.espe.sigec.model.entities.ContratoProfesor;
import org.espe.sigec.web.seguridad.HomeSessionController;
import org.espe.sigec.web.utils.FacesUtils;
import org.espe.sigec.web.utils.ReportesUtil;

public class ReporteFlujoContrato {
	private XWPFDocument xwpfDocumentFlujoContra;
	private ContratoProfesor contratoProfesor;
	private Map<String, Object> mapaSub; 
	
	
	ReportesUtil reportesUtil ;
	public ReporteFlujoContrato() {
		xwpfDocumentFlujoContra = new XWPFDocument();
		reportesUtil = new ReportesUtil();
	}
	
	public ReporteFlujoContrato(ContratoProfesor contratoProfesor){
		xwpfDocumentFlujoContra = new XWPFDocument();
		setContratoProfesor(contratoProfesor);
		reportesUtil = new ReportesUtil();
		generarMapaSubtitucion(contratoProfesor);
		reportesUtil.setMapaSub(mapaSub);
	}
	
	private void generarMapaSubtitucion(ContratoProfesor contratoProfesor){
		mapaSub = new HashMap<String, Object>();
		mapaSub.put("NOMBRE_DEL_CURSO", contratoProfesor.getInvitacionDocente().getCursoPeriodo().getCurso().getNombreCurso());
		mapaSub.put("fecha_curso", reportesUtil.convertirFecha(contratoProfesor.getInvitacionDocente().getCursoPeriodo().getPeriodoAcademico().getFechaInicio()));
		mapaSub.put("lugar_a_dictarse", contratoProfesor.getInvitacionDocente().getCursoPeriodo().getLugarCapacitacion());
		mapaSub.put("valor_a_pagar", contratoProfesor.getInvitacionDocente().getDocValorPagar());
		mapaSub.put("pp", "USD");
		mapaSub.put("fecha_de_invitacion", reportesUtil.convertirFecha(contratoProfesor.getInvitacionDocente().getFechaInvitacion()));
		
		mapaSub.put("nombre_del_proveedor", contratoProfesor.getInvitacionDocente().getCursoPeriodo().getProfesor().getPersona().getNombreCompleto());
		mapaSub.put("NOMBRE_DEL_PROVEEDOR", contratoProfesor.getInvitacionDocente().getCursoPeriodo().getProfesor().getPersona().getNombreCompleto());
		
		mapaSub.put("n_horas",contratoProfesor.getInvitacionDocente().getCursoPeriodo().getNumeroHoras());
		mapaSub.put("N_invitacion",contratoProfesor.getInvitacionDocente().getInvitacionDocentePK().getDocNumInvit());
		mapaSub.put("N_inivitacion",contratoProfesor.getInvitacionDocente().getInvitacionDocentePK().getDocNumInvit());
		
		mapaSub.put("fecha_de_aceptacion", reportesUtil.convertirFecha(contratoProfesor.getInvitacionDocente().getFechaInvitacion()));
		mapaSub.put("cedula", contratoProfesor.getInvitacionDocente().getCursoPeriodo().getProfesor().getPersona().getCedula());
		mapaSub.put("numero_de_contrato", contratoProfesor.getContratoProfesorPK().getNumeroContrato());
		mapaSub.put("nombre_sr_Vicerrector", "____________");
		mapaSub.put("N_certificacion", contratoProfesor.getNumeroCertificacion());
		mapaSub.put("fecha_de_certificacion", contratoProfesor.getFechaCertificacion());
		mapaSub.put("fecha_contrato", reportesUtil.convertirFecha(new Date()));
		mapaSub.put("n_de_memo_legalizacion_contrato", contratoProfesor.getNumeroMemo());
		mapaSub.put("fecha_memmo_imprenta", reportesUtil.convertirFecha(new Date()));
		mapaSub.put("NOMBRE_COORDINADOR", ((HomeSessionController)FacesUtils.getManagedBean("homeSessionController")).getUsuarioPerfil().getPersona().getNombreCompleto());
		mapaSub.put("nombre_coordinador", ((HomeSessionController)FacesUtils.getManagedBean("homeSessionController")).getUsuarioPerfil().getPersona().getNombreCompleto());
		mapaSub.put("HORARIO_DE_CLASES", "____________");
		mapaSub.put("fecha_carta_de_pago", reportesUtil.convertirFecha(new Date()));
		mapaSub.put("actividades", contratoProfesor.getActividades());
		mapaSub.put("M__terna", "____________");
		mapaSub.put("NOMBRE_D_ELA_PP_", contratoProfesor.getNombrePartidaPresupuestaria());
		mapaSub.put("valor", contratoProfesor.getValorPartidaPresupuestaria());
		mapaSub.put("F9", "____________");
		try {
			mapaSub.put("V_HORA_CLASE", contratoProfesor.getInvitacionDocente().getDocValorPagar().divide(new BigDecimal(contratoProfesor.getInvitacionDocente().getCursoPeriodo().getNumeroHoras())));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		mapaSub.put("N_MEMO", contratoProfesor.getNumeroMemo());
		mapaSub.put("tipo_relacion_loboral", contratoProfesor.getTipoRelacionLaboral());
		mapaSub.put("Banco", contratoProfesor.getBanco());
		mapaSub.put("N_cta", contratoProfesor.getNumeroCuenta());
		mapaSub.put("plan", contratoProfesor.getPlan());
	}

	public void reporteContratoP1() {
		XWPFParagraph paragraphOne =  xwpfDocumentFlujoContra.createParagraph();
		XWPFRun paragraphOneRunOne = paragraphOne.createRun();
		
		reportesUtil.agregarEstilos(paragraphOneRunOne);

		reportesUtil.addBreakSpace(paragraphOneRunOne, 6);
		
		reportesUtil.addText(paragraphOneRunOne,"Sangolqu�, ${fecha_de_invitacion}");
		reportesUtil.addText(paragraphOneRunOne,"Oficio No. ${N_invitacion}");
		reportesUtil.addText(paragraphOneRunOne,"Se�or (a)");
		reportesUtil.addText(paragraphOneRunOne,"${nombre_del_proveedor}");
		reportesUtil.addText(paragraphOneRunOne,"Presente.-");

		reportesUtil.addBreakSpace(paragraphOneRunOne, 1);
		reportesUtil.addText(paragraphOneRunOne,"De mi consideraci�n:");
		
		paragraphOne = xwpfDocumentFlujoContra.createParagraph();
		paragraphOneRunOne = paragraphOne.createRun();
		paragraphOne.setAlignment(ParagraphAlignment.BOTH);
		
		reportesUtil.addText(paragraphOneRunOne,"Luego de expresar a usted un cordial saludo deseo extender la invitaci�n para que participe como facilitador en el curso de  \"${NOMBRE_DEL_CURSO}\",  del ${fecha_curso}, con una duraci�n de ${n_horas} horas,  a impartirse en el ${lugar_a_dictarse},  por este servicio de capacitaci�n se le reconocer� el valor total de $   ${valor_a_pagar} ${pp} menos retenciones de Ley.");
		reportesUtil.addBreakSpace(paragraphOneRunOne, 1);
		reportesUtil.addText(paragraphOneRunOne,"El contenido del curso deber� ser propuesto por el facilitador en funci�n de los lineamientos proporcionados por la Unidad de Educaci�n Continua, as� como tambi�n el original del material de apoyo para los estudiantes el cual ser� previamente revisado y aprobado.");
		reportesUtil.addBreakSpace(paragraphOneRunOne, 1);
		reportesUtil.addText(paragraphOneRunOne,"El instructor se sujetar� al Reglamento de la ESPE, en lo que corresponda al r�gimen disciplinario, deberes y derechos.");
		reportesUtil.agregarEstilos(paragraphOneRunOne);
		

		paragraphOne = xwpfDocumentFlujoContra.createParagraph();
		paragraphOneRunOne = paragraphOne.createRun();
		paragraphOne.setAlignment(ParagraphAlignment.LEFT);
		
		reportesUtil.addBreakSpace(paragraphOneRunOne, 2);
		reportesUtil.addText(paragraphOneRunOne,"Atentamente,");
		reportesUtil.addBreakSpace(paragraphOneRunOne, 4);
		reportesUtil.addText(paragraphOneRunOne,"Ing. Karla Benavides");
		reportesUtil.addText(paragraphOneRunOne,"DIRECTORA UNIDAD DE EDUCACI�N CONTINUA");
		
		reportesUtil.agregarEstilos(paragraphOneRunOne);
		paragraphOneRunOne.addBreak(BreakType.PAGE);
	}
	
	public void reporteContratoP2() {
		XWPFParagraph paragraphOne =  xwpfDocumentFlujoContra.createParagraph();
		XWPFRun paragraphOneRunOne = paragraphOne.createRun();
		paragraphOneRunOne.setItalic(Boolean.TRUE);
		paragraphOneRunOne.setFontFamily("Bookman Old Style");
		paragraphOneRunOne.setFontSize(12);

		reportesUtil.addBreakSpace(paragraphOneRunOne, 8);
		
		reportesUtil.addText(paragraphOneRunOne,"Sangolqu�, ${fecha_de_aceptacion}");
		
		reportesUtil.addBreakSpace(paragraphOneRunOne, 2);
		
		reportesUtil.addText(paragraphOneRunOne,"Sres.");
		reportesUtil.addText(paragraphOneRunOne,"ESCUELA POLIT�CNICA DEL EJ�RCITO");
		reportesUtil.addText(paragraphOneRunOne,"Presente.-");
		reportesUtil.addBreakSpace(paragraphOneRunOne, 2);
		reportesUtil.addText(paragraphOneRunOne,"De mi consideraci�n:");
		reportesUtil.addBreakSpace(paragraphOneRunOne, 2);
		reportesUtil.addText(paragraphOneRunOne,"En referencia al oficio No. ${N_inivitacion} mediante el cual me invita a participar como facilitador en el cursode ${NOMBRE_DEL_CURSO}, cuya fecha de ejecuci�n est� prevista del ${fecha_curso}, con una duraci�n de ${n_horas} horas, por medio del presente deseo dejar constancia de mi agradecimiento y aceptaci�n a la propuesta.");
		reportesUtil.addBreakSpace(paragraphOneRunOne, 4);
		reportesUtil.addText(paragraphOneRunOne,"Atentamente,");
		reportesUtil.addBreakSpace(paragraphOneRunOne, 4);
		reportesUtil.addText(paragraphOneRunOne,"${nombre_del_proveedor}");
		reportesUtil.addText(paragraphOneRunOne,"${cedula}");
		reportesUtil.addBreakSpace(paragraphOneRunOne, 3);
		paragraphOneRunOne.addBreak(BreakType.PAGE);
		// document.write(FacesUtils.getTempletaDescargaReporte("reportepoi.doc"));
	}
	
	public void reporteContratoP3(){

		XWPFParagraph paragraphOne =  xwpfDocumentFlujoContra.createParagraph();
		XWPFRun paragraphOneRunOne = paragraphOne.createRun();
		
		
		paragraphOne.setAlignment(ParagraphAlignment.CENTER);
		
		reportesUtil.addText(paragraphOneRunOne,"CONTRATO No. ${numero_de_contrato} -SC-ESPE-UEC");
		paragraphOneRunOne.setFontFamily("Bookman Old Style");
		
		paragraphOneRunOne.addBreak();
		reportesUtil.addText(paragraphOneRunOne,"CONTRATO DE PRESTACI�N DE SERVICIOS PROFESIONALES DE EDUCACI�N CONTINUA A CELEBRARSE ENTRE LA ESCUELA POLIT�CNICA DEL EJ�RCITO Y ${nombre_del_proveedor}");
		reportesUtil.agregarEstilos(paragraphOneRunOne);
		
		paragraphOne = xwpfDocumentFlujoContra.createParagraph();
		paragraphOneRunOne = paragraphOne.createRun();
		paragraphOne.setAlignment(ParagraphAlignment.BOTH);
		reportesUtil.addBreakSpace(paragraphOneRunOne, 3);
		reportesUtil.addText(paragraphOneRunOne,"Intervienen en la celebraci�n del presente contrato, por una parte, la Escuela Polit�cnica del Ej�rcito, legalmente representada por el se�or Cnel. E.M.C.,${nombre_sr_Vicerrector} en calidad de Vicerrector Acad�mico;  y delegado del rector representante legal de la ESPE; y, de otro lado, el (la), ${nombre_del_proveedor}, de nacionalidad ecuatoriana,  quien en lo posterior se denominar� el (la) contratado (a), partes legalmente capaces para contraer obligaciones, que convienen en celebrar el presente contrato, al tenor de las siguientes cl�usulas:");
		reportesUtil.addBreakSpace(paragraphOneRunOne, 3);
		reportesUtil.agregarEstilos(paragraphOneRunOne);

		paragraphOne = xwpfDocumentFlujoContra.createParagraph();
		paragraphOneRunOne = paragraphOne.createRun();
		reportesUtil.addText(paragraphOneRunOne,"PRIMERA.- ANTECEDENTES:");
		reportesUtil.agregarEstilos(paragraphOneRunOne);
		reportesUtil.addBreakSpace(paragraphOneRunOne, 3);

		reportesUtil.addText(paragraphOneRunOne,"1.	Invitaci�n formulada mediante oficio No.${N_invitacion}");
		paragraphOneRunOne.addBreak();
		reportesUtil.addText(paragraphOneRunOne,"2.	Aceptaci�n de los contratistas");
		paragraphOneRunOne.addBreak();
		
		paragraphOne = xwpfDocumentFlujoContra.createParagraph();
		paragraphOneRunOne = paragraphOne.createRun();
		paragraphOne.setAlignment(ParagraphAlignment.BOTH);
		reportesUtil.addText(paragraphOneRunOne,"3.	Certificaci�n Presupuestaria No. ,${N_certificacion}  del, ${fecha_de_certificacion} mediante la cual se informa que existe disponibilidad econ�mica en la Partida Presupuestaria No.${NOMBRE_D_ELA_PP_}, por el valor de U.S.D. $ ${valor} ${F9} para financiar el pago de la contrataci�n al personal de instructores que participar�n en la capacitaci�n continua.");
		reportesUtil.agregarEstilos(paragraphOneRunOne);
		paragraphOneRunOne.addBreak();
		
		paragraphOne = xwpfDocumentFlujoContra.createParagraph();
		paragraphOneRunOne = paragraphOne.createRun();
		reportesUtil.addText(paragraphOneRunOne,"SEGUNDA.- DOCUMENTOS HABILITANTES:");
		paragraphOneRunOne.addBreak();
		reportesUtil.addText(paragraphOneRunOne,"1.	Certificaci�n Presupuestaria No. ${N_certificacion}");
		paragraphOneRunOne.addBreak();
		reportesUtil.addText(paragraphOneRunOne,"2.	Los dem�s que constan en la cl�usula Antecedentes; y, ");
		paragraphOneRunOne.addBreak();
		reportesUtil.addText(paragraphOneRunOne,"3.	Documentos del contratista");
		reportesUtil.agregarEstilos(paragraphOneRunOne);
		paragraphOneRunOne.addBreak();


		reportesUtil.addText(paragraphOneRunOne,"TERCERA.- OBJETO:");
		paragraphOneRunOne.addBreak();
		paragraphOne = xwpfDocumentFlujoContra.createParagraph();
		paragraphOneRunOne = paragraphOne.createRun();
		paragraphOne.setAlignment(ParagraphAlignment.BOTH);
		reportesUtil.addText(paragraphOneRunOne,"Con los antecedentes expuestos, la ESPE contrata los servicios del (la) ${nombre_del_proveedor}, en calidad de instructor para que dicte la materia de, ${NOMBRE_DEL_CURSO} en el ${lugar_a_dictarse}");
		reportesUtil.agregarEstilos(paragraphOneRunOne);
		paragraphOneRunOne.addBreak();

		paragraphOne = xwpfDocumentFlujoContra.createParagraph();
		paragraphOneRunOne = paragraphOne.createRun();
		reportesUtil.addText(paragraphOneRunOne,"CUARTA.- PRECIO:");
		reportesUtil.agregarEstilos(paragraphOneRunOne);
		paragraphOneRunOne.addBreak();
		paragraphOne = xwpfDocumentFlujoContra.createParagraph();
		paragraphOneRunOne = paragraphOne.createRun();
		paragraphOne.setAlignment(ParagraphAlignment.BOTH);
		reportesUtil.addText(paragraphOneRunOne,"Por los servicios de instructor que se contrata, la Escuela Polit�cnica del Ej�rcito pagar� al (la) Contratado (a), el valor �nico de U.S.D. ${valor_a_pagar} ${pp} sujeto a retenciones de Ley.  Este valor se pagar� al finalizar la capacitaci�n previo informe del Coordinador de Unidad de Educaci�n Continua y la Orden de Pago del Vicerrector Acad�mico.");
		reportesUtil.agregarEstilos(paragraphOneRunOne);
		paragraphOneRunOne.addBreak();

		paragraphOne = xwpfDocumentFlujoContra.createParagraph();
		paragraphOneRunOne = paragraphOne.createRun();
		reportesUtil.addText(paragraphOneRunOne,"QUINTA.- DURACI�N:");
		reportesUtil.agregarEstilos(paragraphOneRunOne);
		paragraphOneRunOne.addBreak();
		paragraphOne = xwpfDocumentFlujoContra.createParagraph();
		paragraphOneRunOne = paragraphOne.createRun();
		paragraphOne.setAlignment(ParagraphAlignment.BOTH);
		reportesUtil.addText(paragraphOneRunOne,"La capacitaci�n  se desarrollar� a partir del ${fecha_curso} con una duraci�n de ${n_horas} horas, tiempo en el cual el (la) contratado (a) se compromete a ejecutar las actividades a �l (ella) encomendadas.");
		paragraphOneRunOne.addBreak();
		reportesUtil.addText(paragraphOneRunOne,"La duraci�n puede ser prorrogada por parte de la ESPE, de considerar que han existido causas no imputables al (la) contratado (a), en el cumplimiento del plazo, tales como caso fortuito y fuerza mayor de acuerdo a los t�rminos del Art. 30 del C�digo Civil."); 
		paragraphOneRunOne.addBreak();
		reportesUtil.addText(paragraphOneRunOne,"La Unidad de Educaci�n Continua, en virtud de este contrato, resolver� sobre las peticiones de ampliaci�n o pr�rroga de plazo.");
		paragraphOneRunOne.addBreak();
		reportesUtil.agregarEstilos(paragraphOneRunOne);

		paragraphOne = xwpfDocumentFlujoContra.createParagraph();
		paragraphOneRunOne = paragraphOne.createRun();
		reportesUtil.addText(paragraphOneRunOne,"SEXTA: CONSTANCIA.");
		reportesUtil.agregarEstilos(paragraphOneRunOne);
		paragraphOneRunOne.addBreak();
		paragraphOne = xwpfDocumentFlujoContra.createParagraph();
		paragraphOneRunOne = paragraphOne.createRun();
		paragraphOne.setAlignment(ParagraphAlignment.BOTH);
		reportesUtil.addText(paragraphOneRunOne,"Conste por el presente, que la contrataci�n se realiza por la capacidad y experiencia del contratado (a).  Por la naturaleza del contrato, no existe dependencia laboral entre las partes.");
		paragraphOneRunOne.addBreak();
		reportesUtil.agregarEstilos(paragraphOneRunOne);

		paragraphOne = xwpfDocumentFlujoContra.createParagraph();
		paragraphOneRunOne = paragraphOne.createRun();
		reportesUtil.addText(paragraphOneRunOne,"S�PTIMA.- TERMINACI�N:");
		reportesUtil.agregarEstilos(paragraphOneRunOne);
		paragraphOneRunOne.addBreak();
		paragraphOne = xwpfDocumentFlujoContra.createParagraph();
		paragraphOneRunOne = paragraphOne.createRun();
		paragraphOne.setAlignment(ParagraphAlignment.BOTH);
		reportesUtil.addText(paragraphOneRunOne,"El contrato en condiciones normales terminar� por el cumplimiento cabal de las obligaciones contractuales; para el efecto el Director de la Unidad de Educaci�n Continua presentar� un informe final de cumplimiento del objeto del contrato."); 
		paragraphOneRunOne.addBreak();
		reportesUtil.addText(paragraphOneRunOne,"Adicionalmente el contrato podr� terminar por las siguientes causas:");
		paragraphOneRunOne.addBreak();
		reportesUtil.addText(paragraphOneRunOne,"-	Por renuncia del contratista.");
		paragraphOneRunOne.addBreak();
		reportesUtil.addText(paragraphOneRunOne,"-	Por mutuo acuerdo de las partes.");
		paragraphOneRunOne.addBreak();
		reportesUtil.addText(paragraphOneRunOne,"-	Por muerte s�bita, caso fortuito o calamidad dom�stica.");
		paragraphOneRunOne.addBreak();
		reportesUtil.addText(paragraphOneRunOne,"-	Por inconformidad con su desempe�o acad�mico ");
		paragraphOneRunOne.addBreak();
		reportesUtil.addText(paragraphOneRunOne,"-	Por las causales determinadas en la Ley.");
		paragraphOneRunOne.addBreak();

		reportesUtil.addText(paragraphOneRunOne,"La ESPE podr� tambi�n dar por terminado este contrato unilateralmente, si el (la) contratado (a),  no cumpliere a satisfacci�n las actividades a �l (ella) asignadas, resultantes de este instrumento, o por incumplimiento de la Ley, para cuyo efecto deber� preceder el tr�mite correspondiente.");
		paragraphOneRunOne.addBreak();
		reportesUtil.agregarEstilos(paragraphOneRunOne);
		
		paragraphOne = xwpfDocumentFlujoContra.createParagraph();
		paragraphOneRunOne = paragraphOne.createRun();
		reportesUtil.addText(paragraphOneRunOne,"OCTAVA.- OBLIGACIONES TRIBUTARIAS:");
		reportesUtil.agregarEstilos(paragraphOneRunOne);
		paragraphOneRunOne.addBreak();
		paragraphOne = xwpfDocumentFlujoContra.createParagraph();
		paragraphOneRunOne = paragraphOne.createRun();
		paragraphOne.setAlignment(ParagraphAlignment.BOTH);
		reportesUtil.addText(paragraphOneRunOne,"El (la) contratado (a) se obliga a cumplir todas las obligaciones tributarias que las leyes ecuatorianas imponen, as� como las que devengan del presente instrumento y la ESPE, actuar� como agente de retenci�n en los casos y montos que se determinen en la Ley y reglamentos correspondientes.");
		paragraphOneRunOne.addBreak();
		reportesUtil.agregarEstilos(paragraphOneRunOne);

		paragraphOne = xwpfDocumentFlujoContra.createParagraph();
		paragraphOneRunOne = paragraphOne.createRun();
		reportesUtil.addText(paragraphOneRunOne,"NOVENA.- DEFINICI�N E interpretaci�n DE T�RMINOS:");
		reportesUtil.agregarEstilos(paragraphOneRunOne);
		paragraphOneRunOne.addBreak();
		paragraphOne = xwpfDocumentFlujoContra.createParagraph();
		paragraphOneRunOne = paragraphOne.createRun();
		paragraphOne.setAlignment(ParagraphAlignment.BOTH);
		reportesUtil.addText(paragraphOneRunOne,"La intenci�n de los t�rminos contenidos en este contrato o en cualquier documento o instrumento relativo a �l (la), es el cumplimiento y ejecuci�n del objeto del contrato.");
		paragraphOneRunOne.addBreak();
		reportesUtil.addText(paragraphOneRunOne,"La interpretaci�n de t�rminos ser� al tenor de la Ley y a falta de definici�n legal se estar� al significado t�cnico de los mismos y al significado natural y obvio, de conformidad con el objeto contractual y la intenci�n de los contratantes.");
		paragraphOneRunOne.addBreak();
		reportesUtil.agregarEstilos(paragraphOneRunOne);
		
		paragraphOne = xwpfDocumentFlujoContra.createParagraph();
		paragraphOneRunOne = paragraphOne.createRun();
		reportesUtil.addText(paragraphOneRunOne,"D�CIMA.- DIVERGENCIAS");
		reportesUtil.agregarEstilos(paragraphOneRunOne);
		paragraphOneRunOne.addBreak();
		paragraphOne = xwpfDocumentFlujoContra.createParagraph();
		paragraphOneRunOne = paragraphOne.createRun();
		paragraphOne.setAlignment(ParagraphAlignment.BOTH);
		reportesUtil.addText(paragraphOneRunOne,"En caso de suscitarse controversias en la aplicaci�n e interpretaci�n de este contrato, las partes convienen en sujetar toda controversia, a la soluci�n mediante trato directo, para el caso de no llegar a un acuerdo en el t�rmino de quince d�as, se someter�n a lo dispuesto en la Ley de Arbitraje y Mediaci�n, para lo cual la partes se obligan a buscar una soluci�n con la intervenci�n del Centro de Mediaci�n de la Procuradur�a General del Estado. En el caso de que las partes no lleguen a un acuerdo acudir�n a la v�a judicial, para tal efecto se sujetan a los jueces de la ciudad de Quito.");
		paragraphOneRunOne.addBreak();
		reportesUtil.agregarEstilos(paragraphOneRunOne);
		
		paragraphOne = xwpfDocumentFlujoContra.createParagraph();
		paragraphOneRunOne = paragraphOne.createRun();
		reportesUtil.addText(paragraphOneRunOne,"D�CIMO PRIMERA.- NOTIFICACIONES:");
		reportesUtil.agregarEstilos(paragraphOneRunOne);
		paragraphOneRunOne.addBreak();
		paragraphOne = xwpfDocumentFlujoContra.createParagraph();
		paragraphOneRunOne = paragraphOne.createRun();
		paragraphOne.setAlignment(ParagraphAlignment.BOTH);
		reportesUtil.addText(paragraphOneRunOne,"Las notificaciones que sean necesarias realizar entre las partes durante la ejecuci�n de este contrato se realizar�n por escrito, al contratado, en el lugar donde se desarrolla el objeto del contrato; y, a la ESPE, en las oficinas de la Direcci�n de la Unidad de Educaci�n Continua.");
		paragraphOneRunOne.addBreak();
		reportesUtil.agregarEstilos(paragraphOneRunOne);

		paragraphOne = xwpfDocumentFlujoContra.createParagraph();
		paragraphOneRunOne = paragraphOne.createRun();
		reportesUtil.addText(paragraphOneRunOne,"D�CIMO SEGUNDA.- RATIFICACI�N:");
		reportesUtil.agregarEstilos(paragraphOneRunOne);
		paragraphOneRunOne.addBreak();
		paragraphOne = xwpfDocumentFlujoContra.createParagraph();
		paragraphOneRunOne = paragraphOne.createRun();
		paragraphOne.setAlignment(ParagraphAlignment.BOTH);
		reportesUtil.addText(paragraphOneRunOne,"Para constancia de todo cuanto queda estipulado, en fe de aceptaci�n y conformidad, las partes suscriben el presente contrato, en dos ejemplares de igual tenor y valor, en Sangolqu�, a ${fecha_contrato}");
		paragraphOneRunOne.addBreak();
		reportesUtil.agregarEstilos(paragraphOneRunOne);
 
		paragraphOne = xwpfDocumentFlujoContra.createParagraph();
		paragraphOneRunOne = paragraphOne.createRun();
		reportesUtil.addText(paragraphOneRunOne,"CRNL. EMC. ${nombre_sr_Vicerrector}");
		paragraphOneRunOne.addBreak();
		reportesUtil.addText(paragraphOneRunOne,"VICERRECTOR ACAD�MICO ESPE");
		paragraphOneRunOne.addBreak();

		reportesUtil.addText(paragraphOneRunOne,"${nombre_del_proveedor}");
		paragraphOneRunOne.addBreak();
		reportesUtil.addText(paragraphOneRunOne,"${cedula} ");
		reportesUtil.agregarEstilos(paragraphOneRunOne);
		paragraphOneRunOne.addBreak(BreakType.PAGE);

	}
	
	
	public void reporteContratoP4(){
		XWPFParagraph paragraphOne =  xwpfDocumentFlujoContra.createParagraph();
		XWPFRun paragraphOneRunOne = paragraphOne.createRun();
		paragraphOneRunOne.setFontFamily("Bookman Old Style");
		
		paragraphOne.setAlignment(ParagraphAlignment.CENTER);
		reportesUtil.addText(paragraphOneRunOne,"UNIDAD DE EDUCACION CONTINUA");
		reportesUtil.agregarEstilos(paragraphOneRunOne);
		
		reportesUtil.addBreakSpace(paragraphOneRunOne, 2);

		paragraphOne = xwpfDocumentFlujoContra.createParagraph();
		paragraphOneRunOne = paragraphOne.createRun();
		reportesUtil.addText(paragraphOneRunOne,"No. ${n_de_memo_legalizacion_contrato}");
		reportesUtil.addBreakSpace(paragraphOneRunOne, 2);

		reportesUtil.addText(paragraphOneRunOne,"Sangolqu�, ${fecha_contrato}");
		reportesUtil.addBreakSpace(paragraphOneRunOne, 2);

		reportesUtil.addText(paragraphOneRunOne,"PARA:    Sr. CRNL. E.M.C ${nombre_sr_Vicerrector}");
		reportesUtil.addBreakSpace(paragraphOneRunOne, 1);
		reportesUtil.addText(paragraphOneRunOne,"	VICERRECTOR ACADEMICO");
		reportesUtil.addBreakSpace(paragraphOneRunOne, 2);

		reportesUtil.addText(paragraphOneRunOne,"ASUNTO  Legalizaci�n de contrato.");
		reportesUtil.agregarEstilos(paragraphOneRunOne);
		paragraphOneRunOne.addBreak();
		
		paragraphOne = xwpfDocumentFlujoContra.createParagraph();
		paragraphOneRunOne = paragraphOne.createRun();
		paragraphOne.setAlignment(ParagraphAlignment.BOTH);
		reportesUtil.addText(paragraphOneRunOne,"Para la respectiva legalizaci�n me permito remitir a usted, el contrato N� ${numero_de_contrato} del  ${nombre_del_proveedor} que dictar�  el curso de \"${NOMBRE_DEL_CURSO}\", del ${fecha_curso}.");
		reportesUtil.addBreakSpace(paragraphOneRunOne, 5);
		reportesUtil.agregarEstilos(paragraphOneRunOne);


		paragraphOne = xwpfDocumentFlujoContra.createParagraph();
		paragraphOneRunOne = paragraphOne.createRun();
		reportesUtil.addText(paragraphOneRunOne,"Atentamente,");
		reportesUtil.addBreakSpace(paragraphOneRunOne, 2);

		reportesUtil.addText(paragraphOneRunOne,"_______________________");
		paragraphOneRunOne.addBreak();
		reportesUtil.addText(paragraphOneRunOne,"Ing. Karla Benavides MBA");
		paragraphOneRunOne.addBreak();
		reportesUtil.addText(paragraphOneRunOne,"DIRECTORA UEC	");
		paragraphOneRunOne.addBreak();
		paragraphOneRunOne.addBreak(BreakType.PAGE);
		reportesUtil.agregarEstilos(paragraphOneRunOne);
	}
	
	@Deprecated
	public void reporteContratoP5(){
		/*
		XWPFParagraph paragraphOne =  xwpfDocumentFlujoContra.createParagraph();
		XWPFRun paragraphOneRunOne = paragraphOne.createRun();

		paragraphOneRunOne.setFontFamily("Bookman Old Style");
		
		reportesUtil.addText(paragraphOneRunOne,"UNIDAD DE EDUCACION CONTINUA");
		reportesUtil.addBreakSpace(paragraphOneRunOne, 2);
		reportesUtil.addText(paragraphOneRunOne,"No.${numero_de_contrato}");
		reportesUtil.addBreakSpace(paragraphOneRunOne, 2);
		reportesUtil.addText(paragraphOneRunOne,"Sangolqu�, ${fecha_memmo_imprenta}");
		reportesUtil.addBreakSpace(paragraphOneRunOne, 2);
		reportesUtil.addText(paragraphOneRunOne,"PARA:  	Ing. Byron Tamayo");
		reportesUtil.addBreakSpace(paragraphOneRunOne, 2);
		reportesUtil.addText(paragraphOneRunOne,"Coordinador de la EDITORIAL");
		reportesUtil.addBreakSpace(paragraphOneRunOne, 2);
		reportesUtil.addText(paragraphOneRunOne,"ASUNTO: Reproducci�n de material evento de capacitaci�n");
		reportesUtil.addBreakSpace(paragraphOneRunOne, 2);
		reportesUtil.addText(paragraphOneRunOne,"Agradecer� a usted se�or Ingeniero, disponer la reproducci�n de ${NUMERO_DE_EJEMPLARES} folletos, para el curso de ${NOMBRE_DEL_CURSO}");
		reportesUtil.addBreakSpace(paragraphOneRunOne, 2);
		reportesUtil.addText(paragraphOneRunOne,"Adjunto copia de la certificaci�n presupuestaria N�.${N_partida_presupuestria_imprenta}, de fecha  ${fecha_memmo_imprenta} por el valor de ${valor_imprenta}");
		reportesUtil.addBreakSpace(paragraphOneRunOne, 2);

		reportesUtil.addText(paragraphOneRunOne,"Atentamente,");
		reportesUtil.addBreakSpace(paragraphOneRunOne, 2);

		reportesUtil.addText(paragraphOneRunOne,"_______________________");
		reportesUtil.addBreakSpace(paragraphOneRunOne, 2);
		reportesUtil.addText(paragraphOneRunOne,"Ing. Karla Benavides MBA");
		reportesUtil.addBreakSpace(paragraphOneRunOne, 2);
		reportesUtil.addText(paragraphOneRunOne,"Directora de la UEC");
		paragraphOneRunOne.addBreak(BreakType.PAGE);
		 */
	}
	
	public void reporteContratoP6(){
		XWPFParagraph paragraphOne =  xwpfDocumentFlujoContra.createParagraph();
		XWPFRun paragraphOneRunOne = paragraphOne.createRun();
		
		;
		paragraphOne.setAlignment(ParagraphAlignment.CENTER);
		reportesUtil.addText(paragraphOneRunOne,"UNIDAD DE EDUCACI�N CONTINUA");
		reportesUtil.addBreakSpace(paragraphOneRunOne, 1);
		reportesUtil.addText(paragraphOneRunOne,"INFORME QUE PRESENTA ${NOMBRE_COORDINADOR}, COORDINADOR (A) DE EVENTOS DE LA UNIDAD EDUCACI�N CONTINUA, SOBRE  LA  PRESTACI�N DE SERVICIOS PROFESIONALES DE LA ${NOMBRE_DEL_PROVEEDOR}");
		reportesUtil.addBreakSpace(paragraphOneRunOne, 2);
		reportesUtil.agregarEstilos(paragraphOneRunOne);
		
		paragraphOne = xwpfDocumentFlujoContra.createParagraph();
		paragraphOneRunOne = paragraphOne.createRun();
		reportesUtil.addText(paragraphOneRunOne,"ANTECEDENTES");
		reportesUtil.addBreakSpace(paragraphOneRunOne, 3);
		reportesUtil.agregarEstilos(paragraphOneRunOne);

		reportesUtil.addText(paragraphOneRunOne,"${actividades}");
		reportesUtil.addBreakSpace(paragraphOneRunOne, 1);
		
		paragraphOne = xwpfDocumentFlujoContra.createParagraph();
		paragraphOneRunOne = paragraphOne.createRun();
		paragraphOne.setAlignment(ParagraphAlignment.BOTH);
		reportesUtil.addText(paragraphOneRunOne,"La ejecuci�n del evento de capacitaci�n de \"${NOMBRE_DEL_CURSO}\" se ejecut� en la semana del ${fecha_curso}");
		reportesUtil.addBreakSpace(paragraphOneRunOne, 3);


		reportesUtil.addText(paragraphOneRunOne,"DESARROLLO ");
		reportesUtil.addBreakSpace(paragraphOneRunOne, 2);
		reportesUtil.addText(paragraphOneRunOne,"La  Unidad de Educaci�n Continua dentro del Plan de capacitaci�n realiz� el curso de ${NOMBRE_DEL_CURSO} realizado en ${lugar_a_dictarse},  el mismo que se ejecut� con normalidad del ${fecha_curso} con una duraci�n de ${n_horas} horas en la que la instrucci�n estuvo a cargo de �l (la) ${nombre_del_proveedor}");
		reportesUtil.addBreakSpace(paragraphOneRunOne, 1);
		reportesUtil.addText(paragraphOneRunOne,"El proceso de selecci�n del Facilitador se realiz� a trav�s de la revisi�n de perfiles de docentes que constan en la Base de datos de la Unidad a fin de identificar los candidatos que re�nan el perfil y sean especializados en la tem�tica, con los que se tom� contacto,  para verificar la disponibilidad de tiempo y el inter�s para dictar el curso, siendo seleccionado por su formaci�n y experiencia en el tema de \"${NOMBRE_DEL_CURSO}\"  al (la) ${nombre_del_proveedor} con un resultado  de ${M__terna}, siendo el m�s alto por lo que se procedi� a realizar el contrato N� ${numero_de_contrato},"); 
		reportesUtil.addBreakSpace(paragraphOneRunOne, 1);
		reportesUtil.addText(paragraphOneRunOne,"Se adjunta los siguientes documentos.");
		reportesUtil.addBreakSpace(paragraphOneRunOne, 2);
		
		reportesUtil.addText(paragraphOneRunOne,"CONCLUSIONES");
		reportesUtil.addBreakSpace(paragraphOneRunOne, 2);
		reportesUtil.addText(paragraphOneRunOne,"Despu�s de realizar el an�lisis de la documentaci�n entrega por el Facilitador (a)  y las coordinaciones pertinentes por el personal responsable de la Unidad de Educaci�n Continua de la ESPE ${nombre_coordinador} de dicho proceso, concluye que el profesional ${nombre_del_proveedor} ha cumplido satisfactoriamente con el Desarrollo del curso ${NOMBRE_DEL_CURSO} en el horario de ${HORARIO_DE_CLASES} el mismo que fue programado por la ESPE, objeto del contrato ${numero_de_contrato}");

		reportesUtil.addBreakSpace(paragraphOneRunOne, 1);
		reportesUtil.addText(paragraphOneRunOne,"RECOMENDACIONES");
		reportesUtil.addBreakSpace(paragraphOneRunOne, 2);
		reportesUtil.addText(paragraphOneRunOne,"Una vez devengado el servicio de capacitaci�n del curso \"${NOMBRE_DEL_CURSO}\", en las fechas estipuladas y contando con la Certificaci�n Presupuestaria N� ${N_certificacion} y  toda la documentaci�n solicitada por la Direcci�n Financiera se recomienda se proceda con el tramite respectivo.");
		reportesUtil.addBreakSpace(paragraphOneRunOne, 2);
		reportesUtil.agregarEstilos(paragraphOneRunOne);
		
		
		paragraphOne = xwpfDocumentFlujoContra.createParagraph();
		paragraphOneRunOne = paragraphOne.createRun();
		reportesUtil.addText(paragraphOneRunOne,"Sangolqu�, ${fecha_carta_de_pago}");
		reportesUtil.addBreakSpace(paragraphOneRunOne, 3);
		reportesUtil.addText(paragraphOneRunOne,"____________________________________");
		reportesUtil.addBreakSpace(paragraphOneRunOne, 1);
		reportesUtil.addText(paragraphOneRunOne,"${nombre_coordinador}");
		reportesUtil.addBreakSpace(paragraphOneRunOne, 1);
		reportesUtil.addText(paragraphOneRunOne,"Coordinador (a) de Capacitaci�n UEC");
		reportesUtil.addBreakSpace(paragraphOneRunOne, 3);
		reportesUtil.agregarEstilos(paragraphOneRunOne);
		
		paragraphOne = xwpfDocumentFlujoContra.createParagraph();
		paragraphOneRunOne = paragraphOne.createRun();
		paragraphOne.setAlignment(ParagraphAlignment.BOTH);
		reportesUtil.addText(paragraphOneRunOne,"Despu�s del informe presentado por la Coordinadora de la Capacitaci�n, ${nombre_coordinador} y la revisi�n de los documentos  por el Ing. Edison Cabezas,   yo como Directora de la Unidad de Educaci�n Continua, recomiendo que se proceda con los tr�mites correspondientes.");
		reportesUtil.agregarEstilos(paragraphOneRunOne);
		
		paragraphOne = xwpfDocumentFlujoContra.createParagraph();
		paragraphOneRunOne = paragraphOne.createRun();
		reportesUtil.addBreakSpace(paragraphOneRunOne, 3);
		reportesUtil.addText(paragraphOneRunOne,"_____________________");
		reportesUtil.addBreakSpace(paragraphOneRunOne, 1);
		reportesUtil.addText(paragraphOneRunOne,"Ing. Karla Benavides ");
		reportesUtil.addBreakSpace(paragraphOneRunOne, 1);
		reportesUtil.addText(paragraphOneRunOne,"Directora UEC");
		reportesUtil.agregarEstilos(paragraphOneRunOne);


		paragraphOneRunOne.addBreak(BreakType.PAGE);

	}
	public void reporteContratoP7(){
		XWPFParagraph paragraphOne =  xwpfDocumentFlujoContra.createParagraph();
		XWPFRun paragraphOneRunOne = paragraphOne.createRun();
		paragraphOne.setAlignment(ParagraphAlignment.CENTER);
		reportesUtil.addText(paragraphOneRunOne,"VICERRECTORADO ACADEMICO");
		reportesUtil.addText(paragraphOneRunOne,"UNIDAD DE EDUCACI�N CONTINUA");
		reportesUtil.addText(paragraphOneRunOne,"MEMORANDO");
	
		reportesUtil.addBreakSpace(paragraphOneRunOne, 1);
		reportesUtil.addText(paragraphOneRunOne,"No. -${N_MEMO}");
		reportesUtil.addBreakSpace(paragraphOneRunOne, 1);
		reportesUtil.agregarEstilos(paragraphOneRunOne);
		
		paragraphOne =  xwpfDocumentFlujoContra.createParagraph();
		paragraphOneRunOne = paragraphOne.createRun();
		reportesUtil.addText(paragraphOneRunOne,"Sangolqu� ${fecha_carta_de_pago}");
		reportesUtil.addBreakSpace(paragraphOneRunOne, 1);
		reportesUtil.addText(paragraphOneRunOne,"DE:	CRNL. E.M.C. ${nombre_sr_Vicerrector}");
		reportesUtil.addText(paragraphOneRunOne,"	VICERRECTOR ACADEMICO");
		reportesUtil.addText(paragraphOneRunOne,"PARA:	SRA. ING. GABRIELA CORDOVA");
		reportesUtil.addText(paragraphOneRunOne,"	DIRECTORA FINANCIERA");
		reportesUtil.addText(paragraphOneRunOne,"En:  	      Su despacho.-");
		reportesUtil.addBreakSpace(paragraphOneRunOne, 1);
		reportesUtil.agregarEstilos(paragraphOneRunOne);
		
		paragraphOne =  xwpfDocumentFlujoContra.createParagraph();
		paragraphOneRunOne = paragraphOne.createRun();
		paragraphOne.setAlignment(ParagraphAlignment.BOTH);
		reportesUtil.addText(paragraphOneRunOne,"Mucho agradecer� a usted, se�ora Directora, ordenar y disponer a quien corresponda el pago por servicios de capacitaci�n, de acuerdo al siguiente detalle:");
		reportesUtil.agregarEstilos(paragraphOneRunOne);
		reportesUtil.addBreakSpace(paragraphOneRunOne, 1);
		
		paragraphOne =  xwpfDocumentFlujoContra.createParagraph();
		paragraphOneRunOne = paragraphOne.createRun();
		reportesUtil.addText(paragraphOneRunOne,"Instructor:	${nombre_del_proveedor}");

		reportesUtil.addText(paragraphOneRunOne,"Funci�n:	${tipo_relacion_loboral}");

		reportesUtil.addText(paragraphOneRunOne,"Curso:	${NOMBRE_DEL_CURSO}");

		reportesUtil.addText(paragraphOneRunOne,"Lugar:	${lugar_a_dictarse}");

		reportesUtil.addText(paragraphOneRunOne,"Contrato N�:	${numero_de_contrato}");

		reportesUtil.addText(paragraphOneRunOne,"Fechas:	${fecha_curso}");

		reportesUtil.addText(paragraphOneRunOne,"RUC	${cedula}");

		reportesUtil.addText(paragraphOneRunOne,"Horas del curso:	${n_horas} horas");

		reportesUtil.addText(paragraphOneRunOne,"Valor hora clase:	${V_HORA_CLASE}");

		reportesUtil.addText(paragraphOneRunOne,"Total a pagar: $ ${valor_a_pagar},oo ${pp}");
		reportesUtil.addText(paragraphOneRunOne,"Banco: ${Banco}");
		reportesUtil.addText(paragraphOneRunOne,"No. de Cuentas: ${N_cta}");
		reportesUtil.addText(paragraphOneRunOne,"No. Ticket de avi�n: ${plan}");
		reportesUtil.agregarEstilos(paragraphOneRunOne);
		
		reportesUtil.addBreakSpace(paragraphOneRunOne, 1);
		
		paragraphOne =  xwpfDocumentFlujoContra.createParagraph();
		paragraphOneRunOne = paragraphOne.createRun();
		paragraphOne.setAlignment(ParagraphAlignment.BOTH);
		reportesUtil.addText(paragraphOneRunOne,"Para el efecto adjunto la Certificaci�n de Fondos No.${N_certificacion} de  fecha ${fecha_de_certificacion} Adem�s, informo que cumpli� la capacitaci�n con total satisfacci�n del cliente.");     
		reportesUtil.agregarEstilos(paragraphOneRunOne);
		
		reportesUtil.addBreakSpace(paragraphOneRunOne, 2);
		
		paragraphOne =  xwpfDocumentFlujoContra.createParagraph();
		paragraphOneRunOne = paragraphOne.createRun();
		paragraphOne.setAlignment(ParagraphAlignment.CENTER);
		reportesUtil.addText(paragraphOneRunOne,"DIOS, PATRIA Y LIBERTAD,");

		reportesUtil.addBreakSpace(paragraphOneRunOne, 2);
		reportesUtil.addText(paragraphOneRunOne,"____________________________________");
		reportesUtil.addText(paragraphOneRunOne,"${nombre_sr_Vicerrector}");
		reportesUtil.addText(paragraphOneRunOne,"CRNL. E.M.C.");
		reportesUtil.addText(paragraphOneRunOne,"VICERRECTOR ACADEMICO");
		reportesUtil.addBreakSpace(paragraphOneRunOne, 2);
		reportesUtil.agregarEstilos(paragraphOneRunOne);
		
		paragraphOne =  xwpfDocumentFlujoContra.createParagraph();
		paragraphOneRunOne = paragraphOne.createRun();
		reportesUtil.addText(paragraphOneRunOne,"Adjunto:   documentaci�n");
		reportesUtil.agregarEstilos(paragraphOneRunOne);
		
		paragraphOne =  xwpfDocumentFlujoContra.createParagraph();
		paragraphOneRunOne = paragraphOne.createRun();
		paragraphOne.setAlignment(ParagraphAlignment.RIGHT);
		reportesUtil.addText(paragraphOneRunOne,"Elaborado por: Ing. E. Cabezas");
		reportesUtil.addText(paragraphOneRunOne,"Revisado por: Ing. K. Benavides");
		reportesUtil.agregarEstilos(paragraphOneRunOne);

		
	}
	
	public void generarContrato(String filename) throws IOException{
		
		
		xwpfDocumentFlujoContra.write(FacesUtils.getTempletaDescargaReporte(filename));
	    FacesContext facesContext = FacesContext.getCurrentInstance();
	    facesContext.responseComplete();
	    
	}

	public ContratoProfesor getContratoProfesor() {
		return contratoProfesor;
	}

	public void setContratoProfesor(ContratoProfesor contratoProfesor) {
		this.contratoProfesor = contratoProfesor;
	}

}
