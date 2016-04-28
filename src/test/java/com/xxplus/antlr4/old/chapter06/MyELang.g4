
grammar MyELang;

@header{
 //package com.xxplus.antlr4.old.chapter06.gen
}

expr : 'hello' + ID;
ID : [0-9]+;
WS : [\n\t\r]+ -> skip;
