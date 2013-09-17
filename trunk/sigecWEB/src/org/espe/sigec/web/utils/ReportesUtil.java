package org.espe.sigec.web.utils;

import org.apache.poi.xwpf.usermodel.BreakClear;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

/**
 * @author roberto
 *
 */
public class ReportesUtil {
	private static final ReportesUtil REPORTES_UTIL = new ReportesUtil();

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
		xwpfRunParagraph.setText(textToAdd);
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
}
