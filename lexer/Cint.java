package lexer;
public class Cint extends Token{

	public final int value;

	public Cint(int value){
		super(Tag.CINT);
		this.value = value;
	}

	public String toString(){
		return "" + value;
	}
}
