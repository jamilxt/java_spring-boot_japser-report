package com.jamilxt.java_springboot_japserreport.service.report;

import com.jamilxt.java_springboot_japserreport.domain.report.ExportType;
import com.jamilxt.java_springboot_japserreport.model.transaction.Transaction;
import com.jamilxt.java_springboot_japserreport.service.transaction.TransactionService;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleWriterExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

@Service
public class ReportService {

  private final TransactionService transactionService;

  public ReportService(TransactionService transactionService) {
    this.transactionService = transactionService;
  }

  public void downloadTransactionReport(ExportType exportType, HttpServletResponse response) throws JRException, IOException {
    List<Transaction> transactionList = transactionService.getTransactionList();
    exportReport(transactionList, exportType, response);
  }

  private void exportReport(Collection<?> beanCollection, ExportType exportType, HttpServletResponse response) throws JRException, IOException {
    InputStream transactionReportStream =
        getClass()
            .getResourceAsStream(
                "/transaction_report_" + exportType.toString().toLowerCase() + ".jrxml");
    String titleTransactionBy = "Transactions Report";

    JasperReport jasperReport = JasperCompileManager.compileReport(transactionReportStream);
    JRBeanCollectionDataSource beanColDataSource =
        new JRBeanCollectionDataSource(beanCollection);
    HashMap<String, Object> parameters = new HashMap();
    parameters.put("title", titleTransactionBy);

    JasperPrint jasperPrint =
        JasperFillManager.fillReport(jasperReport, parameters, beanColDataSource);
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
    var dateTimeNow = LocalDateTime.now().format(formatter);
    var fileName = titleTransactionBy.replace(" ", "") + dateTimeNow;

    if (exportType == ExportType.PDF) {

      JRPdfExporter exporter = new JRPdfExporter();
      exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
      exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(response.getOutputStream()));
      response.setContentType("application/pdf");
      response.setHeader(
          HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + fileName + ".pdf;");
      exporter.exportReport();

    } else if (exportType == ExportType.EXCEL) {

      JRXlsxExporter exporter = new JRXlsxExporter();
      SimpleXlsxReportConfiguration reportConfigXLS = new SimpleXlsxReportConfiguration();
      reportConfigXLS.setSheetNames(new String[]{titleTransactionBy});
      reportConfigXLS.setDetectCellType(true);
      reportConfigXLS.setCollapseRowSpan(false);
      exporter.setConfiguration(reportConfigXLS);
      exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
      exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(response.getOutputStream()));
      response.setHeader(
          HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + fileName + ".xlsx;");
      response.setContentType("application/octet-stream");
      exporter.exportReport();

    } else if (exportType == ExportType.CSV) {

      JRCsvExporter exporter = new JRCsvExporter();
      exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
      var outputStream = response.getOutputStream();
      exporter.setExporterOutput((new SimpleWriterExporterOutput(outputStream)));
      response.setHeader(
          HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + fileName + ".csv;");
      response.setContentType("text/csv");
      exporter.exportReport();

    } else if (exportType == ExportType.DOCX) {

      JRDocxExporter exporter = new JRDocxExporter();
      exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
      exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(response.getOutputStream()));
      response.setHeader(
          HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + fileName + ".docx;");
      response.setContentType("application/octet-stream");
      exporter.exportReport();

    } else {
      throw new RuntimeException("File Format isn't supported!");
    }
  }
}
