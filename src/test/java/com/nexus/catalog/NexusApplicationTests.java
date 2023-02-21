package com.nexus.catalog;

import org.junit.jupiter.api.Test;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

import com.nexus.catalog.domain.Book;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class NexusApplicationTests {

    @Autowired
    private WebTestClient webTestClient;

	@Test
	void contextLoads() {
        Book expectedBook = new Book("1231231231", "Title", "Author", new BigDecimal(9.90));

        webTestClient.post()
                     .uri("/books")
                     .bodyValue(expectedBook)
                     .exchange()
                     .expectStatus()
                     .isCreated()
                     .expectBody(Book.class)
                     .value(actualBook -> {
                         assertThat(actualBook).isNotNull();
                         assertThat(actualBook.isbn()).isEqualTo(expectedBook.isbn());
                     });
	}

}
