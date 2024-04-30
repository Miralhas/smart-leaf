set foreign_key_checks = 0;

delete from solar_panel;
delete from foto_solar_panel;

set foreign_key_checks = 1;

alter table solar_panel auto_increment = 1;

INSERT INTO solar_panel(brand, model, maximum_power, efficiency, panel_type, price) VALUES ('Resun', 'RS6E-150P', 150, 15, 'Policristalino ', 260.15);
INSERT INTO solar_panel(brand, model, maximum_power, efficiency, panel_type, price) VALUES ('Canadian ', 'Half-Cell - CS6R 435T', 435, 22.3, 'Monocristalino ', 593.10);
INSERT INTO solar_panel(brand, model, maximum_power, efficiency, panel_type, price) VALUES ('ArchySun', 'ATP-3', 200, 16.3, 'Monocristalino ', 300.00);
INSERT INTO solar_panel(brand, model, maximum_power, efficiency, panel_type, price) VALUES ('TTP-Power ', 'TTP-Cell', 200, 20, 'Policristalino ', 339.99);
INSERT INTO solar_panel(brand, model, maximum_power, efficiency, panel_type, price) VALUES ('Visual Energy ', 'ARX-4', 300, 20, 'Monocristalino ', 500.00);
INSERT INTO solar_panel(brand, model, maximum_power, efficiency, panel_type, price) VALUES ('Vast Life ', 'OT-333', 150, 13, 'Policristalino ', 200.00);
