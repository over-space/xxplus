package com.xxplus.antlr4.test;

import com.xxplus.antlr4.calc01.Calc01BaseListener;
import com.xxplus.antlr4.calc01.Calc01Lexer;
import com.xxplus.antlr4.calc01.Calc01Parser;
import com.xxplus.antlr4.calc02.Calc02BaseListener;
import com.xxplus.antlr4.calc02.Calc02Lexer;
import com.xxplus.antlr4.calc02.Calc02Parser;
import com.xxplus.antlr4.calc02.EvalVisitor;
import com.xxplus.antlr4.hello.HelloBaseListener;
import com.xxplus.antlr4.hello.HelloLexer;
import com.xxplus.antlr4.hello.HelloParser;
import com.xxplus.base.BaseTest;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by admin on 2016-03-26.
 */
public class Antlr4Test extends BaseTest {


    /**
     * @param args
     */
    public static void main(String[] args) throws IOException {
        InputStream is = System.in;

        ANTLRInputStream input = new ANTLRInputStream(is);
        Calc02Lexer lexer = new Calc02Lexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        Calc02Parser parser = new Calc02Parser(tokens);
        ParseTree tree = parser.prog();

        ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk(new Calc01BaseListener(), tree);

        // print LISP-style tree
        System.out.println(tree.toStringTree(parser));
    }


    @Test
    public void testCalc03(){
        String sentence = "4+1*((2+1)*2)+A";

        try {

            ANTLRInputStream input = new ANTLRInputStream(sentence);
            Calc02Lexer lexer = new Calc02Lexer(input);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            Calc02Parser parser = new Calc02Parser(tokens);
            ParseTree tree = parser.prog();

            EvalVisitor eval = new EvalVisitor();
            // 开始遍历语法分析树
            eval.visit(tree);
            System.out.println(tree.toStringTree(parser));
        }catch (Exception e){
            logger.error(e.getMessage(), e);
        }
    }

    @Test
    public void testCalc02(){
        String sentence = "4+1*((2+1)*2)+3";

        logger.info("sentence : {}", sentence);

        Calc02Lexer lexer = new Calc02Lexer(new ANTLRInputStream(sentence));

        CommonTokenStream tokens = new CommonTokenStream(lexer);

        Calc02Parser parser = new Calc02Parser(tokens);

        ParseTree tree = parser.stat();

        ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk(new Calc02BaseListener(), tree);
    }

    @Test
    public void testCalc01(){
        String sentence = "4+1*((2+1)*2) + param";

        logger.info("sentence : {}", sentence);

        Calc01Lexer lexer = new Calc01Lexer(new ANTLRInputStream(sentence));

        CommonTokenStream tokens = new CommonTokenStream(lexer);

        Calc01Parser parser = new Calc01Parser(tokens);

        ParseTree tree = parser.prog();

        ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk(new Calc01BaseListener(), tree);
    }

    @Test
    public void testHello(){

        String sentence = "hello world lee";

        logger.info("sentence : {}", sentence);

        HelloLexer lexer = new HelloLexer(new ANTLRInputStream(sentence));

        CommonTokenStream tokens = new CommonTokenStream(lexer);

        HelloParser parser = new HelloParser(tokens);

        ParseTree tree = parser.s();

        ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk(new HelloBaseListener(), tree);
    }
}
