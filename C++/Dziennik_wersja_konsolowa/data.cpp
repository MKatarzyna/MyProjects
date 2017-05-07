#include "data.h"
#include <string>
#include <ctime>
#include <iostream>
#include "fileOperations.h"
#include "komunikaty.h"
#include <conio.h>

using namespace std;

data::data()
{
}

data::~data()
{
}

void data::wyliczDzisiejszyCzas()
{
	time_t rawtime;
	struct tm * timeinfo;
	char buffer[80];

	time (&rawtime);
	timeinfo = localtime(&rawtime);

	strftime(buffer,80,"%d",timeinfo);
	dzien = (string)buffer;
	
		strftime(buffer,80,"%m",timeinfo);
	miesiac = (string)buffer;
	
		strftime(buffer,80,"%Y",timeinfo);
	rok = (string)buffer;

	strftime(buffer,80,"%d-%m-%Y",timeinfo);
	string str(buffer);
}

void data::ustawDate(string data)
{
	ustawDzien("12");
	ustawMiesiac("12");
	ustawRok("2016");
}

void data::ustawDzien(string nowyDzien){
	dzien = nowyDzien;
}

void data::ustawMiesiac(string nowyMiesiac){
	miesiac = nowyMiesiac;
}

void data::ustawRok(string nowyRok){
	rok = nowyRok;
}

string data::pobierzDate()
{
	return dzien + miesiac + rok;
}

void data::stronaZmianaDaty(fileOperations klasaOperacjeNaPliku, data klasaData)
{
	string nowyDzien;
	string nowyMiesiac;
	string nowyRok;
	
	system( "cls" );
	cout << " Zmien date \n\n";
	
	cout << "Dzien(dd): ";
	cin.sync();
	getline(cin, nowyDzien);
	
	cout << "Miesiac(mm): ";
	cin.sync();
	getline(cin, nowyMiesiac);
	
	cout << "Rok(yyyy): ";
	cin.sync();
	getline(cin, nowyRok);
	
	ustawDzien(nowyDzien);
	ustawMiesiac(nowyMiesiac);
	ustawRok(nowyRok);
	
	if(klasaOperacjeNaPliku.sprawdzCzyIstnieje(dzien + miesiac + rok + ".json"))
	{
		cout << "\nPlik dla wybranej daty istnieje.\n";
		getch();
	}
	else
	{
		klasaOperacjeNaPliku.stworzNowyPlikJsonOrazZapiszPlik(dzien, miesiac, rok);
		cout << "\nUtworzono nowy plik\n\n";
		getch();
	}
	
	
	
	
//	klasaFileOperations.zapiszPlikJSON(klasaData.dzien, klasaData.miesiac, klasaData.rok, plik);
	
	komunikaty klasaKomunikaty;
	klasaKomunikaty.wypiszMenuEdycji();
}
