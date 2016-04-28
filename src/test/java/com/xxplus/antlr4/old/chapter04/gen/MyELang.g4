
grammar MyELang;

@members{
    int count = 0;
    String hello = "hello antlr4";
}

list
@after{
    System.out.println(count + " ints");
    System.out.println("----" + hello + "---");
}
:   INT {count++;} (',' INT{count++;})*;

INT : [0-9]+;
WS  : [ \r\t\n]+ -> skip;