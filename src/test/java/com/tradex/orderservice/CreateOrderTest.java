package com.tradex.orderservice;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.jwt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.tradex.orderservice.client.model.OrderCreateRequest;
import com.tradex.orderservice.client.model.OrderCreateRequest.OrderTypeEnum;
import com.tradex.orderservice.client.model.OrderCreateRequest.SideEnum;

@EmbeddedKafka(ports = 9092, partitions = 1, topics = { "kafka.orderCreateTopic" })
@SpringBootTest(classes = TradexOrderServiceApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.AFTER_CLASS)
@AutoConfigureMockMvc
class CreateOrderTest {

	@MockitoBean
	private JwtDecoder jwtDecoder;

	@Autowired
	private MockMvc mockMvc;

	@LocalServerPort
	private int port;

	@Test
	public void saveAndcreateOrderTest() throws Exception {

		OrderCreateRequest body = new OrderCreateRequest();

		body.setOrderType(OrderTypeEnum.LIMIT);
		body.setPrice(BigDecimal.valueOf(1425.50));
		body.setQuantity(10);
		body.setSymbol("RELIANCE");
		body.setSide(SideEnum.BUY);

		mockMvc.perform(
				post("/orders")
						.with(jwt()
								.jwt(jwt -> jwt
										.claim("scope", "order:create")))
						.contentType(MediaType.APPLICATION_JSON)
						.content("""
								{
								    "symbol": "RELIANCE",
								    "quantity": 10,
								    "side": "BUY",
								    "orderType": "LIMIT",
								    "price": 1425.50
								}
								"""))
				.andExpect(status().isCreated());
	}
}
