package com.dryseed.dryseedapp.designPattern.flyweight;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by caiminming on 2017/1/24.
 */
public class FlyweightFactory {
    private Map<Character, Flyweight> files = new HashMap<Character, Flyweight>();

    public Flyweight factory(Character state) {
        // 先从缓存中查找对象
        Flyweight fly = files.get(state);
        if (fly == null) {
            // 如果对象不存在则创建一个新的Flyweight对象
            fly = new ConcreteFlyweight(state);
            // 把这个新的Flyweight对象添加到缓存中
            files.put(state, fly);
        }else{
            System.out.println(state+"--->>状态对应对象已经存在");
        }
        return fly;
    }
}
