package com.xxplus.antlr4.old.chapter01.test;

import com.xxplus.antlr4.old.chapter01.gen.ArrayInitLexer;
import com.xxplus.antlr4.old.chapter01.gen.ArrayInitParser;
import com.xxplus.base.BaseTest;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.junit.Test;

/**
 * Created by admin on 2016-03-26.
 */
public class AntlrTest extends BaseTest{


    @Test
    public void testAntlr() {

        String sentence = "{99, 3, 451, 4, ' '}";

        ArrayInitLexer lexer = new ArrayInitLexer(new ANTLRInputStream(sentence));

        CommonTokenStream tokens = new CommonTokenStream(lexer);

        ArrayInitParser parser = new ArrayInitParser(tokens);

        ParseTree tree = parser.init();

        new ParseTreeWalker().walk(new ShortToUnicodeString(), tree);

        System.out.println();
    }
}
