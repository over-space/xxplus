// Generated from C:/Users/admin/workspace/xxplus/src/test/java/com/xxplus/antlr4\Calc02.g4 by ANTLR 4.5.1
package com.xxplus.antlr4.calc02;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link Calc02Parser}.
 */
public interface Calc02Listener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link Calc02Parser#prog}.
	 * @param ctx the parse tree
	 */
	void enterProg(Calc02Parser.ProgContext ctx);
	/**
	 * Exit a parse tree produced by {@link Calc02Parser#prog}.
	 * @param ctx the parse tree
	 */
	void exitProg(Calc02Parser.ProgContext ctx);
	/**
	 * Enter a parse tree produced by the {@code printExpr}
	 * labeled alternative in {@link Calc02Parser#stat}.
	 * @param ctx the parse tree
	 */
	void enterPrintExpr(Calc02Parser.PrintExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code printExpr}
	 * labeled alternative in {@link Calc02Parser#stat}.
	 * @param ctx the parse tree
	 */
	void exitPrintExpr(Calc02Parser.PrintExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code assign}
	 * labeled alternative in {@link Calc02Parser#stat}.
	 * @param ctx the parse tree
	 */
	void enterAssign(Calc02Parser.AssignContext ctx);
	/**
	 * Exit a parse tree produced by the {@code assign}
	 * labeled alternative in {@link Calc02Parser#stat}.
	 * @param ctx the parse tree
	 */
	void exitAssign(Calc02Parser.AssignContext ctx);
	/**
	 * Enter a parse tree produced by the {@code func}
	 * labeled alternative in {@link Calc02Parser#stat}.
	 * @param ctx the parse tree
	 */
	void enterFunc(Calc02Parser.FuncContext ctx);
	/**
	 * Exit a parse tree produced by the {@code func}
	 * labeled alternative in {@link Calc02Parser#stat}.
	 * @param ctx the parse tree
	 */
	void exitFunc(Calc02Parser.FuncContext ctx);
	/**
	 * Enter a parse tree produced by the {@code blank}
	 * labeled alternative in {@link Calc02Parser#stat}.
	 * @param ctx the parse tree
	 */
	void enterBlank(Calc02Parser.BlankContext ctx);
	/**
	 * Exit a parse tree produced by the {@code blank}
	 * labeled alternative in {@link Calc02Parser#stat}.
	 * @param ctx the parse tree
	 */
	void exitBlank(Calc02Parser.BlankContext ctx);
	/**
	 * Enter a parse tree produced by the {@code call}
	 * labeled alternative in {@link Calc02Parser#expr}.
	 * @param ctx the parse tree
	 */
	void enterCall(Calc02Parser.CallContext ctx);
	/**
	 * Exit a parse tree produced by the {@code call}
	 * labeled alternative in {@link Calc02Parser#expr}.
	 * @param ctx the parse tree
	 */
	void exitCall(Calc02Parser.CallContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ModMulDiv}
	 * labeled alternative in {@link Calc02Parser#expr}.
	 * @param ctx the parse tree
	 */
	void enterModMulDiv(Calc02Parser.ModMulDivContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ModMulDiv}
	 * labeled alternative in {@link Calc02Parser#expr}.
	 * @param ctx the parse tree
	 */
	void exitModMulDiv(Calc02Parser.ModMulDivContext ctx);
	/**
	 * Enter a parse tree produced by the {@code prim}
	 * labeled alternative in {@link Calc02Parser#expr}.
	 * @param ctx the parse tree
	 */
	void enterPrim(Calc02Parser.PrimContext ctx);
	/**
	 * Exit a parse tree produced by the {@code prim}
	 * labeled alternative in {@link Calc02Parser#expr}.
	 * @param ctx the parse tree
	 */
	void exitPrim(Calc02Parser.PrimContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AddSub}
	 * labeled alternative in {@link Calc02Parser#expr}.
	 * @param ctx the parse tree
	 */
	void enterAddSub(Calc02Parser.AddSubContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AddSub}
	 * labeled alternative in {@link Calc02Parser#expr}.
	 * @param ctx the parse tree
	 */
	void exitAddSub(Calc02Parser.AddSubContext ctx);
	/**
	 * Enter a parse tree produced by the {@code unary}
	 * labeled alternative in {@link Calc02Parser#expr}.
	 * @param ctx the parse tree
	 */
	void enterUnary(Calc02Parser.UnaryContext ctx);
	/**
	 * Exit a parse tree produced by the {@code unary}
	 * labeled alternative in {@link Calc02Parser#expr}.
	 * @param ctx the parse tree
	 */
	void exitUnary(Calc02Parser.UnaryContext ctx);
	/**
	 * Enter a parse tree produced by the {@code num}
	 * labeled alternative in {@link Calc02Parser#primary}.
	 * @param ctx the parse tree
	 */
	void enterNum(Calc02Parser.NumContext ctx);
	/**
	 * Exit a parse tree produced by the {@code num}
	 * labeled alternative in {@link Calc02Parser#primary}.
	 * @param ctx the parse tree
	 */
	void exitNum(Calc02Parser.NumContext ctx);
	/**
	 * Enter a parse tree produced by the {@code id}
	 * labeled alternative in {@link Calc02Parser#primary}.
	 * @param ctx the parse tree
	 */
	void enterId(Calc02Parser.IdContext ctx);
	/**
	 * Exit a parse tree produced by the {@code id}
	 * labeled alternative in {@link Calc02Parser#primary}.
	 * @param ctx the parse tree
	 */
	void exitId(Calc02Parser.IdContext ctx);
	/**
	 * Enter a parse tree produced by the {@code parens}
	 * labeled alternative in {@link Calc02Parser#primary}.
	 * @param ctx the parse tree
	 */
	void enterParens(Calc02Parser.ParensContext ctx);
	/**
	 * Exit a parse tree produced by the {@code parens}
	 * labeled alternative in {@link Calc02Parser#primary}.
	 * @param ctx the parse tree
	 */
	void exitParens(Calc02Parser.ParensContext ctx);
}