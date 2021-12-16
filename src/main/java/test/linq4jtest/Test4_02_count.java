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
public class Test4_02_count {

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
     * count:条数总计
     */
    @Test
    public void test_count(){
        int count = Linq4j.asEnumerable(classess).count();
        assertEquals(3,count);
    }

    @Test
    public void test_countFilter(){
        int count = Linq4j.asEnumerable(classess).count(x -> x.getStudents().size() > 0);
        assertEquals(2,count);
    }
    
    @Test
    public void test_longCount(){
        long count = Linq4j.asEnumerable(classess).longCount();
        assertEquals(3,count);

        long count2 = Linq4j.asEnumerable(classess).longCount(x -> x.getStudents().size() > 0);
        assertEquals(2,count2);
    }
}
