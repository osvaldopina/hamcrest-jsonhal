package com.github.osvaldopina.signedcontract.hal.link;

import com.github.osvaldopina.signedcontract.hal.HalUtils;

public class TemplatedClause extends LinkPropertyClauseImpl {

    private boolean templated;

    public TemplatedClause(boolean templated) {
        this.templated = templated;
    }

    @Override
    public void enforceClause(String document) {

        HalLink halLink = new HalLink(HalUtils.parse(document));

        if (halLink.isTemplated() != templated) {
            addViolation(new StringBuilder()
                    .append("expecting link ")
                    .append((templated?"":"not ") + "to be templated.")
                    .append(" but it was ")
                    .append((halLink.isTemplated()?"":"not ") + "templated").toString());
        }
    }
}
