#include "komunikaty.h"
#include <iostream>
#include <conio.h>

using namespace std;

komunikaty::komunikaty()
{
}

komunikaty::~komunikaty()
{
}

void komunikaty::wypiszMenuDzialu()
{
	cout << "\n ******************************** \n";
	cout << "\n [1] Zmien dane\n";
	cout << " [2] Powrot do MENU\n";
}

void komunikaty::wypiszMenuEdycji()
{
	cout << "Kliknij [ENTER] aby powrocic do menu \n";
	getch();
}
