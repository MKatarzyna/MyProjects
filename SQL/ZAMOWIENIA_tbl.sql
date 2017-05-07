create table ZAMOWIENIA_tbl (
ID_ZAM           varchar(5)               not null,
ID_KLIENT        varchar(5)               not null,
DATA             date                     ,
WARTOSC          float                    ,  
            
primary key (ID_ZAM)	-- tabelaryczny
);