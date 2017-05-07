#include "bmi.h"
#include "fileOperations.h"
#include "data.h"
#include "komunikaty.h"
#include <conio.h>
#include "json.hpp"

using namespace std;
using json = nlohmann::json;

bmi::bmi()
{
}

bmi::~bmi()
{
}

json bmi::stronaBmiZmienDane(data klasaData, json plik, fileOperations klasaFileOperations)
{
	float waga;
	float wzrost;
	float BMI;
	
	system( "cls" );
	cout<<"zmien BMI \n\n";
	cout << "waga[kg]: ";
	cin >> waga;
	cout << "wzrost[cm]: ";
	cin >> wzrost;
	
	BMI = waga / ((wzrost*wzrost)/10000);
	
	cout << "\n\nWskaznik BMI wynosi: " << BMI << "\n\n";
	
	if(BMI < 18.5)
	{
		cout << "Twoj wynik oznacza niedowage. :(" << "\n\n";
	}
	else if(BMI > 18.5 && BMI < 24.99)
	{
		cout << "Twoj wynik oznacza prawidlowa wartosc. Gratulacje! :)" << "\n\n";
	}
	else if(BMI >= 25.0)
	{
		cout << "Twoj wynik oznacza nadwage! :(" << "\n\n";
	}
	
	plik = klasaFileOperations.edycjaLiczbowa("BMI", "waga", plik, waga);
	plik = klasaFileOperations.edycjaLiczbowa("BMI", "wzrost", plik, wzrost);
	plik = klasaFileOperations.edycjaLiczbowa("BMI", "wynik", plik, BMI);

	klasaFileOperations.zapiszPlikJSON(klasaData.dzien, klasaData.miesiac, klasaData.rok, plik);
	
	//wyliczyc bmi 	BMI = masa cia³a (kg) / wzrost (m)2
	
	komunikaty klasaKomunikaty;
	klasaKomunikaty.wypiszMenuEdycji();
	
	return plik;	
}

void bmi::stronaBmi(data klasaData, json plik, fileOperations klasaFileOperations)
{
	float waga, wzrost, bmi;
	int wybranyNumer;
	bool zmiennaWyboru = true;
	
	do
	{
		system( "cls" );
		cout << " BMI \n\n";
		
		waga = klasaFileOperations.pobierzWartoscLiczbowa("BMI", "waga", plik);
		wzrost = klasaFileOperations.pobierzWartoscLiczbowa("BMI", "wzrost", plik);
		bmi = klasaFileOperations.pobierzWartoscLiczbowa("BMI", "wynik", plik);
		
		cout << "Waga: " << waga << "\n";
		cout << "Wzrost: " << wzrost << "\n";
		cout << "BMI: " << bmi << "\n";
		
		komunikaty klasaKomunikaty;
		klasaKomunikaty.wypiszMenuDzialu();
		
		cin >> wybranyNumer;
		switch(wybranyNumer)
		{
			case 1:
				zmiennaWyboru = true;
				plik = stronaBmiZmienDane(klasaData, plik, klasaFileOperations);
				break;
				
			case 2:
				zmiennaWyboru = false;
				break;
			
			default:
				klasaKomunikaty.wypiszMenuDzialu();
				break;
		}
		
	}while(zmiennaWyboru);
	
}
