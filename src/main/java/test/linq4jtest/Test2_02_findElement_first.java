package test.linq4jtest;

import org.apache.calcite.linq4j.Linq4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import test.model.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * @author wangxiaohu
 * @version Id: Test4_first.java, v0.1 2021年12月05日 19:13:21 wangxiaohu Exp $
 */
public class Test2_02_findElement_first {
    List<Student> students = new ArrayList();
    @Before
    public void before(){
        students.add(new Student(1,20,"小李"));
        students.add(new Student(1,18,"小王"));
        students.add(new Student(2,32,"小张"));
    }

    /**
     * first :获取第一个元素，如果集合为empty，抛出NoSuchElementException 异常
     */
    @Test
    public void test_first() {
        Student s = Linq4j.asEnumerable(students).first();
        boolean r = students.get(0).equals(s);
        Assert.assertTrue(r);

        //如果集合为empty，抛出NoSuchElementException 异常
        Exception e = null;
        try{
            String str = Linq4j.<String>emptyEnumerable().first();
        }catch (NoSuchElementException ex) {
            e = ex;
        }
        Assert.assertNotNull(e);
    }

    /**
     * first(Predicate predicate) :  返回符合predicate条件的第1个值
     */
    @Test
    public void test_first_predicate(){
        String[] str_arrays1 = {"xiao","zhang","zhong"};
        String result = Linq4j.asEnumerable(str_arrays1).first(x-> x.toString().startsWith("z"));
        Assert.assertEquals("zhang", result);
    }

    /**
     * firstOrDefault:不会抛出异常
     */
    @Test
    public void test_firstOrDefault(){
        String[] str_array1 = {};
        String result = Linq4j.asEnumerable(str_array1).firstOrDefault();
        Assert.assertNull(result);
    }
}
