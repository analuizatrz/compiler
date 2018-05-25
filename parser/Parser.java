package parser;

import errorreporter.ErrorType;
import errorreporter.Reportable;
import lexer.Lexer;
import lexer.Tag;
import lexer.Token;
import java.io.*;

public class Parser extends Reportable{
    private Token token;
    private Lexer lexer;

    public Parser(Lexer lexer) throws IOException  {
            this.lexer = lexer;
            advance();
    }
    void advance() throws IOException{
        token = lexer.scan();
    }
    void eat(Tag tag) throws IOException {
        System.out.print(" "+ tag.toString()+" ");
        if(token.tag == tag)
            advance();
        else{
            System.out.println();
            error(" quando se esperava " + tag.toString());
            listErrors();
            System.exit(0);
        }
    }
    public void start() throws IOException{
        program();
    }
    void error() throws IOException{
        System.out.println();
            error("");
            listErrors();
            System.exit(0);            
    }
    void error(String complement) throws IOException{
        if(token.tag == Tag.EOF)
            addMessage(ErrorType.UEOF, Lexer.line);
        else
            addMessage(ErrorType.UNT, token.toString() + complement, Lexer.line);
        advance();
    }
    void program() throws IOException {
        switch(token.tag){
            // program ::= program identifier body  
            case PROGRAM: eat(Tag.PROGRAM); eat(Tag.ID); body(); break;           
            default: error();
        }
    }
    void body() throws IOException {
        switch(token.tag){
            // body ::= [declare decl-list] begin stmt-list end 
            case DECLARE: eat(Tag.DECLARE); decl_list();;
            case BEGIN: eat(Tag.BEGIN); stmt_list(); eat(Tag.END);
            break;
            default: error();
        }
    }
    void decl_list() throws IOException {
        switch(token.tag){
            // decl-list ::= decl {";" decl} 
            case ID: decl(); 
                while(token.tag == Tag.SCL){
                    eat(Tag.SCL); decl();
                }
                break;
            default: error();
        }
    }
    void decl() throws IOException {
        switch(token.tag){
            // decl ::= ident-list ":" type
            case ID: ident_list(); eat(Tag.TDT); type(); break;
            default: error();
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
            default: error();
        }
    }
    void type() throws IOException {
        switch(token.tag){
            // type ::= int | float | char
            case INT: eat(Tag.INT); break;
            case FLOAT: eat(Tag.FLOAT); break;
            case CHAR: eat(Tag.CHAR); break;
            default: error();
        }
    }
    void stmt_list() throws IOException {
        switch(token.tag){
            // stmt-list ::= stmt {";" stmt}
            case ID:
            case IF:
            case WHILE:
            case REPEAT:
            case IN:
            case OUT:
                stmt();
                while(token.tag == Tag.SCL){
                    eat(Tag.SCL); stmt();
                }
                break;
            default: error();
        }
    }
    void stmt() throws IOException {
        switch(token.tag){
            // stmt ::= assign-stmt | if-stmt | while-stmt | repeat-stmt | read-stmt | write-stmt
            case ID: assign_stmt(); break;
            case IF: if_stmt(); break;
            case WHILE: while_stmt(); break;
            case REPEAT: repeat_stmt(); break;
            case IN: read_stmt(); break;
            case OUT: write_stmt(); break;
            default: error();
        }
    }
    void assign_stmt() throws IOException {
        switch(token.tag){
            // assign-stmt ::= identifier "=" simple_expr
            case ID: eat(Tag.ID); eat(Tag.ATB); simple_expr(); break;
            default: error();
        }
    }
    void if_stmt() throws IOException {
        switch(token.tag){
            // if-stmt ::= if condition then stmt-list if-stmt'
            case ID: eat(Tag.IF); condition(); eat(Tag.THEN); stmt_list(); if_stmt_(); break;
            default: error();
        }
    }
    void if_stmt_() throws IOException {
        switch(token.tag){
            // if-stmt' ::= end | else stmt-list end
            case END: eat(Tag.END); break;
            case ELSE: eat(Tag.ELSE); stmt_list(); eat(Tag.END); break;
            default: error();
        }
    }
    void condition() throws IOException {
        switch(token.tag){
            // condition ::= expression
            case ID:
            case CINT:
            case CFLOAT:
            case CCHAR:
            case OP:
            case MNS: expression(); break;
            default: error();
        }
    }
    void repeat_stmt() throws IOException {
        switch(token.tag){
            // repeat-stmt ::= repeat stmt-list stmt-suffix
            case REPEAT: eat(Tag.REPEAT); stmt_list(); stmt_suffix(); break;
            default: error();
        }
    }
    void stmt_suffix() throws IOException {
        switch(token.tag){
            // stmt-suffix ::= until condition
            case UNTIL: eat(Tag.UNTIL); condition(); break;
            default: error();
        }
    }
    void while_stmt() throws IOException {
        switch(token.tag){
            // while-stmt ::= stmt-prefix stmt-list end
            case WHILE: stmt_preffix(); stmt_list(); eat(Tag.END); break;
            default: error();
        }
    }
    void stmt_preffix() throws IOException {

    }
    void read_stmt() throws IOException {
        switch(token.tag){
            // read-stmt ::= in"(" identifier")"
            case IN: eat(Tag.IN); eat(Tag.OP); eat(Tag.ID); eat(Tag.CP); break;
            default: error();
        }
    }
    void write_stmt() throws IOException {
        switch(token.tag){
            // write-stmt ::= out"("writable ")"
            case OUT: eat(Tag.OUT); eat(Tag.OP); writeable(); eat(Tag.CP); break;
            default: error();
        }
    }
    void writeable() throws IOException {
        switch(token.tag){
            // writable ::= simple-expr | literal
            case ID:
            case CINT:
            case CFLOAT:
            case CCHAR:
            case OP:
            case NOT:
            case MNS: simple_expr(); break;
            case CSTRING: eat(Tag.CSTRING); break;
            default: error();
        }
    }
    void expression() throws IOException {
        switch(token.tag){
            // expression ::= simple-expr expression'
            case ID:
            case CINT:
            case CFLOAT:
            case CCHAR:
            case OP:
            case NOT:
            case MNS: simple_expr(); expression_(); break;
            default: error();
        }
    }
    void expression_() throws IOException {
        switch(token.tag){
            // expression' ::= relop simple-expr | λ 
            case EQ:
            case GT:
            case GE:
            case LT:
            case LE:
            case NEQ: relop(); simple_expr(); break;

            case CP:
            case THEN:
            case DO:
            case END:
            case ELSE:
            case UNTIL:
            case SCL: break;
            default: error();
        }
    }
    void simple_expr() throws IOException {
        switch(token.tag){
            // simple-expr ::= term simple-expr'
            case ID:
            case CINT:
            case CFLOAT:
            case CCHAR:
            case OP:
            case NOT:
            case MNS: term(); simple_expr_(); break;
            default: error();
        }
    }
    void simple_expr_() throws IOException {
        switch(token.tag){
            // simple-expr' ::= addop term simple-expr' | λ
            case PLS:
            case MNS:
            case OR: addop(); term(); simple_expr_(); break;
            
            case CP:
            case THEN:
            case DO:
            case END:
            case ELSE:
            case UNTIL:
            case SCL: 
            case EQ:
            case GT:
            case GE:
            case LT:
            case LE:
            case NEQ: break;

            default: break;
        }
    }
    void term() throws IOException {
        switch(token.tag){
            // term ::= factor-a term'
            case ID:
            case CINT:
            case CFLOAT:
            case CCHAR:
            case OP:
            case NOT:
            case MNS: factor_a(); term_(); break;
            default: error();
        }
    }
    void term_() throws IOException {
        switch(token.tag){
            // term' ::= mulop factor-a term' | λ
            case MUL:
            case DIV:
            case AND: mulop(); factor_a(); term_(); break;
            
            case CP:
            case THEN:
            case DO:
            case END:
            case ELSE:
            case UNTIL:
            case SCL: 
            case EQ:
            case GT:
            case GE:
            case LT:
            case LE:
            case NEQ: break;

            default: break;
        }
    }
    void factor_a() throws IOException {
        switch(token.tag){
            // factor-a ::= factor | !factor | "-" factor
            case ID:
            case CINT:
            case CFLOAT:
            case CCHAR:
            case OP: factor(); break;
            case NOT: eat(Tag.NOT); factor(); break;
            case MNS: eat(Tag.MNS); factor(); break;
            default: error();
        }
    }
    void factor() throws IOException {
        switch(token.tag){
            // factor ::= identifier | constant | "(" expression ")"
            case ID: eat(Tag.ID); break;
            case CINT:
            case CFLOAT:
            case CCHAR: constant(); break;
            case OP: eat(Tag.OP); expression(); eat(Tag.CP); break;
            default: error();
        }
    }
    void relop() throws IOException {
        switch(token.tag){
            // relop ::= "==" | ">" | ">=" | "<" | "<=" | "!="
            case EQ: eat(Tag.EQ); break;
            case GT: eat(Tag.GT); break;
            case GE: eat(Tag.GE); break;
            case LT: eat(Tag.LT); break;
            case LE: eat(Tag.LE); break;
            case NEQ: eat(Tag.NEQ); break;
            default: error();
        }
    }
    void addop() throws IOException {
        switch(token.tag){
            // addop ::= "+" | "-" | ||
            case PLS: eat(Tag.PLS); break;
            case MNS: eat(Tag.MNS); break;
            case OR: eat(Tag.OR); break;
            default: error();
        }
    }
    void mulop() throws IOException {
        switch(token.tag){
            // mulop ::= "*" | "/" | &&
            case MUL: eat(Tag.MUL); break;
            case DIV: eat(Tag.DIV); break;
            case AND: eat(Tag.AND); break;
            default: error();
        }
    }
    void constant() throws IOException {
        switch(token.tag){
            // constant ::= integer_const | float_const | char_const
            case CINT: eat(Tag.CINT); break;
            case CFLOAT: eat(Tag.CFLOAT); break;
            case CCHAR: eat(Tag.CCHAR); break;
            default: error();
        }
    }
}