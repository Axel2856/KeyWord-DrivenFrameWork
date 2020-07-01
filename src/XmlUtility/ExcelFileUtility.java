package XmlUtility;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelFileUtility {
Workbook wb;
	//for load excel file 
	public ExcelFileUtility(String excelpath)throws Throwable
	{
		FileInputStream fi=new FileInputStream(excelpath);
		wb=WorkbookFactory.create(fi);
	}
	//For calculate no of rows
	public int rowCount(String sheetname)
	{
		return wb.getSheet(sheetname).getLastRowNum();
	}
	//for calculate no of columns
	public int colCount(String sheetname)
	{
		return wb.getSheet(sheetname).getRow(0).getLastCellNum();
	}
	//for getting celldata from excel
	public String getCellData(String sheetname,int row,int column)
	{
		String celldata="";
		if(wb.getSheet(sheetname).getRow(row).getCell(column).getCellType()==Cell.CELL_TYPE_NUMERIC)
		{
			int data=(int)wb.getSheet(sheetname).getRow(row).getCell(column).getNumericCellValue();
			celldata=String.valueOf(data);
		}
		else
		{
			celldata=wb.getSheet(sheetname).getRow(row).getCell(column).getStringCellValue();
		}
		return celldata;
	}
	//for Setting celldata in excel
	public void setCellData(String sheetname,int row, int column,String status,String outputexcel)throws Throwable
	{
		Sheet ws=wb.getSheet(sheetname);
		Row rownum=ws.getRow(row);
		Cell cell=rownum.createCell(column);
		cell.setCellValue(status);
		
		if(status.equalsIgnoreCase("Pass"))
		{
			CellStyle style =wb.createCellStyle();
			Font font=wb.createFont();
			font.setBold(true);
			font.setColor(IndexedColors.BRIGHT_GREEN.getIndex());
			font.setBoldweight(Font.BOLDWEIGHT_BOLD);
			style.setFont(font);
			rownum.getCell(column).setCellStyle(style);
		}
		else if(status.equalsIgnoreCase("Fail"))
		{
			CellStyle style=wb.createCellStyle();
			Font font=wb.createFont();
			font.setBold(true);
			font.setColor(IndexedColors.RED.getIndex());
			font.setBoldweight(Font.BOLDWEIGHT_BOLD);
			style.setFont(font);
			rownum.getCell(column).setCellStyle(style);			
		}
		else if(status.equalsIgnoreCase("Blocked"))
		{
			CellStyle style=wb.createCellStyle();
			Font font=wb.createFont();
			font.setBold(true);
			font.setColor(IndexedColors.ROYAL_BLUE.getIndex());
			font.setBoldweight(Font.BOLDWEIGHT_BOLD);
			style.setFont(font);
			rownum.getCell(column).setCellStyle(style);
		}
		FileOutputStream fo=new FileOutputStream(outputexcel);
		wb.write(fo);
	}
}
