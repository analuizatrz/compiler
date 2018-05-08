/*
Do slide ;)

int tok= getToken(); //lê primeiro token

void advance(){
    tok= getToken();   //lê próximo token
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