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
import javax.swing.JPopupMenu.Separator;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class OknoAbout extends JFrame implements ActionListener
{
	
	JPanel centralPanel;

	public OknoAbout()
	{
		setTitle("Informacje o programie");		// nazwa okna
		setSize(300, 210);				// rozmiar okna
//		Dimension dlugoscPola = new Dimension(100, 20);
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
		
	//	centralPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		Color kolor = new Color(255, 255, 255);
		centralPanel.setBackground(kolor);
		
		JLabel tytul = new JLabel("APLIKACJA TESTOWA", SwingConstants.CENTER);
		tytul.setFont(new Font("Serif", Font.PLAIN, 18));
		
		
	//	JLabel wersja = new JLabel("<html><body style=\"padding:20px;text-align: justify\">"
	//			+ "<h1>Katarzyna Mural</h1>"
	//			+ "<p><center>&copy; 2017 <center></p>"
	//			+ "</body></html>");
		
		JLabel wersja = new JLabel("Wersja: 1.0");
		wersja.setFont(new Font("Serif", Font.PLAIN, 14));
		
		JLabel autor = new JLabel("Autor: Katarzyna Mural");
		autor.setFont(new Font("Serif", Font.PLAIN, 18));
		
		JLabel kontakt = new JLabel("Kontakt: katarzyna.mural@gmail.com");
		kontakt.setFont(new Font("Serif", Font.PLAIN, 16));
		
		JLabel prawa = new JLabel("Copiright (C) by 2017");
		prawa.setFont(new Font("Serif", Font.PLAIN, 14));
		
		centralPanel.setLayout(new BoxLayout(centralPanel, BoxLayout.Y_AXIS));
		
		
		centralPanel.add(tytul);
		Separator();
		centralPanel.add(autor);
		centralPanel.add(kontakt);
		Separator();
		centralPanel.add(wersja);
		centralPanel.add(prawa);
		Separator();
		
		JButton button = new JButton("OK"); 
	    centralPanel.add(button); 
	    button.addActionListener(this);
	}

	private void Separator() 
	{
		// TODO Auto-generated method stub
		centralPanel.add(Box.createVerticalStrut(10));
	}

	@Override
	public void actionPerformed(ActionEvent arg0) 
	{
		// TODO Auto-generated method stub
		setVisible(false); 
	    dispose(); 
	}
}