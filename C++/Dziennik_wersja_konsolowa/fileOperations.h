#ifndef FILEOPERATIONS_H
#define FILEOPERATIONS_H
#include <string>
#include "json.hpp"

using namespace std;
using json = nlohmann::json;

class fileOperations
{
	public:
		string folder;
		string imie;
		string nazwisko;
		
		fileOperations();
		
		~fileOperations();
		
		json wczytajPlikJSON(string dzien, string miesiac, string rok);
		json wczytajPlikJSONUzytkownicy();
		
		string pobierzWartoscTekstowa(string nazwaDzialu, string parametrDzialu, json plik);
		bool pobierzWartoscLogiczna(string nazwaDzialu, string parametrDzialu, json plik);
		float pobierzWartoscLiczbowa(string nazwaDzialu, string parametrDzialu, json plik);
		
		void zapiszPlikJSON(string dzien, string miesiac, string rok, json plik);
		void zapiszPlikJSONUzytkownicy(json plik);
		
		json stworzNowyPustyPlikJson(string dzien, string miesiac, string rok);
		json stworzNowyPustyPlikJsonUzytkownicy(string imie, string nazwisko, string folder);
		
		void stworzNowyPlikJsonOrazZapiszPlik(string dzien, string miesiac, string rok);
		void stworzNowyPlikJsonOrazZapiszPlikUzytkownicy(string imie, string nazwisko, string folder);
		
		bool sprawdzCzyIstnieje(string nazwaPliku);
		bool sprawdzCzyIstniejePlikUzytkownicy();
		
		json dodajUzytkownika(string imie, string nazwisko, string folder, int iloscUzytkownikow, json plik);
		json edytujUzytkownika(string imie, string nazwisko, string folder, int numerUzytkownika, json plik);
		
		json edycjaTekstowa(string nazwaDzialu, string parametrDzialu, json plik, string wartoscTekstowa);
		json edycjaLogiczna(string nazwaDzialu, string parametrDzialu, json plik, bool wartoscLogiczna);
		json edycjaLiczbowa(string nazwaDzialu, string parametrDzialu, json plik, float wartoscLiczbowa);

	private:
		
};

#endif
