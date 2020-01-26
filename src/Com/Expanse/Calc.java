package Com.Expanse;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Calc {
	
	private int totalDays;
	private int items;

	public Calc(int totalDays,int items) {
	this.totalDays = totalDays;
	this.items = items;
	}
	
	public float totalExpense() throws IOException {
	 	 Calendar calendar = Calendar.getInstance();
		    // Note that month is 0-based in calendar, bizarrely.
			int weekdays = 0;
			int saturdays = 0;
			int sundays = 0;
			int sum = 0;
			
			String workingDir = System.getProperty("user.dir");
			
		 FileInputStream testData = new FileInputStream(new File(workingDir+"//"+Constants.sheetName));
	     Workbook workbook = new XSSFWorkbook(testData);
	     Sheet firstSheet = workbook.getSheetAt(0);	
	 	
	     // Save values in map where key as weekday value as induvidual value of cell
	     Map<String,Float> expenses=new HashMap<>();
	 	 for(int i=1;i<totalDays;i++) {
	 		expenses.put(firstSheet.getRow(0).getCell(i).toString(), Float.valueOf(firstSheet.getRow(items).getCell(i).toString()));
	 	 }
		
			// calculate for calendar month Feb/2020
	 	 	calendar.set(Constants.year , Constants.month - 1, 1);
		    int daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

		    for (int day = 1; day <= daysInMonth; day++) {
		        calendar.set(Constants.year, Constants.month - 1, day);
		        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
		        if (dayOfWeek == Calendar.SUNDAY) sundays++;
		        else if (dayOfWeek == Calendar.SATURDAY)saturdays++;
		        else if (dayOfWeek !=Calendar.SUNDAY && dayOfWeek !=Calendar.SATURDAY )weekdays++;      
		     }

		    // Sum of single item
		    System.out.println(expenses.get("Sunday")*sundays +  expenses.get("Saturday")*saturdays + expenses.get("Monday")*weekdays);
		    return expenses.get("Sunday")*sundays +  expenses.get("Saturday")*saturdays + expenses.get("Monday")*weekdays; 	
	}
}
