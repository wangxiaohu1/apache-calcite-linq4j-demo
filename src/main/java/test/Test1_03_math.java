package test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.calcite.linq4j.Linq4j;
import org.apache.calcite.linq4j.function.Function1;
import org.apache.calcite.linq4j.function.IntegerFunction1;
import org.junit.Before;
import org.junit.Test;
import test.model.Classes;
import test.model.Student;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Linq4j
 * @author wangxiaohu
 * @version Id: Test1.java, v0.1 2021年10月29日 15:04:13 wangxiaohu Exp $
 */
public class Test1_03_math {

    List<Student> students = new ArrayList();

    List<Classes> classess = new ArrayList();

    @Before
    public void before(){
        students.add(new Student(1,20,"小李"));
        students.add(new Student(1,18,"小王"));
        students.add(new Student(2,32,"小张"));


        List<Student> s = new ArrayList<>();
        s.add(new Student(1,20,"daiwei"));
        s.add(new Student(1,20,"amay"));
        classess.add(new Classes("5年级",10,students));
        classess.add(new Classes("2年级",11,new ArrayList()));
        classess.add(new Classes("6年级",9,s));

    }

    /**
     * average:求平均数
     */
    @Test
    public void test_average(){
        IntegerFunction1<Classes> func = c -> c.getNo();
        int average = Linq4j.asEnumerable(classess).average(func);
        assertEquals(10,average);
    }

    /**
     * min:最小值
     */
    @Test
    public void test_min(){
        int min = Linq4j.asEnumerable(classess).select(x -> x.getNo()).min();
        assertEquals(9,min);
    }

    /**
     * min(IntegerFunction1 selector)
     * min(Function1 selector):求最小值
     */
    @Test
    public void test_minSelector(){
        IntegerFunction1<Classes> func = c->c.getNo();
        int min = Linq4j.asEnumerable(classess).min(func);
        assertEquals(9,min);

        Function1<Classes,Integer> func2 = c->c.getNo();
        int min2 = Linq4j.asEnumerable(classess).min(func2);
        assertEquals(9,min2);
    }


    /**
     * max()
     * max(IntegerFunction1 selector)
     * max(Function1 selector):求最小值
     */
    @Test
    public void test_max() {
        int max = Linq4j.asEnumerable(classess).select(x -> x.getNo()).max();
        assertEquals(11, max);

        IntegerFunction1<Classes> func = c -> c.getNo();
        int max2 = Linq4j.asEnumerable(classess).max(func);
        assertEquals(11, max2);

        Function1<Classes, Integer> func2 = c -> c.getNo();
        int max3 = Linq4j.asEnumerable(classess).max(func2);
        assertEquals(11, max3);
    }
}
