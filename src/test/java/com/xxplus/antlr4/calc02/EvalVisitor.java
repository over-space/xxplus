package com.xxplus.antlr4.calc02;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by admin on 2016-04-29.
 */
public class EvalVisitor extends Calc02BaseVisitor<Double> {

    private List<Calc02Parser.FuncContext> functionDefinitions = new LinkedList<Calc02Parser.FuncContext>();

    /** Remember local variables. Currently, this is only the function parameter. */
    private Map<String, Double> localMemory = new HashMap<String, Double>();

    /** Remember global variables set by =. */
    private Map<String, Double> globalMemory = new HashMap<String, Double>();

    /**
     * Find matching function definition for a function name and parameter
     * value. The first definition is returned where (a) the name matches
     * and (b) the formal parameter agrees if it is defined as constant.
     */
    private Calc02Parser.FuncContext findFunction(String name, Double paramValue) {
        for (Calc02Parser.FuncContext fn : functionDefinitions) {
            if (fn.ID(0).getText().equals(name)) {
                // Check whether parameter matches
                if (fn.parm.getType() == Calc02Parser.NUM
                        && !new Double(fn.parm.getText()).equals(paramValue)) {
                    // Constant in formalPar list does not match actual value -> no match.
                    continue;
                }
                // Parameter (value for NUM formal arg) as well as fct name agrees!
                return fn;
            }
        }
        return null;
    }

    /** Get value of name up call stack. */
    public Double getValue(String name) {
        Double value = localMemory.get(name);
        if (value != null ) {
            return value;
        }
        value = globalMemory.get(name);
        if (value != null ) {
            return value;
        }
        // not found in local memory or global memory
        System.err.println("undefined variable " + name);
        return 0d;
    }

    /** ID '(' expr ')' */
    @Override
    public Double visitCall(Calc02Parser.CallContext ctx) {
        String name = ctx.ID().getText();
        Double actualValue = visit(ctx.expr());

        Double value = 0d;
        Calc02Parser.FuncContext fn = findFunction(name, actualValue);
        if (fn == null) {
            System.err.println("No match found for " + name + "(" + actualValue + ")");
        } else {
            // Push parameter value into local memory
            String paramName = fn.parm.getText();
            Double prevValue = localMemory.put(paramName, actualValue);

            value = visit(fn.expr());

            // Restore local variable to previous values.
            localMemory.put(paramName, prevValue);
        }

        return value;
    }

    /** ID '(' parm ')' '=' expr */
    @Override
    public Double visitFunc(Calc02Parser.FuncContext ctx) {
        functionDefinitions.add(ctx);
        return 0d;
    }

    /** ID '=' expr */
    @Override
    public Double visitAssign(Calc02Parser.AssignContext ctx) {
        String id = ctx.ID().getText();  // id is left-hand side of '='
        double value = visit(ctx.expr());   // compute value of expression on right
        globalMemory.put(id, value);     // store it in global memory
        return value;
    }

    /** expr */
    @Override
    public Double visitPrintExpr(Calc02Parser.PrintExprContext ctx) {
        Double value = visit(ctx.expr()); // evaluate the expr child
        System.out.println(value);         // print the result
        return 0d;                          // return dummy value
    }

    /** NUM */
    @Override
    public Double visitNum(Calc02Parser.NumContext ctx) {
        return Double.valueOf(ctx.NUM().getText());
    }

    /** ('+'|'-') expr */
    @Override
    public Double visitUnary(Calc02Parser.UnaryContext ctx) {
        double value = visit(ctx.primary());
        if ("-".equals(ctx.sign.getText())) return value * -1;
        return value;
    }

    /** ID */
    @Override
    public Double visitId(Calc02Parser.IdContext ctx) {
        return getValue(ctx.ID().getText());
    }

    /** expr op=('%'|'*'|'/') expr */
    @Override
    public Double visitModMulDiv(Calc02Parser.ModMulDivContext ctx) {
        double left = visit(ctx.expr(0));  // get value of left subexpression
        double right = visit(ctx.expr(1)); // get value of right subexpression
        if (ctx.op.getType() == Calc02Parser.MOD) return left % right;
        if (ctx.op.getType() == Calc02Parser.MUL) return left * right;
        return left / right; // must be DIV
    }

    /** expr op=('+'|'-') expr */
    @Override
    public Double visitAddSub(Calc02Parser.AddSubContext ctx) {
        double left = visit(ctx.expr(0));  // get value of left subexpression
        double right = visit(ctx.expr(1)); // get value of right subexpression
        if ( ctx.op.getType() == Calc02Parser.ADD ) return left + right;
        return left - right; // must be SUB
    }

    /** '(' expr ')' */
    @Override
    public Double visitParens(Calc02Parser.ParensContext ctx) {
        return visit(ctx.expr()); // return child expr's value
    }
}
