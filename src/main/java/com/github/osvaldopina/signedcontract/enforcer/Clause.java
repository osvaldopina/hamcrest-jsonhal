package com.github.osvaldopina.signedcontract.enforcer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Clause<T> {

    private T documentClause;

    private List<Error> errors = new ArrayList<Error>();

    private List<Clause<T>> subClauses = new ArrayList<Clause<T>>();

    public Clause(T documentClause, List<Error> errors, List<Clause<T>> subClauses) {
        this.documentClause = documentClause;
        this.errors = errors;
        this.subClauses = subClauses;
    }

    public T getDocumentClause() {
        return documentClause;
    }

    public List<Error> getErrors() {
        return Collections.unmodifiableList(errors);
    }

    public List<Clause<T>> getSubClauses() {
        return Collections.unmodifiableList(subClauses);
    }
}
