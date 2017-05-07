#ifndef JEDZENIE_H
#define JEDZENIE_H
#include "fileOperations.h"
#include "data.h"
#include "json.hpp"

using namespace std;
using json = nlohmann::json;

class jedzenie
{
	public:
		jedzenie();
		~jedzenie();
		
		void stronaJedzenie(data klasaData, json plik, fileOperations klasaFileOperations);
		json stronaZmienJedzenie(data klasaData, json plik, fileOperations klasaFileOperations);
		
	protected:
};

#endif
