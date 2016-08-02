package com.github.osvaldopina.signedcontract.hal.link;

import com.github.osvaldopina.signedcontract.Violation;
import com.github.osvaldopina.signedcontract.hal.HalUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TemplatedClausule extends BaseLinkPropertyClausule {

    private boolean templated;

    public TemplatedClausule(boolean templated) {
        this.templated = templated;
    }

    @Override
    public List<Violation> enforceClausule(String document) {

        HalLink halLink = new HalLink(HalUtils.parse(document));

        if (halLink.isTemplated() != templated) {
            return Arrays.asList((Violation)
              new LinkPropertyViolation(
                      (templated?"":"not ") + "to be templated.",
                      (halLink.isTemplated()?"":"not ") + "templated"
              )
            );
        }

        return Collections.emptyList();
    }
}
