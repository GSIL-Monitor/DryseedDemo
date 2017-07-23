package com.dryseed.dryseedapp.framework.fastjson.parser;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.dryseed.dryseedapp.framework.fastjson.bean.Person;

import java.util.List;
import java.util.Map;

public class FastJsonParser implements IJsonParser{
    @Override
    public Person fromJson(String jsonString, Class<Person> object){
        return JSON.parseObject(jsonString, object);
    }

    @Override
    public String toJson(Object object){
        return JSON.toJSONString(object);
    }

    @Override
    public List<Person> listFromJson(String jsonString, Class<Person> object){
        return JSON.parseArray(jsonString, object);
    }

    @Override
    public Map<String, Person> mapFromJson(String jsonString, Class<Person> object){
        return JSON.parseObject(jsonString, new TypeReference<Map<String, Person>>(){});
    }
}
