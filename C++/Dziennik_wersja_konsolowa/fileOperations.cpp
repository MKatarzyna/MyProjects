#include "fileOperations.h"
#include <iostream>
#include "json.hpp"
#include <fstream>
#include <string>
//#include <conio.h>

using namespace std;
using json = nlohmann::json;

fileOperations::fileOperations()
{
	folder = "";
	imie = "";
	nazwisko = "";
}

fileOperations::~fileOperations()
{
}

bool fileOperations::sprawdzCzyIstnieje(string nazwaPliku)
{
	if(ifstream(folder + "\\" + nazwaPliku))
	{
		//cout << "Plik istnieje!";
		return true;
	}
	else
	{
		//cout << "\nPlik nie istnieje !\n\n Nacisnij ENTER i wybierz opcje numer [5], aby zmienic date.";
		return false;
	}
}

bool fileOperations::sprawdzCzyIstniejePlikUzytkownicy()
{
	if(ifstream("uzytkownicy.json"))
	{
		//cout << "Plik istnieje!";
		return true;
	}
	else
	{
		return false;
	}	
}


json fileOperations::wczytajPlikJSON(string dzien, string miesiac, string rok)
{	
	string nazwaPliku = dzien + miesiac + rok;	
	string rozszerzeniePliku = ".json";
/*	stringstream ss;
	ss << nazwaPliku << rozszerzeniePliku;
	string plikDoWczytania = ss.str();	*/
	json plik;
	sprawdzCzyIstnieje(nazwaPliku + rozszerzeniePliku);
	ifstream ifs(folder + "\\" + nazwaPliku + rozszerzeniePliku);
	ifs >> plik;

	return plik;
}

json fileOperations::wczytajPlikJSONUzytkownicy()
{	
	json plik;
	ifstream ifs("uzytkownicy.json");
	ifs >> plik;

	return plik;
}

string fileOperations::pobierzWartoscTekstowa(string nazwaDzialu, string parametrDzialu, json plik)
{
	string wartoscTekstowa;
	
	wartoscTekstowa = plik[nazwaDzialu][parametrDzialu];
	
	return wartoscTekstowa;
}

bool fileOperations::pobierzWartoscLogiczna(string nazwaDzialu, string parametrDzialu, json plik)
{
	bool wartoscLogiczna;
	
	wartoscLogiczna = plik[nazwaDzialu][parametrDzialu];
	
	return wartoscLogiczna;
}

float fileOperations::pobierzWartoscLiczbowa(string nazwaDzialu, string parametrDzialu, json plik)
{
	float wartoscLiczbowa;
	
	wartoscLiczbowa = plik[nazwaDzialu][parametrDzialu];
	
	return wartoscLiczbowa;
}

void fileOperations::zapiszPlikJSON(string dzien, string miesiac, string rok, json plik)
{
	string nazwaPliku = dzien + miesiac + rok;
	string rozszerzeniePliku = ".json";
	ofstream plikWyjsciowy (folder + "\\" + nazwaPliku + rozszerzeniePliku);		
	plikWyjsciowy << plik << endl;			// zapis pliku
	plikWyjsciowy.close();
	//cout << "Zmiany zapisano. Kliknij ENTER";
	//getch();
}


void fileOperations::zapiszPlikJSONUzytkownicy(json plik)
{	
	ofstream plikWyjsciowy("uzytkownicy.json");		
	plikWyjsciowy << plik << endl;			// zapis pliku
	plikWyjsciowy.close();
}

json fileOperations::dodajUzytkownika(string imie, string nazwisko, string folder, int iloscUzytkownikow, json plik)
{
	int nowaIloscUzytkownikow = iloscUzytkownikow + 1;
	string nowaIloscUzytkownikowTekst = to_string(nowaIloscUzytkownikow);
	string nowaNazwaUzytkownika = "Uzytkownik" + nowaIloscUzytkownikowTekst;
	plik["Ilosc"] = nowaIloscUzytkownikow;
	plik[nowaNazwaUzytkownika]["imie"] = imie;
	plik[nowaNazwaUzytkownika]["nazwisko"] = nazwisko;
	plik[nowaNazwaUzytkownika]["folder"] = folder;

	return plik;
}

json fileOperations::edytujUzytkownika(string imie, string nazwisko, string folder, int numerUzytkownika, json plik)
{
	string numerUzytkownikaTekst = to_string(numerUzytkownika);
	string nowaNazwaUzytkownika = "Uzytkownik" + numerUzytkownikaTekst;
	plik[nowaNazwaUzytkownika]["imie"] = imie;
	plik[nowaNazwaUzytkownika]["nazwisko"] = nazwisko;
	plik[nowaNazwaUzytkownika]["folder"] = folder;

	return plik;
}

json fileOperations::edycjaTekstowa(string nazwaDzialu, string parametrDzialu, json plik, string wartoscTekstowa)
{
	plik[nazwaDzialu][parametrDzialu]=wartoscTekstowa;
	
	return plik;
}

json fileOperations::edycjaLogiczna(string nazwaDzialu, string parametrDzialu, json plik, bool wartoscLogiczna)
{
	plik[nazwaDzialu][parametrDzialu]=wartoscLogiczna;
	return plik;
}

json fileOperations::edycjaLiczbowa(string nazwaDzialu, string parametrDzialu, json plik, float wartoscLiczbowa)
{
	plik[nazwaDzialu][parametrDzialu]=wartoscLiczbowa;
	return plik;
}

json fileOperations::stworzNowyPustyPlikJson(string dzien, string miesiac, string rok)
{
	json plik;
	plik["BMI"]["waga"] = 1;
	plik["BMI"]["wzrost"] = 1;
	plik["BMI"]["wynik"] = 1;
	
	plik["Cwiczenia"]["czyCwiczyles"] = false;
	plik["Cwiczenia"]["ileMinut"] = 1;
	plik["Cwiczenia"]["rodzajCwiczen"] = " ";
	
	plik["Data"]["dzien"] = dzien;
	plik["Data"]["miesiac"] = miesiac;
	plik["Data"]["rok"] = rok;
	
	plik["Jedzenie"]["sniadanie"] = " ";
	plik["Jedzenie"]["drugieSniadanie"] = " ";
	plik["Jedzenie"]["obiad"] = " ";
	plik["Jedzenie"]["podwieczorek"] = " ";
	plik["Jedzenie"]["kolacja"] = " ";
	
	plik["Plyny"]["ilosc"] = 1;
	
	return plik;
}

json fileOperations::stworzNowyPustyPlikJsonUzytkownicy(string imie, string nazwisko, string folder)
{
	json plik;
	plik["Uzytkownik1"]["imie"] = imie;
	plik["Uzytkownik1"]["nazwisko"] = nazwisko;
	plik["Uzytkownik1"]["folder"] = folder;
	
	plik["Ilosc"] = 1;
	
	return plik;
}

void fileOperations::stworzNowyPlikJsonOrazZapiszPlik(string dzien, string miesiac, string rok){
	json plik;
	plik = stworzNowyPustyPlikJson(dzien, miesiac, rok);
	zapiszPlikJSON(dzien, miesiac, rok, plik);
}

void fileOperations::stworzNowyPlikJsonOrazZapiszPlikUzytkownicy(string imie, string nazwisko, string folder){
	json plik;
	plik = stworzNowyPustyPlikJsonUzytkownicy(imie, nazwisko, folder);
	zapiszPlikJSONUzytkownicy(plik);
}

