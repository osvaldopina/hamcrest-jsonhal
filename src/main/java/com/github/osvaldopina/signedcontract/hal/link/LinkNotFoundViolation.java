package com.github.osvaldopina.signedcontract.hal.link;

import com.github.osvaldopina.signedcontract.Violation;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class LinkNotFoundViolation extends Violation {

    private String expectedLinkRel;

    private Collection<String> actualLinkRels;

    public LinkNotFoundViolation(String expectedLinkRel, Collection<String> actualLinkRels) {
        super(Collections.EMPTY_LIST);
        this.expectedLinkRel = expectedLinkRel;
        this.actualLinkRels = actualLinkRels;
    }

    public String toString() {
        return new StringBuilder()
                .append("expect to find link with rel ")
                .append(expectedLinkRel)
                .append(" but the document links are ")
                .append(actualLinkRels).toString();
    }


}
