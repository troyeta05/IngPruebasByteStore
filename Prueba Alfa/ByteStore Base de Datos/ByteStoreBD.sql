-- Tabla de Cuentas
create table Cuentas (
  numCuenta varchar(10) primary key,
  correoCuenta varchar(50) not null unique,
  contrasena varchar(255) not null,
  pseudonimoCuenta varchar(30) not null,
  tipoCuenta tinyint(1) default 0, -- 0 = cliente, 1 = admin
  codigoCuenta varchar(6) not null,
  estadoCuenta boolean default true
);

-- Tabla de Productos
create table Productos (
  idProducto varchar(10) primary key,
  nombreProducto varchar(50) not null,
  precioProducto decimal(9,2) not null,
  stockProducto int default 0,
  categoriaProducto varchar(20) not null,
  marcaProducto varchar(25) not null,
  formatoProducto tinyint(1) not null, -- 0 = digital, 1 = físico
  imagenProducto blob not null,
  estadoProducto boolean default true
);

INSERT INTO Productos (
  idProducto, nombreProducto, precioProducto, stockProducto,
  categoriaProducto, marcaProducto, formatoProducto,
  imagenProducto, estadoProducto
)
VALUES
('0308122305', 'Mouse inalámbrico', 299.99, 50, 'Accesorios', 'Logitech', 1,'', true),
('1100154509', 'Teclado mecánico RGB', 499.99, 40, 'Accesorios', 'Redragon', 1, '', true),
('0709233002', 'Monitor 24" Full HD', 1899.99, 20, 'Pantallas', 'Samsung', 1, '', true),
('0206215907', 'Laptop Core i5 8GB RAM', 8999.99, 15, 'Computadoras', 'HP', 1, '', true),
('1203181203', 'Audífonos Bluetooth', 699.99, 60, 'Audio', 'Sony', 1, '', true),
('0604172706', 'Licencia Windos 20 Pro', 659.99, 25, 'Licencias', 'Windows', 0, '', true),
('0503198804', 'Disco duro externo 1TB', 1099.99, 35, 'Almacenamiento', 'Seagate', 1, '', true),
('0802145502', 'Memoria USB 64GB', 199.99, 100, 'Almacenamiento', 'Kingston', 1, '', true),
('1103160107', 'Cargador rápido USB-C', 249.99, 80, 'Accesorios', 'Anker', 1, '', true),
('0305146702', 'Adaptador HDMI a VGA', 149.99, 70, 'Accesorios', 'Ugreen', 1, '', true),
('0907213608', 'Base para laptop ajustable', 299.99, 40, 'Accesorios', 'Nulaxy', 1, '', true),
('0500152201', 'Cable USB tipo C 1m', 99.99, 120, 'Accesorios', 'Belkin', 1, '', true),
('0209142304', 'Licencia antivirus 1 año', 499.00, 100, 'Software', 'Kaspersky', 0, '', true),
('0305191806', 'Suscripción mensual a VPN', 199.00, 150, 'Seguridad', 'NordVPN', 0, '', true),
('0908217701', 'Ebook: Fundamentos de programación', 249.00, 180, 'Libros', 'O’Reilly', 0,'', true),
('1209228404', 'SSD interno 500GB', 1599.99, 25, 'Almacenamiento', 'Crucial', 1, '', true);

-- Tabla Carrito
create table Carrito (
  numCarrito varchar(10) primary key,
  numCuenta varchar(10) not null,
  foreign key (numCuenta) references Cuentas(numCuenta)
);

-- Tabla Detalle Carrito
create table DetCarrito (
  numCarrito varchar(10),
  idProducto varchar(10),
  cantProducto int default 0,
  precioProducto decimal(9,2) not null,
  primary key (numCarrito, idProducto),
  foreign key (numCarrito) references Carrito(numCarrito),
  foreign key (idProducto) references Productos(idProducto)
);