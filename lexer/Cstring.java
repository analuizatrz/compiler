package lexer;
public class Cstring extends Token{

	public final String value;

	public Cstring(String value){
		super(Tag.CSTRING);
		this.value = value;
	}

	public String toString(){
		return "" + value;
	}
}
