DROP Table Bookings;
DROP Table BookingStatus;
DROP Table Users;
DROP Table UserStatus;
DROP Table UserType;
DROP Table Drivers;
DROP Table Customers;
DROP Table Configurations;

-- --------------------------------------------------------
CREATE TABLE Customers (
  Id int NOT NULL GENERATED ALWAYS AS IDENTITY
        (START WITH 1, INCREMENT BY 1),
  FirstName varchar(20),
  LastName varchar(20),
  Address varchar(60),
  PRIMARY KEY (Id)
);


INSERT INTO Customers (FirstName, LastName, Address) VALUES
('Eva', 'Smith', '129 Station Rd, London, N3 2AS'),
('Rob', 'Manton', '23 Bow Lane, London, N3'),
('Bob', 'Currie', '54 Teignmouth Rd, London, NW2'),
('Jim', 'Hunter', '765 High Road, London, N12'),
('Phil', 'Johnson', '75 Squires Lane, London, N3'),
('Saim', 'Soyler', '2 Rosemary Ave, London, N3'),
('Gul', 'Hikmet', '31 Clifton Rd, London, N3 2SG'),
('FirstNameTest', 'LastNameTest', 'AddressTest');

-- --------------------------------------------------------
CREATE TABLE Drivers (
  Id int NOT NULL GENERATED ALWAYS AS IDENTITY
        (START WITH 1, INCREMENT BY 1),
  FirstName varchar(20),
  LastName varchar(20),
  Registration varchar(10) NOT NULL,
  PRIMARY KEY (Id)
);

INSERT INTO Drivers (FirstName, LastName, Registration) VALUES
('John', 'Smith', 'AK52VZV'),
('Mehmet', 'Aydin', 'BN60WKA'),
('Mark', 'Johnson','R34AKP'),
('Chris', 'Simmons', 'B23GFE'),
('FirstNameTest', 'LastNameTest', 'T00TTT');

-- --------------------------------------------------------
CREATE TABLE UserType (
  Id int NOT NULL,
  UserType varchar(20),
  PRIMARY KEY (Id)
);

INSERT INTO UserType (Id, UserType) VALUES
(1, 'Admin'),
(2, 'Driver'),
(4, 'Customer');

-- --------------------------------------------------------
CREATE TABLE UserStatus (
  Id int NOT NULL,
  Status varchar(20),
  PRIMARY KEY (Id)
);

INSERT INTO UserStatus (Id, Status) VALUES
(1, 'Unappoved'),
(2, 'Active'),
(4, 'Inactive');

---------------------------------------------------------
CREATE TABLE Users (
  Id int NOT NULL GENERATED ALWAYS AS IDENTITY
        (START WITH 1, INCREMENT BY 1),
  Username varchar(20) NOT NULL,
  Password varchar(20) NOT NULL,
  UserTypeId int NOT NULL,
  CustomerId int DEFAULT NULL,
  DriverId int DEFAULT NULL,
  UserStatusId int NOT NULL,
  PRIMARY KEY (Id)
);

Alter table Users add foreign key (UserTypeId) references UserType;
Alter table Users add foreign key (CustomerId) references Customers;
Alter table Users add foreign key (DriverId) references Drivers;
Alter table Users add foreign key (UserStatusId) references UserStatus;

INSERT INTO Users (Username, Password, UserTypeId, CustomerId, DriverId, UserStatusId) VALUES
('meaydin', '201068', 2, NULL, 2, 2),
('aydinme', '108752', 1, NULL, NULL, 2),
('csimons', '010563', 2, NULL, 4, 2),
('jsmith', '465844', 2, NULL, 1, 2),
('mjohnson', '548657', 2, NULL, 3, 2),
('esmith', '457845', 4, 1, NULL, 2),
('rmanton', '745486', 4, 2, NULL, 2),
('bcurrie', '784596', 4, 3, NULL, 2),
('jhunter', '124956', 4, 4, NULL, 2),
('pjohnson', '258467', 4, 5, NULL, 2),
('ssoyler', '988654', 4, 6, NULL, 2),
('ghikmet', '224969', 4, 7, NULL, 2),
('testa', 'test', 1, NULL, NULL, 2),
('testd', 'test', 2, NULL, 5, 2),
('testc', 'test', 4, 8, NULL, 2);

-- --------------------------------------------------------
CREATE TABLE BookingStatus (
  Id int NOT NULL,
  Status varchar(20),
  PRIMARY KEY (Id)
);

INSERT INTO BookingStatus (Id, Status) VALUES
(1, 'Outstanding'),
(2, 'In Progress'),
(4, 'Complete'),
(8, 'Cancelled');

-- --------------------------------------------------------
CREATE TABLE Bookings (
  Id int NOT NULL GENERATED ALWAYS AS IDENTITY
        (START WITH 1, INCREMENT BY 1),
  CustomerId int NOT NULL,
  DriverId int DEFAULT NULL,
  SourceAddress varchar(60) NOT NULL,
  DestinationAddress varchar(60) NOT NULL,
  NumOfPassengers int NOT NULL,
  Distance decimal NOT NULL,
  Charge decimal NOT NULL,
  TimeBooked timestamp NOT NULL,
  DepartureTime timestamp NOT NULL,
  ArrivalTime timestamp DEFAULT NULL,
  BookingStatusId int NOT NULL,
  PRIMARY KEY (Id)
);

Alter table Bookings add foreign key (CustomerId) references Customers;
Alter table Bookings add foreign key (DriverId) references Drivers;
Alter table Bookings add foreign key (BookingStatusId) references BookingStatus;

INSERT INTO Bookings (CustomerId, DriverId, SourceAddress, DestinationAddress, NumOfPassengers, Distance, Charge, TimeBooked, DepartureTime, ArrivalTime, BookingStatusId) VALUES
(1, 1, '129 Station Rd, London, N3 2AS', 'King''s Cross Station, London', 2, 5.0, 5.0, '2015-10-13 12:30:00', '2015-10-14 09:00:00', '2015-10-14 09:30:00', 4),
(2, 1, '23 Bow Lane, London, N3', 'Heathrow Terminal 3, London', 1, 20.0, 20.0, '2015-10-14 09:30:00', '2015-10-14 10:00:00', '2015-10-14 12:00:00', 4),
(3, 4, '54 Teignmouth Rd, London, NW2', '120 Green Lanes, London, N13', 2, 7.0, 7.0, '2015-10-14 18:00:00', '2015-10-15 05:00:00', '2015-10-15 06:00:00', 4),
(4, 4, '765 High Road, London, N12', '131 Stoke Newington High Road, London, N12', 3, 8.0, 8.0, '2015-10-15 09:00:00', '2015-10-15 11:30:00', '2015-10-15 12:00:00', 4),
(5, 2, '75 Squires Lane, London, N3', 'Luton Airport, Luton', 4, 30.0, 30.0, '2015-10-21 14:30:00', '2015-10-22 09:00:00', '2015-10-22 10:00:00', 4),
(6, 1, 'Finchley, London', 'King''s Cross, London', 1, 5.0, 5.0, '2015-11-02 09:22:18', '2015-11-05 09:00:00', NULL, 1);

-- --------------------------------------------------------
CREATE TABLE Configurations (
  Id int NOT NULL GENERATED ALWAYS AS IDENTITY
        (START WITH 1, INCREMENT BY 1),
  ConfigName varchar(255),
  ConfigValue varchar(255),
  PRIMARY KEY (Id)
);


INSERT INTO Configurations (ConfigName, ConfigValue) VALUES
('VAT', '20'),
('PricePerMile', '1'),
('ShortDistPrice', '2'),
('ShortDistance', '5');