package test.linq4jtest;

import org.apache.calcite.linq4j.Enumerable;
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
 * @version Id: Test3_lookup.java, v0.1 2021年12月05日 19:13:21 wangxiaohu Exp $
 */
public class Test4_02_group_lookup {
    List<Student> students = new ArrayList();
    @Before
    public void before(){
        students.add(new Student(1,20,"小李"));
        students.add(new Student(1,18,"小王"));
        students.add(new Student(2,32,"小张"));
    }

    /**
     * 分组:ToLookUp与GroupBy的功能基本一样，但ToLookUp没有GroupBy那样得延迟执行机制，它是立即执行的
     */
    @Test
    public void test_lookup(){
        Lookup<Integer, Student> groupings = Linq4j.asEnumerable(students).toLookup(x -> x.getSex());
        for (Grouping<Integer, Student> g :groupings) {
            Integer key = g.getKey();
            int count = g.count();
            if(key == 1) {
                Assert.assertEquals(2,count);
            }else if(key == 2) {
                Assert.assertEquals(1,count);
            }else{
                Assert.fail("无法匹配");
            }
            Assert.assertEquals(2,groupings.size());
            System.out.println(key + " : " +  g.count() );
        }
    }

    @Test
    public void test_loopupSelector(){
        Lookup<Integer, String> groupings = Linq4j.asEnumerable(students).toLookup(x -> x.getSex(), x -> x.getName());
        Assert.assertEquals(2,groupings.size());
        for(Grouping<Integer ,String> g : groupings) {
            if(1 == g.getKey()) {
                Assert.assertTrue(g.contains("小李"));
                Assert.assertTrue(g.contains("小王"));
            }else if (2 == g.getKey()) {
                Assert.assertTrue(g.contains("小张"));
            }else{
                Assert.fail("无法匹配");
            }
        }
        String s = groupings.applyResultSelector((v1, v2) -> v1 + ":" + v2.count()).orderBy(Functions.identitySelector())
                .toList().toString(); // [1:2, 2:1]
        System.out.println(s);
    }
}
