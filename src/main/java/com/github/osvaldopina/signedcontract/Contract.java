package com.github.osvaldopina.signedcontract;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class Contract extends BranchClause {

    private List<Clause> clauses;


    public Contract(List<? extends Clause> clauses) {
        super(clauses);
    }




}
