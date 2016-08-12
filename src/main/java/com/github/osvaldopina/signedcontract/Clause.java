package com.github.osvaldopina.signedcontract;

import java.util.List;

public interface Clause  {

    String dismemberDocument(String document);

    void enforce(String document);

    List<String> getViolations();

    void addViolation(String violation);

    void walk(Walker walker);
}
