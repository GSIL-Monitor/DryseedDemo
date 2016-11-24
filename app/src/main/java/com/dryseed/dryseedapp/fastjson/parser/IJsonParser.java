package com.dryseed.dryseedapp.fastjson.parser;


import com.dryseed.dryseedapp.fastjson.bean.Person;

import java.util.List;
import java.util.Map;

public interface IJsonParser{
    Person fromJson(String jsonString, Class<Person> object);

    String toJson(Object object);

    List<Person> listFromJson(String jsonString, Class<Person> object);

    Map<String, Person> mapFromJson(String jsonString, Class<Person> object);
}
