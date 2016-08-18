package com.github.osvadopina.hamcrest.jsonhal.link;

import com.damnhandy.uri.template.MalformedUriTemplateException;
import com.damnhandy.uri.template.UriTemplate;
import com.github.osvadopina.hamcrest.jsonhal.uri.UriVariableFindMatcher;
import org.hamcrest.Description;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class HalLinkPropertyMatcher<T> extends HalLinkMatcher {

    private static Set<String> IANA_RELS = new HashSet<String>(Arrays.asList("about", "alternate", "appendix", "archives",
            "author", "bookmark", "canonical", "chapter", "collection", "contents", "copyright", "create-form",
            "current", "describedby", "describes", "disclosure", "duplicate", "edit", "edit-form", "edit-media",
            "enclosure", "first", "glossary", "help", "hosts", "hub", "icon", "index", "item", "last", "latest-version",
            "license", "lrdd", "memento", "monitor", "monitor-group", "next", "next-archive", "nofollow", "noreferrer",
            "original", "payment", "predecessor-version", "prefetch", "prev", "preview", "previous", "prev-archive",
            "privacy-policy", "profile", "related", "replies", "search", "section", "self", "service", "start",
            "stylesheet", "subsection", "successor-version", "tag", "terms-of-service", "timegate", "timemap",
            "type", "up", "version-history", "via", "working-copy", "working-copy-of"));


    protected T currentValue;
    private T value;
    private String propertyName;


    public HalLinkPropertyMatcher(T value, String propertyName) {
        this.value = value;
        this.propertyName = propertyName;
    }

    @Override
    protected boolean matchesSafely(HalLink halLink) {
        currentValue = getCurrentValue(halLink);
        return value.equals(currentValue);
    }

    protected T getCurrentValue(HalLink halLink) {
        throw new NotImplementedException();
    }

    @Override
    public void describeTo(Description description) {
        description
                .appendText("link ")
                .appendText(propertyName)
                .appendText(" to be ")
                .appendValue(value);
    }

    @Override
    protected void describeMismatchSafely(HalLink actual, Description mismatchDescription) {
        mismatchDescription
                .appendText("link ")
                .appendText(propertyName)
                .appendText(" was ")
                .appendValue(currentValue);
    }

    public static HalLinkMatcher hasRel(String rel) {
        return new HalLinkPropertyMatcher<String>(rel, "rel") {

            protected String getCurrentValue(HalLink halLink) {
                return halLink.getRel();
            }
        };
    }

    public static HalLinkMatcher hasHref(String href) {
        return new HalLinkPropertyMatcher<String>(href, "href") {

            protected String getCurrentValue(HalLink halLink) {
                return halLink.getHRef();
            }
        };
    }

    public static HalLinkMatcher isTamplated() {
        return new HalLinkPropertyMatcher<Boolean>(true, "templated") {

            protected Boolean getCurrentValue(HalLink halLink) {
                return halLink.isTemplated();
            }

            @Override
            public void describeTo(Description description) {
                description
                        .appendText("link to be templated");
            }

            protected void describeMismatchSafely(HalLink actual, Description mismatchDescription) {
                mismatchDescription
                        .appendText("link is not templated");
            }

        };
    }

    public static HalLinkMatcher isNotTamplated() {
        return new HalLinkPropertyMatcher<Boolean>(false, "templated") {

            protected Boolean getCurrentValue(HalLink halLink) {
                return halLink.isTemplated();
            }

            @Override
            public void describeTo(Description description) {
                description
                        .appendText("link not to be templated");
            }

            @Override
            protected void describeMismatchSafely(HalLink actual, Description mismatchDescription) {
                mismatchDescription
                        .appendText("link is templated");
            }

        };
    }

    public static HalLinkMatcher hasValidUriAsHRef() {
        return new HalLinkMatcher() {


            @Override
            protected boolean matchesSafely(HalLink halLink) {

                try {

                    if (halLink.isTemplated()) {
                        UriTemplate.buildFromTemplate(halLink.getHRef());
                    } else {
                        new URI(halLink.getHRef());
                    }
                    return true;

                } catch (URISyntaxException e) {
                    return false;
                } catch (MalformedUriTemplateException e) {
                    return false;
                }
            }

            @Override
            public void describeTo(Description description) {
                description
                        .appendText("link to have a valid href uri");
            }

            @Override
            protected void describeMismatchSafely(HalLink actual, Description mismatchDescription) {
                mismatchDescription
                        .appendText("link href is not a valid uri");
            }

        };
    }

    public static HalLinkMatcher hasValidRel() {
        return new HalLinkMatcher() {


            @Override
            protected boolean matchesSafely(HalLink halLink) {

                if (!IANA_RELS.contains(halLink.getRel())) {
                    try {
                        return new URI(halLink.getRel()).isAbsolute();
                    } catch (URISyntaxException e) {
                        return false;
                    }
                }
                return true;
            }

            @Override
            public void describeTo(Description description) {
                description
                        .appendText("link to have a valid rel (must be a valid absolute URI or a IANA register rel)");
            }

            @Override
            protected void describeMismatchSafely(HalLink actual, Description mismatchDescription) {
                mismatchDescription
                        .appendText("link rel is not valid");
            }

        };
    }


    public static HalLinkMatcher href(UriVariableFindMatcher... uriVariableFindMatchers) {
        return new HalLinkExpressionsMatcher(uriVariableFindMatchers);
    }

    public static HalLinkMatcher isHRefValidUrl() {
        return new HalLinkMatcher() {

            @Override
            protected boolean matchesSafely(HalLink halLink) {
                try {
                    new URL(halLink.getHRef());
                    return true;
                } catch (MalformedURLException e) {
                    return false;
                }

            }

            @Override
            public void describeTo(Description description) {
                description
                        .appendText("link to have a valid href url");
            }

            @Override
            protected void describeMismatchSafely(HalLink actual, Description mismatchDescription) {
                mismatchDescription
                        .appendText("link href is not a valid url");
            }

        };
    }


}
