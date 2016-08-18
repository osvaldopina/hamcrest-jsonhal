package test;

import java.util.ArrayList;
import java.util.List;

public class BranchClause<T,E> implements Clause<T> {

    private List<Clause<E>> subClauses = new ArrayList<Clause<E>>();

    public BranchClause(List<Clause<E>> subClauses) {
        this.subClauses.addAll(subClauses);
    }

    public E dismember(T clause) {
        return null;
    }

    public void validate(T clause) {

        validateClause(clause);

        E subContractClause = dismember(clause);

        for(Clause<E> subClause: subClauses) {
            subClause.validate(subContractClause);
        }

    }

    protected void validateClause(T clause) {

    }


}
