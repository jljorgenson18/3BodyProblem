import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;








/**
 * Testing Method for Models programs
 *
 * @author Jesse Jorgenson
 * @version 1
 */
public class testingMethod {

 
 	public static void main (String[] args) throws IOException {
 		double p1 = 0.347111;
 		double p2 = 0.532728;
 		
 		FileReader file = new FileReader(""+ "Untitled" + ".txt");
		
 		BufferedReader br = new BufferedReader(file);
		System.out.println(br.readLine());
		
		System.out.println(br.readLine());
		
		System.out.println(br.readLine());
		
		System.out.println(br.readLine());
		
		System.out.println(br.readLine());
		
		System.out.println(br.readLine());
		
		System.out.println(br.readLine());
		
		System.out.println(br.readLine());
		
		System.out.println(br.readLine());
		
		System.out.println(br.readLine());
		
		System.out.println(br.readLine());
		
		System.out.println(br.readLine());
		
		br = null;
		
		br = new BufferedReader(file);
		

		System.out.println(br.readLine());
		
		System.out.println(br.readLine());
		
		System.out.println(br.readLine());
		
		System.out.println(br.readLine());
		
		br.close();
	
	}
}
