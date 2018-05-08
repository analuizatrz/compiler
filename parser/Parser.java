import lexer.Lexer;
import lexer.Tag;
import lexer.Token;

public class Parser extends Reportable{
    private Token token;
    private Lexer lexer;

    public Parser(string filename){
        lexer = new Lexer(filename);
    }
    public Parser(Lexer lexer){
        this.lexer = lexer;
        
    }
    void advance(){
        token = lexer.scan();
    }
    void eat(Tag tag){
        if(token.tag == tag) advance();
        else{
            
        }
    }

}

/*
Do slide ;)

int tok = getToken(); //lê primeiro token

void advance(){
    tok = getToken();   //lê próximo token
}

void eat(int t){
    if(tok==t) advance();
    else
        error();
}

void S(){
    switch(tok){
        //S → if E then S else S 
        case IF: eat(IF); E(); eat(THEN); S();eat(ELSE); S(); break;
        
        //S → begin S L 
        case BEGIN: eat(BEGIN); S(); L(); break;
        
        // S → print E
        case PRINT: eat(PRINT); E(); break;
        
        default: error();
    }
}
*/