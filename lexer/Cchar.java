package lexer;
public class Cchar extends Token{

	public final char value;

	public Cfloat(char value){
		super(Tag.CCHAR);
		this.value = value;
	}

	public String toString(){
		return "" + value;
	}
}
