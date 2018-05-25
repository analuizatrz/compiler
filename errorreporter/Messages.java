package errorreporter;

import java.util.Hashtable;
import errorreporter.Messages;
public class Messages{

    public Hashtable<ErrorType, String> dictionary = new Hashtable<ErrorType, String>(){
    private static final long serialVersionUID = 1L;
	{
        //erros lexicos
        put(ErrorType.UEOF, "Fim de arquivo inesperado");
        put(ErrorType.MFT, "Lexema mal formado");
        put(ErrorType.UNT, "Token inesperado");

    }};

    public String get(ErrorType errorType){
        return dictionary.get(errorType);
    }

}
