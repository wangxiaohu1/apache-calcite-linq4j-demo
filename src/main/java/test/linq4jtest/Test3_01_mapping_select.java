package test.linq4jtest;
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
public class Test3_01_mapping_select {

    List<Student> students = new ArrayList();

    @Before
    public void before(){
        students.add(new Student(1,20,"小李"));
        students.add(new Student(1,18,"小王"));
        students.add(new Student(2,32,"小张"));
    }

    /**
     * select: 从集合中将对象元素中的字段提取出来
     *  select(Function1<TSource, TResult> selector): Function1有1个参数，是元素
     */
    @Test
    public void test_select_1(){
        List<String> nameList = Linq4j.asEnumerable(students)
            .select(x -> x.getName()).toList();
        assertEquals("[小李, 小王, 小张]", nameList.toString());
    }

    /**
     * select: 从集合中将对象元素中的字段提取出来
     *  select(Function2<TSource, Integer, TResult> selector): Function2有两个参数，第1个参数是元素，第2个参数索引
     */
    @Test
    public void test_select_2(){
        //2.
        List<String> seq_name =
            Linq4j.asEnumerable(students)
                .select((element, index)->  "#" + index + ": " + element.getName())
                .toList();
        Assert.assertEquals("[#0: 小李, #1: 小王, #2: 小张]", seq_name);
    }

}
