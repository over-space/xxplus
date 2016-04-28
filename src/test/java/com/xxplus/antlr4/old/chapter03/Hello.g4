

grammar Hello;

//@header{
//    package foo;
//}

tokens {A, B, C}
r  : 'hello' ID;
ID : [a-z]+;
WS : [\t\r\n]+ ->skip;