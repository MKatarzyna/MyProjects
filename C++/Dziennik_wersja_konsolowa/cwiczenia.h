#ifndef CWICZENIA_H
#define CWICZENIA_H
#include "fileOperations.h"
#include "data.h"
#include "json.hpp"

using namespace std;
using json = nlohmann::json;

class cwiczenia
{
	public:
		cwiczenia();
		~cwiczenia();
		
		json stronaZmienCwiczenia(data klasaData, json plik, fileOperations klasaFileOperations);
		void stronaCwiczenia(data klasaData, json plik, fileOperations klasaFileOperations);
		
	protected:
};

#endif
