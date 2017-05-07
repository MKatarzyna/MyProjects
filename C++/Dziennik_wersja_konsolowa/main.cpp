#include <iostream>
#include "json.hpp"
#include <fstream>
#include <string>
#include "fileOperations.h"
#include <conio.h>
#include "data.h"
#include "bmi.h"
#include "plyny.h"
#include "jedzenie.h"
#include "cwiczenia.h"


using namespace std;
using json = nlohmann::json; //wymagane przy bibliotece "json.hpp"

void stronaGlowna()
{
	system( "cls" );
	cout<<"\n\n\n\n DZIENNIK \n ZDROWY TRYB \n\n\n\n Opracowala \n Katarzyna Mural \n\n\n\n Nacisnij [ENTER] aby rozpoczac \n";
	getch();
}

fileOperations stronaMenuUzytkownicy(fileOperations klasaOperacjeNaPliku, data klasaData)
{
	bool kiedyKoniec = true;
	
	do
	{
		system( "cls" );
		
		cout << "\n *** MENU UZYTKOWNIKOW *** \n\n";
	
		cout << " [1] Wybierz uzytkownika \n" << " [2] Zmien dane uzytkownika \n" << " [3] Dodaj uzytkownika \n";
		
		int numer;
		cout << "\n\nPodaj numer zakladki, aby przejsc dalej:";
		cin >> numer;
		
		string imie, nazwisko, folder;
		
		switch(numer)
		{
			case 1:
				if(klasaOperacjeNaPliku.sprawdzCzyIstniejePlikUzytkownicy())
				{
					cout << "\n\nWybierz uzytkownika: ";		
					
					// zczytaj plik json uzytkownicy
					json plik;
					plik = klasaOperacjeNaPliku.wczytajPlikJSONUzytkownicy();
					// pobierz ilosc uzytkownikow
					int iloscUzytkownikow = plik["Ilosc"];
					
					string aktulanyUzytownik;
					string nazwaUzytkownika;
					int wybranyNumerUzytkownika;
					
					cout << "\n\n";
					for(int i = 1; i < iloscUzytkownikow+1; i++)
					{
						aktulanyUzytownik = to_string(i);
						nazwaUzytkownika = "Uzytkownik" + aktulanyUzytownik;
						cout << i << ") ";
						cout << "Imie: " << plik[nazwaUzytkownika]["imie"];
						cout << ", Nazwisko: " << plik[nazwaUzytkownika]["nazwisko"];
						cout << ", Folder: " << plik[nazwaUzytkownika]["folder"];
						cout << "\n\n";
					}
					cout << "\n\nWybierz numer uzytkownika, ktorego chcesz wybrac: ";
					cin >> wybranyNumerUzytkownika;
					
					string wybranyNumerUzytkownikaTekst = to_string(wybranyNumerUzytkownika);
					string nazwaWybranegoUzytkownika = "Uzytkownik" + wybranyNumerUzytkownikaTekst;
					klasaOperacjeNaPliku.imie = plik[nazwaWybranegoUzytkownika]["imie"];
					klasaOperacjeNaPliku.nazwisko = plik[nazwaWybranegoUzytkownika]["nazwisko"];
					klasaOperacjeNaPliku.folder = plik[nazwaWybranegoUzytkownika]["folder"];
					
					kiedyKoniec = false;
				}
				else
				{
					cout << "\n\n Utworz nowego uzytkownika w zakladce 'Dodaj'. Kliknij ENTER";
					getch();
				}
				break;
			
			case 2:	
				if(klasaOperacjeNaPliku.sprawdzCzyIstniejePlikUzytkownicy())
				{
					cout << "\nZmien dane uzytkownika\n\n";
					
					// zczytaj plik json uzytkownicy
					json plik;
					plik = klasaOperacjeNaPliku.wczytajPlikJSONUzytkownicy();
					// pobierz ilosc uzytkownikow
					int iloscUzytkownikow = plik["Ilosc"];
					
					string aktulanyUzytownik;
					string nazwaUzytkownika;
					int wybranyNumerUzytkownika;
					
					cout << "\n\n";
					for(int i = 1; i < iloscUzytkownikow+1; i++)
					{
						aktulanyUzytownik = to_string(i);
						nazwaUzytkownika = "Uzytkownik" + aktulanyUzytownik;
						cout << i << ") ";
						cout << "Imie: " << plik[nazwaUzytkownika]["imie"];
						cout << ", Nazwisko: " << plik[nazwaUzytkownika]["nazwisko"];
						cout << ", Folder: " << plik[nazwaUzytkownika]["folder"];
						cout << "\n\n";
					}
					cout << "\nWybierz numer uzytkownika, ktorego dane chcesz zmienic: ";
					cin >> wybranyNumerUzytkownika;
					cout << "Podaj nowe dane\n\n ";
					cout << "Podaj nowe imie: ";
					cin >> imie;	
					cout << "Podaj nowe nazwisko: ";
					cin >> nazwisko;
					cout << "Podaj nowy folder: ";
					cin >> folder;
					
					// utworz folder uzytkownika
					string komendaTypString = "mkdir " + folder;
					const char * komendaTypChar = komendaTypString.c_str();
					system(komendaTypChar); //tworzenie folderu
					
					plik = klasaOperacjeNaPliku.edytujUzytkownika(imie, nazwisko, folder, wybranyNumerUzytkownika, plik);
					
					// zapisz plik uzytkownicy
					klasaOperacjeNaPliku.zapiszPlikJSONUzytkownicy(plik);
				}
				else
				{
					cout << "\n\n Utworz nowego uzytkownika w zakladce 'Dodaj'. Kliknij ENTER";
					getch();
				}
				break;
			
			case 3:
				cout << "Dodaj uzytkownika\n\n ";
				cout << "Podaj imie: ";
				cin >> imie;	
				cout << "Podaj nazwisko: ";
				cin >> nazwisko;
				cout << "Podaj folder: ";
				cin >> folder;
				if(klasaOperacjeNaPliku.sprawdzCzyIstniejePlikUzytkownicy())
				{	
					// zczytaj plik json uzytkownicy
					json plik;
					plik = klasaOperacjeNaPliku.wczytajPlikJSONUzytkownicy();
					
					// pobierz ilosc uzytkownikow
					int iloscUzytkownikow = plik["Ilosc"];
					
					// dodaj uztykownika
					plik = klasaOperacjeNaPliku.dodajUzytkownika(imie, nazwisko, folder, iloscUzytkownikow, plik);
					
					// utworz folder uzytkownika
					string komendaTypString = "mkdir " + folder;
					const char * komendaTypChar = komendaTypString.c_str();
					system(komendaTypChar);
					
					// zapisz plik uzytkownicy
					klasaOperacjeNaPliku.zapiszPlikJSONUzytkownicy(plik);
				}
				else
				{
					klasaOperacjeNaPliku.stworzNowyPlikJsonOrazZapiszPlikUzytkownicy(imie, nazwisko, folder);
					string komendaTypString = "mkdir " + folder;
					const char * komendaTypChar = komendaTypString.c_str();
					system(komendaTypChar);
					klasaOperacjeNaPliku.folder = folder;
					cout << "\n\n Utworzono nowego uzytkownika wraz z jego folderem. Utworzono rowniez plik 'uzytkownicy.json''.";
					getch();	
				}	
				break;
		
		}
		
	}while(kiedyKoniec);
	
	return klasaOperacjeNaPliku;
}

void stronaMenu(fileOperations klasaOperacjeNaPliku, data klasaData)
{
	bmi klasaBmi;
	plyny klasaPlyny;
	jedzenie klasaJedzenie;
	cwiczenia klasaCwiczenia;
	
	bool kiedyKoniec = true;
	do
	{
		system( "cls" );
		
		cout << "Imie uzytkownika: " << klasaOperacjeNaPliku.imie;
		cout << "\nNazwisko uzytkownika: " << klasaOperacjeNaPliku.nazwisko;
		cout << "\nFolder uzytkownika: " << klasaOperacjeNaPliku.folder;
		
		cout<<"\n\n*** MENU *** \n\n";
		cout << "Wybrana data: " << klasaData.dzien + "/"+ klasaData.miesiac +"/" + klasaData.rok + " (dd/mm/yyyy)"<< "\n\n\n";
		
		cout << " [1] BMI \n" << " [2] PLYNY \n" << " [3] JEDZENIE \n" << " [4] CWICZENIA \n" << " [5] Zmien date \n" << " [6] Wyjscie \n";
		
		int numer;
		cout << "\n\nPodaj numer zakladki, aby przejsc dalej:";
		cin >> numer;
		cout << "\n\n";
		
		switch(numer)
		{
			case 1:		// BMI
				if(klasaOperacjeNaPliku.sprawdzCzyIstnieje(klasaData.dzien + klasaData.miesiac + klasaData.rok + ".json"))
				{
					klasaBmi.stronaBmi(klasaData, klasaOperacjeNaPliku.wczytajPlikJSON(klasaData.dzien, klasaData.miesiac, klasaData.rok), klasaOperacjeNaPliku);
				}
				else
				{
					klasaOperacjeNaPliku.stworzNowyPlikJsonOrazZapiszPlik(klasaData.dzien, klasaData.miesiac, klasaData.rok);
					cout << "Utworzono plik dla wybranej daty. Kliknij [ENTER] i ponow wybor.";
					getch();
				}
				break;
		    
			case 2:		// PLYNY
				if(klasaOperacjeNaPliku.sprawdzCzyIstnieje(klasaData.dzien + klasaData.miesiac + klasaData.rok + ".json"))
				{
					klasaPlyny.stronaPlyny(klasaData, klasaOperacjeNaPliku.wczytajPlikJSON(klasaData.dzien, klasaData.miesiac, klasaData.rok), klasaOperacjeNaPliku);
				}
				else
				{
					klasaOperacjeNaPliku.stworzNowyPlikJsonOrazZapiszPlik(klasaData.dzien, klasaData.miesiac, klasaData.rok);
					cout << "Utworzono plik dla wybranej daty. Kliknij [ENTER] i ponow wybor.";
					getch();
				}
		    //	stronaPlyny(klasaOperacjeNaPliku, klasaData);
		    	
		    	break;
		    
		    case 3:		// JEDZENIE
		    	if(klasaOperacjeNaPliku.sprawdzCzyIstnieje(klasaData.dzien + klasaData.miesiac + klasaData.rok + ".json"))
				{
					klasaJedzenie.stronaJedzenie(klasaData, klasaOperacjeNaPliku.wczytajPlikJSON(klasaData.dzien, klasaData.miesiac, klasaData.rok), klasaOperacjeNaPliku);
				}
				else
				{
					klasaOperacjeNaPliku.stworzNowyPlikJsonOrazZapiszPlik(klasaData.dzien, klasaData.miesiac, klasaData.rok);
					cout << "Utworzono plik dla wybranej daty. Kliknij [ENTER] i ponow wybor.";
					getch();
				}
		    	break;
		    
		    case 4:		// CWICZENIA
		    	if(klasaOperacjeNaPliku.sprawdzCzyIstnieje(klasaData.dzien + klasaData.miesiac + klasaData.rok + ".json"))
				{
					klasaCwiczenia.stronaCwiczenia(klasaData, klasaOperacjeNaPliku.wczytajPlikJSON(klasaData.dzien, klasaData.miesiac, klasaData.rok), klasaOperacjeNaPliku);
				}
				else
				{
					klasaOperacjeNaPliku.stworzNowyPlikJsonOrazZapiszPlik(klasaData.dzien, klasaData.miesiac, klasaData.rok);
					cout << "Utworzono plik dla wybranej daty. Kliknij [ENTER] i ponow wybor.";
					getch();
				}
		    	break;
		    
			case 5:		// DATA
				klasaData.stronaZmianaDaty(klasaOperacjeNaPliku, klasaData);
				
				break;
		    
		    case 6:
				kiedyKoniec = false;
		    	break;
		}
	}while(kiedyKoniec);
		cout << " Milego dnia! :) ";
}

int main() 
{	
	string wybranaOpcja;
	string wybranaData;
	
	
	fileOperations operacjeNaPliku;
	data klasaData;
	
	stronaGlowna();
	klasaData.wyliczDzisiejszyCzas();
	
	operacjeNaPliku = stronaMenuUzytkownicy(operacjeNaPliku, klasaData);


	//uzytkownicy menu

	if(operacjeNaPliku.sprawdzCzyIstnieje(klasaData.dzien + klasaData.miesiac + klasaData.rok + ".json"))
	{
		cout << "istnieje";
	}
	else
	{
		//json plik;
		//plik = operacjeNaPliku.stworzNowyPustyPlikJson(klasaData.dzien, klasaData.miesiac, klasaData.rok);
		//operacjeNaPliku.zapiszPlikJSON(klasaData.dzien, klasaData.miesiac, klasaData.rok, plik);
		operacjeNaPliku.stworzNowyPlikJsonOrazZapiszPlik(klasaData.dzien, klasaData.miesiac, klasaData.rok);
		cout << "Utworzony nowy plik dla daty: ";
	}
	

	stronaMenu(operacjeNaPliku, klasaData);
	
	return 0;
}
