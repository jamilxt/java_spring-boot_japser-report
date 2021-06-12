package com.jamilxt.java_springboot_japserreport.domain;

import com.jamilxt.java_springboot_japserreport.domain.report.ExportType;
import com.jamilxt.java_springboot_japserreport.domain.report.dynamic.DynamicReportProperties;
import com.jamilxt.java_springboot_japserreport.model.transaction.Transaction;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import static com.jamilxt.java_springboot_japserreport.util.Constant.*;

public class TransactionFilter {
  ExportType exportType;

  public TransactionFilter(ExportType exportType) {
    this.exportType = exportType;
  }

  public InputStream getInputStream() {
    var name = FOLDER_PATH_REPORT_DYNAMIC + this.exportType.toString().toLowerCase() + EXTENSION_JRXML;
    return getClass().getResourceAsStream(name);
  }

  public String getReportTitle() {
    return  "Transaction Summary Report";
  }

  public DynamicReportProperties generateDynamicColumnAndRows(List<Transaction> list) {
    var totalVolume = Double.valueOf(0.00);
    var totalAmount = Double.valueOf(0.00);
    DecimalFormat df = new DecimalFormat("#,###.###");

    List<String> columnHeaders = new ArrayList<>();
    List<Integer> indexesOfColumnTypeNumber = new ArrayList<>();
    List<List<String>> rows = new ArrayList<>();
    List<String> summary = new ArrayList<>();

    // dynamic

    // static
    columnHeaders.add("Date");
    columnHeaders.add("Time");
    columnHeaders.add("Site");
    columnHeaders.add("Fleet");
    columnHeaders.add("Vehicle");
    columnHeaders.add("Driver");
    columnHeaders.add("Auth Code");
    columnHeaders.add("Tax Payer Id");
    columnHeaders.add("Trn Number");
    columnHeaders.add("Sub Account");
    columnHeaders.add("Identification");
    columnHeaders.add("Fuel");
    columnHeaders.add("Volume\n(LTR)");
    columnHeaders.add("Amount\n(TK)");
    columnHeaders.add("Unit Price\n(TK)");

    for (var s : list) {
      List<String> row = new ArrayList<>();
      row.add(s.getDate());
      row.add(s.getTime());
      row.add(s.getSite());
      row.add(s.getFleet());
      row.add(s.getVehicle());
      row.add(s.getDriver());
      row.add(s.getAuthCode());
      row.add(s.getTaxPayerId());
      row.add(s.getTrnNumber());
      row.add(s.getSubAccount());
      row.add(s.getIdentification());
      row.add(s.getFuel());
      row.add(String.valueOf(s.getVolume()));
      row.add(String.valueOf(s.getAmount()));
      row.add(String.valueOf(s.getUnitPrice()));

      rows.add(row);

      // sum
      totalVolume+=s.getVolume();
      totalAmount+=s.getAmount();
    }

    // summary
    summary.add("Total");
    summary.add(df.format(totalVolume).toString());
    summary.add(df.format(totalAmount).toString());
    summary.add(null);

    // number field index list
    indexesOfColumnTypeNumber.add(columnHeaders.size() - 4); // "Total"
    indexesOfColumnTypeNumber.add(columnHeaders.size() - 3); // totalVolume
    indexesOfColumnTypeNumber.add(columnHeaders.size() - 2); // totalAmount
    indexesOfColumnTypeNumber.add(columnHeaders.size() - 1); // totalAmount

    return new DynamicReportProperties(columnHeaders, indexesOfColumnTypeNumber, rows, summary);
  }
}
