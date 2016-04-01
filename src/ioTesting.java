import java.io.File;


public class ioTesting {
	private String getFile() {
		ClassLoader classLoader = getClass().getClassLoader();
		//File file = new File(classLoader.getResource(fileName) );
         return null;
	}
	
	public static void main(String args[]) {
		 

		File file = new File("src/beans");

        File[] files = file.listFiles();

        for(File f: files){
 
            System.out.println(f.getName().substring(0,f.getName().lastIndexOf(".")));

        } 
	}

}
