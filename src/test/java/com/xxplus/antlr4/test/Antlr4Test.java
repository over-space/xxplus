package com.xxplus.antlr4.test;

import com.xxplus.antlr4.number.NumberBaseListenerImpl;
import com.xxplus.antlr4.number.NumberLexer;
import com.xxplus.antlr4.number.NumberParser;
import com.xxplus.base.BaseTest;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.junit.Test;

/**
 * Created by admin on 2016-03-26.
 */
public class Antlr4Test extends BaseTest {

    @Test
    public void testAntlr4(){

        String sentence = "a,698,90,A";

        logger.info("sentence : {}", sentence);

        NumberLexer lexer = new NumberLexer(new ANTLRInputStream(sentence));

        CommonTokenStream tokens = new CommonTokenStream(lexer);

        NumberParser parser = new NumberParser(tokens);

        ParseTree tree = parser.stat();

        ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk(new NumberBaseListenerImpl(), tree);
    }

}
