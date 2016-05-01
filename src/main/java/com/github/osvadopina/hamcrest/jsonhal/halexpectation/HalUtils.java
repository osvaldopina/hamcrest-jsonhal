package com.github.osvadopina.hamcrest.jsonhal.halexpectation;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HalUtils {

    public static List<HalLink> getLinks(String hal) throws IOException {

        ObjectMapper mapper = new ObjectMapper();

        Map<String,Object> parsedHal = mapper.readValue(hal, Map.class);

        Map<String,Object> halLinks = (Map<String,Object>) parsedHal.get("_links");

        List<HalLink> links = new ArrayList<HalLink>();


        for(Map.Entry<String,Object> link: halLinks.entrySet()) {
            Map<String,Object> linkProps = (Map<String,Object>) link.getValue();
          links.add(new HalLink(link.getKey(), linkProps));
        }
        return links;
    }

    public static class Teste {
        public Teste(String a) {

        }

        public void a() {

        }
    }
}
