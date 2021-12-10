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
public class Test1_03_where {

    List<Student> students = new ArrayList();

    @Before
    public void before(){
        students.add(new Student(1,20,"小李"));
        students.add(new Student(1,18,"小王"));
        students.add(new Student(2,32,"小张"));
    }


    @Test
    public void test_where(){
        List<String> nameList = Linq4j.asEnumerable(students).where(x -> x.getSex() == 1).select(x -> x.getName()).toList();
        assertEquals("[小李, 小王]", nameList.toString());
    }

    @Test
    public void test_where_index(){
        List<String> nameList = Linq4j.asEnumerable(students).where((student, index) -> index % 2 == 1)
            .select(x -> x.getName()).toList();
        assertEquals("[小王]", nameList.toString());
    }
}
