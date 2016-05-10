
/**
 * 每个可能的输入字符都必须被至少一个词法规则匹配。
 */

grammar Calc01;

prog : stat+ ;

stat : expr | ID '=' expr; //语法规则

expr : expr ('*' | '/') expr | expr ('+' | '-') expr | INT | ID | '(' expr ')'; //语法规则

ID : [a-zA-Z]+; //词法规则

INT : [0-9]+;  //词法规则

WS : [ \t\r\n]+ -> skip;
