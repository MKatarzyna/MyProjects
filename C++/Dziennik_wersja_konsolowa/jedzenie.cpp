#include "jedzenie.h"
#include "data.h"
#include "json.hpp"
#include "fileOperations.h"
#include "komunikaty.h"
#include <conio.h>

using namespace std;
using json = nlohmann::json;

jedzenie::jedzenie()
{
}

jedzenie::~jedzenie()
{
}

json jedzenie::stronaZmienJedzenie(data klasaData, json plik, fileOperations klasaFileOperations)
{
	string sniadanie;
	string drugieSniadanie;
	string obiad;
	string podwieczorek;
	string kolacja;
	
	system( "cls" );
	cout<<"Zmien jedzenie \n\n";
	
	
	cout << "sniadanie: ";
	cin.sync();
	getline(cin, sniadanie);
//	cin >> sniadanie;

	cout << "drugieSniadanie: ";
	cin.sync();
	getline(cin, drugieSniadanie);
	
	cout << "obiad: ";
	cin.sync();
	getline(cin, obiad);
	
	cout << "podwieczorek: ";
	cin.sync();
	getline(cin, podwieczorek);
	
	cout << "kolacja: ";
	cin.sync();
	getline(cin, kolacja);
	
	plik = klasaFileOperations.edycjaTekstowa("Jedzenie", "sniadanie", plik, sniadanie);
	plik = klasaFileOperations.edycjaTekstowa("Jedzenie", "drugieSniadanie", plik, drugieSniadanie);
	plik = klasaFileOperations.edycjaTekstowa("Jedzenie", "obiad", plik, obiad);
	plik = klasaFileOperations.edycjaTekstowa("Jedzenie", "podwieczorek", plik, podwieczorek);
	plik = klasaFileOperations.edycjaTekstowa("Jedzenie", "kolacja", plik, kolacja);
	
	klasaFileOperations.zapiszPlikJSON(klasaData.dzien, klasaData.miesiac, klasaData.rok, plik);
	
	komunikaty klasaKomunikaty;
	klasaKomunikaty.wypiszMenuEdycji();
	return plik;
}

void jedzenie::stronaJedzenie(data klasaData, json plik, fileOperations klasaFileOperations)
{
	
	
	string sniadanie, drugieSniadanie, obiad, podwieczorek, kolacja;
	int wybranyNumer;
	bool zmiennaWyboru = true;
	
	do
	{
		system( "cls" );
		cout << " JEDZENIE \n\n";
		
		
	
		sniadanie = klasaFileOperations.pobierzWartoscTekstowa("Jedzenie", "sniadanie", plik);
		drugieSniadanie = klasaFileOperations.pobierzWartoscTekstowa("Jedzenie", "drugieSniadanie", plik);
		obiad = klasaFileOperations.pobierzWartoscTekstowa("Jedzenie", "obiad", plik);
		podwieczorek = klasaFileOperations.pobierzWartoscTekstowa("Jedzenie", "podwieczorek", plik);
		kolacja = klasaFileOperations.pobierzWartoscTekstowa("Jedzenie", "kolacja", plik);
		
		cout << "sniadanie: " << sniadanie << "\n";
		cout << "drugieSniadanie: " << drugieSniadanie << "\n";
		cout << "obiad: " << obiad << "\n";
		cout << "podwieczorek: " << podwieczorek << "\n";
		cout << "kolacja: " << kolacja << "\n";
		
		komunikaty klasaKomunikaty;
		klasaKomunikaty.wypiszMenuDzialu();
	
		cin >> wybranyNumer;
		switch(wybranyNumer)
		{
			case 1:
				zmiennaWyboru = true;
				plik = stronaZmienJedzenie(klasaData, plik, klasaFileOperations);
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

