// Generated from C:/Users/admin/workspace/xxplus/src/test/java/com/xxplus/antlr4\Number.g4 by ANTLR 4.5.1
package com.xxplus.antlr4.number;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class provides an empty implementation of {@link NumberListener},
 * which can be extended to create a listener which only needs to handle a subset
 * of the available methods.
 */
public class NumberBaseListenerImpl extends NumberBaseListener {

	private Logger logger = LoggerFactory.getLogger(NumberBaseListenerImpl.class);

	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void enterStat(NumberParser.StatContext ctx) {
		logger.info("enterStat : {}", ctx.getText());
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void exitStat(NumberParser.StatContext ctx) {
		logger.info("exitStat : {}", ctx.getText());
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void enterExpr(NumberParser.ExprContext ctx) {
		logger.info("enterExpr : {}", ctx.getText());
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void exitExpr(NumberParser.ExprContext ctx) {
		logger.info("exitExpr : {}", ctx.getText());
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void enterEveryRule(ParserRuleContext ctx) {
		logger.info("enterEveryRule : {}", ctx.getText());
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void exitEveryRule(ParserRuleContext ctx) {
		logger.info("exitEveryRule : {}", ctx.getText());
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void visitTerminal(TerminalNode node) {
		logger.info("visitTerminal : {}", node.getText());
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void visitErrorNode(ErrorNode node) {
		logger.info("visitErrorNode : {}", node.getText());
	}
}