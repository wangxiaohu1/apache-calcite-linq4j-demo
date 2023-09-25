package test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LeftTest {
    /**
     * 测试集合left join 左联接
     * @param args
     */
    public static void main(String[] args) {
        List<Student> left = new ArrayList();
        left.add(new Student(1, "小张", 110));
        left.add(new Student(2, "小王", 120));
        left.add(new Student(3, "xiaoli", 130));

        List<Cls> right = new ArrayList();
        right.add(new Cls(110,"一年级"));
        right.add(new Cls(120,"二年级"));

        //-------------------------------------------------------------------------------//
        //--------需求：从left和right中按 left join on left.clsId=right.id得到结果-----------//
        //-------------------------------------------------------------------------------//

        //1. 第1种实现的方式
        Map<Integer,Cls> map = right.stream().collect(Collectors.toMap(e->e.id, e->e, (k,v)->k));
        List<StudentView> resultList2 = new ArrayList<>(left.size());
        left.stream().forEach(lp->{
            Integer key = lp.clsId;
            Cls cls = map.get(key);
            if(cls!=null){
                resultList2.add(new StudentView(lp.name,cls.name,cls.id));
            }else{
                resultList2.add(new StudentView(lp.name,"null",lp.id));
            }
        });

        resultList2.stream().forEach(x->{
            System.out.println(x.name + "@" + x.clsName + "-" + x.clsId);
        });
        System.out.println("-----------");




        //2.第2种实现的方式
        LeftTest test = new LeftTest();
        List<StudentView> resultList = test.leftJoin(left, right, rp->rp.id, lp->lp.clsId,
                        (lp,rp)-> new StudentView(lp.name,rp.name,rp.id),
                        lp-> new StudentView(lp.name,"null",lp.clsId)  );

        resultList.stream().forEach(x->{
            System.out.println(x.name + "@" + x.clsName + "-" + x.clsId);
        });
    }

    /**
     *
     * @param left   左联接的集合
     * @param right  右联接的集合
     * @param right_compareKey  比较值
     * @param left_compareKey   比较值
     * @param resultFunc  结果处理函数（当右集合匹配非null，走这个函数）
     * @param resultFunc_rightNull  结果处理函数（当右集合匹配为null，走这个函数）
     * @param <PL> 左联接的集合元素泛型类型
     * @param <PR> 右联接的集合元素泛型类型
     * @param <K>  比较值泛型类型
     * @param <R>  返回值泛型类型
     * @return
     */
    public  <PL,PR,K,R>   List<R>  leftJoin(List<PL> left, List<PR> right, Func1<PR,K> right_compareKey,
                                        Func1<PL,K> left_compareKey, Func2<PL,PR,R> resultFunc,
                                            Func1<PL,R> resultFunc_rightNull){
        Map<K,PR> map = new HashMap<>(right.size());
        for(PR f:right){
            K key = right_compareKey.apply(f);
            map.put(key, f);
        }

        List<R> resultList = new ArrayList<>(left.size());
        for(PL e : left) {
            K key = left_compareKey.apply(e);
            PR f = map.get(key);

            R r = null;
            if(f!=null){
                r= resultFunc.apply(e,f);
            }else{
                r= resultFunc_rightNull.apply(e);
            }

            if(r!=null){
                resultList.add(r);
            }
        }
        return resultList;

    }

    interface Func1<PR,K>{
        K apply(PR e);
    }
    interface Func2<PL,PR,R>{
        R apply(PL e,PR f);
    }

    static class Student{
        public Integer id;
        public String name;
        public Integer clsId;
        public Student(Integer id, String name, Integer clsId){
            this.id=id;
            this.name=name;
            this.clsId=clsId;
        }
    }
    static class Cls{
        public Integer id;
        public String name;
        public Cls(Integer id, String name){
            this.id=id;
            this.name=name;
        }
    }
    static class StudentView{
        public String name;
        public String clsName;
        public Integer clsId;
        public StudentView(String name, String clsName, Integer clsId){
            this.name=name;
            this.clsName=clsName;
            this.clsId=clsId;
        }
    }
}
