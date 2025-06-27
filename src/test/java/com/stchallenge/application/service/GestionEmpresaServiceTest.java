package com.stchallenge.application.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.stchallenge.application.port.out.EmpresaPort;
import com.stchallenge.application.port.out.TransferenciaPort;
import com.stchallenge.domain.exception.BusinessException;
import com.stchallenge.domain.model.Empresa;

class GestionEmpresaServiceTest {

	private EmpresaPort empresaPort;
	private TransferenciaPort transferenciaPort;
	private GestionEmpresaService service;

	@BeforeEach
	void setUp() {
		empresaPort = mock(EmpresaPort.class);
		transferenciaPort = mock(TransferenciaPort.class);
		service = new GestionEmpresaService(empresaPort, transferenciaPort);
	}

	@Test
	void guardarEmpresaSiNoEstaAdherida() {
		String cuit = "20-12345678-1";
		Empresa empresa = new Empresa(cuit, "Empresa Nueva", null);

		when(empresaPort.existeAdheridaPorCuit(cuit)).thenReturn(false);
		when(empresaPort.guardar(any())).thenAnswer(invocation -> invocation.getArgument(0));

		Empresa result = service.adherirEmpresa(empresa);

		assertEquals(cuit, result.cuit());
		assertEquals("Empresa Nueva", result.razonSocial());
		assertNotNull(result.fechaAdhesion());

		verify(empresaPort).guardar(any());
	}

	@Test
	void lanzarExcepcionSiEmpresaYaEstaAdherida() {
		String cuit = "20-12345678-9";
		Empresa empresa = new Empresa(cuit, "Empresa Adherida", null);

		when(empresaPort.existeAdheridaPorCuit(cuit)).thenReturn(true);

		BusinessException e = assertThrows(BusinessException.class, () -> {
			service.adherirEmpresa(empresa);
		});

		assertEquals("La empresa con el CUIT 20-12345678-9 ya esta adherida.", e.getMessage());
		verify(empresaPort, never()).guardar(any());
	}

}
