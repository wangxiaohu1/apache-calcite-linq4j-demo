package test;

import org.apache.calcite.linq4j.Grouping;
import org.apache.calcite.linq4j.Linq4j;
import org.apache.calcite.linq4j.Lookup;
import org.apache.calcite.linq4j.function.Functions;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import test.model.Classes;
import test.model.Student;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangxiaohu
 * @version Id: Test4_contains.java, v0.1 2021年12月05日 19:13:21 wangxiaohu Exp $
 */
public class Test6_E_contains {
    List<Student> students = new ArrayList();
    @Before
    public void before(){
        students.add(new Student(1,20,"小李"));
        students.add(new Student(1,18,"小王"));
        students.add(new Student(2,32,"小张"));
    }
    @Test
    public void test_lookup() {
        Student source = students.get(0);
        Student clone = new Student(source.getSex(),source.getAge(),source.getName());
        //Assert.assertEquals(source,clone);
        boolean r = Linq4j.asEnumerable(students).contains(source);
        Assert.assertTrue(r);

        r = Linq4j.asEnumerable(students).contains(clone);
        Assert.assertTrue(r);
    }
}
