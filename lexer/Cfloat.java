package lexer;
public class Cfloat extends Token{

	public final double value;

	public Cfloat(double value){
		super(Tag.CFLOAT);
		this.value = value;
	}

	public String toString(){
		return "" + value;
	}
}
