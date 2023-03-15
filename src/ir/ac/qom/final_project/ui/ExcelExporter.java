package ir.ac.qom.final_project.ui;

import ir.ac.qom.final_project.data.entity.Product;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.logging.Logger;

public class ExcelExporter {
    Logger logger = Logger.getLogger(ExcelExporter.class.getName());

    static String[] columns = {I18NUtility.getMessage("product.dialog.title"), I18NUtility.getMessage("product.date"), I18NUtility.getMessage("product.price"), I18NUtility.getMessage("product.quantity"),};

    public static void export(List<Product> products, File file) throws Exception {
        // Create a Workbook
        Workbook workbook = new XSSFWorkbook(); // new HSSFWorkbook() for generating `.xls` file

        /* CreationHelper helps us create instances of various things like DataFormat,
           Hyperlink, RichTextString etc, in a format (HSSF, XSSF) independent way */
        CreationHelper createHelper = workbook.getCreationHelper();

        // Create a Sheet
        Sheet sheet = workbook.createSheet("Product");

        // Create a Font for styling header cells
        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short) 14);
        headerFont.setColor(IndexedColors.RED.getIndex());

        // Create a CellStyle with the font
        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFont(headerFont);

        // Create a Row
        Row headerRow = sheet.createRow(0);

        // Create cells
        for (int i = 0; i < columns.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columns[i]);
            cell.setCellStyle(headerCellStyle);
        }

        // Create Cell Style for formatting Date
        CellStyle dateCellStyle = workbook.createCellStyle();
        dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd-MM-yyyy"));

        // Create Other rows and cells with products ir.ac.qom.final_project.data
        int rowNum = 1;
        for (Product product : products) {
            Row row = sheet.createRow(rowNum++);

            row.createCell(0).setCellValue(product.getNameOfProduct());


            Cell dateOfManuCell = row.createCell(1);
            dateOfManuCell.setCellValue(product.getDateOfManufacture());
            dateOfManuCell.setCellStyle(dateCellStyle);
            row.createCell(2).setCellValue(product.getPrice());

            row.createCell(3).setCellValue(product.getQuantity());
        }

        // Resize all columns to fit the content size
        for (int i = 0; i < columns.length; i++) {
            sheet.autoSizeColumn(i);
        }

        // Write the output to a file
        FileOutputStream fileOut = new FileOutputStream(file + ".xls");
        workbook.write(fileOut);
        fileOut.close();

        // Closing the workbook
        workbook.close();
    }
}
