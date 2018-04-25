package errorreporter;
import java.util.Hashtable;
import java.util.List;
import lexer.Tag;


public abstract class Reportable{
    public List<String> messages;
    private Messages template = new Messages();

    public void addMessage(Tag tag, int line){
        messages.add((new Messages()).dictionary.get(tag)+" "+line);
    }
}