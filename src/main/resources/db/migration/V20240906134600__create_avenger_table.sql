create table tb_avenger (
    id bigserial not null,
    nick varchar(36),
    person varchar(128),
    description varchar(128),
    history text,
    primary key (id)
);

alter table tb_avenger add constraint UK_5r88eemotwgru6k0ilqb2ledh unique (nick);