package com.nttdata.bootcamp.debitcardservice;

import com.nttdata.bootcamp.debitcardservice.application.DebitCardOperations;
import com.nttdata.bootcamp.debitcardservice.domain.DebitCard;
import com.nttdata.bootcamp.debitcardservice.infrastructure.model.dto.DebitCardDto;
import com.nttdata.bootcamp.debitcardservice.infrastructure.rest.DebitCardController;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.MockMvc;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

@WebFluxTest(controllers = DebitCardController.class)
public class DebitCardControllerTest {

    @MockBean
    DebitCardOperations operations;

    @Autowired
    private WebTestClient webClient;

    @Test
    void shouldReturnMockedDebitCard() throws Exception {
        DebitCard dc = new DebitCard("4",72352628L,"mainAccount3",
                LocalDate.now().plusYears(4), "789");
        DebitCardDto dto = DebitCardDto.map(dc, BigDecimal.TEN);

        when(operations.get("1")).thenReturn(Mono.just(dto));

        webClient.get().uri("/debit-cards/{id}", "1")
                .header(HttpHeaders.ACCEPT, "application/json")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(DebitCardDto.class);

        Mockito.verify(operations, times(1)).get("1");
    }

}
