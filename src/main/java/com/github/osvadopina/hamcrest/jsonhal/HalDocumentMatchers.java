package com.github.osvadopina.hamcrest.jsonhal;

import com.github.osvadopina.hamcrest.jsonhal.link.HalLinkFindMatcher;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;

/**
 * Created by deinf.osvaldo on 21/07/2016.
 */
public class HalDocumentMatchers {

    public static HalDocumentMatchers halDocument() {
        return new HalDocumentMatchers();
    }


}
