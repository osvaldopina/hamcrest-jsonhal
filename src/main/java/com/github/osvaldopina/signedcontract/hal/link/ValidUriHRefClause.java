package com.github.osvaldopina.signedcontract.hal.link;

import com.damnhandy.uri.template.MalformedUriTemplateException;
import com.damnhandy.uri.template.UriTemplate;
import com.github.osvaldopina.signedcontract.hal.HalUtils;

import java.net.URI;
import java.net.URISyntaxException;

public class ValidUriHRefClause extends LinkPropertyClauseImpl {


    @Override
    public void enforceClause(String document) {

        HalLink halLink = new HalLink(HalUtils.parse(document));


        try {
            if (halLink.isTemplated()) {
                UriTemplate.buildFromTemplate(halLink.getHRef());
            } else {
                new URI(halLink.getHRef());
            }
        } catch (URISyntaxException e) {
            addViolation("Expecting link href to be a valid uri.");
        } catch (MalformedUriTemplateException e) {
            addViolation("Expecting link href to be a valid uri.");
        }
    }


}
