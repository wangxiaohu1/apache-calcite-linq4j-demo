package test.linq4jtest;

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
public class Test2_01_filter_any {

    List<Student> students = new ArrayList();

    @Before
    public void before(){
        students.add(new Student(1,20,"小李"));
        students.add(new Student(1,18,"小王"));
        students.add(new Student(2,32,"小张"));
    }


    /**
     * any:判断集合是否有元素，有则为true
     */
    @Test
    public void test_any(){
        boolean any = Linq4j.asEnumerable(students).any();

        List<Student> emptys = Collections.emptyList();
        boolean any2 = Linq4j.asEnumerable(emptys).any();
        assertTrue(any);
        assertFalse(any2);
    }

    /**
     * any(predicate):判断否有符合过滤器指定条件的元素，有则为true
     */
    @Test
    public void test_anyFilter(){
        boolean any = Linq4j.asEnumerable(students).any(x -> "小王".equals(x.getName()));
        boolean any2 = Linq4j.asEnumerable(students).any(x -> "sim".equals(x.getName()));
        assertTrue(any);
        assertFalse(any2);
    }
}
