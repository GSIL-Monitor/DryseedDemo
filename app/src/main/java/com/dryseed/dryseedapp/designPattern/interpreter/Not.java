package com.dryseed.dryseedapp.designPattern.interpreter;

/**
 * Created by caiminming on 2017/1/24.
 */
public class Not extends Expression {

    private Expression exp;

    public Not(Expression exp) {
        this.exp = exp;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj instanceof Not) {
            return exp.equals(((Not) obj).exp);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.toString().hashCode();
    }

    @Override
    public boolean interpret(Context ctx) {
        return !exp.interpret(ctx);
    }

    @Override
    public String toString() {
        return "(Not " + exp.toString() + ")";
    }

}
