package com.github.osvaldopina.signedcontract;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BranchClause extends ClauseImpl {

    private List<Clause> subClauses;

    public BranchClause(List<? extends Clause> subClauses) {
        this.subClauses = new ArrayList<Clause>(subClauses);
    }


    public List<Clause> getSubClauses() {
        return subClauses;
    }

    @Override
    public final void enforce(String document) {

        try {

            enforceClause(document);

            for (Clause clause : subClauses) {

                String subDocument = clause.dismemberDocument(document);

                clause.enforce(subDocument);
            }
        }
        catch(HaltViolation e) {

        }
    }

    public void walk(Walker walker) {

        Method before = getBestFit(this, walker, "before");

        if (before != null) {
            exec(this, walker, before);
        }

        Method walk =  getBestFit(this, walker, null);

        if (walk != null) {
            exec(this, walker, walk);
        }


        for(Clause clause: getSubClauses()) {
            clause.walk(walker);
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
