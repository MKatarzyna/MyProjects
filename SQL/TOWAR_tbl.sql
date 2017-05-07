create table TOWAR_tbl (
ID_TOWAR              varchar(5)                primary key, 	-- kolumnowy
ID_KATEGORIA          varchar(5)                not null references KATEGORIA_tbl(ID_KATEGORIA),
NAZWA_TOW             varchar(30)               not null,
ILOSC                 integer                   ,
CENA                  float                     ,
OPIS                  varchar(50)               ,
);