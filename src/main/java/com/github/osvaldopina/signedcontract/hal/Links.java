package com.github.osvaldopina.signedcontract.hal;

import com.github.osvaldopina.signedcontract.BranchClausule;
import com.github.osvaldopina.signedcontract.Clausule;
import com.github.osvaldopina.signedcontract.hal.link.Link;

import java.util.List;

public class Links extends BranchClausule implements HalClausule{

    public Links(List<Link> subClausules) {
        super(subClausules);
    }

    @Override
    public String dismemberDocument(String document) {
        return HalUtils.getLinks(document);
    }
}
