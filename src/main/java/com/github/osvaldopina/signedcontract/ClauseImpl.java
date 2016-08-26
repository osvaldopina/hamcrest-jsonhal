package com.github.osvaldopina.signedcontract;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ClauseImpl implements Clause {

    private List<String> violations = new ArrayList<String>();

    public String dismemberDocument(String document) {
        return document;
    }

    protected void enforceClause(String document) {
    }

    @Override
    public void enforce(String document) {

        try {

            String documentClausePart = dismemberDocument(document);

            enforceClause(documentClausePart);
        }
        catch(HaltViolation e) {

        }
    }

    @Override
    public List<String> getViolations() {
        return Collections.unmodifiableList(violations);
    }

    @Override
    public void addViolation(String violation) {
        violations.add(violation);
    }

    public void addHaltViolation(String violation) {
        violations.add(violation);
        throw new HaltViolation();
    }

    public void walk(Walker walker) {

        Method before = getBestFit(this, walker, "before");

        if (before != null) {
            exec(this, walker, before);
        }

        Method after = getBestFit(this, walker, "after");

        if (after != null) {
            exec(this, walker, after);
        }

    }

    private void exec(Clause clause, Walker walker, Method method) {

        try {
            method.invoke(walker, clause);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }




    private Method getBestFit(Clause clause, Walker walker, String prefix) {
        for(Method method: walker.getClass().getMethods()) {
            if (method.getName().startsWith((prefix==null?"w":(prefix+ "W"))+"alk")) {
                if (method.getParameterTypes().length >= 1) {
                    Class<?> methodParam = method.getParameterTypes()[0];
                    if (methodParam.isAssignableFrom(clause.getClass())) {
                        return method;
                    }
                }
            }
        }
        return null;
    }

}
