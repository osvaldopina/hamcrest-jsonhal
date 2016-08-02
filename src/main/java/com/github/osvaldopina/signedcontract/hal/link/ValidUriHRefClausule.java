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

public class ValidUriHRefClausule extends BaseLinkPropertyClausule {


    @Override
    public List<Violation> enforceClausule(String document) {

        HalLink halLink = new HalLink(HalUtils.parse(document));


        try {
            if (halLink.isTemplated()) {
                UriTemplate.buildFromTemplate(halLink.getHRef());
            } else {
                new URI(halLink.getHRef());
            }
            return Collections.EMPTY_LIST;

        } catch (URISyntaxException e) {
            return ViolationsFactory.getInstance().createViolations(
                    new LinkPropertyViolation("link href to be a valid URI", "not")
            );
        } catch (MalformedUriTemplateException e) {
            return ViolationsFactory.getInstance().createViolations(
                    new LinkPropertyViolation("link href to be a valid URI template", "not")
            );
        }
    }


}
