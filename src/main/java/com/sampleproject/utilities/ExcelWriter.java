package com.sampleproject.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelWriter {

	public void writeExcel(String fileName,String sheetName,int rowNumber,int colNumber,String data) throws Exception {
//		"D:\\Workspace\\SampleProject\\src\\test\\resources\\testdata\\master (Recovered).xlsx"
		File src = new File(fileName);

		// Load the file

		FileInputStream fis = new FileInputStream(src);

		// load the workbook

		XSSFWorkbook wb = new XSSFWorkbook(fis);

		// get the sheet which you want to modify or create
//		"TestCases"
		XSSFSheet sh1 = wb.getSheet(sheetName);

		// getRow specify which row we want to read and getCell which column

		// System.out.println(sh1.getRow(0).getCell(0).getStringCellValue());
		//
		// System.out.println(sh1.getRow(0).getCell(1).getStringCellValue());
		//
		// System.out.println(sh1.getRow(1).getCell(0).getStringCellValue());
		//
		// System.out.println(sh1.getRow(1).getCell(1).getStringCellValue());
		//
		// System.out.println(sh1.getRow(2).getCell(0).getStringCellValue());
		//
		// System.out.println(sh1.getRow(2).getCell(1).getStringCellValue());

		// here createCell will create column

		// and setCellvalue will set the value
//		"2.41.0"
		sh1.getRow(rowNumber).createCell(colNumber).setCellValue(data);

//		sh1.getRow(1).createCell(7).setCellValue("2.5");

//		sh1.getRow(2).createCell(7).setCellValue("2.39");

		// here we need to specify where you want to save file

		FileOutputStream fout = new FileOutputStream(
				new File(fileName));

		// finally write content

		wb.write(fout);

		// close the file

		fout.close();

	}

	public static void main(String[] args) throws Exception {

		ExcelWriter excel = new ExcelWriter();
		excel.writeExcel("D:\\Workspace\\SampleProject\\src\\test\\resources\\testdata\\New folder\\master.xlsx", "TestData", 717, 3, "2.41.0");		
		excel.writeExcel("D:\\Workspace\\SampleProject\\src\\test\\resources\\testdata\\New folder\\master.xlsx", "TestData", 718, 3, "2.5");
		excel.writeExcel("D:\\Workspace\\SampleProject\\src\\test\\resources\\testdata\\New folder\\master.xlsx", "TestData", 719, 3, "2.39");
	}

}
