create table tb_usuario (
	id bigint not null auto_increment,
    nm_nome varchar(60) not null,
    nm_email varchar(60) not null,
    ds_password varchar(16),
    
    primary key (id)
);