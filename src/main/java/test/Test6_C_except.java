package test;

import org.apache.calcite.linq4j.Enumerable;
import org.apache.calcite.linq4j.Linq4j;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * @author wangxiaohu
 * @version Id: Test6_intersect.java, v0.1 2021年12月06日 17:45:01 wangxiaohu Exp $
 */
public class Test6_C_except {
    /**
     * exceptEnumerable<TSource> var1, boolean var2)：集合差集
     *  var2 默认false，求差集后，将将元素去重，例如 [a,a,b,c]和[b,c,d]的差集是[a]
     *  var2 为true  ，求差集后，元素不去重，例如 [a,a,b,c]和[b,c,d]的差集是[a,a]
     */
    @Test
    public void test_except(){
        List<String> strings1 = Arrays.asList("a", "b", "c","d");
        List<String> strings2 = Arrays.asList("e", "a");

        Enumerable<String> except = Linq4j.asEnumerable(strings1).except(Linq4j.asEnumerable(strings2),false);
        System.out.println(except.toList().toString());
        Assert.assertEquals("[b, c, d]", except.toList().toString());



        //
        List<String> strings3 = Arrays.asList("a", "a", "c","d");
        List<String> strings4 = Arrays.asList("c", "d", "e", "f");

        Enumerable<String> except2 = Linq4j.asEnumerable(strings3).except(Linq4j.asEnumerable(strings4),true);
        System.out.println(except2.toList().toString());
        Assert.assertEquals("[a, a]", except2.toList().toString());

        except2 = Linq4j.asEnumerable(strings3).except(Linq4j.asEnumerable(strings4),false);
        System.out.println(except2.toList().toString());
        Assert.assertEquals("[a]", except2.toList().toString());
    }
}
