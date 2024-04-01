package rs.ac.bg.etf.pp1;

import java_cup.runtime.Symbol;

%%

%{

	// ukljucivanje informacije o poziciji tokena
	private Symbol new_symbol(int type) {
		return new Symbol(type, yyline+1, yycolumn);
	}
	
	// ukljucivanje informacije o poziciji tokena
	private Symbol new_symbol(int type, Object value) {
		return new Symbol(type, yyline+1, yycolumn, value);
	}

%}

%cup
%line
%column

%xstate COMMENT

%eofval{
	return new_symbol(sym.EOF);
%eofval}

%%

" " 	{ }
"\b" 	{ }
"\t" 	{ }
"\r\n" 	{ }
"\f" 	{ }

"program"   { return new_symbol(sym.PROG, yytext()); }
"print" 	{ return new_symbol(sym.PRINT, yytext()); }
"read"		{ return new_symbol(sym.READ, yytext()); }
"return" 	{ return new_symbol(sym.RETURN, yytext()); }
"if" 		{ return new_symbol(sym.IF, yytext()); }
"else"   { return new_symbol(sym.ELSE, yytext()); }
"new" 	{ return new_symbol(sym.NEW, yytext()); }
"while" 	{ return new_symbol(sym.WHILE, yytext()); }
"extends" 		{ return new_symbol(sym.EXTENDS, yytext()); }
"continue" 		{ return new_symbol(sym.CONTINUE, yytext()); }
"foreach"   { return new_symbol(sym.FOREACH, yytext()); }
"const" 	{ return new_symbol(sym.CONST, yytext()); }
"class" 	{ return new_symbol(sym.CLASS, yytext()); }
"break" 		{ return new_symbol(sym.BREAK, yytext()); }
"void"		{ return new_symbol(sym.VOID, yytext()); }
"findAny"   {  return new_symbol(sym.FINDANY, yytext()); }
"namespace" {  return new_symbol(sym.NMSPACE, yytext()); }
<YYINITIAL> "+" 		{ return new_symbol(sym.PLUS, yytext()); }
"=" 		{ return new_symbol(sym.EQUAL, yytext()); }
";" 		{ return new_symbol(sym.SEMI, yytext()); }
"," 		{ return new_symbol(sym.COMMA, yytext()); }
"(" 		{ return new_symbol(sym.LPAREN, yytext()); }
")" 		{ return new_symbol(sym.RPAREN, yytext()); }
"{" 		{ return new_symbol(sym.LBRACE, yytext()); }
"}"			{ return new_symbol(sym.RBRACE, yytext()); }
"=>" 		{ return new_symbol(sym.ARROW, yytext()); }
"[" 		{ return new_symbol(sym.LSQRBRACK, yytext()); }
"]" 		{ return new_symbol(sym.RSQRBRACK, yytext()); }
"." 		{ return new_symbol(sym.DOT, yytext()); }
":" 		{ return new_symbol(sym.COLON, yytext()); }
"::"		{ return new_symbol(sym.DOUBLECOLON, yytext()); }
"++" 		{ return new_symbol(sym.INC, yytext()); }
"--"			{ return new_symbol(sym.DEC, yytext()); }
"||" 		{ return new_symbol(sym.OR, yytext()); }
"&&" 		{ return new_symbol(sym.AND, yytext()); }
"<=" 		{ return new_symbol(sym.LEA, yytext()); }
"<" 		{ return new_symbol(sym.LES, yytext()); }
">=" 		{ return new_symbol(sym.GEA, yytext()); }
">"			{ return new_symbol(sym.GRE, yytext()); }
"<=" 		{ return new_symbol(sym.LEA, yytext()); }
"<" 		{ return new_symbol(sym.LES, yytext()); }
">=" 		{ return new_symbol(sym.GEA, yytext()); }
">"			{ return new_symbol(sym.GRE, yytext()); }
"!=" 		{ return new_symbol(sym.NEQU, yytext()); }
"==" 		{ return new_symbol(sym.EQU, yytext()); }
"%" 		{ return new_symbol(sym.MOD, yytext()); }
"/"			{ return new_symbol(sym.DIV, yytext()); }
"*" 		{ return new_symbol(sym.MUL, yytext()); }
"-"			{ return new_symbol(sym.MINUS, yytext()); }

<YYINITIAL> "//" 		     { yybegin(COMMENT); }
<COMMENT> .      { yybegin(COMMENT); }
<COMMENT> "\r\n" { yybegin(YYINITIAL); }

"'"."'"  { return new_symbol(sym.CHARCONST, new Character (yytext().charAt(1))); }
"true"|"false" { return new_symbol(sym.BOOLCONST, new Boolean (yytext())); }
[0-9]+  { return new_symbol(sym.NUMBER, new Integer (yytext())); }
([a-z]|[A-Z])[a-zA-Z0-9_]* 	{return new_symbol (sym.IDENT, yytext()); }

. { System.err.println("Leksicka greska ("+yytext()+") u liniji "+(yyline+1)); }






