package Com.Expanse;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class CalculateExpanse {

	public static void main(String[] args) throws IOException {
	
		// Dimesion of the datasheet
		int items = getTotalRow();
		int totalDays = getTotalColumn();
		double sum = 0;
		
		ArrayList<Float> overallExpanse = new ArrayList<Float>();

	     // Read data
	     Calc[] calculateMyExpense = new Calc[items];
	     for(int util =1;util<items;util++) {
	    	 calculateMyExpense[util] = new Calc(totalDays,util);
	     }
	     
	     // print sum of induvidual item
	     for(int util =1;util<items;util++) {
	    	overallExpanse.add(calculateMyExpense[util].totalExpense());
	     }  
	     
	     // print sum of all the items as monthly expense
	     for(int i = 0; i < overallExpanse.size(); i++) sum += overallExpanse.get(i);
	     System.out.println("overall expense: "+ sum);	
	}
		    

	private static int getTotalRow() throws IOException {
		String workingDir = System.getProperty("user.dir");
		 FileInputStream testData = new FileInputStream(new File(workingDir+"//"+Constants.sheetName));
	     Workbook workbook = new XSSFWorkbook(testData);
	     Sheet firstSheet = workbook.getSheetAt(0);
	     return firstSheet.getLastRowNum();
	}

	private static int getTotalColumn() throws IOException {
		String workingDir = System.getProperty("user.dir");
		FileInputStream testData = new FileInputStream(new File(workingDir+"//"+Constants.sheetName));
	     Workbook workbook = new XSSFWorkbook(testData);
	     Sheet firstSheet = workbook.getSheetAt(0);
	     return firstSheet.getRow(0).getLastCellNum();
	}
	
	

}


