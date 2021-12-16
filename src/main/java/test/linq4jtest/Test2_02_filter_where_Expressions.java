package test.linq4jtest;

import java.util.ArrayList;
import java.util.List;

import org.apache.calcite.linq4j.Linq4j;
import org.apache.calcite.linq4j.Queryable;
import org.apache.calcite.linq4j.function.Predicate1;
import org.apache.calcite.linq4j.tree.Expressions;
import org.apache.calcite.linq4j.tree.FunctionExpression;
import org.apache.calcite.linq4j.tree.ParameterExpression;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import test.model.Classes;
import test.model.Student;
import test.model.StudentN;

/**
 * @author wangxiaohu
 * @version Id: Test1_whereExpressions.java, v0.1 2021年12月05日 19:13:21 wangxiaohu Exp $
 */
public class Test2_02_filter_where_Expressions {
    List<StudentN> students = new ArrayList();

    @Before
    public void before(){
        StudentN student = new StudentN("小李",110);

        students.add(new StudentN("小李",110));
        students.add(new StudentN("小王",110));

        students.add(new StudentN("大张",120));
        students.add(new StudentN("大刘",120));

        students.add(new StudentN("老夏",130));
    }

    /**
     * where(
     *       FunctionExpression<? extends Predicate1<TSource>> predicate) : 通过FunctionExpression 表达式来查询
     */
    @Test
    public void test_where_lambdaExpression1(){
        ParameterExpression parameter = Expressions.parameter(Student.class);

        FunctionExpression functionExpression = Expressions.lambda(
            Predicate1.class,
            Expressions.equal(
                Expressions.field(
                    parameter,
                    StudentN.class,
                    "classNo"),
                Expressions.constant(110)),
            parameter);

        Queryable<StudentN> where = Linq4j.asEnumerable(students)
            .asQueryable()
            .where(functionExpression);

        List<String> stringList = where.select(x -> x.getName() + "-" + x.getClassNo()).toList();
        System.out.println(stringList);
        //[小李-110, 小王-110]

    }

    @Test
    public void test_where_lambdaExpression2(){
        List<String> stringList = Linq4j.asEnumerable(students)
            .asQueryable()
            .where(Expressions.lambda(v1 -> v1.getClassNo() == 110))
            .select(x -> x.getName() + "-" + x.getClassNo())
            .toList();
        System.out.println(stringList);
        //[小李-110, 小王-110]
    }
}
