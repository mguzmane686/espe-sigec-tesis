package org.espe.sigec.web.memos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.text.StrSubstitutor;
import org.espe.sigec.model.views.Memo;
import org.espe.sigec.web.reportes.ReporteGenerico;

/**
 * @author Roberto
 *
 */
@SuppressWarnings("serial")
@ManagedBean(name="generacionMemosController")
@ViewScoped
public class GeneracionMemosController implements Serializable{
	private Memo memo;
	
	public GeneracionMemosController() {
		super();
	}

	public void btnGenerarMemo(){
		Collection<Memo> lstMemos = new ArrayList<Memo>(1);
		Map valuesMap = new HashMap();
		valuesMap.put("NOMBRE_DEL_CURSO", "quick brown fox");
		valuesMap.put("fecha_curso", String.valueOf(new Date()));
		valuesMap.put("lugar_a_dictarse", "Espe");
		valuesMap.put("valor_a_pagar", "10");
		valuesMap.put("pp", "USD");
		 
		valuesMap.put("n_horas", "10");
		String templateString = "Luego de expresar a usted un cordial saludo deseo extender la invitación para que participe como facilitador en el curso de  " +
		"${NOMBRE_DEL_CURSO},  del ${fecha_curso}, con una duración de ${n_horas} horas,  a impartirse en el ${lugar_a_dictarse},  " +
		"por este servicio de capacitación se le reconocerá el valor total de ${valor_a_pagar} ${pp} menos retenciones de Ley. " +
		"El contenido del curso deberá ser propuesto por el facilitador en función de los lineamientos proporcionados por la Unidad de Educación Continua, " +
		"así como también el original del material de apoyo para los estudiantes el cual será previamente revisado y aprobado. " +
		"El instructor se sujetará al Reglamento de la ESPE, en lo que corresponda al régimen disciplinario, deberes y derechos";
		System.out.println(templateString);
		StrSubstitutor sub = new StrSubstitutor(valuesMap);
		String resolvedString = sub.replace(templateString);
		System.out.println(resolvedString);
		setMemo(new Memo());
		getMemo().setFechaInivitacion(new Date());
		getMemo().setNombreDirectora("Ing. Karla Benavides");
		getMemo().setNombreElaborador("maniac787");
		getMemo().setNombreProveedor("Paul diaz");
		getMemo().setNumeroInivitacion("000001");
		getMemo().setCuerpoMemo(resolvedString);
		
		lstMemos.add(getMemo());
		ReporteGenerico.getResource().generarReporteSimple("invitacionProveedor",  lstMemos);
	}
	
	public Memo getMemo() {
		return memo;
	}

	public void setMemo(Memo memo) {
		this.memo = memo;
	}
	
	
}
