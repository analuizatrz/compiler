package errorreporter;
import java.util.List;
import java.util.ArrayList;

public abstract class Reportable{
    private List<String> messages = new ArrayList<String>();
    private Messages template = new Messages();

    public void addMessage(ErrorType errorType, int line){
        messages.add(template.get(errorType) + " na linha " + line);
    }
    public void addMessage(ErrorType errorType, String complement, int line){
        messages.add(template.get(errorType) + ", " + complement + ", na linha " + line);
    }
    public int listErrors(){
        for (String message : messages)
            System.out.println(message);
        return messages.size();
    }
}
