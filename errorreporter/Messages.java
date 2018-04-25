package errorreporter;

import lexer.Tag;
import java.util.Hashtable;
import errorreporter.Messages;
public class Messages{

    public Hashtable<Tag, String> dictionary = new Hashtable<Tag, String>(){
    {
        //erros lexicos
        put(Tag.UEOF, "Fim de arquivo inesperado");
        put(Tag.MFT, "Lexema mal formado");

    }};

    // public Messages(){
    //   this.dictionary = new Hashtable<Tag, String>();
    //   this.dictionary.put(Tag.UEOF, "Fim de arquivo inesperado");
    //   this.dictionary.put(Tag.MFT, "Lexema mal formado");
    // }

    public String get(Tag tag){
        return dictionary.get(tag);
    }

}
