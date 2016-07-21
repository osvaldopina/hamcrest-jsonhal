package com.github.osvaldopina.resultmatcher.jsonhal;

import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;

public class LinkFinderResultMatcher implements ResultMatcher {

    private String rel;

   public LinkFinderResultMatcher(String rel) {
        this.rel = rel;
   }


    @Override
    public void match(MvcResult result) throws Exception {


    }
}
