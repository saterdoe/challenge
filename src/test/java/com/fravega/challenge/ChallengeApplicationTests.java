package com.fravega.challenge;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fravega.challenge.entity.Sucursal;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static com.fravega.challenge.util.DistanciaEntreDosPuntos.getDistancia;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureMockMvc
class ChallengeApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void contextLoads() {
	}

	@Test
	public void createSucursalTestSuccess() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
				.post("/sucursal")
				.content(asJsonString(new Sucursal("Test", "-34.6940751", "-58.7305333")))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated())
				.andExpect(MockMvcResultMatchers.jsonPath("id").exists());
	}

	@Test
	public void createSucursalTestFail() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
				.post("/sucursal")
				.content(asJsonString(new Sucursal("Fail", "asd", "ul")))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest());
	}

	@Test
	public void  calculoDistanciasTest() throws Exception {
		String latitud = "-34.69244482541838";
		String longitud = "-58.72990968866752";
		mockMvc.perform(MockMvcRequestBuilders
				.get("/inicio")
				.param("latitud", latitud)
				.param("longitud", longitud)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("direccion").exists());
	}


	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
