package test.linq4jtest;

import java.util.ArrayList;
import java.util.List;

import org.apache.calcite.linq4j.Enumerable;
import org.apache.calcite.linq4j.Enumerator;
import org.apache.calcite.linq4j.Linq4j;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author wangxiaohu
 * @version Id: Test0_enumerable.java, v0.1 2021年12月06日 16:49:36 wangxiaohu Exp $
 */
public class Test1_01_asEnumerable {

    /**
     * asEnumerable:将java集合和数组转换成Enumerable对象，Enumerable对象中有若干API可易提简化集合处理方式
     * ，如果入参为null，互抛出NPE
     */
    @Test
    public void test_asEnumerable_list() {
        List<String> list = null;
        Exception e = null;
        try{
            Linq4j.asEnumerable(list).count();
            Assert.fail("不匹配");
        }catch (NullPointerException ex){
            e = ex;
        }
        Assert.assertNotNull(e);


        List<String> list2 = new ArrayList<>();
        int count = Linq4j.asEnumerable(list2).count();
        Assert.assertEquals(0,count);


        List<String> list3 = new ArrayList<>();
        Enumerator<String> enumerator = Linq4j.asEnumerable(list3).enumerator();
        while(enumerator.moveNext()){
            Assert.assertEquals("", enumerator.current());
        }

        List<String> list4 = new ArrayList<>();
        Enumerator<String> enumerator2 = Linq4j.asEnumerable(list4).defaultIfEmpty().enumerator();
        while(enumerator2.moveNext()){
            Assert.assertNull(enumerator2.current());
        }

        List<String> list5 = new ArrayList<>();
        Enumerator<String> enumerator3 = Linq4j.asEnumerable(list5).defaultIfEmpty("defaultValue").enumerator();
        while(enumerator2.moveNext()){
            Assert.assertEquals("defaultValue",enumerator2.current());
        }


    }
}
