import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;


public class testingfileswriting {
	
	
	public static void main(String args[])
	   {
	   try{
	   // Create file 
	   FileWriter fstream = new FileWriter("E:\\out.csv");
	   BufferedWriter out = new BufferedWriter(fstream);
	   out.write("SBI DFHI LTD,35150,DEALER4,31285,Regular,Standard,2.01301E+14,22-Jan-13,16:54:24,201301000000000,BUY,T+1,23-Jan-13,IN0020120047,IN0020120047,08.20 GOVT. STOCK 2025,24-Sep-25,50000000,102.145,7.9264,51072500,24-Sep-12,119,1355277.78,52427777.78,-");
	  // out.write("'");
	   //Close the output stream
	   out.close(); 
	   
	   File file =new File("E:\\out.csv");
	   String data = " This content will append to the end of the file";
	   FileWriter fileWritter = new FileWriter(file.getName(),true);
	   BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
       bufferWritter.write(data);
       bufferWritter.flush();
       bufferWritter.close();


	   }catch (Exception e){//Catch exception if any
	   System.err.println("Error: " + e.getMessage());
	   }
	   }
	 

}
