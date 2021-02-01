package com.jamilxt.java_springboot_japserreport.controller.report;

import com.jamilxt.java_springboot_japserreport.model.report.ExportType;
import com.jamilxt.java_springboot_japserreport.service.report.ReportService;
import net.sf.jasperreports.engine.JRException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@RestController
public class ReportController implements ReportApi {

  private final ReportService reportService;

  public ReportController(ReportService reportService) {
    this.reportService = reportService;
  }

  @Override
  public ResponseEntity<Void> downloadTransactionReport(ExportType exportType,
                                                        HttpServletResponse response) throws IOException, JRException {
    reportService.downloadTransactionReport(exportType, response);
    return ResponseEntity.ok().build();
  }
}
