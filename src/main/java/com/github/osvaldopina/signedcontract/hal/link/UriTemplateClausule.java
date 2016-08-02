package com.github.osvaldopina.signedcontract.hal.link;

import com.github.osvaldopina.signedcontract.BranchClausule;
import com.github.osvaldopina.signedcontract.hal.HalUtils;
import com.github.osvaldopina.signedcontract.hal.link.uritemplate.UriTemplateVariableClausule;

import java.util.List;

public class UriTemplateClausule extends BranchClausule {

    public UriTemplateClausule(List<UriTemplateVariableClausule> subClausules) {
        super(subClausules);
    }


    @Override
    protected String dismemberDocument(String document) {

        HalLink halLink = new HalLink(HalUtils.parse(document));


        return halLink.getHRef();

    }

}
