create table categoria
(
    id UUID constraint pk_categoria primary key,
    nombre varchar(255),
    descripcion varchar(255),
    activo boolean
);

create table producto
(
    id UUID constraint pk_producto primary key,
    nombre varchar(255),
    descripcion varchar(255),
    activo boolean,
    categoria_id UUID not null constraint fk_producto_categoria references categoria (id),
    precio numeric(12, 3) constraint check_precio check (precio >= 0)
);

