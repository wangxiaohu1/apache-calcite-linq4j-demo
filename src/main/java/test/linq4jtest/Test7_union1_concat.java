package test.linq4jtest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.calcite.linq4j.Enumerable;
import org.apache.calcite.linq4j.Linq4j;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author wangxiaohu
 * @version Id: Test6_concat.java, v0.1 2021年12月06日 17:45:01 wangxiaohu Exp $
 */
public class Test7_union1_concat {
    /**
     * concat：在集合A尾部追加集合B的内容，A不变，B不变，同时返回追加后的元素列表。相当于相当于list.addAll，但是不变原有的集合
     *
     * list concat = Linq4j.asEnumerable(listA).concat(Linq4j.asEnumerable(listB))
     *
     * */
    @Test
    public void test(){
        List<String> listA = Arrays.asList("a", "c", "b");
        List<String> listB = Arrays.asList("ew", "3", "b");

        List<String> concat = Linq4j.asEnumerable(listA).concat(Linq4j.asEnumerable(listB)).toList();

        Assert.assertEquals("[a, c, b, ew, 3, b]",concat.toString());
        Assert.assertEquals(3,listA.size());
        Assert.assertEquals(3,listB.size());
        Assert.assertEquals(6,concat.size());
    }


    @Test
    public void test_Linq4j_concat(){
        List<String> listA = Arrays.asList("a", "c", "b");
        List<String> listB = Arrays.asList("ew", "3", "b");

        List<Enumerable<String>> enumerableList = new ArrayList<>();
        enumerableList.add(Linq4j.asEnumerable(listA));
        enumerableList.add(Linq4j.asEnumerable(listB));

        List<String> concat = Linq4j.concat(enumerableList).toList();

        Assert.assertEquals("[a, c, b, ew, 3, b]",concat.toString());
        Assert.assertEquals(3,listA.size());
        Assert.assertEquals(3,listB.size());
        Assert.assertEquals(6,concat.size());
    }
}
