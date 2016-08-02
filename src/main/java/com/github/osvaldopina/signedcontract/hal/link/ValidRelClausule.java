package com.github.osvaldopina.signedcontract.hal.link;

import com.github.osvaldopina.signedcontract.Violation;
import com.github.osvaldopina.signedcontract.ViolationsFactory;
import com.github.osvaldopina.signedcontract.hal.HalUtils;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

public class ValidRelClausule extends BaseLinkPropertyClausule {

    private static Set<String> IANA_RELS = new HashSet<String>(Arrays.asList("about", "alternate", "appendix", "archives",
            "author", "bookmark", "canonical", "chapter", "collection", "contents", "copyright", "create-form",
            "current", "describedby", "describes", "disclosure", "duplicate", "edit", "edit-form", "edit-media",
            "enclosure", "first", "glossary", "help", "hosts", "hub", "icon", "index", "item", "last", "latest-version",
            "license", "lrdd", "memento", "monitor", "monitor-group", "next", "next-archive", "nofollow", "noreferrer",
            "original", "payment", "predecessor-version", "prefetch", "prev", "preview", "previous", "prev-archive",
            "privacy-policy", "profile", "related", "replies", "search", "section", "self", "service", "start",
            "stylesheet", "subsection", "successor-version", "tag", "terms-of-service", "timegate", "timemap",
            "type", "up", "version-history", "via", "working-copy", "working-copy-of"));


    @Override
    public List<Violation> enforceClausule(String document) {

        HalLink halLink = new HalLink(HalUtils.parse(document));


            if (!IANA_RELS.contains(halLink.getRel())) {
                try {
                    URI relUri = new URI(halLink.getRel());
                    if (! relUri.isAbsolute()) {
                        return ViolationsFactory.getInstance().createViolations(
                                new LinkPropertyViolation("link to be a absolute uri or a valid IANA rel", "not")
                        );
                    }
                } catch (URISyntaxException e) {
                    return ViolationsFactory.getInstance().createViolations(
                            new LinkPropertyViolation("link to be a absolute uri or a valid IANA rel", "not")
                    );
                }
            }
            return  Collections.EMPTY_LIST;
    }
}
