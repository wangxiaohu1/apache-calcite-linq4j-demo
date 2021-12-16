package test.linq4jtest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.calcite.linq4j.Linq4j;
import org.apache.calcite.linq4j.Queryable;
import org.apache.calcite.linq4j.function.Function;
import org.apache.calcite.linq4j.function.Predicate1;
import org.apache.calcite.linq4j.function.Predicate2;
import org.apache.calcite.linq4j.tree.Expressions;
import org.apache.calcite.linq4j.tree.FunctionExpression;
import org.apache.calcite.linq4j.tree.ParameterExpression;
import org.junit.Before;
import org.junit.Test;
import test.model.Student;
import test.model.StudentN;

/**
 * @author wangxiaohu
 * @version Id: Test1_whereExpressions.java, v0.1 2021年12月05日 19:13:21 wangxiaohu Exp $
 */
public class Test2_02_filter_whereN_Expressions {
    List<StudentN> students = new ArrayList();

    @Before
    public void before(){
        StudentN student = new StudentN("小李",110);

        students.add(new StudentN("小李",110));
        students.add(new StudentN("小王",110));

        students.add(new StudentN("大张",110));
        students.add(new StudentN("大刘",110));

        students.add(new StudentN("老夏",130));
    }

    /**
     * whereN(
     *       FunctionExpression<? extends Predicate2<TSource, Integer>> predicate)
     *       通过FunctionExpression 表达式来查询
     */
    @Test
    public void test_whereN_lambdaExpression1(){
        ParameterExpression parameter1 = Expressions.parameter(StudentN.class);
        ParameterExpression parameter2 = Expressions.parameter(Integer.TYPE);
        List<ParameterExpression> parameterList = Arrays.asList(parameter1,parameter2);

        FunctionExpression functionExpression = Expressions.lambda(
            Predicate2.class,
            Expressions.andAlso(
                Expressions.equal(
                    Expressions.field(
                        parameter1,
                        StudentN.class,
                        "classNo"),
                    Expressions.constant(110)
                ),
                Expressions.lessThan(
                    parameter2,
                    Expressions.constant(2)
                )
            ),
            parameterList
        );

        Queryable<StudentN> where = Linq4j.asEnumerable(students)
            .asQueryable()
            .whereN(functionExpression);

        List<String> stringList = where.select(x -> x.getName() + "-" + x.getClassNo()).toList();
        System.out.println(stringList);
        //[小李-110, 小王-110]

    }
}
