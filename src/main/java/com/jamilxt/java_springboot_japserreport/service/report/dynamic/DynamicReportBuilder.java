package com.jamilxt.java_springboot_japserreport.service.report.dynamic;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRLineBox;
import net.sf.jasperreports.engine.design.*;
import net.sf.jasperreports.engine.type.HorizontalTextAlignEnum;
import net.sf.jasperreports.engine.type.LineStyleEnum;
import net.sf.jasperreports.engine.type.ModeEnum;
import net.sf.jasperreports.engine.type.VerticalTextAlignEnum;

import java.awt.*;
import java.util.List;

// Uses the Jasper Report API to dynamically add columns to the Report
public class DynamicReportBuilder {
  // The prefix used in defining the field name that is later used by the JasperFillManager
  public static final String COL_EXPR_PREFIX = "col";
  // The prefix used in defining the column header name that is later used by the JasperFillManager
  public static final String COL_HEADER_EXPR_PREFIX = "header";
  // The prefix used in defining the summary field name that is later used by the JasperFillManager
  public static final String SUMMARY_PREFIX = "summary";
  // The page width for a page in portrait mode with 10 pixel margins
  private static final int TOTAL_PAGE_WIDTH = 814;
  // The whitespace between columns in pixels
  private static final int SPACE_BETWEEN_COLS = 0;
  // The height in pixels of an element in a row and column
  private static final int COLUMN_HEIGHT = 45;
  // The total height of the column header or detail band
  private static final int BAND_HEIGHT = 45;
  // The left and right margin in pixels
  private static final int MARGIN = -4;
  // The JasperDesign object is the internal representation of a report
  private final JasperDesign jasperDesign;
  // The number of columns that are to be displayed
  private final int numColumns;

  public DynamicReportBuilder(JasperDesign jasperDesign, int numColumns) {
    this.jasperDesign = jasperDesign;
    this.numColumns = numColumns;
  }

  public void addDynamicColumns(List<Integer> currencyFieldIndexes) throws JRException {
    var detailBand = new JRDesignBand();
    var headerBand = new JRDesignBand();
    var summaryBand = new JRDesignBand(); // Summary band for total

    var normalStyle = getNormalStyle();
    var columnHeaderStyle = getColumnHeaderStyle();
    jasperDesign.addStyle(normalStyle);
    jasperDesign.addStyle(columnHeaderStyle);
    int xPos = MARGIN;
    int columnWidth = (TOTAL_PAGE_WIDTH - (SPACE_BETWEEN_COLS * (numColumns - 1))) / numColumns;

    for (int i = 0, j = 0; i < numColumns; i++) {
      // Create a Column Field
      var field = new JRDesignField();
      field.setName(COL_EXPR_PREFIX + i);
      field.setValueClass(String.class);
      jasperDesign.addField(field);

      // Create a Header Field
      var headerField = new JRDesignField();
      headerField.setName(COL_HEADER_EXPR_PREFIX + i);
      headerField.setValueClass(String.class);
      jasperDesign.addField(headerField);

      // Add a Header Field to the headerBand
      headerBand.setHeight(BAND_HEIGHT);

      var colHeaderField = new JRDesignTextField();
      colHeaderField.setX(xPos);
      colHeaderField.setY(10);
      colHeaderField.setWidth(columnWidth);
      colHeaderField.setHeight(COLUMN_HEIGHT - 10);

      colHeaderField.setMode(ModeEnum.OPAQUE);
      colHeaderField.setBackcolor(new Color(81, 76, 110));
      colHeaderField.setForecolor(Color.WHITE);
      colHeaderField.setHorizontalTextAlign(HorizontalTextAlignEnum.CENTER);
      colHeaderField.setVerticalTextAlign(VerticalTextAlignEnum.MIDDLE);
      colHeaderField.setStyle(columnHeaderStyle);

      var headerExpression = new JRDesignExpression();
      headerExpression.setValueClass(String.class);
      headerExpression.setText("$F{" + COL_HEADER_EXPR_PREFIX + i + "}");
      colHeaderField.setExpression(headerExpression);
      headerBand.addElement(colHeaderField);

      // Add text field to the detailBand
      detailBand.setHeight(BAND_HEIGHT);

      var textField = new JRDesignTextField();
      textField.setX(xPos);
      textField.setY(0);
      textField.setWidth(columnWidth);
      textField.setHeight(COLUMN_HEIGHT);
      textField.setStyle(normalStyle);

      var box = textField.getLineBox();
      this.designTextBox(box);

      if (currencyFieldIndexes.contains(i)) {
        if (j > 0) {
          textField.setHorizontalTextAlign(HorizontalTextAlignEnum.RIGHT);
        }
        // Summary Band
        // create summary field
        var summaryField = new JRDesignField();
        summaryField.setName(SUMMARY_PREFIX + j);
        summaryField.setValueClass(String.class);
        jasperDesign.addField(summaryField);
        // Add a Summary Field to the summary band
        summaryBand.setHeight(BAND_HEIGHT);

        var summaryTextField = new JRDesignTextField();
        summaryTextField.setX(xPos);
        summaryTextField.setY(0);
        summaryTextField.setWidth(columnWidth);
        summaryTextField.setHeight(30);
        summaryTextField.setStyle(normalStyle);

        var summaryBox = summaryTextField.getLineBox();
        this.designTextBox(summaryBox);
        summaryTextField.setHorizontalTextAlign(HorizontalTextAlignEnum.RIGHT);
        summaryTextField.setVerticalTextAlign(VerticalTextAlignEnum.MIDDLE);

        var summaryExpression = new JRDesignExpression();
        summaryExpression.setValueClass(String.class);
        summaryExpression.setText("$F{" + SUMMARY_PREFIX + j + "}");
        summaryTextField.setExpression(summaryExpression);
        summaryTextField.setBlankWhenNull(true);
        summaryBand.addElement(summaryTextField);

        j++;
      } else {
        textField.setHorizontalTextAlign(HorizontalTextAlignEnum.CENTER);
      }
      textField.setVerticalTextAlign(VerticalTextAlignEnum.MIDDLE);

      var expression = new JRDesignExpression();
      expression.setValueClass(String.class);
      expression.setText("$F{" + COL_EXPR_PREFIX + i + "}");
      textField.setExpression(expression);
      textField.setBlankWhenNull(true);
      detailBand.addElement(textField);

      xPos = xPos + columnWidth + SPACE_BETWEEN_COLS;
    }
    jasperDesign.setColumnHeader(headerBand);
    ((JRDesignSection) jasperDesign.getDetailSection()).addBand(detailBand);
    jasperDesign.setSummary(summaryBand);
  }

  private JRDesignStyle getNormalStyle() {
    var normalStyle = new JRDesignStyle();
    normalStyle.setName("Sans_Normal");
    normalStyle.setDefault(true);
    normalStyle.setFontName("SansSerif");
    normalStyle.setFontSize(7.5F);
    normalStyle.setPdfFontName("Helvetica");
    normalStyle.setPdfEncoding("Cp1252");
    normalStyle.setPdfEmbedded(false);
    normalStyle.setVerticalTextAlign(VerticalTextAlignEnum.MIDDLE);
    normalStyle.setHorizontalTextAlign(HorizontalTextAlignEnum.CENTER);
    return normalStyle;
  }

  private JRDesignStyle getColumnHeaderStyle() {
    var columnHeaderStyle = new JRDesignStyle();
    columnHeaderStyle.setBackcolor(Color.MAGENTA);
    columnHeaderStyle.setName("Sans_Header");
    columnHeaderStyle.setDefault(false);
    columnHeaderStyle.setFontName("SansSerif");
    columnHeaderStyle.setFontSize(8.5F);
    columnHeaderStyle.setBold(true);
    columnHeaderStyle.setPdfFontName("Helvetica");
    columnHeaderStyle.setPdfEncoding("Cp1252");
    return columnHeaderStyle;
  }

  private void designTextBox(JRLineBox box) {
    box.getBottomPen().setLineStyle(LineStyleEnum.SOLID);
    box.getBottomPen().setLineWidth(1F);
    box.getBottomPen().setLineColor(new Color(235, 235, 235));

    box.getTopPen().setLineStyle(LineStyleEnum.SOLID);
    box.getTopPen().setLineWidth(1F);
    box.getTopPen().setLineColor(new Color(235, 235, 235));

    box.getLeftPen().setLineStyle(LineStyleEnum.SOLID);
    box.getLeftPen().setLineWidth(1F);
    box.getLeftPen().setLineColor(new Color(235, 235, 235));

    box.getRightPen().setLineStyle(LineStyleEnum.SOLID);
    box.getRightPen().setLineWidth(1F);
    box.getRightPen().setLineColor(new Color(235, 235, 235));
    box.setRightPadding(Integer.valueOf(3));
  }
}
