package test.linq4jtest;

import java.util.ArrayList;
import java.util.List;

import org.apache.calcite.linq4j.Linq4j;
import org.apache.calcite.linq4j.function.Function0;
import org.apache.calcite.linq4j.function.Function1;
import org.apache.calcite.linq4j.function.IntegerFunction1;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import test.model.Classes;
import test.model.Student;

import static org.junit.Assert.assertEquals;

/**
 *
 * aggregate:
 *     aggregate这个语法可以做一些复杂的聚合运算，例如累计求和，求最小值，求最大值，请均值，累计求乘积，拼接字符串。它接受2个参数，
 *     一般第一个参数是称为累积数（默认情况下等于第一个值），而第二个代表了下一个元素值。
 *     第一次计算之后，计算的结果会替换掉第一个参数，继续参与下一次计算
 * @author wangxiaohu
 * @version Id: Test1.java, v0.1 2021年10月29日 15:04:13 wangxiaohu Exp $
 */
public class Test1_06_aggregate {

    List<Student> students = new ArrayList();

    @Before
    public void before(){
        students.add(new Student(1,20,"小李"));
        students.add(new Student(1,18,"小王"));
        students.add(new Student(2,32,"小张"));
    }

    /**
     *
     * TSource aggregate(Function2<@Nullable TSource, TSource, TSource> func)
     * 返回值，第1个入参，第2个入参都是 同一类型
     *
     * 案例：获取集合中年纪最小值的学生
     */
    @Test
    public void test_aggregate_1(){
        //1. 从集合中获取性别最小的对象
        Student student1 = Linq4j.asEnumerable(students)
            .aggregate(
                //current:当前的元素
                //next:下一个元素
                (current, next) -> current.getAge()<=next.getAge()?current:next
            );
        Assert.assertEquals(18, student1.getAge());

        //2. 求和值
        int add = Linq4j.asEnumerable(students)
            .select(x->x.getAge())
            .aggregate(
                (current, next) ->  ( current + next )
            );
        Assert.assertEquals(70,add);
    }

    /**
     *
     * aggregate(TAccumulate seed,
     *       Function2<TAccumulate, TSource, @PolyNull TAccumulate> func)
     *   seed: 初始值
     *   func: 有2个参数，其中第1个参数是最后一次/上一次 累加的值 ; 第2个参数下一个值
     *
     * 案例：拼接字符串
     */
    @Test
    public void test_aggregate_2(){

        //1. 拼接字符串
        String str = Linq4j.asEnumerable(students).select(x->x.getName()).
            aggregate(
                //初始值
                null,
                //addValue:累积值；next:下一个元素值
                (addValue, next) -> addValue == null ? next : addValue + "," + next);

        assertEquals("小李,小王,小张", str);


        //2. 拼接字符串，并设置初始值
        String str2 = Linq4j.asEnumerable(students).
            select(x->x.getName())
            .aggregate(
                //初始值
                "12",
                //lastAggregateValue:最后一次/上一次 累计的值
                //currentElement:当前的元素值
                (addValue, next) -> addValue == null ? next : addValue + "," + next
            );
        assertEquals("12,小李,小王,小张", str2);

    }

    /**
     * aggregate(TAccumulate seed,
     *       Function2<TAccumulate, TSource, TAccumulate> func,
     *       Function1<TAccumulate, TResult> selector)
     *
     *  处理list，一般用于拼接元素的属性值
     *     seed：用于拼接字段的初始化处理器
     *     func: 拼接处理,接收2个参数，参数1是累积值，才是2是下个元素值
     *     selector:对拼接最终结果处理
     */
    @Test
    public void test_aggregate3() {
        //从studentList中获取age最小的student
        String str = Linq4j.asEnumerable(students)
            .aggregate(
                //初始化的值
                ( (Function0<String>) () -> null).apply(),
                //拼接处理
                (addValue, next)->addValue==null?next.getName():addValue + "+"+next.getName(),
                //对拼接最终结果处理
                (finalValue)-> "<bean>" + finalValue + "</bean>"
            );
        Assert.assertEquals("<bean>小李+小王+小张</bean>",str);
    }

}
