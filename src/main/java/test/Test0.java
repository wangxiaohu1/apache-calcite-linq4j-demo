package test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.calcite.linq4j.Linq4j;
import org.junit.Before;
import org.junit.Test;
import test.model.Classes;
import test.model.Student;

import static org.junit.Assert.assertEquals;

/**
 * @author wangxiaohu
 * @version Id: Test0.java, v0.1 2021年11月06日 17:56:05 wangxiaohu Exp $
 */
public class Test0 {
    List<Student> students = new ArrayList();

    List<Classes> classess = new ArrayList();

    @Before
    public void before(){
        students.add(new Student(1,20,"小李"));
        students.add(new Student(1,18,"小王"));
        students.add(new Student(2,32,"小张"));



        List<Student> s = new ArrayList<>();
        s.add(new Student(1,20,"daiwei"));
        s.add(new Student(1,20,"amay"));
        classess.add(new Classes("5年级",10,students));
        classess.add(new Classes("2年级",11,new ArrayList()));
        classess.add(new Classes("6年级",9,s));

    }

    @Test
    public void test() {

        //使用java8 stream处理集合
        List<String> nameList2 = students.stream().filter(x -> x.getSex() == 1).
            map(Student::getName).
            sorted((o1, o2)->o2.compareTo(o1)).collect(
            Collectors.toList());
        assertEquals("[小王, 小李]",nameList2.toString());


        //使用linq4j处理结合
        List<String> nameList = Linq4j.asEnumerable(students).where(x -> x.getSex() == 1)
            .select(x->x.getName())
            .orderByDescending(String::toString).toList();

        assertEquals("[小王, 小李]",nameList2.toString());
    }
}
