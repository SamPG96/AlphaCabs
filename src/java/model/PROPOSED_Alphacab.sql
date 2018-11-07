DROP Table Customers;
CREATE TABLE Customers (
  Id int NOT NULL,
  FirstName varchar(20),
  LastName varchar(20),
  Address varchar(60),
  PRIMARY KEY (Id)
);


INSERT INTO Customers (Id, FirstName, LastName, Address) VALUES
(1, 'Eva', 'Smith', '129 Station Rd, London, N3 2AS'),
(2, 'Rob', 'Manton', '23 Bow Lane, London, N3'),
(3, 'Bob', 'Currie', '54 Teignmouth Rd, London, NW2'),
(4, 'Jim', 'Hunter', '765 High Road, London, N12'),
(5, 'Phil', 'Johnson', '75 Squires Lane, London, N3'),
(6, 'Saim', 'Soyler', '2 Rosemary Ave, London, N3'),
(7, 'Gul', 'Hikmet', '31 Clifton Rd, London, N3 2SG');

-- --------------------------------------------------------

DROP Table Drivers;
CREATE TABLE Drivers (
  Id int NOT NULL,
  FirstName varchar(20),
  LastName varchar(20),
  Registration varchar(10) NOT NULL,
  PRIMARY KEY (Id)
);

INSERT INTO Drivers (Id, FirstName, LastName, Registration) VALUES
(1, 'John', 'Smith', 'AK52VZV'),
(2, 'Mehmet', 'Aydin', 'BN60WKA'),
(3, 'Mark', 'Johnson','R34AKP'),
(4, 'Chris', 'Simmons', 'B23GFE');

-- --------------------------------------------------------

DROP Table UserType;
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

DROP Table UserStatus;
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

DROP Table Users;
CREATE TABLE Users (
  Id int NOT NULL,
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

INSERT INTO Users (Id, Username, Password, UserTypeId, CustomerId, DriverId, UserStatusId) VALUES
(1, 'meaydin', '201068', 2, NULL, 2, 2),
(2, 'aydinme', '108752', 1, NULL, NULL, 2),
(3, 'csimons', '010563', 2, NULL, 4, 2),
(4, 'jsmith', '465844', 2, NULL, 1, 2),
(5, 'mjohnson', '548657', 2, NULL, 3, 2),
(6, 'esmith', '457845', 4, 1, NULL, 2),
(7, 'rmanton', '745486', 4, 2, NULL, 2),
(8, 'bcurrie', '784596', 4, 3, NULL, 2),
(9, 'jhunter', '124956', 4, 4, NULL, 2),
(10, 'pjohnson', '258467', 4, 5, NULL, 2),
(11, 'ssoyler', '988654', 4, 6, NULL, 2),
(12, 'ghikmet', '224969', 4, 7, NULL, 2);

-- --------------------------------------------------------

DROP Table BookingStatus;
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
DROP Table Bookings;
CREATE TABLE Bookings (
  Id int NOT NULL,
  CustomerId int NOT NULL,
  DriverId int DEFAULT NULL,
  SourceAddress varchar(60) NOT NULL,
  DestinationAddress varchar(60) NOT NULL,
  DistanceKM decimal DEFAULT NULL,
  TimeBooked timestamp NOT NULL,
  TimeArrived timestamp DEFAULT NULL,
  BookingStatusId int NOT NULL,
  PRIMARY KEY (Id)
);

Alter table Bookings add foreign key (CustomerId) references Users;
Alter table Bookings add foreign key (DriverId) references Users;
Alter table Bookings add foreign key (BookingStatusId) references BookingStatus;

INSERT INTO Bookings (Id, CustomerId, DriverId, SourceAddress, DestinationAddress, DistanceKM, TimeBooked, TimeArrived, BookingStatusId) VALUES
(1, 1, 1, '129 Station Rd, London, N3 2AS', 'King''s Cross Station, London', 5, '2015-10-13 12:30:00', '2015-10-14 09:30:00', 4),
(2, 2, 1, '23 Bow Lane, London, N3', 'Heathrow Terminal 3, London', 20, '2015-10-14 09:30:00', '2015-10-14 12:00:00', 4),
(3, 3, 4, '54 Teignmouth Rd, London, NW2', '120 Green Lanes, London, N13', 7, '2015-10-14 18:00:00', '2015-10-15 06:00:00', 4),
(4, 4, 4, '765 High Road, London, N12', '131 Stoke Newington High Road, London, N12', 8, '2015-10-15 09:00:00', '2015-10-15 12:00:00', 4),
(5, 5, 5, '75 Squires Lane, London, N3', 'Luton Airport, Luton', 30, '2015-10-21 14:30:00', '2015-10-22 10:00:00', 4),
(1, 6, 1, 'Finchley, London', 'King''s Cross, London', NULL, '2015-11-02 09:22:18', NULL, 1);