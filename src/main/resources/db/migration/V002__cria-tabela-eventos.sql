create table tb_evento (
	id bigint not null auto_increment,
    tb_usuario_id bigint not null,
    nm_nome varchar(60) not null,
    nm_local varchar(60) not null,
    vl_valor decimal(10,2),
    ds_descricao varchar(255),
    dt_data date,
    
    primary key (id)
);

alter table tb_evento add constraint fk_evento_usuario
foreign key (tb_usuario_id) references tb_usuario (id);