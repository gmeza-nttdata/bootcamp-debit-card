package com.nttdata.bootcamp.debitcardservice;

import com.nttdata.bootcamp.debitcardservice.application.DebitCardOperations;
import com.nttdata.bootcamp.debitcardservice.application.repository.DebitCardRepository;
import com.nttdata.bootcamp.debitcardservice.domain.DebitCard;
import com.nttdata.bootcamp.debitcardservice.infrastructure.model.dto.DebitCardDto;
import com.nttdata.bootcamp.debitcardservice.infrastructure.rest.DebitCardController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import reactor.core.publisher.Mono;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.time.LocalDate;

@SpringBootTest
class DebitCardApplicationTests {

	@Test
	void contextLoads() {
	}



}
