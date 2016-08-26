package com.github.osvaldopina.signedcontract.enforcer;

import java.util.List;

public interface ClauseEnforcer<T> {

    List<Error> enforce(T documentClause);

}
