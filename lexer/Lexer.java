package lexer;
import java.io.*;
import java.util.*;
import lexer.Tag;
import lexer.Word;

public class Lexer{
	public static int line = 1;
	public char ch = ' ';
	private FileReader file;

	private Hashtable<String, Token> words = new Hashtable<String, Token>();

	private void reserve(Word w){
		this.words.put(w.getLexeme(), w);
	}

	public Lexer(String fileName) throws FileNotFoundException{
		try{
			file = new FileReader(fileName);
		}
		catch(FileNotFoundException e){
			System.out.println("File not found!");
			throw e;
		}
		//putting reserved words on Hashtable
		reserve(new Word("program", Tag.PROGRAM));
		reserve(new Word ("declare", Tag.DECLARE));
		reserve(new Word ("begin", Tag.BEGIN));
		reserve(new Word ("end", Tag.END));
		reserve(new Word ("int", Tag.INT));
		reserve(new Word ("float", Tag.FLOAT));
		reserve(new Word ("char", Tag.CHAR));
		reserve(new Word ("if", Tag.IF));
		reserve(new Word ("then", Tag.THEN));
		reserve(new Word ("else", Tag.ELSE));
		reserve(new Word ("repeat", Tag.REPEAT));
		reserve(new Word ("until", Tag.UNTIL));
		reserve(new Word ("while", Tag.WHILE));
		reserve(new Word ("do", Tag.DO));
		reserve(new Word ("in", Tag.IN));
		reserve(new Word ("out", Tag.OUT));
	}

	//read next char from file
	private void readch() throws IOException{
		ch = (char) file.read();
	}

	//read next char and compares with c
	private boolean readch(char c) throws IOException{
		readch();
		if(ch != c) return false;
		ch = ' ';
		return true;
	}

	//return next token
	public Token scan() throws IOException{
		//ignore delim
		for(;; readch()){
			if (ch == ' ' || ch == '\t' || ch == '\r' || ch == '\b' ) continue;
			else if (ch == '\n') line++;
			else if(ch == '/'){
				if(readch('*')){// is a comment

					do{
						if((int)ch == 65535) return new Token(Tag.UEOF); //65535 it means EOF
						if (ch == '\n') line++; //compute new line in comments
						readch();
					}while (ch != '*');

					if(!readch('/')){
						return new Token(Tag.MFT);
					}
					continue;
				}
				else{
					ch = '/';
					break;
				}
			}
			else break;
		}

		//end of file
		if((int)ch == 65535) return new Token(Tag.EOF);

		switch(ch){
			//operators and punctation
			case ';':
				readch();
				return new Token(Tag.SCL);

			case ':':
				readch();
				return new Token(Tag.TDT);

			case ',':
				readch();
				return new Token(Tag.CMA);

			case '(':
				readch();
				return new Token(Tag.OP);

			case ')':
				readch();
				return new Token(Tag.CP);

			case '+':
				readch();
				return new Token(Tag.PLS);

			case '-':
				readch();
				return new Token(Tag.MNS);

			case '*':
				readch();
				return new Token(Tag.MUL);

			case '/':


					readch();
					return new Token(Tag.DIV);


			case '&':
				if (readch('&')) return Word.and;
				else return new Token(Tag.MFT); //invalid token

			case '|':
				if (readch('|')) return Word.or;
				else return new Token(Tag.MFT); //invalid token

			case '=':
				if (readch('=')) return Word.eq;
				else return new Token(Tag.ATB);

			case '>':
				if (readch('=')) return Word.ge;
				else return new Token(Tag.GT);

			case '<':
				if (readch('=')) return Word.ge;
				else return new Token(Tag.LT);

			case '!':
				if (readch('=')) return Word.neq;
				else return new Token(Tag.NOT);
		}

		//id
		if (Character.isLetter(ch)) {
			StringBuffer sb = new StringBuffer();
			do{
				sb.append(ch);
				readch();
			} while(Character.isLetterOrDigit(ch) || ch == '_');

			String s = sb.toString();
			Word w = (Word)words.get(s);
			if (w != null) return w; //table contains while ()
			w = new Word(s, Tag.ID);
			words.put(s,w);
			return w;
			}

			//const number
			if(Character.isDigit(ch)){
				int left = 0;
				do{
					left = 10 * left + Character.digit(ch, 10);
					readch();
				} while(Character.isDigit(ch));

				//its a float?
				if(ch == '.'){
					readch(); //digit{digit}.digit{digit}
					if(Character.isDigit(ch)){
						int right = 0;
						int counter = 0;
						do{
							right = 10 * right + Character.digit(ch, 10);
							counter++;
							readch();
						} while(Character.isDigit(ch));
						double result = (double)right/(double)Math.pow(10,counter);
						return new Cfloat(result);
					}
					else{ //digit{digit}.something - return invalid token
						return new Token(Tag.MFT);
					}

				}
				else{ //its an integer
					return new Cint(left);
				}
			}

			//const char String
			int ascii = -1;
			switch(ch){
				case '\'': //char
					readch();
					char c = ch;
					ascii = (int)ch;
					if (readch('\'') && ascii < 127){ // is in ascii table?
						return new Cchar(c);
					}
					else{
						return new Token(Tag.MFT); //invalid token
					}

				case '\"': //string
					StringBuffer sb = new StringBuffer();

					do{
						readch();
						ascii = (int)ch;
						if(ascii < 127 && ! (ch == '\t' || ch == '\r'//refactor into a function
							|| ch == '\b' || ch == '\n')){ // is in ascii table?
							if(ch != '\"'){
							 sb.append(ch);
						 }
						}
						else{
							return new Token(Tag.MFT); //invalid token
						}
					} while(ch != '\"');
					readch(); //advance to next char
					String s = sb.toString();
					return new Cstring(s);

			}

			ch = ' ';
			return new Token(Tag.MFT);
		}
}
