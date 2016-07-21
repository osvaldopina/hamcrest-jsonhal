package com.github.osvaldopina.resultmatcher.jsonhal;

import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;

public class LinkFinderResultMatchers implements ResultMatcher {

    private LinkFinderResultMatcher[] linkFinderResultMatchers;


   public LinkFinderResultMatchers(LinkFinderResultMatcher ... linkFindResultMatchers) {
       this.linkFinderResultMatchers = linkFindResultMatchers;
   }


    @Override
    public void match(MvcResult result) throws Exception {
        for(LinkFinderResultMatcher linkFinderResultMatcher: linkFinderResultMatchers) {
        }

    }
}
