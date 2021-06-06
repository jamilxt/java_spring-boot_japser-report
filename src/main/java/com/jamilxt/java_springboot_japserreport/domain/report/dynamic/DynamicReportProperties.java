package com.jamilxt.java_springboot_japserreport.domain.report.dynamic;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter@Setter
public class DynamicReportProperties {
  List<String> columnHeaders;
  List<Integer> indexesOfColumnTypeNumber;
  List<List<String>> rows;
  List<String> summary;
}
