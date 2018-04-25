package errorreporter;
import java.util.Hashtable;
import java.util.List;
import java.util.ArrayList;
import lexer.Tag;


public abstract class Reportable{
    public List<String> messages = new ArrayList<String>();
    private Messages template = new Messages();

    public void addMessage(Tag tag, int line){
        messages.add(template.get(tag)+" na linha: "+line);
    }
}
