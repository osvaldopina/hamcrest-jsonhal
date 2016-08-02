package com.github.osvaldopina.signedcontract;

import java.util.Arrays;
import java.util.List;

public class ViolationsFactory {


    private static ViolationsFactory INSTANCE = new ViolationsFactory();

    public static  ViolationsFactory getInstance() {
        return INSTANCE;
    }

    public List<Violation> createViolations(Violation ... violations) {
        return Arrays.asList(violations);
    }
}
