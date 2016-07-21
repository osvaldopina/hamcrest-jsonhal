package com.github.osvadopina.hamcrest.jsonhal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;


@SpringBootApplication
@RestController
@EnableHypermediaSupport(type = EnableHypermediaSupport.HypermediaType.HAL)
public class RestControllerTest {

    public static void main(String[] args) {
        SpringApplication.run(RestControllerTest.class, args);

    }

    @RequestMapping("/hal")
    public Resource<Document> getHalDocument() {
        Document document = new Document();

        Document embeddedDocument = new Document();
        document.setFirstProperty("first property");
        document.setSecondProperty(100);

        embeddedDocument.setFirstProperty("first property of embedded document");
        embeddedDocument.setSecondProperty(200);

        document.addEmbedded("embedded-document", embeddedDocument);

        Link self = linkTo(methodOn(RestControllerTest.class).getHalDocument()).withSelfRel();

        return new Resource<Document>(document, self);
    }
}
