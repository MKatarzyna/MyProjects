
package app;

import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * @author Kate
 *
 */
@SuppressWarnings("serial")
public class Gui1_WindowApp extends JFrame implements ActionListener, ChangeListener{

	// definiujemy kontrolki jako pola naszej klasy - potrzebny do tego JMenuBar
	JMenuBar menuBar;
	JMenu menuPlik, menuEdycja, menuWidok, menuObliczenia, menuPomoc;
	
	JMenuItem mItemZapisz, mItemDrukuj, mItemZamknij;						// menu plik
	JMenuItem mItemDodajLiczbe, mItemWyzerujTablice, mItemWypelnijLosowo;	// menu edycja
	JCheckBoxMenuItem mChPasekNarzedziowy, mChPasekStatusu;					// menu widok
	JMenuItem mItemSumElementow, mItemSrElementow, mItemLMin, mItemLMax;	// menu obliczenia
	JMenuItem mItemOProgramie, mItemPomoc;												// menu o programie, pomoc
	
	JButton saveButton, printButton, closeButton;		// ikonki przyciski - zapisz,drukuj,wyjscie
	JButton addButton, resetButton, randomButton;		// ikonki przyciski - dodaj, wyzeruj tablice, wypelnij tablice wartosciami randomowymi
	JButton sumButton, avgButton, minButton, maxButton;	// ikonki przyciski - suma, srednia, min, max
	JButton helpButton, aboutButton;					// ikonki przyciski - pomoc, informacja o programie/autorze
	
	JButton buttonDodaj, buttonWyzeruj, buttonWypelnij, buttonZapisz;
	JButton buttonCheckBox;
	
	ImageIcon iconSave, iconPrint, iconClose;
	ImageIcon iconAdd, iconReset, iconRandom;
	ImageIcon iconSum, iconAvg, iconMin, iconMax;
	ImageIcon iconHelp, iconAbout;
	
	ImageIcon iconSaveMin, iconPrintMin, iconCloseMin;
	ImageIcon iconAddMin, iconResetMin, iconRandomMin;
	ImageIcon iconSumMin, iconAvgMin, iconMinMin, iconMaxMin;
	ImageIcon iconHelpMin, iconAboutMin;
	
	JPanel statusPanel;
	JPanel centralnyPanel;
	
	JLabel statusLabel;
	JLabel labelNumerWierszaDisplay;
	JLabel labelNumerKolumnyDisplay;
	JLabel statusLabel2;
	
	JSlider textAreaNumerWiersza;
	JSlider textAreaNumerKolumny;

	JTextArea textAreaWprowadzLiczbe;
	JTextArea textArea;
	
	JToolBar toolbar;
	
	JComboBox listaComboBox;
	
	JTable table;
	
	
	public static void main(String[] args)
	{	
		Gui1_WindowApp appMenu = new Gui1_WindowApp();
		appMenu.setVisible(true);
	}
	
	public Gui1_WindowApp() 
	{
		setTitle("Moje okienko");		// nazwa okna
		setSize(750, 500);				// rozmiar okna
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);	// ustawienie okna na srodku ekranu
		setVisible(true);				// wyswietlenie okna
		
		tworzenieMenu();
		tworzenieToolBar();
		tworzeniePaskaStatusu();
		tworzenieCentralnejCzesci();
		
		
		// nasluchiwanie okna
		this.addWindowListener(new WindowAdapter()		
		{
			public void windowClosing(WindowEvent e)
				{
				 	closeWindow();		// zamkniecie okna
				 	System.exit(0);		// zamkniecie programu
				}
			private void closeWindow() {
				// TODO Auto-generated method stub
			}
		});
	}
	
	// tworzenie paska narzedzi z ikonkami	
	// TOOLBAR
	private void tworzenieToolBar(){
		
		toolbar = new JToolBar();
		
		// ikonka zapisania do pliku
		iconSave = new ImageIcon("src/images/save.png");
        saveButton = new JButton(iconSave);
        saveButton.setToolTipText("Zapisanie do pliku");
        toolbar.add(saveButton);
        saveButton.addActionListener(this);
        
        // ikonka drukowanie
        iconPrint = new ImageIcon("src/images/print.jpg");
        printButton = new JButton(iconPrint);
        printButton.setToolTipText("Drukowanie");
        toolbar.add(printButton);

        // ikonka zamkniecia aplikacji
        iconClose = new ImageIcon("src/images/close.jpg");
        closeButton = new JButton(iconClose);
        closeButton.setToolTipText("Zamknięcie aplikacji");
        toolbar.add(closeButton);
        
  	        // separator - odstep miedzy ikonkami
	        toolbar.add(Box.createHorizontalStrut(15));
        
        // ikonka dodania liczby do tablicy
        iconAdd = new ImageIcon("src/images/add.png");
        addButton = new JButton(iconAdd);
        addButton.setToolTipText("Dodanie liczby do tablicy");
        toolbar.add(addButton);
        addButton.addActionListener(this);
        
        // ikonka wyzerowania tablicy	
        iconReset = new ImageIcon("src/images/reset.png");
        resetButton = new JButton(iconReset);
        resetButton.setToolTipText("Wyzerowanie tablicy");
        toolbar.add(resetButton);
        resetButton.addActionListener(this);
        
        // ikonka wypelniania tablicy wartosciami randomowymi
        iconRandom = new ImageIcon("src/images/random.png");
        randomButton = new JButton(iconRandom);
        randomButton.setToolTipText("Wypełnienie tablicy liczbami losowymi");
        toolbar.add(randomButton);
        randomButton.addActionListener(this);
        
        	// separator - odstep miedzy ikonkami
        	toolbar.add(Box.createHorizontalStrut(15));
        
        // ikonka sumy
        iconSum = new ImageIcon("src/images/sum.png");
        sumButton = new JButton(iconSum);
        sumButton.setToolTipText("Obliczanie sumy elementów tablicy");
        toolbar.add(sumButton);
        sumButton.addActionListener(this);
        
        // ikonka sredniej	
        iconAvg = new ImageIcon("src/images/avg.png");
        avgButton = new JButton(iconAvg);
        avgButton.setToolTipText("Obliczanie średniej elementów tablicy");
        toolbar.add(avgButton);
        avgButton.addActionListener(this);
        
        // ikonka min
        iconMin = new ImageIcon("src/images/min.png");
        minButton = new JButton(iconMin);
        minButton.setToolTipText("Wyszukanie wartości MIN");
        toolbar.add(minButton);
        minButton.addActionListener(this);
        
        // ikonka max
        iconMax = new ImageIcon("src/images/max.png");
        maxButton = new JButton(iconMax);
        maxButton.setToolTipText("Wyszukanie wartości MAX");
        toolbar.add(maxButton);
        maxButton.addActionListener(this);
        
	        // separator - odstep miedzy ikonkami
	        toolbar.add(Box.createHorizontalStrut(15));
	        
        // ikonka pomocy
        iconHelp = new ImageIcon("src/images/help_context.jpg");
        helpButton = new JButton(iconHelp);
        helpButton.setToolTipText("Uruchomienie pomocy");
        toolbar.add(helpButton);
        helpButton.addActionListener(this);
        
        // ikonka o programie
        iconAbout = new ImageIcon("src/images/about.jpg");
        aboutButton = new JButton(iconAbout);
        aboutButton.setToolTipText("Informacje o programie");
        toolbar.add(aboutButton);
        aboutButton.addActionListener(this);
        
        
        // nasluchiwacz do ikonki zamkniecia apki
        closeButton.addActionListener((ActionEvent event) -> {
            System.exit(0);
        });

        add(toolbar, BorderLayout.NORTH);
		
	}
	
	// MENU ITEM,BAR
	private void tworzenieMenu() {
		menuBar = new JMenuBar();
		
		menuPlik = new JMenu("Plik");	// tworzenie pierwszego menu "Plik"		
			mItemZapisz = new JMenuItem("Zapisz", 'S'); // mnemonik 'Z' - szybka akcja skrotu klawiszowego
			mItemDrukuj = new JMenuItem("Drukuj", 'D');
			mItemZamknij = new JMenuItem("Zamknij", 'Z');
			
			menuPlik.add(mItemZapisz);
			menuPlik.add(mItemDrukuj);
			menuPlik.addSeparator();	// oddzielenie separatorem ostatniego wiersza "Zamknij"
			menuPlik.add(mItemZamknij);
			
			mItemZapisz.addActionListener(this);
			mItemDrukuj.addActionListener(this);
			mItemZamknij.addActionListener(this);
			
			mItemZapisz.setAccelerator(KeyStroke.getKeyStroke("alt L"));
			mItemDrukuj.setAccelerator(KeyStroke.getKeyStroke("alt D"));
			mItemZamknij.setAccelerator(KeyStroke.getKeyStroke("alt X"));	// AKCELERATOR - przypisanie skrotu klawiszowego CTRL+X do zamkniecia programu
			
		menuEdycja = new JMenu("Narzedzia");
			mItemDodajLiczbe = new JMenuItem("Dodaj liczbe", 'D');
			mItemWyzerujTablice = new JMenuItem("Wyzeruj tablice", 'W');
			mItemWypelnijLosowo = new JMenuItem("Wypelnij losowo", 'Y');
			
			menuEdycja.add(mItemDodajLiczbe);
			menuEdycja.add(mItemWyzerujTablice);
			menuEdycja.add(mItemWypelnijLosowo);
			
			mItemDodajLiczbe.addActionListener(this);
			mItemWyzerujTablice.addActionListener(this);
			mItemWypelnijLosowo.addActionListener(this);
			
			mItemDodajLiczbe.setAccelerator(KeyStroke.getKeyStroke("alt M"));
			mItemWyzerujTablice.setAccelerator(KeyStroke.getKeyStroke("alt N"));
			mItemWypelnijLosowo.setAccelerator(KeyStroke.getKeyStroke("alt R"));
			
		menuWidok = new JMenu("Widok");
			mChPasekNarzedziowy = new JCheckBoxMenuItem("Pasek narzedziowy");
			mChPasekStatusu = new JCheckBoxMenuItem("Pasek statusu");
			
			mChPasekNarzedziowy.setSelected(true);	// zaznaczony checkbox 
			mChPasekStatusu.setSelected(true);		// - || - 
			
			menuWidok.add(mChPasekNarzedziowy);
			menuWidok.add(mChPasekStatusu);
			
			mChPasekNarzedziowy.addActionListener(this);
			mChPasekStatusu.addActionListener(this);
			
		menuObliczenia = new JMenu("Obliczenia");
			mItemSumElementow = new JMenuItem("Oblicz sume elementow", 'O');
			mItemSrElementow = new JMenuItem("Oblicz srednia elementow", 'S');
			mItemLMin = new JMenuItem("Wyszukaj liczbe minimalna", 'L');
			mItemLMax = new JMenuItem("Wyszukaj liczbe maksymalna", 'M');
			
			menuObliczenia.add(mItemSumElementow);
			menuObliczenia.add(mItemSrElementow);
			menuObliczenia.add(mItemLMin);
			menuObliczenia.add(mItemLMax);
			
			mItemSumElementow.addActionListener(this);
			mItemSrElementow.addActionListener(this);
			mItemLMin.addActionListener(this);
			mItemLMax.addActionListener(this);
			
			
			mItemSumElementow.setAccelerator(KeyStroke.getKeyStroke("alt F"));
			mItemSrElementow.setAccelerator(KeyStroke.getKeyStroke("alt G"));
			mItemLMin.setAccelerator(KeyStroke.getKeyStroke("alt S"));
			mItemLMax.setAccelerator(KeyStroke.getKeyStroke("alt J"));
			
		menuPomoc = new JMenu("Pomoc");
			mItemOProgramie = new JMenuItem("Informacje o programie", 'I');
			menuPomoc.add(mItemOProgramie);
			mItemOProgramie.addActionListener(this);
			mItemOProgramie.setAccelerator(KeyStroke.getKeyStroke("alt H"));
			
			mItemPomoc = new JMenuItem("Pomoc", 'M');
			menuPomoc.add(mItemPomoc);
			mItemPomoc.addActionListener(this);
			mItemPomoc.setAccelerator(KeyStroke.getKeyStroke("alt I"));
			
			
			
		// ustawienie menu aby bylo widoczne
		setJMenuBar(menuBar); // dodanie do ramki JMenuBaru
		// dodanie poszczegolnych menu do menuBar
		menuBar.add(menuPlik);
		menuBar.add(menuEdycja);
		menuBar.add(menuWidok);
		menuBar.add(menuObliczenia);
		menuBar.add(menuPomoc);
	}
	
	
	// centralna czesc - GUI
	private void tworzenieCentralnejCzesci()
	{
		// wymiar dla komponentow JTextField
		Dimension dlugoscPola = new Dimension(100, 20);	
					
		centralnyPanel = new JPanel();
		centralnyPanel.setLayout(new GridBagLayout());
		centralnyPanel.setBorder(new EmptyBorder(20, 20, 20, 20));	// ustawienie ramki/marginesów okna
	//	centralnyPanel.setBackground(new Color(128, 128, 128));
		centralnyPanel.setPreferredSize(new Dimension(700, 500));
		add(centralnyPanel);
		
		centralnyPanel.setLayout(new FlowLayout(FlowLayout.LEFT));	// ustawienie tekstu od lewej strony	
        
        
		JLabel labelWprowadzLiczbe = new JLabel("Wprowadz liczbe");
		centralnyPanel.add(labelWprowadzLiczbe);
		
		textAreaWprowadzLiczbe = new JTextArea();
		textAreaWprowadzLiczbe.setPreferredSize(dlugoscPola);
		textAreaWprowadzLiczbe.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		textAreaWprowadzLiczbe.setText("0");
		
		centralnyPanel.add(textAreaWprowadzLiczbe);
		
		
		// separator - odstep miedzy ikonkami
        centralnyPanel.add(Box.createHorizontalStrut(25));
		
        
		JLabel labelNumerWiersza = new JLabel("Numer wiersza");
		centralnyPanel.add(labelNumerWiersza);
		

		
		textAreaNumerWiersza = new JSlider(JSlider.HORIZONTAL, 1, 5, 1);
		textAreaNumerWiersza.setMajorTickSpacing(1);	// wartosc wiersza - suwaka, co ile sie przesuwa
		textAreaNumerWiersza.setPaintLabels(true);		// ustawienie widoczności liczb na suwaku
		textAreaNumerWiersza.setPaintTicks(true);
		
		// utworzenie nowego panelu, w ktorym umieszczono jslidera - suwak
		JPanel topPanel = new JPanel(new BorderLayout());
		centralnyPanel.add(topPanel);
	        topPanel.setPreferredSize(new Dimension(100, 40));
	        topPanel.add(textAreaNumerWiersza);
	    textAreaNumerWiersza.addChangeListener(this);
	    labelNumerWierszaDisplay = new JLabel("1");
	    centralnyPanel.add(labelNumerWierszaDisplay);
	    
		
	    // separator - odstep miedzy ikonkami
        centralnyPanel.add(Box.createHorizontalStrut(25));
		
        
        JLabel labelNumerKolumny = new JLabel("Numer kolumny");
		centralnyPanel.add(labelNumerKolumny);
		
		textAreaNumerKolumny = new JSlider(JSlider.HORIZONTAL, 1, 5, 1);
		textAreaNumerKolumny.setMajorTickSpacing(1);	// wartosc wiersza - suwaka, co ile sie przesuwa
		textAreaNumerKolumny.setPaintLabels(true);		// ustawienie widoczności liczb na suwaku
		textAreaNumerKolumny.setPaintTicks(true);
		// utworzenie nowego panelu, w ktorym umieszczono jslidera - suwak
		JPanel topPanel1 = new JPanel(new BorderLayout());
		centralnyPanel.add(topPanel1);
	        topPanel1.setPreferredSize(new Dimension(100, 40));
	        topPanel1.add(textAreaNumerKolumny);
	    textAreaNumerKolumny.addChangeListener(this);
	    labelNumerKolumnyDisplay = new JLabel("1");
	    centralnyPanel.add(labelNumerKolumnyDisplay);
        
	    
	    // przerwa pozioma - odstep
	    JPanel przerwaPozioma = new JPanel(new BorderLayout());
		 przerwaPozioma.setPreferredSize(new Dimension(650, 10));
	    centralnyPanel.add(przerwaPozioma);
	    
	      
	    
		// DODANIE 4 PRZYCISKÓW
	//	DODAJ
		buttonDodaj = new JButton("Dodaj");
		iconAddMin = new ImageIcon("src/images/min_add.png");
		buttonDodaj.setIcon(iconAddMin);
		centralnyPanel.add(buttonDodaj);
	//	buttonDodaj.setBounds(500, 150, 100, 20);
		buttonDodaj.addActionListener(this);
	    
	 // separator - odstep miedzy ikonkami
        centralnyPanel.add(Box.createHorizontalStrut(25));
        
        // WYZERUJ
	    buttonWyzeruj = new JButton("Wyzeruj");
	    iconResetMin = new ImageIcon("src/images/min_reset.png");
		buttonWyzeruj.setIcon(iconResetMin);
	    centralnyPanel.add(buttonWyzeruj);
	    buttonWyzeruj.addActionListener(this);
	    
	 // separator - odstep miedzy ikonkami		
        centralnyPanel.add(Box.createHorizontalStrut(25));
        
        // WYPELNIJ LOSOWO
	    buttonWypelnij = new JButton("Wypelnij");
	    iconRandomMin = new ImageIcon("src/images/min_random.png");
		buttonWypelnij.setIcon(iconRandomMin);
	    centralnyPanel.add(buttonWypelnij);
	    buttonWypelnij.addActionListener(this);
	    
	 // separator - odstep miedzy ikonkami
        centralnyPanel.add(Box.createHorizontalStrut(25));
        
        // ZAPISZ
	    buttonZapisz = new JButton("Zapisz");
	    iconSaveMin = new ImageIcon("src/images/min_save.png");
	    buttonZapisz.setIcon(iconSaveMin);
	    centralnyPanel.add(buttonZapisz);
	    buttonZapisz.addActionListener(this);
	    
	    
	    // TWORZENIE TABELI
	    
	    String[] columnNames = {"1", "2", "3", "4", "5"};
	    
	    Object[][] data = {
	    	    {0,0,0,0,0},	// pierwszy wiersz w kolumnie
	    	    {0,0,0,0,0},
	    	    {0,0,0,0,0},
	    	    {0,0,0,0,0},
	    	    {0,0,0,0,0}
	    	};
	    table = new JTable(data, columnNames)
	    {
	    	// wyłączenie edytowania komórek w tabeli
	    	public boolean isCellEditable(int row, int column)
	    	{  
		          return false;  
		    }
	    };
	    
	    Dimension wymiarTabela = new Dimension(675, 100);    
	    JScrollPane scrollPane = new JScrollPane();
	    scrollPane.setPreferredSize(wymiarTabela);
	    scrollPane.setViewportView(table);
	    table.setFillsViewportHeight(true);
	    
	    table.setRowHeight(table.getRowHeight() + 4);	// wysokość wiersza, + 2 piksele z góry i dołu

	    //wyrownanie liczb do prawej strony w tabeli
	    DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
	    rightRenderer.setHorizontalAlignment(JLabel.RIGHT);
	    table.getColumnModel().getColumn(0).setCellRenderer(rightRenderer);
	    table.getColumnModel().getColumn(1).setCellRenderer(rightRenderer);
	    table.getColumnModel().getColumn(2).setCellRenderer(rightRenderer);
	    table.getColumnModel().getColumn(3).setCellRenderer(rightRenderer);
	    table.getColumnModel().getColumn(4).setCellRenderer(rightRenderer);
	    
	    centralnyPanel.add(scrollPane);
	    
	    // DODANIE JCOMBOBOX
	    String[] opcjeComboBox = { "Wybierz operację", "Suma elementów", "Średnia elementów", "Wartość MIN", "Wartość MAX" };

	    listaComboBox = new JComboBox(opcjeComboBox);
	    listaComboBox.setSelectedIndex(0);	// nadanie wartosci poczatkowej, ktora ma byc ustawiona na poczatku
	    listaComboBox.addActionListener(this);
	    centralnyPanel.add(listaComboBox);
	    
	    
	    buttonCheckBox = new JButton("Oblicz");
	    iconAvgMin = new ImageIcon("src/images/min_avg.png");
		buttonCheckBox.setIcon(iconAvgMin);
	    centralnyPanel.add(buttonCheckBox);
	    buttonCheckBox.addActionListener(this);
		    
	    
	 // przerwa pozioma - odstep
	    JPanel przerwaPozioma2 = new JPanel(new BorderLayout());
		przerwaPozioma2.setPreferredSize(new Dimension(650, 10));
	    centralnyPanel.add(przerwaPozioma2);
	    
	    
	    //  TWORZENIE POLA TEKSTOWEGO
	    textArea = new JTextArea(5, 60);
	    JScrollPane scrollPaneTextArea = new JScrollPane(textArea); 
	    textArea.setEditable(false);
	    centralnyPanel.add(scrollPaneTextArea);
	}
	
	
	private void tworzeniePaskaStatusu()
	{
		statusPanel = new JPanel();
		statusPanel.setBorder(new BevelBorder(BevelBorder.LOWERED));
		add(statusPanel, BorderLayout.SOUTH);
		
		statusPanel.setLayout(new FlowLayout(FlowLayout.LEFT));	// ustawienie tekstu od lewej strony
		
		statusLabel = new JLabel("INFO: ");
		statusPanel.add(statusLabel);
		
		statusLabel2 = new JLabel("Włączono aplikację.");
		statusPanel.add(statusLabel2);
	}
	
	// TWORZENIE GUI

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		// TODO Auto-generated method stub
		
		Object zrodlo = e.getSource();
		
		// WYPISANIE OBSLUGI MENU
		if (zrodlo == mItemZapisz) {
			zapiszDoPliku();
		}
		if (zrodlo == mItemZamknij) {
			int odpowiedz = JOptionPane.showConfirmDialog(null, "Czy napewno chcesz wyjsc z programu?", "Pytanie", JOptionPane.YES_NO_OPTION);
			if (odpowiedz == JOptionPane.YES_OPTION) {
				dispose(); // METODA KONCZĄCA PROGRAM
			}
			else if (odpowiedz == JOptionPane.NO_OPTION);
		}

		if (zrodlo == mItemDodajLiczbe){
			dodajLiczbeDoTabeli();
		}
		if (zrodlo == mItemWyzerujTablice){
			wyzerujTabele();
		}
		if (zrodlo == mItemWypelnijLosowo){
			wypelnijTabeleLosowo();
		}
		
		
		// OBSLUGA MENU ITEM: SUMA, SREDNIA, MIN, MAX	
				
		if (zrodlo == mItemSumElementow){
			obliczSumeElementow();
		}
		if (zrodlo == mItemSrElementow){
			obliczSredniaElementow();
		}
		if (zrodlo == mItemLMin){
			znajdzMinElementow();
		}
		if (zrodlo == mItemLMax){
			znajdzMaxElementow();
		}
			
		
		
		
		// PASKI STATUSU
		if (zrodlo == mChPasekNarzedziowy){
			if (mChPasekNarzedziowy.isSelected() == true){
				toolbar.setVisible(true);
				statusLabel2.setText("Włączono toolbar.");
			}else if (mChPasekNarzedziowy.isSelected() == false) {
				toolbar.setVisible(false);
				statusLabel2.setText("Wyłączono toolbar.");
			}
		}
		if (zrodlo == mChPasekStatusu){
			if (mChPasekStatusu.isSelected() == true){
				statusPanel.setVisible(true);
				statusLabel2.setText("Włączono statusBar.");
			}else if (mChPasekStatusu.isSelected() == false) {
				statusPanel.setVisible(false);
			}
		}
		
		if (zrodlo == mItemPomoc || zrodlo == helpButton) {
				OknoPomocy oknoPomocy = new OknoPomocy();
			}
		
		
		if (zrodlo == mItemOProgramie || zrodlo == aboutButton) {
			OknoAbout okienko = new OknoAbout();
		}
		
		// OBSLUGA PRZYCISKOW NA TOOLBAR
		
		if (zrodlo == saveButton) {		// przycisk zapisu
			zapiszDoPliku();
		}
		if (zrodlo == addButton) {		// przycisk dodania liczby do tabeli
			dodajLiczbeDoTabeli();
		}
		if (zrodlo == resetButton) {	// przycisk wyzerowania tabeli
			wyzerujTabele();
		}
		if (zrodlo == randomButton) {	// przycisk wypelnienia randomowego
			wypelnijTabeleLosowo();
		}
		
		
		if (zrodlo == sumButton) {		// przycisk obliczenia sumy elementow w tabeli
			obliczSumeElementow();
		}
		if (zrodlo == avgButton) {		// przycisk obliczenia sredniej elementow w tabeli
			obliczSredniaElementow();
		}
		if (zrodlo == minButton) {		// przycisk znalezienia wartosci MIN w tabeli
			znajdzMinElementow();
		}
		if (zrodlo == maxButton) {		// przycisk znalezienia wartosci MAX w tabeli
			znajdzMaxElementow();
		}
		   
		
		
		// OBSLUGA PRZYCISKÓW W PANELU CENTRALNYM
		
		if (zrodlo == buttonDodaj) {		// dodanie liczby do tablicy
			dodajLiczbeDoTabeli();
		}
		if (zrodlo == buttonWyzeruj) {		// wyzerowanie tablicy
			wyzerujTabele();
		}
		if (zrodlo == buttonWypelnij) {		// randomowe wypelnienie tablicy
			wypelnijTabeleLosowo();
		}
		if (zrodlo == buttonZapisz) {		// zapis wartosci do pliku
			zapiszDoPliku();
		}
		
		
		// OBSLUGA LISTY COMBOBOX	
		if (zrodlo == buttonCheckBox) {
			if (listaComboBox.getSelectedIndex() == 0){
				JOptionPane.showMessageDialog(this, "Nie wybrano operacji matematycznej na elementach tablicy.");
			}
			else if (listaComboBox.getSelectedIndex() == 1){
				obliczSumeElementow();							// suma elementow
			}
			else if (listaComboBox.getSelectedIndex() == 2){
				obliczSredniaElementow();						// srednia elementow
			}
			else if (listaComboBox.getSelectedIndex() == 3){
				znajdzMinElementow();							// min elementow
			}
			else if (listaComboBox.getSelectedIndex() == 4){
				znajdzMaxElementow();							// max elementow
			}
		}
		
	}

	private void obliczSumeElementow() {
		int pomocnicza, suma = 0;
		for(int wiersz = 0; wiersz < 5; wiersz++)
		{
			for(int kolumna = 0; kolumna < 5; kolumna++)
			{
				pomocnicza = (Integer) table.getValueAt(wiersz, kolumna);
				suma = suma + pomocnicza;
			}
		}
		textArea.append("Suma wszystkich wyrazów tablicy wynosi: " + suma + "\n");
		statusLabel2.setText("Obliczono sumę wszystkich elementów tablicy.");
	}

	private void obliczSredniaElementow() {
		int pomocnicza, suma = 0;
		double srednia = 0;
		for(int wiersz = 0; wiersz < 5; wiersz++)
		{
			for(int kolumna = 0; kolumna < 5; kolumna++)
			{
				pomocnicza = (Integer) table.getValueAt(wiersz, kolumna);
				suma = suma + pomocnicza;
			}
		}
		srednia = suma/25.0;
		textArea.append("Średnia wszystkich wyrazów tablicy wynosi: " + srednia + "\n");
		statusLabel2.setText("Obliczono średnią wartość wszystkich wyrazów tablicy.");
	}

	private void znajdzMinElementow() {
		int pomocnicza, min = (Integer) table.getValueAt(0, 0);
		for(int wiersz = 0; wiersz < 5; wiersz++)
		{
			for(int kolumna = 0; kolumna < 5; kolumna++)
			{
				pomocnicza = (Integer) table.getValueAt(wiersz, kolumna);
				if (pomocnicza < min){
					min = pomocnicza;
				}
			}
		}
		textArea.append("Wartość minimalna tablicy wynosi: " + min + "\n");
		statusLabel2.setText("Znaleziono wartość minimalną.");
	}

	private void znajdzMaxElementow() {
		int pomocnicza, max = (Integer) table.getValueAt(0, 0);
		for(int wiersz = 0; wiersz < 5; wiersz++)
		{
			for(int kolumna = 0; kolumna < 5; kolumna++)
			{
				pomocnicza = (Integer) table.getValueAt(wiersz, kolumna);
				if (pomocnicza > max){
					max = pomocnicza;
				}
			}
		}
		textArea.append("Wartość maksymalna tablicy wynosi: " + max + "\n");
		statusLabel2.setText("Znaleziono wartość maksymalną.");
	}

	private void wypelnijTabeleLosowo() {
		int min = 0;
		int max = 10000;
		
		for(int wiersz = 0; wiersz < 5; wiersz++)
		{
			for(int kolumna = 0; kolumna < 5; kolumna++)
			{
				int randomNum = ThreadLocalRandom.current().nextInt(min, max + 1);
				table.setValueAt(randomNum, wiersz, kolumna);
			}
		}
		textArea.append("Tabele wypelniono wartosciami losowymi.\n");
		statusLabel2.setText("Tabele wypelniono wartosciami losowymi.");
	}

	private void wyzerujTabele() {
		for(int wiersz = 0; wiersz < 5; wiersz++)
		{
			for(int kolumna = 0; kolumna < 5; kolumna++)
			{
				table.setValueAt(0, wiersz, kolumna);
			}
		}
		textArea.append("Tabela zostala wyzerowana.\n");
		statusLabel2.setText("Tabela zostala wyzerowana.");
	}

	private void dodajLiczbeDoTabeli() {
		try{
			int liczba, wiersz, kolumna;
			liczba = Integer.parseInt(textAreaWprowadzLiczbe.getText());
			wiersz = textAreaNumerWiersza.getValue();
			kolumna = textAreaNumerKolumny.getValue();
			table.setValueAt(liczba, wiersz - 1, kolumna - 1);
			textArea.append("Wprowadzono liczbe " + liczba + " w kolumnie " + kolumna + " i w wierszu " + wiersz + ".\n");	// dodanie tekstu do pola
			statusLabel2.setText("Dodano liczbę do tabeli.");
		}
		catch(NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "Prosze wpisac liczbe.");	// okno dialogowe do informacji o błędach
		}
		statusLabel2.setText("Dodano liczbe do tabeli.");
		
	}

	private void zapiszDoPliku() {
		try{
		    PrintWriter writer = new PrintWriter("plik_gui.txt", "UTF-8");
		   
		    for(int wiersz = 0; wiersz < 5; wiersz++)
			{
				for(int kolumna = 0; kolumna < 5; kolumna++)
				{
				//	writer.println(table.getValueAt(wiersz, kolumna));
					writer.print(table.getValueAt(wiersz, kolumna) + " ");
				}
			}
		    
		//    writer.println("The second line");
		    writer.close();
		} catch (IOException e1) {
		   // do something
		}
		textArea.append("Pomyslnie zapisano plik.\n");
		statusLabel2.setText("Pomyslnie zapisano plik.");
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		// TODO Auto-generated method stub
		
		int pobranaWartosc = textAreaNumerWiersza.getValue();
		String pobranaWartoscString = Integer.toString(pobranaWartosc);
		labelNumerWierszaDisplay.setText(pobranaWartoscString);
		
		int pobranaWartosc1 = textAreaNumerKolumny.getValue();
		String pobranaWartoscString1 = Integer.toString(pobranaWartosc1);
		labelNumerKolumnyDisplay.setText(pobranaWartoscString1);
	}
}