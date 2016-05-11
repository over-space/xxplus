// Generated from C:/Users/admin/workspace/xxplus/src/test/java/com/xxplus/antlr4\Calc01.g4 by ANTLR 4.5.1
package com.xxplus.antlr4.calc01;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link Calc01Parser}.
 */
public interface Calc01Listener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link Calc01Parser#prog}.
	 * @param ctx the parse tree
	 */
	void enterProg(Calc01Parser.ProgContext ctx);
	/**
	 * Exit a parse tree produced by {@link Calc01Parser#prog}.
	 * @param ctx the parse tree
	 */
	void exitProg(Calc01Parser.ProgContext ctx);
	/**
	 * Enter a parse tree produced by {@link Calc01Parser#stat}.
	 * @param ctx the parse tree
	 */
	void enterStat(Calc01Parser.StatContext ctx);
	/**
	 * Exit a parse tree produced by {@link Calc01Parser#stat}.
	 * @param ctx the parse tree
	 */
	void exitStat(Calc01Parser.StatContext ctx);
	/**
	 * Enter a parse tree produced by {@link Calc01Parser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(Calc01Parser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link Calc01Parser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(Calc01Parser.ExprContext ctx);
}