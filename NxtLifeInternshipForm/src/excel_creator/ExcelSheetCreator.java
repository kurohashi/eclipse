package excel_creator;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import factory_classes.Candidate;
import logics.Validator;

public class ExcelSheetCreator 
{
	private static File file = null;
	private static FileInputStream fis = null;
	private static FileOutputStream fout = null;
	private static XSSFWorkbook workbook = null;
	private static XSSFSheet spreadsheet = null;
	private static XSSFRow row = null;
	private static XSSFCell cell = null;
			
	public static boolean addToSheet(Candidate tempCandidate) {
		
		if (!Validator.validateCandidate(tempCandidate)) {
			return true;
		}
		
		if (!Validator.validateEmail(tempCandidate.getEmail())) {
			return false;
		}
		
		if (!Validator.validateMobile(tempCandidate.getPhoneContact())) {
			return false;
		}
		
		int newRowIndex = 0;
		
		try {
			file = new File("D:\\NxtLife Interns' List.xlsx");
			fis = new FileInputStream(file);
			workbook = new XSSFWorkbook(fis);
			spreadsheet = workbook.getSheet("Intern Database");			
		} catch (FileNotFoundException e1) {
			workbook = new XSSFWorkbook();
			spreadsheet = workbook.createSheet("Intern Database");
			initiate();
			try {
				fis = new FileInputStream(file);
			} catch (FileNotFoundException e) {
				System.out.println(e);
			}
		} catch (IOException e1) {
			return false;
		} 
		
		Iterator <Row> rowIterator = spreadsheet.iterator();	    
		while (rowIterator.hasNext()) 
	    {
	    	row = (XSSFRow) rowIterator.next();
	    	newRowIndex++;
	    }
		
		if (newRowIndex == 0) {
			initiate();
			newRowIndex++;
		}
		
		row = spreadsheet.createRow(newRowIndex);
      
		cell=row.createCell(0);
		cell.setCellValue(newRowIndex+1);
		cell=row.createCell(1);
		cell.setCellValue(tempCandidate.getName());
		cell=row.createCell(2);
		cell.setCellValue(tempCandidate.getTechnologiesKnown());
		cell=row.createCell(3);
		cell.setCellValue(tempCandidate.getNumberOfMonthsToCommit());
		cell=row.createCell(4);
		cell.setCellValue(tempCandidate.getLatestJoiningDate().toString());
		cell=row.createCell(5);
		cell.setCellValue(tempCandidate.getPhoneContact());
		cell=row.createCell(6);
		cell.setCellValue(tempCandidate.getEmail());
		
		try {
			fis.close();
			fout = new FileOutputStream(file);
			workbook.write(fout);
			workbook.close();
			fout.close();
		} catch (IOException e) {
			return false;
		}	
		
		return true;
	}
	
	private static void initiate() {
		row = spreadsheet.createRow(0);
		cell = row.createCell(0);
		cell.setCellValue("CaID");
		cell = row.createCell(1);
		cell.setCellValue("Full Name");
		cell = row.createCell(2);
		cell.setCellValue("Known Technologies");
		cell = row.createCell(3);
		cell.setCellValue("Min. no. candidate can commit");
		cell = row.createCell(4);
		cell.setCellValue("Latest joining date");
		cell = row.createCell(5);
		cell.setCellValue("Mobile");
		cell = row.createCell(6);
		cell.setCellValue("Email");
		try {
			fout = new FileOutputStream(file);
			workbook.write(fout);
			fout.close();
		} catch (IOException e) {
			System.out.println(e);
		}
	}
}