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
public class Test7_union2_into {

    /**
     * 在集合B尾部追加集合A的内容，A不变，B元素增多，同时返回追加后的元素
     * List into = Linq4j.asEnumerable(listA).into(listB)
     * into.size=listB.size
     */
    @Test
    public void test_into(){
        List<Student> listA = new ArrayList<>();
        listA.add(new Student(1, 20, "小王"));
        listA.add(new Student(1, 20, "小李"));

        List<Student> listB = new ArrayList<>();
        listB.add(new Student(1, 20, "xiao"));
        listB.add(new Student(1, 20, "da"));


        List<Student> into = Linq4j.asEnumerable(listA).into(listB);

        String string = Linq4j.asEnumerable(into).select(x -> x.getName()).toList().toString();
        Assert.assertEquals("[xiao, da, 小王, 小李]", string);
        Assert.assertEquals(2,listA.size());
        Assert.assertEquals(4,listB.size());
        Assert.assertEquals(4,into.size());

    }
}
