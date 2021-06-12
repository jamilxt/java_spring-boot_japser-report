package com.jamilxt.java_springboot_japserreport.domain.report;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleWriterExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;
import org.springframework.http.HttpHeaders;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;

import static com.jamilxt.java_springboot_japserreport.domain.report.ExportType.*;

public class Report {
  public static final String ATTACHMENT_FILENAME = "attachment;filename=";
  InputStream inputStream;
  String title;
  Collection<?> beanCollection;
  ExportType exportType;
  HttpServletResponse response;

  public Report(
      InputStream inputStream,
      String title,
      Collection<?> beanCollection,
      ExportType exportType,
      HttpServletResponse response) {
    this.inputStream = inputStream;
    this.title = title;
    this.beanCollection = beanCollection;
    this.exportType = exportType;
    this.response = response;
  }

  public InputStream getInputStream() {
    return inputStream;
  }

  public String getTitle() {
    return title;
  }

  public Collection<?> getBeanCollection() {
    return beanCollection;
  }

  public ExportType getExportType() {
    return exportType;
  }

  public void exportViaJasperReport(JasperPrint jasperPrint) throws JRException, IOException {
    if (exportType == PDF) {
      JRPdfExporter exporter = new JRPdfExporter();
      exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
      exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(response.getOutputStream()));
      setHeaderAndContentType(response, exportType);
      exporter.exportReport();
    } else if (exportType == EXCEL) {
      JRXlsxExporter exporter = new JRXlsxExporter();
      SimpleXlsxReportConfiguration reportConfigXLS = new SimpleXlsxReportConfiguration();
      reportConfigXLS.setSheetNames(new String[] {title});
      reportConfigXLS.setDetectCellType(true);
      reportConfigXLS.setCollapseRowSpan(false);
      reportConfigXLS.setRemoveEmptySpaceBetweenRows(true);
      reportConfigXLS.setRemoveEmptySpaceBetweenColumns(true);
      exporter.setConfiguration(reportConfigXLS);
      exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
      exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(response.getOutputStream()));
      setHeaderAndContentType(response, exportType);
      exporter.exportReport();
    } else if (exportType == CSV) {
      JRCsvExporter exporter = new JRCsvExporter();
      exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
      var outputStream = response.getOutputStream();
      exporter.setExporterOutput((new SimpleWriterExporterOutput(outputStream)));
      setHeaderAndContentType(response, exportType);
      exporter.exportReport();
    } else if (exportType == DOCX) {
      JRDocxExporter exporter = new JRDocxExporter();
      exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
      exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(response.getOutputStream()));
      setHeaderAndContentType(response, exportType);
      exporter.exportReport();
    } else {
      throw new RuntimeException("File Format isn't supported!");
    }
  }

  private void setHeaderAndContentType(HttpServletResponse response, ExportType exportType)
      throws UnsupportedEncodingException {
    response.setHeader(HttpHeaders.CONTENT_DISPOSITION, getHeaderValue(exportType));
    response.setContentType(exportType.getContentType());
  }

  private String getHeaderValue(ExportType exportType) throws UnsupportedEncodingException {
    var formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
    var dateTimeNow = LocalDateTime.now().format(formatter);
    var fileName =
        URLEncoder.encode(title.replaceAll("[^\\w-]+", "_") + "_" + dateTimeNow, "UTF-8");
    return ATTACHMENT_FILENAME + fileName + exportType.getExtension() + ";";
  }
}