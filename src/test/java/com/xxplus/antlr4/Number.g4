
grammar Number;
options {output=AST;}
stat : (expr ',')+;
expr : INT;
INT : [0-9]+;
WS : [\r\t\n]+ -> skip;