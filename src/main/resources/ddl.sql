create table foto_solar_panel (
    solar_panel_id bigint not null,
    tamanho bigint,
    content_type varchar(255),
    descricao varchar(255),
    nome_arquivo varchar(255),

    primary key (solar_panel_id)
) engine=InnoDB;

create table solar_panel (
    id bigint not null auto_increment,
    efficiency integer not null,
    maximum_power integer not null,
    price decimal(38,2) not null,
    brand varchar(255) not null,
    model varchar(255) not null,
    panel_type varchar(255) not null,

    primary key (id)
) engine=InnoDB;

alter table foto_solar_panel add constraint FK16o88ii9rq228ewwb8dfuuf8r foreign key (solar_panel_id) references solar_panel (id);
