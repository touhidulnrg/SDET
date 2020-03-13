READ in EXCEL
package excelExportAndFileIO;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadGuru99ExcelFile {
    public void readExcel(String filePath,String fileName,String sheetName) throws IOException{

    //Create an object of File class to open xlsx file
    File file =    new File(filePath+"\\"+fileName);

    //Create an object of FileInputStream class to read excel file
    FileInputStream inputStream = new FileInputStream(file);
    Workbook guru99Workbook = null;

    //Find the file extension by splitting file name in substring  and getting only extension name
    String fileExtensionName = fileName.substring(fileName.indexOf("."));

    //Check condition if the file is xlsx file
    if(fileExtensionName.equals(".xlsx")){  guru99Workbook = new XSSFWorkbook(inputStream);  }

    //Check condition if the file is xls file
    else if(fileExtensionName.equals(".xls")){ guru99Workbook = new HSSFWorkbook(inputStream);}

    Sheet guru99Sheet = guru99Workbook.getSheet(sheetName);
    int rowCount = guru99Sheet.getLastRowNum()-guru99Sheet.getFirstRowNum();    
    for (int i = 0; i < rowCount+1; i++) {
        Row row = guru99Sheet.getRow(i);

        //Create a loop to print cell values in a row

        for (int j = 0; j < row.getLastCellNum(); j++) {
            //Print Excel data in console
            System.out.print(row.getCell(j).getStringCellValue()+"|| ");
        }
        System.out.println();
    } 

    }  
    
    public static void main(String...strings) throws IOException{

        ReadGuru99ExcelFile objExcelFile = new ReadGuru99ExcelFile();
    
        String filePath = System.getProperty("user.dir")+"\\src\\excelExportAndFileIO";

    //Call read file method of the class to read data
    objExcelFile.readExcel(filePath,"ExportExcel.xlsx","ExcelGuru99Demo");

    }

}


WRITE IN EXCEL
package excelExportAndFileIO;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class WriteGuru99ExcelFile {

    public void writeExcel(String filePath,String fileName,String sheetName,String[] dataToWrite) throws IOException{
        File file = new File(filePath+"\\"+fileName);
        FileInputStream inputStream = new FileInputStream(file);
        Workbook guru99Workbook = null;
        String fileExtensionName = fileName.substring(fileName.indexOf("."));        
        if(fileExtensionName.equals(".xlsx")){ guru99Workbook = new XSSFWorkbook(inputStream); }
        else if(fileExtensionName.equals(".xls")){guru99Workbook =new HSSFWorkbook(inputStream);}    

    Sheet sheet = guru99Workbook.getSheet(sheetName);
    int rowCount = sheet.getLastRowNum()-sheet.getFirstRowNum();
    Row row = sheet.getRow(0); 
    Row newRow = sheet.createRow(rowCount+1);
    for(int j = 0; j < row.getLastCellNum(); j++){
        Cell cell = newRow.createCell(j);
        cell.setCellValue(dataToWrite[j]);
    }
    inputStream.close();

    //Create an object of FileOutputStream class to create write data in excel file

    FileOutputStream outputStream = new FileOutputStream(file);
    guru99Workbook.write(outputStream);
    outputStream.close();
	
    }

    public static void main(String...strings) throws IOException{

 //Create an array with the data in the same order in which you expect to be filled in excel file

        String[] valueToWrite = {"Mr. E","Noida"};
        WriteGuru99ExcelFile objExcelFile = new WriteGuru99ExcelFile();

        //Write the file using file name, sheet name and the data to be filled

        objExcelFile.writeExcel(System.getProperty("user.dir")+"\\src\\excelExportAndFileIO","ExportExcel.xlsx","ExcelGuru99Demo",valueToWrite);

    }

}

