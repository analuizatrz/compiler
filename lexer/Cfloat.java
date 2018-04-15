package lexer;
public class Cfloat extends Token{

	public final float value;

	public Cfloat(float value){
		super(Tag.CFLOAT);
		this.value = value;
	}

	public String toString(){
		return "" + value;
	}
}
