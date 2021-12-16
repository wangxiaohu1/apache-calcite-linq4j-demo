package test.linq4jtest;

import java.util.ArrayList;
import java.util.List;

import org.apache.calcite.linq4j.Linq4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import test.model.Student;

/**
 * @author wangxiaohu
 * @version Id: Test6_G2_join.java, v0.1 2021年12月05日 19:13:21 wangxiaohu Exp $
 */
public class Test2_02_findElement_skip {
    List<Student> students = new ArrayList();

    @Before
    public void before(){
        students.add(new Student("xiao",110));
        students.add(new Student("wang",120));
        students.add(new Student("zhang",130));
    }

    /**
     * skip: 截取子集合，如果超过索引长度不会报索引越界异常
     *   skip(1) : 返回index[1~size]的子集合
     *   skip(2) : 返回index[2~size的子集合]
     * skip(n)相当于subList(n, size)
     *
     */
    @Test
    public void test_skip(){
        String string = Linq4j.asEnumerable(students).skip(1).select(x -> x.getName()).toList().toString();
        System.out.println(string);
        //[wang, zhang]

        string = Linq4j.asEnumerable(students).skip(2).select(x -> x.getName()).toList().toString();
        System.out.println(string);
        //[zhang]

        string = Linq4j.asEnumerable(students).skip(4).select(x -> x.getName()).toList().toString();
        System.out.println(string);
        //[]
    }

}
