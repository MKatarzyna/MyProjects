package app;

public class BMI {
	
	// deklaracja zmiennych zmiennoprzecinkowych
	static float waga;		
	static float wzrost;
	static float wynikBMI;

	protected static float ObliczWartoscBMI(float waga, float wzrost) {
		float wynikBMI;
		// obliczenie wartosci bmi
		wynikBMI = waga / ((wzrost*wzrost)/10000); 
		return wynikBMI;
	}

	protected static String pokazWartoscBMI(float wynikBMI) {
		System.out.println("\nTwoj wynik BMI to: ");
		// wyswietlenie wyniku bmi
		System.out.println(wynikBMI);	

		String komunikat = "";
		
		System.out.println("\n*** Wartosci BMI ***");
		
		if(wynikBMI < 18.5)
		{
			komunikat = "Twoj wynik oznacza niedowage :(";
		}
		else if(wynikBMI > 18.5 && wynikBMI < 24.99)
		{
			komunikat = "Twoj wynik oznacza prawidlowa wartosc. Gratulacje! :)";
		}
		else if(wynikBMI >= 25.0)
		{
			komunikat = "Twoj wynik oznacza nadwage :(";
		}
		
		return komunikat;
	}

	public BMI() {
		
		super();
	}

}