package com.stchallenge.application.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.stchallenge.application.port.out.EmpresaPort;
import com.stchallenge.application.port.out.TransferenciaPort;
import com.stchallenge.domain.exception.AdhesionEmpresaException;
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

		AdhesionEmpresaException e = assertThrows(AdhesionEmpresaException.class, () -> {
			service.adherirEmpresa(empresa);
		});

		assertEquals("La empresa con el CUIT 20-12345678-9 ya esta adherida.", e.getMessage());
		verify(empresaPort, never()).guardar(any());
	}
	
	@Test
	void lanzarExcepcionSiElCuitEsNulo() {
		Empresa empresa = new Empresa(null, "Empresa sin CUIT", null);

		AdhesionEmpresaException e = assertThrows(AdhesionEmpresaException.class,
				() -> service.adherirEmpresa(empresa));

		assertEquals("El CUIT no puede ser nulo o vacio.", e.getMessage());
		verifyNoInteractions(empresaPort);
	}
	
	@Test
	void obtenerEmpresasAdheridasUltimoMes() {
		LocalDate hoy = LocalDate.now();
		LocalDate ultimoMes = hoy.minusMonths(1);
		List<Empresa> empresas = List.of(new Empresa("20-11111111-1", "Empresa1", hoy));

		when(empresaPort.buscarAdheridasDesde(any())).thenReturn(empresas);

		List<Empresa> resultado = service.obtenerEmpresasAdheridasUltimoMes();

		assertEquals(1, resultado.size());
		verify(empresaPort).buscarAdheridasDesde(argThat(fecha -> fecha.isEqual(ultimoMes)));
	}

	@Test
	void obtenerEmpresasConTransferenciasUltimoMes() {
		LocalDate hoy = LocalDate.now();
		LocalDate ultimoMes = hoy.minusMonths(1);
		List<Empresa> empresas = List.of(new Empresa("20-22222222-2", "Empresa2", hoy));

		when(transferenciaPort.buscarEmpresasConTransferenciasDesde(any())).thenReturn(empresas);

		List<Empresa> resultado = service.obtenerEmpresasConTransferenciasUltimoMes();

		assertEquals(1, resultado.size());
		verify(transferenciaPort).buscarEmpresasConTransferenciasDesde(argThat(fecha -> fecha.isEqual(ultimoMes)));
	}
}
