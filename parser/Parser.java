package parser;

import errorreporter.ErrorType;
import errorreporter.Reportable;
import lexer.Lexer;
import lexer.Tag;
import lexer.Token;
import java.util.*;
import java.lang.*;
import java.io.*;

public class Parser extends Reportable{
    private Token token;
    private Lexer lexer;

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
            error();
    }
    void error(){
        if(token.tag == Tag.EOF)
            addMessage(ErrorType.UEOF, Lexer.line);
        else
            addMessage(ErrorType.UNT, Lexer.line);
    }
    void program() throws java.io.IOException {
        switch(token.tag){
            //program ::= program identifier body  
            case PROGRAM: eat(Tag.PROGRAM); eat(Tag.ID); body(); break;           
            default: 
                error();
        }
    }
    void body(){
        
    }
    void decl_list(){
        
    }
    void decl_list_(){
        
    }
    void decl(){
        
    }
    void ident_list(){
        
    }
    void type(){

    }
    void stmt_list(){

    }
    void stmt(){

    }
    void assign_stmt(){

    }
    void if_stmt(){

    }
    void if_stmt_(){

    }
    void condfition(){

    }
    void repeat_stmt(){

    }
    void stmt_suffix(){

    }
    void while_stmt(){

    }
    void stmt_preffix(){

    }
    void read_stmt(){

    }
    void write_stmt(){

    }
    void writeable(){

    }
    void expression(){

    }
    void expression_(){

    }
    void simple_expr(){

    }
    void simple_expr_(){

    }
    void term(){

    }
    void term_(){

    }
    void factor_a(){

    }
    void factor(){

    }
    void relop(){

    }
    void addop(){

    }
    void mulop(){

    }
    void constant(){

    }
}