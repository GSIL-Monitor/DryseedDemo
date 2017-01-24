package com.dryseed.dryseedapp.designPattern.interpreter;

/**
 * Created by caiminming on 2017/1/24.
 */
public class Constant extends Expression {

    private boolean value;

    public Constant(boolean value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object obj) {

        if (obj != null && obj instanceof Constant) {
            return this.value == ((Constant) obj).value;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.toString().hashCode();
    }

    @Override
    public boolean interpret(Context ctx) {

        return value;
    }

    @Override
    public String toString() {
        return new Boolean(value).toString();
    }

}
