package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.calcite.linq4j.Enumerable;
import org.apache.calcite.linq4j.Grouping;
import org.apache.calcite.linq4j.Linq4j;
import org.apache.calcite.linq4j.function.Function0;
import org.apache.calcite.linq4j.function.Functions;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import test.model.Student;

/**
 * @author wangxiaohu
 * @version Id: Test6_groupby.java, v0.1 2021年12月06日 09:47:58 wangxiaohu Exp $
 */
public class Test1_orderby {
    List<Student> students = new ArrayList();

    @Before
    public void before(){
        students.add(new Student(1,20,"小李"));
        students.add(new Student(1,18,"小王"));
        students.add(new Student(2,32,"小张"));
    }

    /**
     * orderBy：正序排序
     */
    @Test
    public void test_orderBy() {
        //1. linq4j实现排序
        String string = Linq4j.asEnumerable(students).orderBy(x -> x.getAge()).select(
            x -> x.getAge() + "-" + x.getName()).toList().toString();
        System.out.println(string);
        Assert.assertEquals("[18-小王, 20-小李, 32-小张]", string);

        //2. java8实现排序, 按照age倒序排列
        string = students.stream().sorted((o1, o2)->o1.getAge()-o2.getAge()).
            map(x->x.getAge()+"-"+x.getName())
            .collect(Collectors.toList()).toString();
        System.out.println(string);
        Assert.assertEquals("[18-小王, 20-小李, 32-小张]", string);

        //2. java8实现排序, 按照age倒序排列
        students.stream().sorted(Comparator.comparing(x->x.getAge())).
            map(x->x.getAge()+"-"+x.getName())
            .collect(Collectors.toList()).toString();
        System.out.println(string);
        Assert.assertEquals("[18-小王, 20-小李, 32-小张]", string);

        //2. java8实现排序 , 按照age倒序排列
        string = students.stream().sorted((o1, o2)->o2.getAge()-o1.getAge()).
            map(x->x.getAge()+"-"+x.getName())
            .collect(Collectors.toList()).toString();
        System.out.println(string);
        Assert.assertEquals("[32-小张, 20-小李, 18-小王]", string);

    }

    /**
     * orderByDescending：倒序排序
     */
    @Test
    public void test_orderByDescending() {
        String string = Linq4j.asEnumerable(students).orderByDescending(x -> x.getAge()).select(
            x -> x.getAge() + "-" + x.getName()).toList().toString();
        System.out.println(string);
        Assert.assertEquals("[32-小张, 20-小李, 18-小王]", string);
    }

    @Test
    public void test_orderByComparator() {
        String string = Linq4j.asEnumerable(students).
            orderBy(x -> x.getAge())
            .orderBy(x->x.getSex(), Collections.reverseOrder())
            .select(
            x -> x.getAge() + "-" + x.getSex() + "-" + x.getName()).toList().toString();
        System.out.println(string);
        //Assert.assertEquals("[32-小张, 20-小李, 18-小王]", string);
    }

    /**
     * reverse:翻转排序
     */
    @Test
    public void test_reverse(){
        //1. Linq4j实现翻转排序:reverse , 不会改变原来的list
        String string = Linq4j.asEnumerable(students).reverse().
            select(x->x.getAge() + "-" + x.getSex() + "-" + x.getName()).toList().toString();
        Assert.assertEquals("[32-2-小张, 18-1-小王, 20-1-小李]", string);

        //2. java实现翻转排序:reverse , Collections.reverse 会改变原来list的排序
        //Collections.reverse(students);

        //3. java实现翻转排序:reverse ,如果不影响原来的list排序，需要先copy list
        List<Student> copyList = new ArrayList<>(students);
        Collections.reverse(copyList);
        string = copyList.stream().map(x->x.getAge() + "-" + x.getSex() + "-" + x.getName()).collect(Collectors.toList()).toString();
        Assert.assertEquals("[32-2-小张, 18-1-小王, 20-1-小李]", string);
    }

}
