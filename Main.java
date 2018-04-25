import java.io.*;

import errorreporter.Reportable;
import errorreporter.*;
import lexer.Lexer;
import lexer.Token;
import lexer.Tag;

public class Main extends Reportable{

	public static  void lexerAnalisys(String fileName)throws FileNotFoundException, IOException{
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
		}while(scanned.tag != Tag.EOF && scanned.tag != Tag.MFT && scanned.tag != Tag.UEOF); //interrupt if detect error or EOF

		System.out.println("");
		if(lexer.getWords().size() > 0 ) System.out.println("\n======Tabela de Símbolos======");

		for (Token value : lexer.getWords().values()) {
    	System.out.println(value);
		}

		System.out.println("");
		if(lexer.messages.size() > 0) System.out.println("\n======Erros Detectados======");

		for (String message : lexer.messages) {
			System.out.println(message);
		}
	}
	public static void main(String[] args) throws FileNotFoundException, IOException {
		 lexerAnalisys(args[0]);
		// Main m = new Main();
		// m.addMessage(Tag.UEOF, 3);
		//
		// for (String message : m.messages) {
		// 	System.out.println(message);
		// }


	}
}
