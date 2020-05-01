import java.awt.Point;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

	public static void GenGrid (String grid) throws IOException {
		File file = new File("KB1.pl");
		file.createNewFile();
		
		File file2 = new File("KB2.pl");
		file2.createNewFile();
		
		File file3 = new File("Endgame.pl");
		file3.createNewFile();
		
		String[] g1= grid.split(";");
		String size = "size(" + g1[0] + ")." + "\n";
		
		//Creating a list of pairs for the stones positions.
		String stones = "[";
		String [] tempstones =  g1[3].split(",");
		for(int i =0 ; i<tempstones.length; i+=2){
			int j = i+1;
			if(i != 0){
				stones += ",";
			}
			stones += "(" + tempstones[i] + "," + tempstones[j] + ")";
		}
		
		stones += "]";

		//Creating ironman, thanos and stones facts for the knowledge base.
		String ironman = "ironman(" + g1[1] + ",s0," + "[]" + ")." + "\n";
		String thanos = "thanos("+g1[2]+")."+"\n";
		String st = "stones(" + stones + ").\n";
		
		//Writing the knowledge base facts to KB2.pl (or KB1.pl) file.
		FileOutputStream fos = new FileOutputStream("KB2.pl");
		fos.write(size.getBytes());
		fos.write(thanos.getBytes());
		fos.write(st.getBytes());
		fos.write(ironman.getBytes());
		fos.flush();
		fos.close();
	}
	
	public static void main(String []args) throws IOException{
		//GenGrid("5,5;1,2;3,4;1,1,2,1,2,2,3,3");
		GenGrid("5,5;1,2;4,2;1,3,2,1,3,3,4,1");
	}
}
