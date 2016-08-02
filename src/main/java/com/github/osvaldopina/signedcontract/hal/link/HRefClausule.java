package com.github.osvaldopina.signedcontract.hal.link;

import com.damnhandy.uri.template.MalformedUriTemplateException;
import com.damnhandy.uri.template.UriTemplate;
import com.github.osvaldopina.signedcontract.Violation;
import com.github.osvaldopina.signedcontract.ViolationsFactory;
import com.github.osvaldopina.signedcontract.hal.HalUtils;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.List;

public class HRefClausule extends BaseLinkPropertyClausule {

    @Override
    protected String dismemberDocument(String document) {

        HalLink halLink = new HalLink(HalUtils.parse(document));

        return halLink.getHRef();
    }

}
