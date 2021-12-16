package test.linq4jtest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.calcite.linq4j.Enumerable;
import org.apache.calcite.linq4j.EnumerableDefaults;
import org.apache.calcite.linq4j.Enumerator;
import org.apache.calcite.linq4j.Linq4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import test.model.Student;

/**
 * @author wangxiaohu
 * @version Id: Test6_G2_join.java, v0.1 2021年12月05日 19:13:21 wangxiaohu Exp $
 */
public class Test2_02_findElement_take {
    List<Student> students = new ArrayList();

    @Before
    public void before(){
        students.add(new Student("xiao",110));
        students.add(new Student("wang",120));
        students.add(new Student("zhang",130));
    }
    /**
     *
     * take: 截取子集合，如果超过索引长度不会报索引越界异常
     *  take(1) : 返回index[0~n-1]的子集合，即第1个
     *  take(2) : 返回index[0~n-2]的子集合，即第1、2个
     *
     * take(n)相当于subList(0, n-1)
     */
    @Test
    public void test_take(){
        //1. take
        List<Student> ll = Linq4j.asEnumerable(this.students)
            .take(2).toList();
        for (Student s:ll) {
            System.out.println(s.getName());
            //xiao
            //wang
        }
        Assert.assertEquals(2, ll.size());

        //2. java api subList
        ll = this.students.subList(0, 2);
        for (Student s:ll) {
            System.out.println(s.getName());
            //xiao
            //wang
        }
    }

    /**
     * takeWhile：根据过滤条件，这个方法没没什么用，它只从第0个元素匹配，而不会匹配第0个元素之后的元素
     */
    @Test
    public void test_takeWhile(){
        String string = Linq4j.asEnumerable(students)
            .takeWhile(x -> x.getName().contains("x")).select(x -> x.getName()).toList().toString();
        System.out.println("："+ string);
        //[xiao]
        Assert.assertEquals("[xiao]", string);

        string = Linq4j.asEnumerable(students)
            .takeWhile(x -> x.getName().contains("w")).select(x -> x.getName()).toList().toString();
        System.out.println("："+ string);
        //[]
        Assert.assertEquals("[]", string);

    }

}
