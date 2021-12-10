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
 * @version Id: Test0_foreach.java, v0.1 2021年12月05日 19:13:21 wangxiaohu Exp $
 */
public class Test0_foreach {
    List<Student> students = new ArrayList();

    @Before
    public void before(){
        students.add(new Student("xiao",110));
        students.add(new Student("wang",120));
        students.add(new Student("zhang",130));
    }

    @Test
    public void test_forEach1(){
        final List<String> l = new ArrayList<>();
        Linq4j.asEnumerable(students)
            .forEach(x-> l.add(x.getName()+":"+x.getAge()));
        System.out.println(l);
    }


    @Test
    public void test_forEach2(){
        StringBuffer buff = new StringBuffer("[");
        Linq4j.asEnumerable(students)
            .foreach( x -> buff.append(x.getName()).append(",")) ;
        buff.append("]");
        System.out.println(buff.toString());
        Assert.assertEquals("[xiao,wang,zhang,]", buff.toString());
    }

}
