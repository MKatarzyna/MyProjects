create table ZAMOWIONY_TOWAR_tbl (
ID_ZAM_TOW           varchar(5)               primary key,	-- kolumnowy
ID_ZAM               varchar(5)               not null references ZAMOWIENIA_tbl(ID_ZAM), ,
ID_TOW               varchar(5)               not null, references TOWAR_tbl(ID_TOWAR),
ILOSC                integer                  ,          
);