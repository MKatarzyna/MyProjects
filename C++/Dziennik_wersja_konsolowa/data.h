#ifndef DATA_H
#define DATA_H
#include <string>
#include "fileOperations.h"

using namespace std;

class data
{
	public:
		data();
		~data();

		void wyliczDzisiejszyCzas();
		void ustawDate(string data);
		string pobierzDate();
		string wybranaData;
		void stronaZmianaDaty(fileOperations klasaOperacjeNaPliku, data klasaData);
		
		void ustawDzien(string nowyDzien);
		void ustawMiesiac(string nowyMiesiac);
		void ustawRok(string nowyRok);
	
	string dzien;
	string miesiac;
	string rok;
		
		
		
		
	protected:
};

#endif
