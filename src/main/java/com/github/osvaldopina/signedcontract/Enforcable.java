package com.github.osvaldopina.signedcontract;

import java.util.List;

public interface Enforcable {

    List<Violation> enforce(String document);
}
