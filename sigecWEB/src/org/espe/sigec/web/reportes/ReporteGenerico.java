package org.espe.sigec.web.reportes;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.HashMap;

import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRPdfExporterParameter;

import com.lowagie.text.pdf.PdfWriter;

/**
 * @author roberto
 *
 */
public class ReporteGenerico {
	private JasperPrint jasperPrint;
	private JRBeanCollectionDataSource beanCollectionDataSource;
	
	public <T> void generarReporteSimple(String jasperFileName, Collection<T>lista){
		setBeanCollectionDataSource(new JRBeanCollectionDataSource(lista));
		try {
			String recursosReportes ="/WEB-INF/reportes/"+jasperFileName.concat(".jasper") ;
			
			InputStream stream = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream(recursosReportes);
			
			setJasperPrint(JasperFillManager.fillReport(stream, new HashMap<String, Object>(), getBeanCollectionDataSource() ));
			HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
			
			httpServletResponse.setHeader("Content-disposition", "attachment; filename=sigecPDFReport.pdf");
			
			ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
			JRExporter exporter = new JRPdfExporter();
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM,servletOutputStream);
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            exporter.setParameter(JRPdfExporterParameter.IS_ENCRYPTED, Boolean.TRUE);
			exporter.setParameter(JRPdfExporterParameter.IS_128_BIT_KEY, Boolean.TRUE);
			exporter.setParameter(JRPdfExporterParameter.USER_PASSWORD, "jasper");
			exporter.setParameter(JRPdfExporterParameter.OWNER_PASSWORD, "reports");
			exporter.setParameter(
				JRPdfExporterParameter.PERMISSIONS, new Integer(PdfWriter.ALLOW_COPY | PdfWriter.ALLOW_PRINTING) );
			
			JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
		} catch (JRException e1) {
			e1.printStackTrace();
		} catch (IOException e2) {
			e2.printStackTrace();
		}
	}

	public JasperPrint getJasperPrint() {
		return jasperPrint;
	}

	public void setJasperPrint(JasperPrint jasperPrint) {
		this.jasperPrint = jasperPrint;
	}

	public JRBeanCollectionDataSource getBeanCollectionDataSource() {
		return beanCollectionDataSource;
	}

	public void setBeanCollectionDataSource(
			JRBeanCollectionDataSource beanCollectionDataSource) {
		this.beanCollectionDataSource = beanCollectionDataSource;
	}
}
