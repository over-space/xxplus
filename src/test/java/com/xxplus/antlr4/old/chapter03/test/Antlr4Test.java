package com.xxplus.antlr4.old.chapter03.test;

import com.xxplus.antlr4.old.chapter03.gen.HelloLexer;
import com.xxplus.antlr4.old.chapter03.gen.HelloParser;
import com.xxplus.base.BaseTest;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.junit.Test;

/**
 * Created by admin on 2016-03-26.
 */
public class Antlr4Test extends BaseTest{

    @Test
    public void testAntlr4(){

        String sentence = "123456, 2, 4";

        HelloLexer lexer = new HelloLexer(new ANTLRInputStream(sentence));

        CommonTokenStream tokens = new CommonTokenStream(lexer);

        HelloParser parser = new HelloParser(tokens);

        ParseTree tree = parser.r();

        ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk(new HelloBaseListenerImpl(), tree);
    }

}
