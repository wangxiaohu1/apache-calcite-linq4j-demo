package test;

import org.apache.calcite.linq4j.Linq4j;
import org.apache.calcite.linq4j.function.Functions;
import org.junit.Assert;
import org.junit.Test;

import java.util.Objects;

/**
 * @author wangxiaohu
 * @version Id: Test5_functions.java, v0.1 2021年12月05日 19:13:21 wangxiaohu Exp $
 */
public class Test5_functions {
    /**
     * Functions.identityComparer.equal相当于Objects.equals
     */
    @Test
    public void test_identityComparer(){
        final Integer one = 1000;
        final Integer one2 = Integer.valueOf(one.toString());
        Assert.assertFalse(one == one2);

        Assert.assertTrue(  Functions.identityComparer().equal(one,one2) );
        Assert.assertTrue(Objects.equals(one, one2));
    }

    @Test
    public  void test(){

    }
}
