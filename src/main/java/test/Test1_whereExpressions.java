package test;

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

/**
 * @author wangxiaohu
 * @version Id: Test1_whereExpressions.java, v0.1 2021年12月05日 19:13:21 wangxiaohu Exp $
 */
public class Test1_whereExpressions {
    List<Student> students = new ArrayList();

    @Before
    public void before(){
        Student student = new Student("小李",110);

        students.add(new Student("小李",110));
        students.add(new Student("小王",110));

        students.add(new Student("大张",120));
        students.add(new Student("大刘",120));

        students.add(new Student("老夏",130));
    }

    @Test
    public void test(){
        ParameterExpression parameter = Expressions.parameter(Student.class);

        FunctionExpression fe = (FunctionExpression<? extends Predicate1<Student>>)Expressions.lambda(
            Predicate1.class,
            Expressions.equal(
                Expressions.field(
                    parameter,
                    Student.class,
                    "classNo"),
                Expressions.constant(110)),
            parameter);

        Linq4j.asEnumerable(students)
            .asQueryable()
            .where(fe);




    }

    @Test
    public void test2(){
        List<Student> where = Linq4j.asEnumerable(students)
            .asQueryable()
            .where(Expressions.lambda(v1 -> v1.getClassNo() == 110)).toList();

        for (Student s : where) {
            System.out.println(s.getClassNo());
        }
    }
}
