package test.linq4jtest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.calcite.linq4j.Enumerable;
import org.apache.calcite.linq4j.Linq4j;
import org.apache.calcite.linq4j.function.EqualityComparer;
import org.apache.calcite.linq4j.function.Function0;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import test.model.Student;

/**
 * @author wangxiaohu
 * @version Id: Test6_F_distinct.java, v0.1 2021年12月05日 19:13:21 wangxiaohu Exp $
 */
public class Test4_02_distinct {
    @Test
    public void test_distinct() {
        List<String> strings = Arrays.asList("a", "b", "a");
        String distinct = Linq4j.asEnumerable(strings).distinct().toList().toString();
        Assert.assertEquals("[a, b]",distinct);
    }

    /**
     * distinct: 元素去重
     */
    @Test
    public void test_distinct_equalityComparer(){
        List<Student> list = new ArrayList<>();
        list.add(new Student(1,20,"小王"));
        list.add(new Student(1,25,"小王"));
        list.add(new Student(2,30,"小李"));

        List<Student> r = Linq4j.asEnumerable(list).distinct(new EqualityComparer<Student>() {
            @Override
            public boolean equal(Student v1, Student v2) {
                return v1.getName().equals(v2.getName());
            }

            @Override
            public int hashCode(Student student) {
                return student.getName().hashCode();
            }
        }).toList();

        String ct = "";
        for(Student s : r) {
            System.out.println(s.getName() + "-" + s.getAge() + "-" + s.getSex());
            //小李-30-2
            //小王-20-1
        }
    }
}
