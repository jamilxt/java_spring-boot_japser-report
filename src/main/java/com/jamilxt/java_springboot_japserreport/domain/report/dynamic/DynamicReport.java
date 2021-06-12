package com.jamilxt.java_springboot_japserreport.domain.report.dynamic;

import com.jamilxt.java_springboot_japserreport.domain.report.ExportType;
import com.jamilxt.java_springboot_japserreport.domain.report.Report;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;

public class DynamicReport extends Report {
  private final DynamicReportProperties dynamicReportProperties;

  public DynamicReport(
      InputStream inputStream,
      String title,
      ExportType exportType,
      HttpServletResponse response,
      DynamicReportProperties dynamicReportProperties) {
    super(inputStream, title, null, exportType, response);
    this.dynamicReportProperties = dynamicReportProperties;
  }

  public DynamicReportProperties getDynamicReportProperties() {
    return dynamicReportProperties;
  }
}
