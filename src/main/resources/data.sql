INSERT INTO empresa (cuit, razon_social, fecha_adhesion) VALUES
('20-12345678-1', 'Empresa Uno SA', CURRENT_DATE - 5),
('30-98765432-2', 'Empresa Dos SRL', CURRENT_DATE - 40),
('27-55555555-3', 'Empresa Tres SAS', CURRENT_DATE - 10);

INSERT INTO transferencia (cuit_empresa, importe, cuenta_debito, cuenta_credito, fecha) VALUES
('20-12345678-1', 10000.00, '123-456-789', '987-654-321', CURRENT_DATE - 3),
('30-98765432-2', 5000.00, '111-222-333', '333-222-111', CURRENT_DATE - 20),
('27-55555555-3', 7000.00, '222-333-444', '444-333-222', CURRENT_DATE - 2);