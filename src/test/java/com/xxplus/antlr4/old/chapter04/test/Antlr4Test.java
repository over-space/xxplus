package com.xxplus.antlr4.old.chapter04.test;

import com.xxplus.antlr4.old.chapter04.gen.MyELangBaseListener;
import com.xxplus.antlr4.old.chapter04.gen.MyELangLexer;
import com.xxplus.antlr4.old.chapter04.gen.MyELangParser;
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

        String sentence = "1, 2, 3, 4, 5, 6";
        MyELangLexer lexer = new MyELangLexer(new ANTLRInputStream(sentence));

        CommonTokenStream tokens = new CommonTokenStream(lexer);

        MyELangParser parser = new MyELangParser(tokens);

        ParseTree tree = parser.list();

        ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk(new MyELangBaseListener(), tree);
    }

}
