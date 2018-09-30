package com.inetbanking.utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XLUtils {
//This class will use to read and write data from excel file.
	
	public static FileInputStream fi;
	public static FileOutputStream fo;
	public static XSSFWorkbook wb;
	public static XSSFSheet ws;
	public static XSSFRow row;
	public static XSSFCell cell;
	
	public static void setExcelFile (String xlfile, String xlsheet) throws Exception {
	//This method will use to load the excel and sheet	
		try {
			FileInputStream ExcelFile = new FileInputStream(xlfile);
			wb = new XSSFWorkbook(ExcelFile);
			ws = wb.getSheet(xlsheet);
		} catch (Exception e){
			throw (e);
		}
	}
	
	public static int getRowCount(String xlfile, String xlsheet) throws IOException 
	{
	//This method will use to count number of rows in the sheet 	
		fi = new FileInputStream (xlfile);
		wb = new XSSFWorkbook(fi);
		ws = wb.getSheet(xlsheet);
		int rowcount=ws.getLastRowNum();
		wb.close();
		fi.close();
		return rowcount;
	}

	public static int getCellCount(String xlfile, String xlsheet, int rownum) throws IOException 
	{
	//This method will use to count number of cells in the sheet 	
		fi = new FileInputStream (xlfile);
		wb = new XSSFWorkbook(fi);
		ws = wb.getSheet(xlsheet);
		row=ws.getRow(rownum);
		int Cellcount=row.getLastCellNum();
		wb.close();
		fi.close();
		return Cellcount;
	}
	public static String getCellData(String xlfile, String xlsheet, int rownum, int colnum) throws IOException 
	{
	//This method will use to get cell data from the sheet 	
		fi = new FileInputStream (xlfile);
		wb = new XSSFWorkbook(fi);
		ws = wb.getSheet(xlsheet);
		row=ws.getRow(rownum);
		cell=row.getCell(colnum);
		String data;
		
		try {
			DataFormatter formatter = new DataFormatter ();
			String cellData = formatter.formatCellValue(cell);
			return cellData;
		}
		catch (Exception e)
		{
			data = "";
		}
		wb.close();
		fi.close();
		return data;
	}
	public static void setCellData(String xlfile, String xlsheet, int rownum, int colnum, String data) throws IOException 
	{
	//Write in the excel sheet
	//This method will use to set cell data to the sheet  	
		fi = new FileInputStream (xlfile);
		wb = new XSSFWorkbook(fi);
		ws = wb.getSheet(xlsheet);
		row=ws.getRow(rownum);
		cell=row.createCell(colnum);
		cell.setCellValue(data);
		fo = new FileOutputStream(xlfile);
		wb.write(fo);
		wb.close();
		fi.close();
		fo.close();
	}

}
