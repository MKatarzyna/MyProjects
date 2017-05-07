package app;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class OknoPomocy extends JFrame implements ActionListener
{	
	JPanel centralPanel;

	public OknoPomocy()
	{
		setTitle("Pomoc");		// nazwa okna
		setSize(620, 290);				// rozmiar okna
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setLocationRelativeTo(null);	// ustawienie okna na srodku ekranu
		setVisible(true);	
		
		CentralnaCzesc();
	}
	
	private void CentralnaCzesc()
	{
		
		centralPanel = new JPanel();
		centralPanel.setLayout(new GridBagLayout());
		add(centralPanel, BorderLayout.CENTER);
		
		centralPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		Color kolor = new Color(255, 255, 255);
		centralPanel.setBackground(kolor);
		
		JLabel tytul = new JLabel("INSTRUKCJA");
		tytul.setFont(new Font("Serif", Font.PLAIN, 16));
		
		JLabel tekst1 = new JLabel("    Program umożliwiający obliczanie sumy i średniej, a także wyszukanie min i max wartości z tabeli.");
		tekst1.setFont(new Font("Serif", Font.PLAIN, 14));
		
		JLabel tekst2 = new JLabel("Należy wprowadzać tylko cyfry i liczby do tablicy poprzez wpisanie wartości do pola 'Wprowadz liczbę'.");
		tekst2.setFont(new Font("Serif", Font.PLAIN, 14));
		
		JLabel tekst3 = new JLabel("Po wpisaniu wartości do pola 'Wprowadz liczbę' zatwierdzamy to przyciskiem 'Dodaj'.");
		tekst3.setFont(new Font("Serif", Font.PLAIN, 14));
		
		JLabel tekst4 = new JLabel("    Następnie po wprowadzeniu wartości do tabeli, możemy wybrać jakie działanie chcemy wykonać ");
		tekst4.setFont(new Font("Serif", Font.PLAIN, 14));
		
		JLabel tekst4cd = new JLabel("wybierając je z listy pod tabelą, bądz w menu(zakładka 'Obliczenia') lub klikając w odpowiednią ikonę.");
		tekst4cd.setFont(new Font("Serif", Font.PLAIN, 14));
		
		JLabel inne = new JLabel("    Inne dostępne opcje programu to zerowanie tablicy, wypełnienie jej wartościami losowymi oraz zapis ");
		inne.setFont(new Font("Serif", Font.PLAIN, 14));
		JLabel inne2 = new JLabel("wszystkich elementów do pliku tekstowego.");
		inne2.setFont(new Font("Serif", Font.PLAIN, 14));

		
		centralPanel.setLayout(new BoxLayout(centralPanel, BoxLayout.Y_AXIS));
		
		
		centralPanel.add(tytul);
		Separator();
		centralPanel.add(tekst1);
		centralPanel.add(tekst2);
		centralPanel.add(tekst3);
		Separator();
		centralPanel.add(tekst4);
		centralPanel.add(tekst4cd);
		Separator();
		centralPanel.add(inne);
		centralPanel.add(inne2);
		
		Separator();
		
		JButton button = new JButton("OK"); 
	    centralPanel.add(button); 
	    button.addActionListener(this);
	}

	private void Separator() {
		// TODO Auto-generated method stub
		centralPanel.add(Box.createVerticalStrut(15));
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		setVisible(false); 
	    dispose(); 
	}
}