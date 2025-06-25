package com.stchallenge.application.port.out;

import com.stchallenge.domain.model.Empresa;

public interface EmpresaPort {
	Empresa guardar(Empresa empresa);
}
