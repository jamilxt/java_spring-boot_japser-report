# Jasper Report using Spring Boot (Jave 11, Maven)
## Export (PDF, EXCEL, CSV, DOCX)
### Reference Documentation
Run the application.
Then, Export:
* PDF: [http://localhost:8080/reports/transactions/download?exportType=PDF](http://localhost:8080/reports/transactions/download?exportType=PDF) <br><br>
![image](https://user-images.githubusercontent.com/18072164/106577485-9bcf7280-6568-11eb-925e-938f188ca2b1.png) <br><br>
* EXCEL: [http://localhost:8080/reports/transactions/download?exportType=EXCEL](http://localhost:8080/reports/transactions/download?exportType=EXCEL) <br><br>
![image](https://user-images.githubusercontent.com/18072164/106577645-d0432e80-6568-11eb-9063-5fd02df2bc0c.png) <br><br>
* CSV: [http://localhost:8080/reports/transactions/download?exportType=CSV](http://localhost:8080/reports/transactions/download?exportType=CSV) <br><br>
![image](https://user-images.githubusercontent.com/18072164/106577739-ee109380-6568-11eb-87ef-8ae5d7f7d50e.png) <br><br>
* DOCX: [http://localhost:8080/reports/transactions/download?exportType=DOCX](http://localhost:8080/reports/transactions/download?exportType=DOCX) <br><br>
![image](https://user-images.githubusercontent.com/18072164/106577866-14ceca00-6569-11eb-85d8-08bd5d80a115.png) <br><br>

### Download [Jaspersoft Studio](https://community.jaspersoft.com/project/jaspersoft-studio) to modify the template (file with .jrxml extension) as you like.
#### Templates are: 
* PDF: transaction_report_pdf.jrxml
* EXCEL: transaction_report_excel.jrxml
* CSV: transaction_report_csv.jrxml
* DOCX: transaction_report_docx.jrxml

Open these templates using Jasper Studio & modify as your own. It takes sometime to be familiar with the syntax to design the template. It's similar to HTML but need some practice to make a better design. 
Enjoy!

### Credits:
* [iabur](https://github.com/iabur)
* https://github.com/javahowtos/jasper-export-demo
* https://github.com/javahowtos/jasper-excel-export-demo
* StackOverflow

I didn't find any repo which provides (PDF, EXCEL, CSV, DOCX) all of them at once. Thanks to these^ repo I got the basic idea how to export the report. Thank you very much [iabur](https://github.com/iabur) vai to provide me different template design from your old projects as well as assist me in every way.  
