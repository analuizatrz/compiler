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

    public Parser(Lexer lexer) throws IOException  {
            this.lexer = lexer;
            advance();
            eat(Tag.END);
      
    }
    void advance() throws IOException{
        token = lexer.scan();
    }
    void eat(Tag tag) throws IOException {
        if(token.tag == tag)
            advance();
        else
            error();
    }
    void error(){
        System.out.println("erro");
        if(token.tag == Tag.EOF)
            addMessage(ErrorType.UEOF, Lexer.line);
        else
            addMessage(ErrorType.UNT, Lexer.line);
    }
    void program() throws IOException {
        switch(token.tag){
            //program ::= program identifier body  
            case PROGRAM: eat(Tag.PROGRAM); eat(Tag.ID); body(); break;           
            default: 
                error();
        }
    }
    void body() throws IOException {
        switch(token.tag){
            //body ::= [declare decl-list] begin stmt-list end 
            case DECLARE: eat(Tag.PROGRAM); decl_list();;
            case BEGIN: eat(Tag.BEGIN); stmt_list(); eat(Tag.END);
            break;
            default: 
                error();
        }
    }
    void decl_list() throws IOException {
        switch(token.tag){
            //decl-list ::= decl {";" decl} 
            case ID: decl(); 
                while(token.tag == Tag.SCL){
                    eat(Tag.SCL); decl();
                }
                break;
            default: 
                error();
        }
    }
    void decl() throws IOException {
        switch(token.tag){
            // decl ::= ident-list ":" type
            case ID: ident_list(); eat(Tag.TDT); type(); break;
            default: 
                error();
        }
    }
    void ident_list() throws IOException {
        switch(token.tag){
            // ident-list ::= identifier {"," identifier}
            case ID: eat(Tag.ID);
                while(token.tag == Tag.CMA){
                    eat(Tag.CMA); eat(Tag.ID);
                }
                break;
            default: 
                error();
        }
    }
    void type() throws IOException {
        switch(token.tag){
            // type ::= int | float | char
            case CINT: eat(Tag.INT); break;
            case CFLOAT: eat(Tag.CFLOAT); break;
            case CCHAR: eat(Tag.CCHAR); break;
            default: 
                error();
        }
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