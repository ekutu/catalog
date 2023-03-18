package com.nexus.catalog;

import org.junit.jupiter.api.Test;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

import com.nexus.catalog.domain.Book;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ConfigurationPropertiesScan
@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("integration")
class NexusApplicationTests {

    @Autowired
    private WebTestClient webTestClient;

	@Test
	void contextLoads() {
        Book expectedBook = Book.of("2231231231", "Main Titles", "Mafulacha Author", new BigDecimal(12.90), "Polarsophia");

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
