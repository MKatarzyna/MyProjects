//Autor: Katarzyna Mural

package app;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.UnknownHostException;

import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class AppletBMI extends JApplet implements ActionListener , MouseListener {

	JPanel panel;
	JFrame frame;
	JButton buttonObliczBMI, buttonZapisDoPliku, buttonWczytajZPliku;
	JTextField textFieldWaga, textFieldWzrost, textFieldWynikBMI, textFieldWpiszNazwePliku;
	JLabel labelTytul, labelWaga, labelWzrost, labelWynikBMI, labelOznaczenieWartosciBMI, labelWpiszNazwePliku;
	JTextArea textAreaOznaczenieWartosciBMI;
		
	
	public void start(){
		
		textFieldWaga.setText("0");
		textFieldWzrost.setText("0");
	}
	
	public void stop(){
		String informacja="Thank You for using my application! :)";
		JOptionPane.showMessageDialog(null, informacja);
		
	}
	
	public void destroy(){
		
		removeMouseListener(this);
	}
	
	public void init(){
	
		 addMouseListener(this);
		
		EventQueue.invokeLater(() -> {
			// wymiar dla komponentow JTextField
			Dimension dlugoscPola = new Dimension(100, 20);
			// rozmiar okna
			setSize(600, 500);
			
			setLayout(new GridBagLayout());		
			GridBagConstraints c = new GridBagConstraints();
		//	setBackground(Color.CYAN);
			
			// odstepmy pomiedzy komponentami
			c.insets = new Insets(5, 5, 5, 5);	
			
			
			labelTytul = new JLabel("PROGRAM DO OBLICZANIA BMI");
			// wysrodkowanie interfejsu
			c.fill = GridBagConstraints.HORIZONTAL;		
			c.gridx = 0;	//wspolrzedne
			c.gridy = 0;	// wspolrzedne
			add(labelTytul, c);
			
			labelWaga = new JLabel("Podaj swoja wage[kg]:");
			c.gridx = 0;
			c.gridy = 1;
			add(labelWaga, c);
			
			textFieldWaga = new JTextField();
			// ustawnienie preferowanego/zalecanego rozmiaru komponentu
			textFieldWaga.setPreferredSize(dlugoscPola);	
			c.gridx = 1;
			c.gridy = 1;
			add(textFieldWaga, c);
			
			labelWzrost = new JLabel("Podaj swoj wzrost[cm]:");
			c.gridx = 0;
			c.gridy = 2;
			add(labelWzrost, c);
			
			textFieldWzrost = new JTextField();
			textFieldWaga.setPreferredSize(dlugoscPola);
			c.gridx = 1;
			c.gridy = 2;
			add(textFieldWzrost, c);

			buttonObliczBMI = new JButton("Oblicz BMI");
			//nasluchiwanie przycisku
			buttonObliczBMI.addActionListener(this);		
			c.gridx = 1;
			c.gridy = 3;
			add(buttonObliczBMI, c);
			
			labelWynikBMI = new JLabel("Twoj wynik BMI:");
			c.gridx = 0;
			c.gridy = 4;
			add(labelWynikBMI, c);
			
			textFieldWynikBMI = new JTextField();
			// ustawienie komponentu nieedytowalnego
			textFieldWynikBMI.disable();		
			textFieldWynikBMI.setPreferredSize(dlugoscPola);
			c.gridx = 1;
			c.gridy = 4;
			add(textFieldWynikBMI, c);
			
			labelOznaczenieWartosciBMI = new JLabel("Oznaczenie wartosci BMI:");
			c.gridx = 0;
			c.gridy = 5;
			add(labelOznaczenieWartosciBMI, c);
			
			textAreaOznaczenieWartosciBMI = new JTextArea();
			textAreaOznaczenieWartosciBMI.disable();
			c.gridx = 0;
			c.gridy = 6;
			//szerokosc komponentu na dwie kolumny
			c.gridwidth = 2;	
			//wysokosc komponentu
			c.ipady = 40;		
			add(textAreaOznaczenieWartosciBMI, c);
			
			labelWpiszNazwePliku = new JLabel("Wpisz nazwe pliku: ");
			c.gridx = 0;
			c.gridy = 7;
			c.gridwidth = 1;
			c.ipady = 0;
			add(labelWpiszNazwePliku, c);
			
			textFieldWpiszNazwePliku = new JTextField();
			textFieldWpiszNazwePliku.setPreferredSize(dlugoscPola);
			c.gridx = 1;
			c.gridy = 7;
			c.gridwidth = 1;
			add(textFieldWpiszNazwePliku, c);
			
			buttonZapisDoPliku = new JButton("Zapisz do pliku");
			//nasluchiwanie przycisku
			buttonZapisDoPliku.addActionListener(this);		
			c.gridx = 1;
			c.gridy = 8;
			c.gridwidth = 1;
			add(buttonZapisDoPliku, c);
			
			buttonWczytajZPliku = new JButton("Wczytaj plik");
			buttonWczytajZPliku.addActionListener(this);
			c.gridx = 1;
			c.gridy = 9;
			add(buttonWczytajZPliku, c);
			
			
			setVisible(true);
		});
	}


	@Override
	// wykonanie nasluchiwania
		public void actionPerformed(ActionEvent e) 
		{		
			String nazwa = e.getActionCommand();
			System.out.println(nazwa);
			
			BMI klasaBMI = new BMI();
			
			if(nazwa.equals("Oblicz BMI"))
			{	
				try
				{	//w ponizszych dwoch linijkach kodu, moze zostac wychwycony wyjatek, 
					//poniewaz nie da sie zamienic litery na liczbe 
					klasaBMI.waga = Float.parseFloat(textFieldWaga.getText());
					klasaBMI.wzrost = Float.parseFloat(textFieldWzrost.getText());
					
					klasaBMI.wynikBMI = klasaBMI.ObliczWartoscBMI(klasaBMI.waga, klasaBMI.wzrost);
				}
				// przechwycenie wyjątku typu "NumberFormatException", 
				//jeśli wystąpi użytkownik wpisze inną wartość niż liczbową, 
				//wtedy pojawi się komunikat
				catch (NumberFormatException e3)  
				{
					//wyświetlenie okienka "MessageDialog" z komunikatem
					String zlaWartosc="Nalezy podawac tylko liczby.\nJesli to konieczne, uzywac kropki zamiast przecinka.";
					JOptionPane.showMessageDialog(null, zlaWartosc);
				}
				textFieldWynikBMI.setText(Float.toString(klasaBMI.wynikBMI));
				
				//pobieranie wyniku bmi i przekazanie go jako parametru do funkcji "pokaz wartosc BMI", 
				//ktora nastepnie zwraca komunikat, a natepnie zostaje ustawiona w polu 
				textAreaOznaczenieWartosciBMI.setText(klasaBMI.pokazWartoscBMI(klasaBMI.wynikBMI));
			} 
			// Zapis do pliku
			else if(nazwa.equals("Zapisz do pliku")) 
			{
				FileWriter inputFile;
				try 
				{
					// określenie jaka nazwa plika zostanie utworzona i w jakim miejscu
					// (plik zostaje zapisany w tym samym folderze co aplikacja)
					inputFile = new FileWriter(textFieldWpiszNazwePliku.getText() + ".txt");
					// strumieniowanie wartosci do pliku
					BufferedWriter out = new BufferedWriter(inputFile);
					out.write(Float.toString(klasaBMI.waga));
					out.newLine();
					out.write(Float.toString(klasaBMI.wzrost));
					out.newLine();
					out.write(Float.toString(klasaBMI.wynikBMI));
					out.newLine();
					out.write(textAreaOznaczenieWartosciBMI.getText());
					out.close();
				} 
				//przechwytywanie wyjątku "IOException" przez blok "e1" (e1 - nazwa parametru 
				//którym jest obiekt typu IOException, który odpowada za operacje wejścia/wyjścia)
				catch (IOException e1) 
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}	
			} 
			// Odczyt z pliku
			else if(nazwa.equals("Wczytaj plik"))
			{
				FileReader odczyt;
				try 
				{	// tworzenie uchwytu(przetrzymanie pliku) - otwieranie pliku
					odczyt = new FileReader(textFieldWpiszNazwePliku.getText() + ".txt");
					BufferedReader strumien = new BufferedReader(odczyt);
					String linia;
					// deklaracja tablicy na cele przechowania wartosci..
					//..zczytanych z pliku tekstowego
					String[] tablicaWartosci = new String[4];
					int i = 0;	//licznik
					// petla while zczytuje linia po lini 
					while ( (linia = strumien.readLine()) != null )
					{
						System.out.print(linia + "\n");
						// zapis calej lini do tablicy pod wybrana komorke tablicy
						tablicaWartosci[i] = linia;
						i++;
					}
					// wartosc z tablicy ustawiana jest w polu tekstowym
					textFieldWaga.setText(tablicaWartosci[0]);
					textFieldWzrost.setText(tablicaWartosci[1]);
					textFieldWynikBMI.setText(tablicaWartosci[2]);
					textAreaOznaczenieWartosciBMI.setText(tablicaWartosci[3]);
				} 
				// przechwycenie wyjatku, kiedy plik nie istnieje
				catch (FileNotFoundException | UnknownHostException e2)
				{
					System.out.println(e2.getMessage());
					
					String zlyPlik="Nie można odnalezc okreslonego pliku. \nWpisz nazwę ponownie.";
					JOptionPane.showMessageDialog(null, zlyPlik);
				}
				catch (IOException e1) 
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
		String mouseClicked = "mouseClicked";
		JOptionPane.showMessageDialog(null, mouseClicked);
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
		
	}
}