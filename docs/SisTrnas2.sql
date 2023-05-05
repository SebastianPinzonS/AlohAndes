CREATE TABLE hotel
    (nombre VARCHAR2(20) NOT NULL,
    calificacion NUMBER(2,1) NOT NULL,
    direccion VARCHAR2(40) NOT NULL);

ALTER TABLE hotel
ADD CONSTRAINT PK_hotel
PRIMARY KEY (direccion);  

ALTER TABLE hotel
ADD CONSTRAINT CK_hotel_calificacion
CHECK (calificacion BETWEEN 0.0 AND 5.0);

CREATE TABLE cliente
    (id VARCHAR2(20) NOT NULL,
    tipo_id VARCHAR2(4) NOT NULL,
    nombre VARCHAR2(20) NOT NULL,
    tipo VARCHAR2(20) NOT NULL);
    
ALTER TABLE cliente
ADD CONSTRAINT PK_cliente
PRIMARY KEY (id); 

CREATE TABLE operador
    (id VARCHAR2(20) NOT NULL,
    nombre VARCHAR2(20) NOT NULL,
    tipo VARCHAR2(20) NOT NULL,
    validacion_camara_de_comercio_empresa NUMBER(1,0),
    validacion_super_turismo_empresa NUMBER(1,0),
    miembro_comunidad_universitaria_persona NUMBER(1,0));
    
ALTER TABLE operador
ADD CONSTRAINT CK_empresa_o_persona
CHECK( ((validacion_camara_de_comercio_empresa IS NOT NULL) AND (validacion_super_turismo_empresa IS NOT NULL) AND (miembro_comunidad_universitaria_persona IS NULL)) 
        OR ((validacion_camara_de_comercio_empresa IS NULL) AND (validacion_super_turismo_empresa IS NULL) AND (miembro_comunidad_universitaria_persona IS NOT NULL)));


ALTER TABLE operador
ADD CONSTRAINT PK_operador
PRIMARY KEY (id);

ALTER TABLE operador
ADD CONSTRAINT CK_bool_validacion_camara_de_comercio_empresa
CHECK (validacion_camara_de_comercio_empresa in (1,0));

ALTER TABLE operador
ADD CONSTRAINT CK_bool_validacion_super_turismo_empresa
CHECK (validacion_super_turismo_empresa in (1,0));

ALTER TABLE operador
ADD CONSTRAINT CK_bool_miembro_comunidad_universitaria_persona
CHECK (miembro_comunidad_universitaria_persona in (1,0));


CREATE TABLE apartamento
    (nombre VARCHAR2(40) NOT NULL,
    numero VARCHAR2(10) NOT NULL,
    amoblado NUMBER(1,0) NOT NULL,
    numero_habitaciones NUMBER(3,0) NOT NULL,
    direccion VARCHAR2(40) NOT NULL,
    incluye_servicios NUMBER(1,0) NOT NULL,
    calificacion NUMBER(2,1) NOT NULL);
    
ALTER TABLE apartamento
ADD CONSTRAINT CK_apartamento_calificacion
CHECK (calificacion BETWEEN 0.0 AND 5.0);

ALTER TABLE apartamento
ADD CONSTRAINT PK_apartamento
PRIMARY KEY (numero, direccion);

ALTER TABLE apartamento
ADD CONSTRAINT CK_bool_amoblado
CHECK (amoblado in (1,0));

ALTER TABLE apartamento
ADD CONSTRAINT CK_bool_incluye_servicios
CHECK (incluye_servicios in (1,0));

CREATE TABLE vivienda_express
    (nombre VARCHAR2(20) NOT NULL,
    direccion VARCHAR2(40) NOT NULL,
    numero_habitaciones NUMBER(3,0) NOT NULL,
    menaje VARCHAR2(100) NOT NULL,
    calificacion NUMBER(2,1) NOT NULL);
    
ALTER TABLE vivienda_express
ADD CONSTRAINT PK_viviendaExpress
PRIMARY KEY (direccion);

ALTER TABLE vivienda_express
ADD CONSTRAINT CK_vivienda_express_calificacion
CHECK (calificacion BETWEEN 0.0 AND 5.0);

CREATE TABLE habitacion_hotel
    (tipo VARCHAR2(20) NOT NULL,
    numero_habitacion VARCHAR2(20) NOT NULL,
    tamano NUMBER(20,2) NOT NULL,
    direccion_hotel VARCHAR2(40) NOT NULL);

ALTER TABLE habitacion_hotel
ADD CONSTRAINT FK_direccion_hotel
FOREIGN KEY (direccion_hotel)
REFERENCES hotel(direccion);

ALTER TABLE habitacion_hotel
ADD CONSTRAINT PK_habitacion_hotel
PRIMARY KEY (numero_habitacion, direccion_hotel);

CREATE TABLE instalacion_habitacion_hotel
    (tipo_instalacion VARCHAR2(20) NOT NULL,
    numero_habitacion_hotel_habitacion VARCHAR2(20) NOT NULL,
    direccion_hotel_habitacion_hotel VARCHAR2(40) NOT NULL);

ALTER TABLE instalacion_habitacion_hotel
ADD CONSTRAINT FK_numero_habitacion_y_direccion_habitacion_hotel
FOREIGN KEY (numero_habitacion_hotel_habitacion, direccion_hotel_habitacion_hotel)
REFERENCES habitacion_hotel(numero_habitacion, direccion_hotel);

ALTER TABLE instalacion_habitacion_hotel
ADD CONSTRAINT PK_instalacion_habitacion_hotel
PRIMARY KEY (tipo_instalacion, numero_habitacion_hotel_habitacion, direccion_hotel_habitacion_hotel);

CREATE TABLE servicio_habitacion_hotel
    (tipo_servicio VARCHAR2(20) NOT NULL,
    numero_habitacion_habitacion_hotel VARCHAR2(20) NOT NULL,
    direccion_hotel_habitacion_hotel VARCHAR2(40) NOT NULL);

ALTER TABLE servicio_habitacion_hotel
ADD CONSTRAINT FK_numero_habitacion_y_direccion_habitacion_hotel_ser_hab_hot
FOREIGN KEY (numero_habitacion_habitacion_hotel, direccion_hotel_habitacion_hotel)
REFERENCES habitacion_hotel(numero_habitacion, direccion_hotel);

ALTER TABLE servicio_habitacion_hotel
ADD CONSTRAINT PK_servicio_habitacion_hotel
PRIMARY KEY (tipo_servicio, numero_habitacion_habitacion_hotel, direccion_hotel_habitacion_hotel);

CREATE TABLE hostal
    (nombre VARCHAR2(40) NOT NULL,
    direccion VARCHAR2(40) NOT NULL,
    horario_apertura TIMESTAMP NOT NULL,
    horario_cierre TIMESTAMP NOT NULL,
    calificacion NUMBER(2,1) NOT NULL);

ALTER TABLE hostal
ADD CONSTRAINT PK_hostal
PRIMARY KEY (direccion);

ALTER TABLE hostal
ADD CONSTRAINT CK_hostal_calificacion
CHECK (calificacion BETWEEN 0.0 AND 5.0);

CREATE TABLE habitacion_hostal
    (direccion_hostal VARCHAR2(40) NOT NULL,
    numero_habitacion VARCHAR2(20) NOT NULL,
    tamano NUMBER(20,2) NOT NULL);

ALTER TABLE habitacion_hostal
ADD CONSTRAINT FK_direccion_hostal
FOREIGN KEY (direccion_hostal)
REFERENCES hostal(direccion);

ALTER TABLE habitacion_hostal
ADD CONSTRAINT PK_habitacion_hostal
PRIMARY KEY (direccion_hostal, numero_habitacion);

CREATE TABLE vivienda_universitaria
    (nombre VARCHAR2(40) NOT NULL,
    direccion VARCHAR2(40) NOT NULL,
    numero_apartamento VARCHAR2(20) NOT NULL,
    vivienda_compartida NUMBER(1,0) NOT NULL,
    menaje VARCHAR2(100) NOT NULL,
    capacidad NUMBER(3) NOT NULL,
    calificacion NUMBER(2,1) NOT NULL);

ALTER TABLE vivienda_universitaria
ADD CONSTRAINT PK_vivienda_universitaria
PRIMARY KEY (direccion, numero_apartamento);

ALTER TABLE vivienda_universitaria
ADD CONSTRAINT CK_bool_vivienda_compartida
CHECK (vivienda_compartida in (1,0));

ALTER TABLE vivienda_universitaria
ADD CONSTRAINT CK_vivienda_universitaria_calificacion
CHECK (calificacion BETWEEN 0.0 AND 5.0);

CREATE TABLE vivienda_habitacion
    (nombre VARCHAR2(40) NOT NULL,
    direccion VARCHAR2(40) NOT NULL,
    numero_apartamento VARCHAR2(20) NOT NULL,
    calificacion NUMBER(2,1) NOT NULL);

ALTER TABLE vivienda_habitacion
ADD CONSTRAINT PK_vivienda_habitacion
PRIMARY KEY (direccion, numero_apartamento);

ALTER TABLE vivienda_habitacion
ADD CONSTRAINT CK_vivienda_habitacion_calificacion
CHECK (calificacion BETWEEN 0.0 AND 5.0);

CREATE TABLE servicio_habitacion
    (tipo VARCHAR2(20) NOT NULL,
    precio NUMBER(38,0) NOT NULL);

ALTER TABLE servicio_habitacion
ADD CONSTRAINT PK_servicio_habitacion
PRIMARY KEY (tipo);

CREATE TABLE vivienda_habitacion_servicio_habitacion
    (direccion_vivienda_habitacion VARCHAR2(40) NOT NULL,
    numero_vivienda_habitacion VARCHAR2(20) NOT NULL,
    tipo_servicio_habitacion VARCHAR2(20) NOT NULL);

ALTER TABLE vivienda_habitacion_servicio_habitacion
ADD CONSTRAINT FK_numero_habitacion_y_direccion_vivienda_habitacion
FOREIGN KEY (direccion_vivienda_habitacion, numero_vivienda_habitacion)
REFERENCES vivienda_habitacion(direccion, numero_apartamento);

ALTER TABLE vivienda_habitacion_servicio_habitacion
ADD CONSTRAINT FK_tipo_servicio_habitacion
FOREIGN KEY (tipo_servicio_habitacion)
REFERENCES servicio_habitacion(tipo);

ALTER TABLE vivienda_habitacion_servicio_habitacion
ADD CONSTRAINT PK_vivienda_habitacion_servicio_habitacion
PRIMARY KEY (direccion_vivienda_habitacion, numero_vivienda_habitacion, tipo_servicio_habitacion);

CREATE TABLE oferta
    (id NUMERIC(38,0) NOT NULL,
    visitas NUMERIC(38,0) NOT NULL,
    fecha_inicial DATE NOT NULL,
    duracion_contrato_dias NUMBER(10,0) NOT NULL,
    costo_contrato NUMBER(20,0) NOT NULL,
    precio_especial NUMBER(20,0) NOT NULL,
    condicion_precio_especial VARCHAR2(200) NOT NULL,
    costo_adicional_servicios NUMBER(20,0) NOT NULL,
    costo_seguro_arrendamiento NUMBER(20,0) NOT NULL,
    id_operador VARCHAR2(20) NOT NULL,
    direccion_hostal_habitacion_hostal VARCHAR2(40),
    numero_habitacion_habitacion_hostal VARCHAR2(20),
    direccion_hotel_habitacion_hotel VARCHAR2(40),
    numero_habitacion_habitacion_hotel VARCHAR2(20),
    direccion_vivienda_universitaria VARCHAR2(40),
    numero_apartamento_vivienda_universitaria VARCHAR2(20),
    direccion_vivienda_habitacion VARCHAR2(40),
    numero_apartamento_vivienda_habitacion VARCHAR2(20),
    direccion_apartamento VARCHAR2(40),
    numero_apartamento VARCHAR2(20),
    direccion_vivienda_express VARCHAR2(40));
    
ALTER TABLE oferta
ADD CONSTRAINT CK_oferta_solo_un_lugar
CHECK((direccion_hostal_habitacion_hostal IS NOT NULL  AND numero_habitacion_habitacion_hostal IS NOT NULL) 
OR (direccion_hotel_habitacion_hotel IS NOT NULL AND numero_habitacion_habitacion_hotel IS NOT NULL) 
OR (direccion_vivienda_universitaria IS NOT NULL AND numero_apartamento_vivienda_universitaria IS NOT NULL)
OR (direccion_vivienda_habitacion IS NOT NULL AND numero_apartamento_vivienda_habitacion IS NOT NULL)
OR (direccion_apartamento IS NOT NULL AND numero_apartamento IS NOT NULL)
OR (direccion_vivienda_express IS NOT NULL));
    
ALTER TABLE oferta
ADD CONSTRAINT PK_oferta
PRIMARY KEY (id);

ALTER TABLE oferta
ADD CONSTRAINT FK_id_operador
FOREIGN KEY (id_operador)
REFERENCES operador(id);

ALTER TABLE oferta
ADD CONSTRAINT FK_direccion_y_numero_habitacion_hostal
FOREIGN KEY (direccion_hostal_habitacion_hostal,numero_habitacion_habitacion_hostal)
REFERENCES habitacion_hostal(direccion_hostal,numero_habitacion);

ALTER TABLE oferta
ADD CONSTRAINT FK_numero_habitacion_y_direccion_habitacion_hotel_oferta
FOREIGN KEY (numero_habitacion_habitacion_hotel, direccion_hotel_habitacion_hotel)
REFERENCES habitacion_hotel(numero_habitacion, direccion_hotel);

ALTER TABLE oferta
ADD CONSTRAINT FK_numero_habitacion_y_direccion_vivienda_universitaria
FOREIGN KEY (numero_apartamento_vivienda_universitaria, direccion_vivienda_universitaria)
REFERENCES vivienda_universitaria(numero_apartamento, direccion);

ALTER TABLE oferta
ADD CONSTRAINT FK_numero_apartamento_y_direccion_vivienda_habitacion
FOREIGN KEY (direccion_vivienda_habitacion, numero_apartamento_vivienda_habitacion)
REFERENCES vivienda_habitacion(direccion, numero_apartamento);

ALTER TABLE oferta
ADD CONSTRAINT FK_numero_y_direccion_apartamento
FOREIGN KEY (direccion_apartamento, numero_apartamento)
REFERENCES apartamento(direccion, numero);

ALTER TABLE oferta
ADD CONSTRAINT FK_direccion_vivienda_express
FOREIGN KEY (direccion_vivienda_express)
REFERENCES vivienda_express(direccion);

CREATE SEQUENCE AlohAndes_sequence
START WITH 40
INCREMENT BY 1
NOCYCLE;

CREATE TABLE reserva
    (id_oferta NUMBER(38,0) NOT NULL,
    id_cliente VARCHAR2(20) NOT NULL,
    precio_especial_tomado NUMBER(1,0) NOT NULL);

ALTER TABLE reserva
ADD CONSTRAINT PK_reserva
PRIMARY KEY (id_oferta, id_cliente);

ALTER TABLE reserva
ADD CONSTRAINT CK_precio_especial_tomado
CHECK (precio_especial_tomado in (1,0));

ALTER TABLE reserva
ADD CONSTRAINT FK_id_oferta
FOREIGN KEY (id_oferta)
REFERENCES oferta(id);

ALTER TABLE reserva
ADD CONSTRAINT FK_id_cliente
FOREIGN KEY (id_cliente)
REFERENCES cliente(id);

--RFC1  
SELECT dinero.id_operador, nombres.nombre, dinero.dinero_total, dinero.dinero_ano
FROM
    (SELECT total.id_operador, total.dinero_total, ano.dinero_ano
    FROM
        (SELECT id_operador, SUM(costo_contrato) AS dinero_total
        FROM oferta
        WHERE id_cliente IS NOT NULL
        GROUP BY id_operador) total
        FULL OUTER JOIN
        (SELECT id_operador, SUM(costo_contrato) AS dinero_ano
        FROM oferta
        WHERE (id_cliente IS NOT NULL)
        AND (fecha_inicial >= to_date('01-01-2022','DD-MM-YYYY'))
        GROUP BY id_operador) ano
        ON total.id_operador = ano.id_operador) dinero
    INNER JOIN
    (SELECT id, nombre
    FROM operador) nombres
    ON dinero.id_operador = nombres.id
    ORDER BY dinero.id_operador;

--RFC2
SELECT id, visitas, costo_contrato, fecha_inicial
FROM oferta
ORDER BY visitas DESC
FETCH FIRST 20 ROWS ONLY;

--RFC4 
SELECT id, costo_contrato, precio_especial, fecha_inicial, fecha_inicial+duracion_contrato_dias AS fecha_final, duracion_contrato_dias
FROM oferta
WHERE (fecha_inicial >= to_date('01-01-2021','DD-MM-YYYY')) 
AND (fecha_inicial+duracion_contrato_dias <= to_date('01-01-2022','DD-MM-YYYY'));

--RFC5
-- Tabla clientes
SELECT clientes.id, clientes.tipo,
    ofertas.DIRECCION_HOSTAL_HABITACION_HOSTAL, ofertas.NUMERO_HABITACION_HABITACION_HOSTAL, 
    ofertas.DIRECCION_HOTEL_HABITACION_HOTEL, ofertas.NUMERO_HABITACION_HABITACION_HOTEL,
    ofertas.DIRECCION_VIVIENDA_UNIVERSITARIA, ofertas.NUMERO_APARTAMENTO_VIVIENDA_UNIVERSITARIA,
    ofertas.DIRECCION_VIVIENDA_HABITACION, ofertas.NUMERO_APARTAMENTO_VIVIENDA_HABITACION,
    ofertas.DIRECCION_APARTAMENTO, ofertas.NUMERO_APARTAMENTO, ofertas.DIRECCION_VIVIENDA_EXPRESS
FROM
    (SELECT id, id_cliente, 
        NVL(DIRECCION_HOSTAL_HABITACION_HOSTAL,'No Aplica') AS DIRECCION_HOSTAL_HABITACION_HOSTAL, NVL(NUMERO_HABITACION_HABITACION_HOSTAL,'No Aplica') AS NUMERO_HABITACION_HABITACION_HOSTAL, 
        NVL(DIRECCION_HOTEL_HABITACION_HOTEL,'No Aplica') AS DIRECCION_HOTEL_HABITACION_HOTEL, NVL(NUMERO_HABITACION_HABITACION_HOTEL,'No Aplica') AS NUMERO_HABITACION_HABITACION_HOTEL, 
        NVL(DIRECCION_VIVIENDA_UNIVERSITARIA,'No Aplica') AS DIRECCION_VIVIENDA_UNIVERSITARIA, NVL(NUMERO_APARTAMENTO_VIVIENDA_UNIVERSITARIA,'No Aplica') AS NUMERO_APARTAMENTO_VIVIENDA_UNIVERSITARIA, 
        NVL(DIRECCION_VIVIENDA_HABITACION,'No Aplica') AS DIRECCION_VIVIENDA_HABITACION, NVL(NUMERO_APARTAMENTO_VIVIENDA_HABITACION,'No Aplica') AS NUMERO_APARTAMENTO_VIVIENDA_HABITACION, 
        NVL(DIRECCION_APARTAMENTO,'No Aplica') AS DIRECCION_APARTAMENTO, NVL(NUMERO_APARTAMENTO,'No Aplica') AS NUMERO_APARTAMENTO, 
        NVL(DIRECCION_VIVIENDA_EXPRESS,'No Aplica') AS DIRECCION_VIVIENDA_EXPRESS
    FROM oferta
    WHERE id_cliente IS NOT NULL) ofertas
    LEFT OUTER JOIN
    (SELECT id, nombre, tipo
    FROM cliente) clientes
    ON clientes.id = ofertas.id_cliente
    ORDER BY clientes.tipo, clientes.id DESC;

--Tabla Operadores
SELECT operadores.id, operadores.nombre,
    ofertas.DIRECCION_HOSTAL_HABITACION_HOSTAL, ofertas.NUMERO_HABITACION_HABITACION_HOSTAL, 
    ofertas.DIRECCION_HOTEL_HABITACION_HOTEL, ofertas.NUMERO_HABITACION_HABITACION_HOTEL,
    ofertas.DIRECCION_VIVIENDA_UNIVERSITARIA, ofertas.NUMERO_APARTAMENTO_VIVIENDA_UNIVERSITARIA,
    ofertas.DIRECCION_VIVIENDA_HABITACION, ofertas.NUMERO_APARTAMENTO_VIVIENDA_HABITACION,
    ofertas.DIRECCION_APARTAMENTO, ofertas.NUMERO_APARTAMENTO, ofertas.DIRECCION_VIVIENDA_EXPRESS
FROM
    (SELECT id, id_operador, 
            NVL(DIRECCION_HOSTAL_HABITACION_HOSTAL,'No Aplica') AS DIRECCION_HOSTAL_HABITACION_HOSTAL, NVL(NUMERO_HABITACION_HABITACION_HOSTAL,'No Aplica') AS NUMERO_HABITACION_HABITACION_HOSTAL, 
            NVL(DIRECCION_HOTEL_HABITACION_HOTEL,'No Aplica') AS DIRECCION_HOTEL_HABITACION_HOTEL, NVL(NUMERO_HABITACION_HABITACION_HOTEL,'No Aplica') AS NUMERO_HABITACION_HABITACION_HOTEL, 
            NVL(DIRECCION_VIVIENDA_UNIVERSITARIA,'No Aplica') AS DIRECCION_VIVIENDA_UNIVERSITARIA, NVL(NUMERO_APARTAMENTO_VIVIENDA_UNIVERSITARIA,'No Aplica') AS NUMERO_APARTAMENTO_VIVIENDA_UNIVERSITARIA, 
            NVL(DIRECCION_VIVIENDA_HABITACION,'No Aplica') AS DIRECCION_VIVIENDA_HABITACION, NVL(NUMERO_APARTAMENTO_VIVIENDA_HABITACION,'No Aplica') AS NUMERO_APARTAMENTO_VIVIENDA_HABITACION, 
            NVL(DIRECCION_APARTAMENTO,'No Aplica') AS DIRECCION_APARTAMENTO, NVL(NUMERO_APARTAMENTO,'No Aplica') AS NUMERO_APARTAMENTO, 
            NVL(DIRECCION_VIVIENDA_EXPRESS,'No Aplica') AS DIRECCION_VIVIENDA_EXPRESS
    FROM oferta) ofertas
    RIGHT OUTER JOIN
    (SELECT id, nombre
    FROM operador
    WHERE miembro_comunidad_universitaria_persona = 1) operadores
    ON ofertas.id_operador = operadores.id;
    
--RFC6
SELECT id_cliente, fecha_inicial, duracion_contrato_dias, costo_contrato, costo_adicional_servicios, costo_seguro_arrendamiento, precio_especial, precio_especial_tomado,
    NVL(DIRECCION_HOSTAL_HABITACION_HOSTAL,'No Aplica') AS DIRECCION_HOSTAL_HABITACION_HOSTAL, NVL(NUMERO_HABITACION_HABITACION_HOSTAL,'No Aplica') AS NUMERO_HABITACION_HABITACION_HOSTAL, 
    NVL(DIRECCION_HOTEL_HABITACION_HOTEL,'No Aplica') AS DIRECCION_HOTEL_HABITACION_HOTEL, NVL(NUMERO_HABITACION_HABITACION_HOTEL,'No Aplica') AS NUMERO_HABITACION_HABITACION_HOTEL, 
    NVL(DIRECCION_VIVIENDA_UNIVERSITARIA,'No Aplica') AS DIRECCION_VIVIENDA_UNIVERSITARIA, NVL(NUMERO_APARTAMENTO_VIVIENDA_UNIVERSITARIA,'No Aplica') AS NUMERO_APARTAMENTO_VIVIENDA_UNIVERSITARIA, 
    NVL(DIRECCION_VIVIENDA_HABITACION,'No Aplica') AS DIRECCION_VIVIENDA_HABITACION, NVL(NUMERO_APARTAMENTO_VIVIENDA_HABITACION,'No Aplica') AS NUMERO_APARTAMENTO_VIVIENDA_HABITACION, 
    NVL(DIRECCION_APARTAMENTO,'No Aplica') AS DIRECCION_APARTAMENTO, NVL(NUMERO_APARTAMENTO,'No Aplica') AS NUMERO_APARTAMENTO, 
    NVL(DIRECCION_VIVIENDA_EXPRESS,'No Aplica') AS DIRECCION_VIVIENDA_EXPRESS
FROM oferta
WHERE id_cliente = 10;

SELECT *
FROM apartamento
WHERE (direccion = 'Av 65 Kr 34') AND (numero = 'C405') ;