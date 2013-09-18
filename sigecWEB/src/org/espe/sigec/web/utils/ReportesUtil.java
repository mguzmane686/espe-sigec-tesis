package org.espe.sigec.web.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.apache.commons.lang3.text.StrSubstitutor;
import org.apache.poi.xwpf.usermodel.BreakClear;
import org.apache.poi.xwpf.usermodel.XWPFRun;

/**
 * @author roberto
 *
 */
public class ReportesUtil {
	private static final ReportesUtil REPORTES_UTIL = new ReportesUtil();
	private Map<String, Object> mapaSub; 
	public static ReportesUtil getInstance() {
		return REPORTES_UTIL;
	}
	
	public void addBreakSpace(XWPFRun xwpfRunParagraph, int numSpaces){
		for(int i=0;i<numSpaces;i++){
			xwpfRunParagraph.addBreak(BreakClear.RIGHT);
		}
	}
	
	
	public void addText(XWPFRun xwpfRunParagraph, String textToAdd, int spacesAtStart, int spacesAtEnd){
		addBreakSpace(xwpfRunParagraph, spacesAtStart);
		xwpfRunParagraph.setText(textToAdd);
		addBreakSpace(xwpfRunParagraph, spacesAtEnd);
	}
	
	public void addText(XWPFRun xwpfRunParagraph, String textToAdd){
		xwpfRunParagraph.setText(resolveString(textToAdd));
		xwpfRunParagraph.addBreak();
	}
	
	/*public XWPFParagraph createNewPragraph(XWPFDocument xwpfDocumentFlujoContra, XWPFParagraph xwpfParagraph,  XWPFRun xwpfRun, ParagraphAlignment paragraphAlignment){
		xwpfParagraph =  xwpfDocumentFlujoContra.createParagraph();
		if(paragraphAlignment!=null){
			xwpfParagraph.setAlignment(paragraphAlignment);
		}
		
		xwpfRun.setItalic(Boolean.TRUE);
		return xwpfParagraph;
	}*/
	
	public void agregarEstilos(XWPFRun xwpfRun){
		xwpfRun.setItalic(true);
		xwpfRun.setFontFamily("Bookman Old Style");
		xwpfRun.setFontSize(12);
	}
	
	
	public String resolveString(String texto){
		StrSubstitutor sub = new StrSubstitutor(getMapaSub());
		String resolvedString = sub.replace(texto);
		System.out.println(resolvedString);
		return resolvedString;
	}

	public Map<String, Object> getMapaSub() {
		return mapaSub;
	}

	public void setMapaSub(Map<String, Object> mapaSub) {
		this.mapaSub = mapaSub;
	}
	
	public String convertirFecha(Date fecha){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-YYYY") ;
		return simpleDateFormat.format(fecha);
	}
	
}