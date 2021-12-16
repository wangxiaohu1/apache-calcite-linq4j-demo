package test.expressiontest;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;

import org.apache.calcite.linq4j.function.Function;
import org.apache.calcite.linq4j.function.Function1;
import org.apache.calcite.linq4j.tree.Expressions;
import org.apache.calcite.linq4j.tree.FunctionExpression;
import org.apache.calcite.linq4j.tree.FunctionExpression.Invokable;
import org.apache.calcite.linq4j.tree.ParameterExpression;
import org.junit.Test;

/**
 * @author wangxiaohu
 * @version Id: Test1.java, v0.1 2021年12月14日 19:37:48 wangxiaohu Exp $
 */
public class Test1 {
    @Test
    public void test_test_lambdaPrimitiveOneArgs(){
        ParameterExpression my_arg_expression = Expressions.parameter(Double.TYPE, "my_arg");

        FunctionExpression<Function<Double>> lambda_expression = Expressions.lambda(
            Expressions.add(
                my_arg_expression,
                Expressions.constant(2D)
            ),
            Arrays.asList(my_arg_expression)

        );

        System.out.println(lambda_expression);

        Invokable compile = lambda_expression.compile();
        double result = (double)compile.dynamicInvoke(1.2d);
        System.out.println(result);

        Function1<Double, Double> my_function1 = new Function1<Double, Double>() {
            @Override
            public Double apply(Double my_arg) {
                return my_arg + 2.0D;
            }
        };
        result = my_function1.apply(1.2d);
        System.out.println(result);
    }



    @Test
    public void test_lambdaPrimitiveTwoArgs() {
        //1. lamdba表达式的入数，2个参数，都是int类型，参数名称为key1, key2
        ParameterExpression paramExpr1 = Expressions.parameter(int.class, "key");
        ParameterExpression paramExpr2 = Expressions.parameter(int.class, "key2");

        List<ParameterExpression> parameterExpressions = Arrays.asList(paramExpr1, paramExpr2);

        //2. lambda 表达式，Expressions.lambda用来创建一个表达式
        FunctionExpression lambdaExpr = Expressions.lambda(
            //Expressions.block 创建一个方法body体，即{}
            Expressions.block(
                (Type) null,
                //Expressions.return_创建一个return
                Expressions.return_(
                null,
                    paramExpr1
                )
            ),
            parameterExpressions

        );

        // 输出表达式字符串
        System.out.println(Expressions.toString(lambdaExpr));

        //执行表达式
        int result = (int)lambdaExpr.compile().dynamicInvoke(1, 2);
        System.out.println(result);


    }
}
