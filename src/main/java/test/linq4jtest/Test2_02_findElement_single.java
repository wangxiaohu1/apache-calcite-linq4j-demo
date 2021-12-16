package test.linq4jtest;

import org.apache.calcite.linq4j.Linq4j;
import org.apache.calcite.linq4j.function.Functions;
import org.hamcrest.CoreMatchers;
import org.hamcrest.core.IsNot;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author wangxiaohu
 * @version Id: Test4_single.java, v0.1 2021年12月05日 19:13:21 wangxiaohu Exp $
 */
public class Test2_02_findElement_single {

    /**
     * single:当集合size=1，则返回这个index=0的元素，否则如果集合size>1个或者size=0都会报错IllegalStateException
     */
    @Test
    public void test_single(){
        String[] str_array1 = {"zhang"};
        String s = Linq4j.asEnumerable(str_array1).single();
        Assert.assertEquals("zhang",s);

        //如果集合元素大于1个或者等于0都会报错
        String[] str_array2 = {"zhang","li"};
        Exception e=null;
        try {
            String s2 = Linq4j.asEnumerable(str_array2).single();
            Assert.fail("不符合预期");
        }catch (IllegalStateException ex){
            e=ex;
        }
        Assert.assertNotNull(e);
    }

    /**
     * singleOrDefault: 当集合size=1，则返回这个index=0的元素，否则如果集合size>1个或者size=0，返回null，不报错
     */
    @Test
    public void test_singleOrDefault(){
        String[] str_array1 = {"zhang","li"};
        String s1 = Linq4j.asEnumerable(str_array1).singleOrDefault();
        Assert.assertNull(s1);

        String[] str_array2 = {"zhang"};
        String s2 = Linq4j.asEnumerable(str_array2).singleOrDefault();
        Assert.assertEquals("zhang", s2);
    }

    /**
     * single(Predicate predicate):和singleg作用一样，predicate是过滤条件
     */
    @Test
    public void test_single_predicate(){
        String[] str_array1= {"zhang","li"};
        String s1 = Linq4j.asEnumerable(str_array1).single(x->x.startsWith("z"));
        Assert.assertEquals("zhang", s1);


        //过滤多余一个元素或者无元素，会抛出异常IllegalStateException
        String[] str_array2= {"zhang","li", "zhong"};
        Exception e = null;
        try {
            String s2 = Linq4j.asEnumerable(str_array2).single(x->x.startsWith("z"));
        }catch (IllegalStateException ex) {
            e = ex;
        }
        Assert.assertNotNull(e);
    }
}
