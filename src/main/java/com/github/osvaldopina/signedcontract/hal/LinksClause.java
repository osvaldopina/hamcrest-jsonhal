package com.github.osvaldopina.signedcontract.hal;

import com.github.osvaldopina.signedcontract.hal.link.LinkClause;

import java.util.List;

public class LinksClause extends HalDocumentClause {

    public LinksClause(List<LinkClause> subClausules) {
        super(subClausules);
    }

    @Override
    public String dismemberDocument(String document) {
        return HalUtils.getLinks(document);
    }

}
