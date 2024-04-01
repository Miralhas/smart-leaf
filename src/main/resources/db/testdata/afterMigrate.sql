set foreign_key_checks = 0;

delete from solar_panel;

set foreign_key_checks = 1;

alter table solar_panel auto_increment = 1;

INSERT INTO solar_panel(brand, model, maximum_power, efficiency, panel_type, price) VALUES ('Resun', 'RS6E-150P', 150, 15, 'Policristalino ', 260.15);
INSERT INTO solar_panel(brand, model, maximum_power, efficiency, panel_type, price) VALUES ('Canadian ', 'Half-Cell - CS6R 435T', 435, 22.3, 'Monocristalino ', 593.10);
