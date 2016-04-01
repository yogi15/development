
// use with JFlex version 1.3.5 or later
// NOPMD: this is generated

package bo.html.Formatter.Util;

import java_cup.runtime.*;

%%


%class Lexer
%unicode
%cup
%line
%column
%integer
%eofval{
  return null;
%eofval}
%eof{
  FormatBuilder.addLine(null);
%eof}
%eofclose
//%debug

%{
  StringBuffer string = new StringBuffer();

  private Symbol symbol(int type) {
    return new Symbol(type, yyline, yycolumn);
  }
  private Symbol symbol(int type, Object value) {
    return new Symbol(type, yyline, yycolumn, value);
  }
%}


%state COSMOS
%state IGNORE
%state GENERATED



LineTerminator = \r|\n|\r\n
WhiteSpace = [ \t\f] | {LineTerminator}
Letter = [:jletter:]
LetterDigit = [:jletterdigit:]
PositiveInteger = 0 | [1-9][0-9]*
Integer = -?{PositiveInteger}
Double = {Integer} | {Integer}? "." "0"* {Integer} | {Integer} [eE] "-"? {Integer} | {Integer}? "." "0"* {Integer} [eE] "-"? {Integer}
String = "\"" ([^\"] | "\\\"")* "\""
Function = {LetterDigit}* "/"
Parameter = "#" {LetterDigit}*
Identifier = {Function}* {Letter} {LetterDigit}* {Parameter}*
StartCosmos = "<" "!--"? [cC][aA][lL][yY][pP][sS][oO] ":r"? ">"
EndCosmos = "</"[cC][aA][lL][yY][pP][sS][oO] "--"? ">"
StartIgnore = "<!--"[iI][gG][nN][oO][rR][eE]"-->"
EndIgnore = "<!--/"[iI][gG][nN][oO][rR][eE]"-->"
StartGenerated = "<!--generatedStart-->"
EndGenerated = "<!--generatedEnd-->"

Year = (19 | 20)[0-9][0-9]
Month = 0?[1-9] | 10 | 11 | 12
Day = 0?[1-9] | [1-2][0-9] | 30 | 31
Date = {Month}"/"{Day}"/"{Year}

%%

<YYINITIAL> {
  {StartIgnore}			  	  {
  					      yybegin(IGNORE);
  					  }  				                                          
  
  {StartGenerated}			  {
  					      
                                              yybegin(GENERATED);
                                              FormatBuilder.startGenerated();
  					  }
  {StartCosmos}  	  		  { 
                                              boolean regenerate = yytext().contains(":r");
                                              if (regenerate) FormatBuilder.addOriginalLine("<!--generatedStart-->");
                                              FormatBuilder.startCosmosLevel();
                                              FormatBuilder.start(yytext()); 
                                              yybegin(COSMOS); 
                                              return symbol( regenerate ? sym.START_REGEN : sym.START);
                                          }
  !(![^\n\r] | {StartIgnore} | {StartGenerated} | {StartCosmos})	  { 
                                              FormatBuilder.addLine(yytext());
                                          }
  
  {LineTerminator}			  {
  						FormatBuilder.addLine("\n");
  					  }                                          

}


<COSMOS> {
  {EndCosmos}			  	  {
                                              yybegin(YYINITIAL); 
                                              FormatBuilder.endCosmosLevel();
                                              return symbol(sym.END);
                                              
                                          }
}
<GENERATED> {
  {EndGenerated}			  {
                                              boolean end = FormatBuilder.endGenerated();
                                              if (end) yybegin(YYINITIAL); 
                                          }
  
  {StartGenerated}			  {
                                              FormatBuilder.startGenerated();
                                          }
  ~({EndGenerated}|{StartGenerated})	  {
                                              String s = yytext();
                                              int endIndex = s.lastIndexOf("<!--");
                                              boolean isStart = s.endsWith("<!--generatedStart-->");
                                              FormatBuilder.collectGenerated(s.substring(0, endIndex));
                                              
                                              boolean end = isStart ? false : FormatBuilder.endGenerated();
                                              
                                              if (end) yybegin(YYINITIAL);
                                          }
}

<IGNORE> {
  ~{EndIgnore}			  	  {
                                              yybegin(YYINITIAL); 
                                          }
}



/*
 * --- Keywords ---
 */

<COSMOS>"if"		{ FormatBuilder.echoCosmos(yytext()); return symbol(sym.IF); }
<COSMOS>"iterator"     { FormatBuilder.echoCosmos(yytext()); return symbol(sym.ITERATOR); }
<COSMOS>"else"		{ FormatBuilder.echoCosmos(yytext()); return symbol(sym.ELSE); }
<COSMOS>"include"	{ FormatBuilder.echoCosmos(yytext()); return symbol(sym.INCLUDE); }
<COSMOS>"inline"	{ FormatBuilder.echoCosmos(yytext()); return symbol(sym.INLINE); }
<COSMOS>"set"		{ FormatBuilder.echoCosmos(yytext()); return symbol(sym.SET); }
<COSMOS>[Tt]"rue"	{ FormatBuilder.echoCosmos(yytext()); return symbol(sym.TRUE); }
<COSMOS>[Ff]"alse"	{ FormatBuilder.echoCosmos(yytext()); return symbol(sym.FALSE); }
<COSMOS>[Tt]"rade"	{ FormatBuilder.echoCosmos(yytext()); return symbol(sym.TRADE); }
<COSMOS>[Tt]"ransfer"	{ FormatBuilder.echoCosmos(yytext()); return symbol(sym.TRANSFER); }
<COSMOS>[Mm]"essage"	{ FormatBuilder.echoCosmos(yytext()); return symbol(sym.MESSAGE); }
<COSMOS>[Ss]"ender"	{ FormatBuilder.echoCosmos(yytext()); return symbol(sym.SENDER); }
<COSMOS>[Rr]"eceiver"	{ FormatBuilder.echoCosmos(yytext()); return symbol(sym.RECEIVER); }
<COSMOS>[Pp]"roduct"	{ FormatBuilder.echoCosmos(yytext()); return symbol(sym.PRODUCT); }


/*
 * --- Operators ---
 */

<COSMOS>"("			{ FormatBuilder.echoCosmos(yytext()); return symbol(sym.LPAREN); }
<COSMOS>")"			{ FormatBuilder.echoCosmos(yytext()); return symbol(sym.RPAREN); }
<COSMOS>"<"			{ FormatBuilder.echoCosmos(yytext()); return symbol(sym.LT); }
<COSMOS>">"			{ FormatBuilder.echoCosmos(yytext()); return symbol(sym.GT); }
<COSMOS>"<="			{ FormatBuilder.echoCosmos(yytext()); return symbol(sym.LTE); }
<COSMOS>">="			{ FormatBuilder.echoCosmos(yytext()); return symbol(sym.GTE); }
<COSMOS>"="			{ FormatBuilder.echoCosmos(yytext()); return symbol(sym.EQ); }
<COSMOS>"=="			{ FormatBuilder.echoCosmos(yytext()); return symbol(sym.EQEQ); }
<COSMOS>"!="			{ FormatBuilder.echoCosmos(yytext()); return symbol(sym.NEQ); }
<COSMOS>"||"			{ FormatBuilder.echoCosmos(yytext()); return symbol(sym.OR); }
<COSMOS>"&&"			{ FormatBuilder.echoCosmos(yytext()); return symbol(sym.AND); }
<COSMOS>"!"			{ FormatBuilder.echoCosmos(yytext()); return symbol(sym.NOT); }
<COSMOS>"EMPTY"|"empty"	{ FormatBuilder.echoCosmos(yytext()); return symbol(sym.EMPTY); }
<COSMOS>"NOTEMPTY"|"notempty"	{ FormatBuilder.echoCosmos(yytext()); return symbol(sym.NOTEMPTY); }
<COSMOS>"LIKE"|"like"		{ FormatBuilder.echoCosmos(yytext()); return symbol(sym.LIKE); }
<COSMOS>"IN"|"in"		{ FormatBuilder.echoCosmos(yytext()); return symbol(sym.IN); }
<COSMOS>"NOTIN"|"notin"	{ FormatBuilder.echoCosmos(yytext()); return symbol(sym.NOTIN); }


/*
 * --- Punctuation ---
 */
 
<COSMOS>"."		{ FormatBuilder.echoCosmos(yytext()); return symbol(sym.PERIOD); }
<COSMOS>","		{ FormatBuilder.echoCosmos(yytext()); return symbol(sym.COMMA); }
<COSMOS>"{"		{ FormatBuilder.echoCosmos(yytext()); return symbol(sym.LBLOCK); }
<COSMOS>"}"		{ FormatBuilder.echoCosmos(yytext()); return symbol(sym.RBLOCK); }
<COSMOS>";"		{ FormatBuilder.echoCosmos(yytext()); return symbol(sym.SEMIC); }
<COSMOS>"|"		{ FormatBuilder.echoCosmos(yytext()); return symbol(sym.KEYWORD_DELIM); }

<COSMOS> {

  /*
   * --- Identifiers ---
   */
  {Identifier}		{ FormatBuilder.echoCosmos(yytext()); return symbol(sym.IDENTIFIER, yytext()); }

  /*
   * --- Literals ---
   */
  {Double}		{ FormatBuilder.echoCosmos(yytext()); return symbol(sym.NUMBER, new Double(yytext())); }
  {String}		{ FormatBuilder.echoCosmos(yytext()); return symbol(sym.STRING, yytext().substring(1, yytext().length()-1)); }
  {Date}		{ FormatBuilder.echoCosmos(yytext()); return symbol(sym.DATE); }

  {WhiteSpace}		{ FormatBuilder.echoCosmos(yytext()); }
  
  /*
   * Ignore standard HTML tags in code that may have been
   * added by HTML editor
   */
  "<"[Bb][Rr]">"	{ FormatBuilder.echoCosmos(yytext()); FormatBuilder.addLine("<br>"); }
  "<"[Pp]">"		{ FormatBuilder.echoCosmos(yytext()); FormatBuilder.addLine("<p>"); }
  "</"[Pp]">"		{ FormatBuilder.echoCosmos(yytext()); FormatBuilder.addLine("</p>"); }
  "<"[Bb][Rr]"/>"	{ FormatBuilder.echoCosmos(yytext()); FormatBuilder.addLine("<br/>"); }
  "</"[Bb][Rr]">"	{ FormatBuilder.echoCosmos(yytext()); FormatBuilder.addLine("</br>"); }
  "<span>"		{ FormatBuilder.echoCosmos(yytext()); FormatBuilder.addLine("<span>"); }
  "</span>"		{ FormatBuilder.echoCosmos(yytext()); FormatBuilder.addLine("</span>"); }
  "<SPAN>"		{ FormatBuilder.echoCosmos(yytext()); FormatBuilder.addLine("<span>"); }
  "</SPAN>"		{ FormatBuilder.echoCosmos(yytext()); FormatBuilder.addLine("</span>"); }
}
