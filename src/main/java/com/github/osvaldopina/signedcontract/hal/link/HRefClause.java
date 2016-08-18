package com.github.osvaldopina.signedcontract.hal.link;

import com.github.osvaldopina.signedcontract.hal.HalUtils;

public class HRefClause extends LinkPropertyClauseImpl {

    @Override
    public String dismemberDocument(String document) {

        HalLink halLink = new HalLink(HalUtils.parse(document));

        return halLink.getHRef();
    }

}
