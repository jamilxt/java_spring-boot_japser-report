# Jasper Report using Spring Boot
## Export (PDF, EXCEL, CSV, DOCX)
### Reference Documentation
Run the application.
Then, Export:
* PDF: [http://localhost:8080/reports/transactions/download?exportType=PDF](http://localhost:8080/reports/transactions/download?exportType=PDF)
* EXCEL: [http://localhost:8080/reports/transactions/download?exportType=Excel](http://localhost:8080/reports/transactions/download?exportType=EXCEL)
* CSV: [http://localhost:8080/reports/transactions/download?exportType=CSV](http://localhost:8080/reports/transactions/download?exportType=CSV)
* DOCX: [http://localhost:8080/reports/transactions/download?exportType=DOCX](http://localhost:8080/reports/transactions/download?exportType=DOCX)

### Download [Jaspersoft Studio](https://community.jaspersoft.com/project/jaspersoft-studio) to modify the template (file with .jrxml extension) as you like.
#### Templates are: 
* PDF: transaction_report_pdf.jrxml
* EXCEL: transaction_report_excel.jrxml
* CSV: transaction_report_csv.jrxml
* DOCX: transaction_report_docx.jrxml

Open these templates using Jasper Studio & modify as your own. It takes sometime to be familiar with the syntax to design the template. It's similar to HTML but need some practice to make a better design. 
Enjoy!