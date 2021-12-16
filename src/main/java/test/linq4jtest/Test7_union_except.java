package test.linq4jtest;

import org.apache.calcite.linq4j.Enumerable;
import org.apache.calcite.linq4j.Linq4j;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author wangxiaohu
 * @version Id: Test6_intersect.java, v0.1 2021年12月06日 17:45:01 wangxiaohu Exp $
 */
public class Test7_union_except {
    /**
     * except(exceptEnumerable<TSource> list, boolean all)：集合差集，左边集合减去右边集合，不影响以前的集合
     *  all 默认false，求差集后，将元素去重，例如 [a,a,b,c]和[b,c,d]的差集是[a]
     *  all 为true  ，求差集后，元素不去重，例如 [a,a,b,c]和[b,c,d]的差集是[a,a]
     */
    @Test
    public void test_except(){
        List<String> listA = Arrays.asList("a", "b", "c","d");
        List<String> listB = Arrays.asList("e", "a");

        //1. 差集，并去重，all=false，all默认为false
        List<String> except = Linq4j.asEnumerable(listA).except(Linq4j.asEnumerable(listB),false).toList();
        Assert.assertEquals("[b, c, d]", except.toString());
        Assert.assertEquals(4, listA.size());
        Assert.assertEquals(2, listB.size());
        Assert.assertEquals(3, except.size());



        listA = Arrays.asList("a", "a", "c","d");
        listB = Arrays.asList("c", "d", "e", "f");

        //2.1. 差集，不去重，all=true
        except = Linq4j.asEnumerable(listA).except(Linq4j.asEnumerable(listB),true).toList();
        Assert.assertEquals("[a, a]", except.toString());

        //2.2. 差集，去重，all=false
        except = Linq4j.asEnumerable(listA).except(Linq4j.asEnumerable(listB),false).toList();
        Assert.assertEquals("[a]", except.toString());
    }
}
