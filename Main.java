import java.io.*;
import lexer.Lexer;

public class Main{
	public static void main(String[] args) throws FileNotFoundException, IOException {
		String scanned = "";
		Lexer lexer = new Lexer(args[0]);
		while(!scanned.equals("-1")){
			scanned = lexer.scan().toString();
			System.out.println(scanned);
		}
	}
}
