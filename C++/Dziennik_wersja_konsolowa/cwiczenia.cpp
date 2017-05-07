#include "cwiczenia.h"
#include "fileOperations.h"
#include "data.h"
#include "komunikaty.h"
#include <conio.h>
#include "json.hpp"

using namespace std;
using json = nlohmann::json;

cwiczenia::cwiczenia()
{
}

cwiczenia::~cwiczenia()
{
}

json cwiczenia::stronaZmienCwiczenia(data klasaData, json plik, fileOperations klasaFileOperations)
{
	system( "cls" );
	cout<<"Strona zmien cwiczenia \n\n";
	
	bool czyCwiczyles;
	int ileMinut;
	string rodzajCwiczen;

	cout << "czy cwiczyles(jesli tak to wpisz 1, jesli nie to wpisz 0): ";
	cin >> czyCwiczyles;
	cout << "ile minut(podaj liczbe): ";
	cin >> ileMinut;
	cout << "rodzaj cwiczen: ";
	cin.sync();
	getline(cin, rodzajCwiczen);
	
	plik = klasaFileOperations.edycjaLogiczna("Cwiczenia", "czyCwiczyles", plik, czyCwiczyles);
	plik = klasaFileOperations.edycjaLiczbowa("Cwiczenia", "ileMinut", plik, ileMinut);
	plik = klasaFileOperations.edycjaTekstowa("Cwiczenia", "rodzajCwiczen", plik, rodzajCwiczen);
	
	klasaFileOperations.zapiszPlikJSON(klasaData.dzien, klasaData.miesiac, klasaData.rok, plik);
	
	komunikaty klasaKomunikaty;
	klasaKomunikaty.wypiszMenuEdycji();
	
	return plik;
}

void cwiczenia::stronaCwiczenia(data klasaData, json plik, fileOperations klasaFileOperations)
{
	
	
	bool czyCwiczyles;
	int ileMinut;
	string rodzajCwiczen;
	int wybranyNumer;
	bool zmiennaWyboru = true;
	
	do
	{
		system( "cls" );
		cout << " CWICZENIA \n\n";
		
		
	
		czyCwiczyles = klasaFileOperations.pobierzWartoscLogiczna("Cwiczenia", "czyCwiczyles", plik);
		ileMinut = klasaFileOperations.pobierzWartoscLiczbowa("Cwiczenia", "ileMinut", plik);
		rodzajCwiczen = klasaFileOperations.pobierzWartoscTekstowa("Cwiczenia", "rodzajCwiczen", plik);
		
		cout << "czyCwiczyles: " << czyCwiczyles << "\n";
		cout << "ileMinut: " << ileMinut << "\n";
		cout << "rodzajCwiczen: " << rodzajCwiczen << "\n";
		
		komunikaty klasaKomunikaty;
		klasaKomunikaty.wypiszMenuDzialu();
		
		cin >> wybranyNumer;
		switch(wybranyNumer)
		{
			case 1:
				zmiennaWyboru = true;
				plik = stronaZmienCwiczenia(klasaData, plik, klasaFileOperations);
				break;
				
			case 2:
				zmiennaWyboru = false;
			//	stronaMenu(klasaData);
				break;
			
			default:
				klasaKomunikaty.wypiszMenuDzialu();
				break;
		}
		
	}while(zmiennaWyboru);

}

