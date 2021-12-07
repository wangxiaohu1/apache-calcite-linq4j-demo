package test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.calcite.linq4j.Enumerable;
import org.apache.calcite.linq4j.Grouping;
import org.apache.calcite.linq4j.Linq4j;
import org.apache.calcite.linq4j.function.Function0;
import org.apache.calcite.linq4j.function.Functions;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import test.model.Student;

/**
 * @author wangxiaohu
 * @version Id: Test6_aggregate.java, v0.1 2021年12月06日 09:47:58 wangxiaohu Exp $
 */
public class Test5_aggregate {
    List<Student> students = new ArrayList();

    @Before
    public void before(){
        students.add(new Student(1,20,"小李"));
        students.add(new Student(1,18,"小王"));
        students.add(new Student(2,32,"小张"));
    }

    /**
     * aggregate:每次可以对list结合中相邻的两个元素迭代处理
     *
     * 比如：从studentList中获取age最小的student
     */
    @Test
    public void test_aggregate1() {
        //从studentList中获取age最小的student
         Student stu = Linq4j.asEnumerable(students)
            .aggregate(
                (v0, v1) -> {
                    if (v0.getAge() < v1.getAge()) {
                        return v0;
                    } else {
                        return v1;
                    }
                }
            );
        System.out.println(stu.getName());
        Assert.assertEquals("小王",stu.getName());
    }

    /**
     * aggregate(TAccumulate seed,
     *       Function2<TAccumulate, TSource, TAccumulate> func,
     *       Function1<TAccumulate, TResult> selector)
     *
     *  处理list，一般用于拼接元素的属性值
     *     seed：用于拼接字段的初始化处理器
     *     func: 拼接处理
     *     selector:对拼接最终结果处理
     */
    @Test
    public void test_aggregate3() {
        //从studentList中获取age最小的student
        String str = Linq4j.asEnumerable(students)
            .aggregate(
                ( (Function0<String>) () -> null).apply(), //first_init_appendstr
                (previous_appendstr, student)->previous_appendstr==null?student.getName():previous_appendstr + "+"+student.getName(),
                (final_appendstr)-> "<bean>" + final_appendstr + "</bean>"
            );
        System.out.println(str); //<bean>小李+小王+小张</bean>
        Assert.assertEquals("<bean>小李+小王+小张</bean>",str);
    }
}
