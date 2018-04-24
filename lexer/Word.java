package lexer;
public class Word extends Token{

	private String lexeme = "";

	public static final Word and = new Word ("&&", Tag.AND);
	public static final Word or = new Word ("||", Tag.OR);
	public static final Word eq = new Word ("==", Tag.EQ);
	public static final Word neq = new Word ("!=", Tag.NEQ);
	public static final Word le = new Word ("<=", Tag.LE);
	public static final Word ge = new Word (">=", Tag.GE);

	public Word(String s, Tag tag){
		super(tag);
		this.lexeme = s;
	}

	public String getLexeme(){
		return this.lexeme;
	}

	public String toString(){
		return "<"+tag+", "+lexeme+">";
	}

}
