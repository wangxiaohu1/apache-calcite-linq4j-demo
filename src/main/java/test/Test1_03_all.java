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
public class Test1_03_all {

    List<Student> students = new ArrayList();
    @Before
    public void before(){
        students.add(new Student(1,20,"小李"));
        students.add(new Student(1,18,"小王"));
        students.add(new Student(2,32,"小张"));
    }

    /**
     * all:判断是否全部符合条件
     */
    @Test
    public void test_allFilter(){
        boolean all = Linq4j.asEnumerable(students).all(x -> x.getAge() > 10);
        boolean all2 = Linq4j.asEnumerable(students).all(x -> x.getAge() > 20);
        assertTrue(all);
        assertFalse(all2);
    }
}
