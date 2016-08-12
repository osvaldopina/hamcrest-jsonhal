package com.github.osvaldopina.signedcontract.hal.link;

import com.github.osvaldopina.signedcontract.BranchClause;
import com.github.osvaldopina.signedcontract.hal.HalUtils;
import com.github.osvaldopina.signedcontract.uritemplate.UriTemplateVariableFindClause;

import java.util.List;

public class UriTemplateClause extends BranchClause implements LinkPropertyClause {

    public UriTemplateClause(List<UriTemplateVariableFindClause> subClausules) {
        super(subClausules);
    }


    @Override
    public String dismemberDocument(String document) {

        HalLink halLink = new HalLink(HalUtils.parse(document));


        return halLink.getHRef();

    }

}
