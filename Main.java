import java.io.*;
import lexer.Lexer;
import lexer.Token;
import lexer.Tag;

public class Main{
	public static void main(String[] args) throws FileNotFoundException, IOException {
		Token scanned;
		int currentLine = 1;
		Lexer lexer = new Lexer(args[0]);
		do{
			scanned = lexer.scan();
			if (lexer.line != currentLine) {
				System.out.println("");
				currentLine = lexer.line;
			}
			System.out.print(scanned + " ");
		}while(scanned.tag != Tag.EOF);
		System.out.println("");
	}
}
