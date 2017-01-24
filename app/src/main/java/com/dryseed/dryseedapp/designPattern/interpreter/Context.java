package com.dryseed.dryseedapp.designPattern.interpreter;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by caiminming on 2017/1/24.
 */
public class Context {

    private Map<Variable, Boolean> map = new HashMap<Variable, Boolean>();

    public void assign(Variable var, boolean value) {
        map.put(var, new Boolean(value));
    }

    public boolean lookup(Variable var) throws IllegalArgumentException {
        Boolean value = map.get(var);
        if (value == null) {
            throw new IllegalArgumentException();
        }
        return value.booleanValue();
    }
}
