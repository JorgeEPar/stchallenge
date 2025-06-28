package com.stchallenge;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class GestionEmpresaIntegrationTest {

	private static final String ADHERIR_PATH = "/empresas/adherir";

	@Autowired
	private MockMvc mockMvc;

	@Test
	void adherirEmpresaConRequestCorrecto() throws Exception {
		String requestOK = """
				    {
				      "cuit": "20-12345678-9",
				      "razon_social": "Empresa Test"
				    }
				""";
		mockMvc.perform(post(ADHERIR_PATH).contentType(MediaType.APPLICATION_JSON).content(requestOK))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.cuit").value("20-12345678-9"))
				.andExpect(jsonPath("$.razon_social").value("Empresa Test"))
				.andExpect(jsonPath("$.fecha_adhesion").value(notNullValue()));
	}

	@Test
	void rechazarAdhesionConCuitInvalido() throws Exception {
		String requestCuitInvalido = """
				    {
				      "cuit": "123",
				      "razonSocial": "Empresa Test"
				    }
				""";
		mockMvc.perform(post(ADHERIR_PATH).contentType(MediaType.APPLICATION_JSON).content(requestCuitInvalido))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.message").value(containsString("El CUIT posee formato invalido")));
	}

	@Test
	void rechazarAdhesionSinCuit() throws Exception {
		String requestSinCuit = """
				    {
					 "razon_social": "Empresa Test"
				    }
				""";
		mockMvc.perform(post(ADHERIR_PATH).contentType(MediaType.APPLICATION_JSON).content(requestSinCuit))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.message").value(containsString("El CUIT es obligatorio")));
	}

	@Test
	void obtenerEmpresasAdheridasElUltimoMes() throws Exception {
		mockMvc.perform(get("/empresas/adheridas-ultimo-mes"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].cuit").value("20-12345678-1"))
				.andExpect(jsonPath("$[0].razon_social").value("Empresa Uno SA"))
				.andExpect(jsonPath("$[0].fecha_adhesion").value(notNullValue()))
				.andExpect(jsonPath("$[1].cuit").value("27-55555555-3"))
				.andExpect(jsonPath("$[1].razon_social").value("Empresa Tres SAS"))
				.andExpect(jsonPath("$[1].fecha_adhesion").value(notNullValue()));
	}

	@Test
	void obtenerTransferenciasDelUltimoMes() throws Exception {
		mockMvc.perform(get("/empresas/transferencias-ultimo-mes"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].cuit").value("20-12345678-1"))
				.andExpect(jsonPath("$[0].razon_social").value("Empresa Uno SA"))
				.andExpect(jsonPath("$[0].fecha_adhesion").value(notNullValue()))
				.andExpect(jsonPath("$[1].cuit").value("27-99999999-4"))
				.andExpect(jsonPath("$[1].razon_social").value("Empresa Cuatro SAS"))
				.andExpect(jsonPath("$[1].fecha_adhesion").value(notNullValue()));
	}

}
