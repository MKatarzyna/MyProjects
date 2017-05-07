package app;

import java.text.BreakIterator;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class ParsSort {

	private static String someText;
	static List<String> arr = new ArrayList<String>();

	static void wydzielSlowa(String target) {
		// Obiekt służący do wydzielenia słów z ciągu znaków(tekstu)
		// Locale.getDefault() - rozpoznanie jezyka w systemie
		BreakIterator wordIterator = BreakIterator.getWordInstance(Locale.getDefault());
		// podanie tekstu, który ma zostać podzielony
		wordIterator.setText(target);
		// znalezienie pierwszego słowa
		int start = wordIterator.first();
		// znalezienie następnego słowa
		int end = wordIterator.next();

		while (end != BreakIterator.DONE) {
			// wycięcie słowa pomiędzy slowem pierwszym, a następnym
			String word = target.substring(start, end);
			// zamiana znaków na małe znaki
			word = word.toLowerCase();
			if (Character.isLetterOrDigit(word.charAt(0))) {
				// zmienna do sprawdzania czy istnieje duplikat słowa
				Boolean czyIstnieje = false;
				// przejście po wszystkich elementach w celu wyszukania duplikatu
				for (String temp : arr) {
					if (temp.equals(word)) {
						czyIstnieje = true;
						 System.out.println(word); // slowa te ktore sie duplikuja wyswietla
					}
				}
				// jesli nie znaleziono duplikatu w tablicy to dodajemy słowo do niej
				if (czyIstnieje != true) {
					arr.add(word);
				}
			}
			start = end;
			end = wordIterator.next();
		}
	}

	public static void main(String[] args) {
		
		someText = "Hello aaa my ddd name bbb is Kate. My www surname ooo is Mural. I qqq study in rrr www Koszalin.";
		// Wydzielenie słów, zmiana dużych liter na małe i pomijanie duplikatów
		wydzielSlowa(someText);
		 
		sortowanieSlow();

		wyswietlSlowa();
	}

	private static void wyswietlSlowa() {
		System.out.println(Arrays.toString(arr.toArray()));
	}

	private static void sortowanieSlow() {
		Collator coll = Collator.getInstance(Locale.getDefault());
		coll.setStrength(Collator.PRIMARY);
		// domyślne sortowanie tablicy przy uzyciu lokalnego języka
		Collections.sort(arr, coll);
	}
}
