package com.xxplus.expression;

import com.xxplus.base.BaseTest;
import org.junit.Test;
import org.wltea.expression.ExpressionEvaluator;
import org.wltea.expression.datameta.Variable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2016-03-20.
 */
public class IKExpressionTest extends BaseTest{


    @Test
    public void testExpression(){
        String expression = "统计项 * (100 + 基本工资) + 奖金";

        List<Variable> variables = new ArrayList<>();
        variables.add(Variable.createVariable("统计项", 200));
        variables.add(Variable.createVariable("基本工资", 5000));
        variables.add(Variable.createVariable("奖金", "select count(*) from t_user"));
        variables.add(Variable.createVariable("delete from t_user", 200));

        Object result = ExpressionEvaluator.evaluate(expression, variables);
//        String result = ExpressionEvaluator.compile(expression, variables);

        logger.info("evaluate result : {}", result);
    }

}
