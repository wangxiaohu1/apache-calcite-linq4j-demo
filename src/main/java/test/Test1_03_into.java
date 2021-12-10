package test;

import java.util.ArrayList;
import java.util.List;

import org.apache.calcite.linq4j.Linq4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import test.model.Student;

import static org.junit.Assert.assertEquals;

/**
 * Linq4j
 * @author wangxiaohu
 * @version Id: Test1.java, v0.1 2021年10月29日 15:04:13 wangxiaohu Exp $
 */
public class Test1_03_into {

    List<Student> students = new ArrayList();

    @Before
    public void before(){
        students.add(new Student(1,20,"小李"));
        students.add(new Student(1,18,"小王"));
        students.add(new Student(2,32,"小张"));
    }

    /**
     * 追加集合到指定的list
     */
    @Test
    public void test_into(){
        List<Student> list = new ArrayList<>();
        list.add(new Student(1, 20, "xiao"));

        List<Student> into = Linq4j.asEnumerable(students)
            .into(list);

        String string = Linq4j.asEnumerable(into).select(x -> x.getName()).toList().toString();
        Assert.assertEquals("[xiao, 小李, 小王, 小张]", string);

    }
}
