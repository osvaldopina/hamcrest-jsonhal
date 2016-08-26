package com.github.osvaldopina.signedcontract.enforcer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BranchClauseEnforcer<T, E> implements  ClauseEnforcer<T> {

    private List<ClauseEnforcer<E>> subClauses = new ArrayList<ClauseEnforcer<E>>();

    private Navigator<T,E> navigator;

    public BranchClauseEnforcer(List<? extends ClauseEnforcer<E>> subClauses) {
        this.subClauses.addAll(subClauses);
    }


    @Override
    public final List<Error> enforce(T documentClause) {

        List<Error> clauseErrors = new ArrayList<Error>();

        clauseErrors.addAll(enforceClause(documentClause));

        E documentSubClause = navigate(documentClause);

        for(ClauseEnforcer<E> subClause: subClauses) {
             clauseErrors.addAll(subClause.enforce(documentSubClause));
        }

        return clauseErrors;
    }

    protected List<Error> enforceClause(T documentClause) {
        return Collections.EMPTY_LIST;
    }

    protected final E navigate(T documentClause) {
        if (navigator == null) {
            navigator = createNavigator();
        }
        return navigator.navigate(documentClause);
    }

    protected Navigator<T,E> createNavigator() {
        throw new IllegalStateException("Must be overrided!");
    }
}
