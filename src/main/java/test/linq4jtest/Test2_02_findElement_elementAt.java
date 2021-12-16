package test.linq4jtest;

import java.util.ArrayList;
import java.util.List;

import org.apache.calcite.linq4j.Linq4j;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import test.model.Student;

/**
 * @author wangxiaohu
 * @version Id: Test6_G2_join.java, v0.1 2021年12月05日 19:13:21 wangxiaohu Exp $
 */
public class Test2_02_findElement_elementAt {

    /**
     * elementAt: 获取指定索引位置的元素，从0开始，超过集合长度会抛出IndexOutOfBoundsException
     */
    @Test
    public void test_elementAt(){
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");

        String s1 = Linq4j.asEnumerable(list).elementAt(0);
        Assert.assertEquals("1",s1);

        String s2 = Linq4j.asEnumerable(list).elementAt(1);
        Assert.assertEquals("2",s2);


        Exception e = null;
        try {
            Linq4j.asEnumerable(list).elementAt(2);
            Assert.fail("不匹配");
        }catch (Exception ex){
            e = ex;
        }
        Assert.assertNotNull(e);
    }

    /**
     * elementAtOrDefault: 获取指定索引位置的元素，从0开始，超过集合长度返回null,不会抛出IndexOutOfBoundsException
     */
    @Test
    public void test_elementAtOrDefault(){
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");

        String s1 = Linq4j.asEnumerable(list).elementAtOrDefault(0);
        Assert.assertEquals("1",s1);

        String s2 = Linq4j.asEnumerable(list).elementAtOrDefault(1);
        Assert.assertEquals("2",s2);

        String s3 = Linq4j.asEnumerable(list).elementAtOrDefault(2);
        Assert.assertNull(s3);
    }


}
