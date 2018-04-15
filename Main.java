import java.io.*;
import lexer.Lexer;

public class Main{
	public static void main(String[] args) throws FileNotFoundException, IOException {
		System.out.println("Hello World!");
		Lexer lexer = new Lexer("test.txt");
		System.out.println(lexer.scan());
		System.out.println(lexer.scan());
		System.out.println(lexer.scan());
		System.out.println(lexer.scan());
		System.out.println(lexer.scan());
		System.out.println(lexer.scan());
		System.out.println(lexer.scan());
		System.out.println(lexer.scan());
		System.out.println(lexer.scan());
		System.out.println(lexer.scan());
		System.out.println(lexer.scan());
	}
}
