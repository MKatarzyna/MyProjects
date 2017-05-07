#ifndef BMI_H
#define BMI_H
#include "fileOperations.h"
#include "data.h"
#include "json.hpp"

using namespace std;
using json = nlohmann::json;

class bmi
{
	public:
		bmi();
		~bmi();
		
		json stronaBmiZmienDane(data klasaData, json plik, fileOperations klasaFileOperations);
		void stronaBmi(data klasaData, json plik, fileOperations klasaFileOperations);
		
	protected:
};

#endif
