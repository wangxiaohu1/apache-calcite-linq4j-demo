package test.linq4jtest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Spliterator;

import org.apache.calcite.linq4j.Enumerator;
import org.apache.calcite.linq4j.Extensions;
import org.apache.calcite.linq4j.Linq4j;
import org.apache.calcite.linq4j.Queryable;
import org.apache.calcite.linq4j.function.Predicate1;
import org.apache.calcite.linq4j.tree.Expressions;
import org.apache.calcite.linq4j.tree.FunctionExpression;
import org.apache.calcite.linq4j.tree.ParameterExpression;
import org.junit.Test;
import test.model.Student;
import test.model.StudentN;

/**
 * @author wangxiaohu
 * @version Id: Test6_G2_join.java, v0.1 2021年12月05日 19:13:21 wangxiaohu Exp $
 */
public class Test1_09_asQueryable {

    /**
     * asQueryable:
     */
    @Test
    public void test(){
        List<StudentN> list = new ArrayList<>();
        list.add(new StudentN("小王",100));
        list.add(new StudentN("小王",120));
        list.add(new StudentN("小李",120));

        Queryable<StudentN> queryable = Linq4j.asEnumerable(list).asQueryable();

        ParameterExpression parameterExpression = Expressions.parameter(StudentN.class);

        FunctionExpression lambda = Expressions.lambda(
            Predicate1.class,
            Expressions.equal(
                Expressions.field(
                    parameterExpression,
                    StudentN.class,
                    "name"
                ),
                Expressions.constant("小王")
            ),
            parameterExpression
        );

        List<StudentN> list1 = queryable.where(lambda).toList();
        System.out.println(list1);
    }
}
