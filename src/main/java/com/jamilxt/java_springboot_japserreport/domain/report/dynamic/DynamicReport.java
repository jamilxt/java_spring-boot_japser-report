package com.jamilxt.java_springboot_japserreport.domain.report.dynamic;

import com.jamilxt.java_springboot_japserreport.domain.report.Report;
import com.jamilxt.java_springboot_japserreport.model.report.ExportType;
import lombok.Getter;
import lombok.experimental.Accessors;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.Collection;

@Getter
@Accessors(chain = true)
public class DynamicReport extends Report {
  private final DynamicReportProperties dynamicReportProperties;

  public DynamicReport(
      InputStream inputStream,
      String title,
      Collection<?> beanCollection,
      ExportType exportType,
      HttpServletResponse response,
      DynamicReportProperties dynamicReportProperties) {
    super(inputStream, title, beanCollection, exportType, response);
    this.dynamicReportProperties = dynamicReportProperties;
  }
}
