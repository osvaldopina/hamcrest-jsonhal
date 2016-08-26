package com.github.osvaldopina.signedcontract.enforcer;

import java.util.Collections;
import java.util.List;

public class LeafClauseEnforcer<T> implements ClauseEnforcer<T> {


    @Override
    public final List<Error> enforce(T documentClause) {
        return enforceClause(documentClause);
    }

    protected List<Error> enforceClause(T documentClause) {
        return Collections.emptyList();
    }
}
