package com.github.osvadopina.hamcrest.jsonhal;

/*
@SpringBootApplication
@RestController
@EnableHypermediaSupport(type = EnableHypermediaSupport.HypermediaType.HAL)
*/
public class RestControllerTest {
/*
    public static void main(String[] args) {
        SpringApplication.run(RestControllerTest.class, args);

    }

    @RequestMapping("/hal")
    public Resource<Document> getHalDocument() {
        Document document = new Document();

        Document embeddedDocument = new Document();
        document.setFirstProperty("first property");
        document.setSecondProperty(100);

        embeddedDocument.setFirstProperty("first property of embeddeds document");
        embeddedDocument.setSecondProperty(200);

        document.addEmbedded("embeddeds-document", embeddedDocument);

        Link self = linkTo(methodOn(RestControllerTest.class).getHalDocument()).withSelfRel();

        return new Resource<Document>(document, self);
    }
    */
}
