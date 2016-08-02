package com.github.osvaldopina.signedcontract.hal;

import com.github.osvaldopina.signedcontract.hal.link.LinkClausule;

import java.util.List;

public class Links extends HalClausule {

    public Links(List<LinkClausule> subClausules) {
        super(subClausules);
    }

    @Override
    public String dismemberDocument(String document) {
        return HalUtils.getLinks(document);
    }
}
