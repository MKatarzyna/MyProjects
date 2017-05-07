#include "plyny.h"
#include "data.h"
#include "json.hpp"
#include "fileOperations.h"
#include "komunikaty.h"
#include <conio.h>

using namespace std;
using json = nlohmann::json;

plyny::plyny()
{
}

plyny::~plyny()
{
}

json plyny::stronaZmienPlyny(data klasaData, json plik, fileOperations klasaFileOperations)
{
	int plyny;
	
	system( "cls" );
	cout << " Zmien ilosc plynow \n\n";
	cout << "ilosc[ml]: ";
	cin >> plyny;
	
	plik = klasaFileOperations.edycjaLiczbowa("Plyny", "ilosc", plik, plyny);

	klasaFileOperations.zapiszPlikJSON(klasaData.dzien, klasaData.miesiac, klasaData.rok, plik);

	komunikaty klasaKomunikaty;
	klasaKomunikaty.wypiszMenuEdycji();
	return plik;
}

void plyny::stronaPlyny(data klasaData, json plik, fileOperations klasaFileOperations)
{
	
	int plyny;
	int wybranyNumer;
	bool zmiennaWyboru = true;
	
	do
	{
		system( "cls" );
		cout << " PLYNY \n\n";
		
	
		
		plyny = klasaFileOperations.pobierzWartoscLiczbowa("Plyny", "ilosc", plik);
		cout << "ilosc: " << plyny << " [ml]\n";
		
		komunikaty klasaKomunikaty;
		klasaKomunikaty.wypiszMenuDzialu();
		cin >> wybranyNumer;
		switch(wybranyNumer)
		{
			case 1:
				zmiennaWyboru = true;
				plik = stronaZmienPlyny(klasaData, plik, klasaFileOperations);
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

