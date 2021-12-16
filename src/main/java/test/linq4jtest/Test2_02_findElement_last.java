package test.linq4jtest;

import java.util.ArrayList;
import java.util.List;

import org.apache.calcite.linq4j.Linq4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author wangxiaohu
 * @version Id: Test4_last.java, v0.1 2021年12月05日 19:13:21 wangxiaohu Exp $
 */
public class Test2_02_findElement_last {
    List<String> stringList = new ArrayList();
    @Before
    public void before(){
        stringList.add("1");
        stringList.add("2");
        stringList.add("3");
    }

    /**
     * last: 获取最后一个元素，empty集合会抛出NoSuchElementException
     */
    @Test
    public void test_last() {
        List<String> list = new ArrayList();
        list.add("1");
        list.add("2");
        list.add("3");

        String s1 = Linq4j.asEnumerable(list).last();
        Assert.assertEquals("3", s1);

        //2. empty集合会抛出NoSuchElementException
        List<String> list3 = new ArrayList();
        Exception e = null;
        try {
            Linq4j.asEnumerable(list3).last();
            Assert.fail("不匹配");
        }catch (Exception ex){
            e=ex;
        }
        Assert.assertNotNull(e);
    }

    /**
     * lastOrDefault:获取最后一个元素，empty集合不会抛出NoSuchElementException，返回null
     */
    @Test
    public void test_lastOrDefault(){
        //1. 查询empty集合，不会抛出异常
        List<String> emptyList = new ArrayList<>();
        String s = Linq4j.asEnumerable(emptyList).lastOrDefault();
        Assert.assertNull(s);

        //2. 查询非empty集合，返回最后一个元素
        String last = Linq4j.asEnumerable(stringList).lastOrDefault();
        Assert.assertEquals("3", last);

    }
}
