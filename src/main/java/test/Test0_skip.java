package test;

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
public class Test0_skip {
    List<Student> students = new ArrayList();

    @Before
    public void before(){
        students.add(new Student("xiao",110));
        students.add(new Student("wang",120));
        students.add(new Student("zhang",130));
    }
    /**
     * take:获取list子集，相当于list.subList
     *  take方法不出出现索引越界问题
     *  subList索引越界抛出异常
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
        System.out.println("："+ string); //[xiao]
        Assert.assertEquals("[xiao]", string);

        string = Linq4j.asEnumerable(students)
            .takeWhile(x -> x.getName().contains("w")).select(x -> x.getName()).toList().toString();
        System.out.println("："+ string); //[]
        Assert.assertEquals("[]", string);

    }

    /**
     * skip(n):跳过前n个元素，1:前1个(跳过索引1的严元素)，2:前2个(跳过索引0,1的元素)
     */
    @Test
    public void test_skip(){
        String string = Linq4j.asEnumerable(students).skip(1).select(x -> x.getName()).toList().toString();
        System.out.println(string);//[wang, zhang]

        string = Linq4j.asEnumerable(students).skip(2).select(x -> x.getName()).toList().toString();
        System.out.println(string);//[zhang]
    }

}
