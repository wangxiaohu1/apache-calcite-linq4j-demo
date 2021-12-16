package test.linq4jtest;

import java.util.Arrays;
import java.util.List;

import org.apache.calcite.linq4j.Enumerable;
import org.apache.calcite.linq4j.Linq4j;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author wangxiaohu
 * @version Id: Test6_intersect.java, v0.1 2021年12月06日 17:45:01 wangxiaohu Exp $
 */
public class Test7_union_intersect {
    /**
     * intersect：集合交集，不影响以前的集合
     *  all 默认false，求交集后，将元素去重
     *  all 为true  ，求交集后，元素不去重
     */
    @Test
    public void test_intersect(){
        List<String> listA = Arrays.asList("b", "c", "wf", "za", "b");
        List<String> listB = Arrays.asList("ew", "b", "3", "wf", "p", "b");

        //1. 交集，去重，all默认为false
        List<String> intersect = Linq4j.asEnumerable(listA).intersect(Linq4j.asEnumerable(listB)).toList();
        Assert.assertEquals("[b, wf]", intersect.toString());
        Assert.assertEquals(5, listA.size());
        Assert.assertEquals(6, listB.size());


        //2.1. 交集，不去重，all=true
        intersect = Linq4j.asEnumerable(listA).intersect(Linq4j.asEnumerable(listB), true).toList();
        Assert.assertEquals("[b, b, wf]", intersect.toString());
        Assert.assertEquals(5, listA.size());
        Assert.assertEquals(6, listB.size());

        //2.1. 交集，去重，all=false
        intersect = Linq4j.asEnumerable(listA).intersect(Linq4j.asEnumerable(listB), false).toList();
        Assert.assertEquals("[b, wf]", intersect.toString());
        Assert.assertEquals(5, listA.size());
        Assert.assertEquals(6, listB.size());
    }
}
