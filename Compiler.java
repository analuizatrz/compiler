import java.io.*;

import lexer.Lexer;
import lexer.Token;
import lexer.Tag;
import parser.*;

public class Compiler {
	public static void printSymbolTable(Lexer lexer){
		System.out.println("");
		if(lexer.getWords().size() > 0 ) System.out.println("\n======Tabela de Símbolos======");

		for (Token value : lexer.getWords().values()) {
    		System.out.println(value);
		}
	}
	public static void lexerAnalisys(String fileName) throws FileNotFoundException, IOException{
		Token scanned;
		int currentLine = 1;
		Lexer lexer = new Lexer(fileName);

		System.out.println("======Sequência de Tokens======");

		do{
			scanned = lexer.scan();
			if (Lexer.line != currentLine) {
				System.out.println("");
				currentLine = Lexer.line;
			}
			System.out.print(scanned + " ");
		}while(scanned.tag != Tag.EOF /*&& scanned.tag != Tag.MFT && scanned.tag != Tag.UEOF*/); //interrupt if detect error or EOF
		printSymbolTable(lexer);
		if(lexer.listErrors() == 0)
			System.out.println("\nNenhum erro foi detectado, tokens identificados com sucesso");

	}
	public static void parserAnalisys(String fileName)throws FileNotFoundException, IOException{
		Lexer lexer = new Lexer(fileName);
		Parser parser = new Parser(lexer);
		parser.start();
		if(lexer.listErrors() == 0 && parser.listErrors() == 0)
			System.out.println("\nNenhum erro foi detectado");
	}
	public static void main(String[] args) throws FileNotFoundException, IOException {
		//lexerAnalisys(args[0]);
		parserAnalisys(args[0]);
	}
}
