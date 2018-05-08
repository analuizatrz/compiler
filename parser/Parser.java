package parser;

import errorreporter.ErrorType;
import errorreporter.Reportable;
import lexer.Lexer;
import lexer.Tag;
import lexer.Token;

public class Parser extends Reportable{
    private Token token;
    private Lexer lexer;

    // public Parser(string filename) throws java.io.IOException{
    //     lexer = new Lexer(filename);
    // }
    public Parser(Lexer lexer){
        this.lexer = lexer;
    }
    void advance() throws java.io.IOException {
        token = lexer.scan();
    }
    void eat(Tag tag) throws java.io.IOException {
        if(token.tag == tag)
            advance();
        else
            addMessage(ErrorType.UNT, Lexer.line);
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