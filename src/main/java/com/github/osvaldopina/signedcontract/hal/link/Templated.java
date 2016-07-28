package com.github.osvaldopina.signedcontract.hal.link;

import com.github.osvaldopina.signedcontract.Violation;
import com.github.osvaldopina.signedcontract.hal.HalUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Templated extends LinkProperty {

    private boolean templated;

    public Templated(String rel, boolean templated) {
        this.templated = templated;
    }

    @Override
    public List<Violation> enforce(String document) {



        Map<String, Object> link = HalUtils.parse(document);

        String propValue = getCaseInsensitiveFromMap(link, "templated");

        propValue==null

        if (Boolean getCaseInsensitiveFromMap(link, "templated") != templated) {
            return Arrays.asList(
                    (Violation) new LinkPropertyViolation(
                            templated?"":"not " + "templated" ,
                            link.isTemplated()?"": "not " + "templated"));
        }
        return Collections.emptyList();
    }
}
