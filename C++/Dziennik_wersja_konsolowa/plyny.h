#ifndef PLYNY_H
#define PLYNY_H
#include "fileOperations.h"
#include "data.h"
#include "json.hpp"

using namespace std;
using json = nlohmann::json;

class plyny
{
	public:
		plyny();
		~plyny();
		
		void stronaPlyny(data klasaData, json plik, fileOperations klasaFileOperations);
		json stronaZmienPlyny(data klasaData, json plik, fileOperations klasaFileOperations);
		

	protected:
};

#endif
