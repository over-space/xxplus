// Generated from C:/Users/admin/workspace/xxplus/src/test/java/com/xxplus/antlr4\Calc02.g4 by ANTLR 4.5.1
package com.xxplus.antlr4.calc02;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link Calc02Parser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface Calc02Visitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link Calc02Parser#prog}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProg(Calc02Parser.ProgContext ctx);
	/**
	 * Visit a parse tree produced by the {@code printExpr}
	 * labeled alternative in {@link Calc02Parser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrintExpr(Calc02Parser.PrintExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code assign}
	 * labeled alternative in {@link Calc02Parser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssign(Calc02Parser.AssignContext ctx);
	/**
	 * Visit a parse tree produced by the {@code func}
	 * labeled alternative in {@link Calc02Parser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunc(Calc02Parser.FuncContext ctx);
	/**
	 * Visit a parse tree produced by the {@code blank}
	 * labeled alternative in {@link Calc02Parser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlank(Calc02Parser.BlankContext ctx);
	/**
	 * Visit a parse tree produced by the {@code call}
	 * labeled alternative in {@link Calc02Parser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCall(Calc02Parser.CallContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ModMulDiv}
	 * labeled alternative in {@link Calc02Parser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitModMulDiv(Calc02Parser.ModMulDivContext ctx);
	/**
	 * Visit a parse tree produced by the {@code prim}
	 * labeled alternative in {@link Calc02Parser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrim(Calc02Parser.PrimContext ctx);
	/**
	 * Visit a parse tree produced by the {@code AddSub}
	 * labeled alternative in {@link Calc02Parser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAddSub(Calc02Parser.AddSubContext ctx);
	/**
	 * Visit a parse tree produced by the {@code unary}
	 * labeled alternative in {@link Calc02Parser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnary(Calc02Parser.UnaryContext ctx);
	/**
	 * Visit a parse tree produced by the {@code num}
	 * labeled alternative in {@link Calc02Parser#primary}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNum(Calc02Parser.NumContext ctx);
	/**
	 * Visit a parse tree produced by the {@code id}
	 * labeled alternative in {@link Calc02Parser#primary}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitId(Calc02Parser.IdContext ctx);
	/**
	 * Visit a parse tree produced by the {@code parens}
	 * labeled alternative in {@link Calc02Parser#primary}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParens(Calc02Parser.ParensContext ctx);
}