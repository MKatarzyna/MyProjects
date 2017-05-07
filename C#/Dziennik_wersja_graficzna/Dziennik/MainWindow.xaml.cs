using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Globalization;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Navigation;
using System.Windows.Shapes;

namespace Dziennik
{
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window //samo zostało utwozone dla aplikacji wpf
    {
        //Zmienne globalne
        String wybranyDzien;
        String wybranyMiesiac;
        String wybranyRok;

        //Obiekty globalne klas, inicjalizacja
        DaneJSON klasaDaneJSON = new DaneJSON();
        DaneBMI klasaDaneBMI = new DaneBMI();
        DaneCwiczenia klasaDaneCwiczenia = new DaneCwiczenia();
        DaneData klasaDaneData = new DaneData();
        DaneJedzenie klasaDaneJedzenie = new DaneJedzenie();
        DanePlyny klasaDanePlyny = new DanePlyny();

        UzytkownicyJSON klasaUzytkownicyJSON = new UzytkownicyJSON();
        
        public MainWindow()
        {
            //Domyślna inicjalizacja aplikacji okienkowej, xaml
            InitializeComponent();

            //Przypisanie obiektów do głównego obiektu agregującego, czyli tworzenie nowych klas uzywając klas już istniejących
            klasaDaneJSON.BMI = klasaDaneBMI;
            klasaDaneJSON.Cwiczenia = klasaDaneCwiczenia;
            klasaDaneJSON.Data = klasaDaneData;
            klasaDaneJSON.Jedzenie = klasaDaneJedzenie;
            klasaDaneJSON.Plyny = klasaDanePlyny;

            //Wczytanie uzytkownikow do aplikacji
            WczytajUzytkownikow();

            //Ustawienie trybu kalendarza
            ustawKalendarz();
        }
        private void WczytajUzytkownikow()  
        {
            listBoxUsers.Items.Clear();
            UzytkownicyJSON nowaKlasaUzytkownicyJSON = JsonConvert.DeserializeObject<UzytkownicyJSON>(File.ReadAllText(@"users.json")); //zamiana z pliku textowego json na obiekt uzytkownicy json
            klasaUzytkownicyJSON = nowaKlasaUzytkownicyJSON;
            int iloscUzytkownikow = klasaUzytkownicyJSON.Uzytkownicy.Count;
            for (int i = 0; i < iloscUzytkownikow; i++)
            {
                listBoxUsers.Items.Add(klasaUzytkownicyJSON.Uzytkownicy[i].imie + " " + klasaUzytkownicyJSON.Uzytkownicy[i].nazwisko); //dodawanie do listy uzytkownikow
            }
        }

        private void ustawKalendarz()
        {   // wlasciwosci kalendarza
            CalendarItem.DisplayDate = DateTime.Today; //wyswietlnie daty, wlasciwosci(datetime..)
            CalendarItem.IsTodayHighlighted = true; 
            CalendarItem.SelectionMode = CalendarSelectionMode.SingleDate;

            CalendarItem.SelectedDate = new DateTime(DateTime.Today.Year, DateTime.Today.Month, DateTime.Today.Day);

            //Zapamietaj date w zmiennych globalnych
            wybranyDzien = CalendarItem.SelectedDate.Value.ToString("dd");
            wybranyMiesiac = CalendarItem.SelectedDate.Value.ToString("MM");
            wybranyRok = CalendarItem.SelectedDate.Value.ToString("yyyy");

            labelCurrentlyLoadedDate.Content = wybranyDzien + "/" + wybranyMiesiac + "/" + wybranyRok;
        }

        private void Wyjscie(object sender, RoutedEventArgs e) //standardowo
        {
            Console.WriteLine("wyjscie z programu");
            MessageBox.Show("Exit");
            Application.Current.Shutdown();
        }

        private void buttonDApplyDate(object sender, RoutedEventArgs e) 
        {
            wybranyDzien = CalendarItem.SelectedDate.Value.ToString("dd");
            wybranyMiesiac = CalendarItem.SelectedDate.Value.ToString("MM");
            wybranyRok = CalendarItem.SelectedDate.Value.ToString("yyyy");
            labelCurrentlyLoadedDate.Content = wybranyDzien + "/" + wybranyMiesiac + "/" + wybranyRok;
            //Powiadomienia
            MessageBox.Show(wybranyDzien + "/" + wybranyMiesiac + "/" + wybranyRok);
            labelStatusBar.Content = "Date applied.";

            int numerZaznaczonegoUzytkownika = listBoxUsers.SelectedIndex;
            if (numerZaznaczonegoUzytkownika != -1) 
            {
                MessageBox.Show("Loading data for user: " + klasaUzytkownicyJSON.Uzytkownicy[numerZaznaczonegoUzytkownika].imie + " " + klasaUzytkownicyJSON.Uzytkownicy[numerZaznaczonegoUzytkownika].nazwisko + " for Date: " + wybranyDzien + "/" + wybranyMiesiac + "/" + wybranyRok);
                labelStatusBar.Content = "Loading data...";

                string nazwaFolderuUzytkownika = klasaUzytkownicyJSON.Uzytkownicy[numerZaznaczonegoUzytkownika].folder;
                if (sprawdzCzyFolderIstnieje(nazwaFolderuUzytkownika) == true)
                {
                    MessageBox.Show("Folder exists");
                    //sprawdz czy json istnieje
                    if (sprawdzCzyJsonIstnieje(wybranyDzien, wybranyMiesiac, wybranyRok, nazwaFolderuUzytkownika) == true)
                    {
                        MessageBox.Show("JSON exists");
                        //tak - wczytaj
                        wczytajPlikjson_DaneUzytkownika(wybranyDzien, wybranyMiesiac, wybranyRok, nazwaFolderuUzytkownika);
                        wyczyscPola();
                        wyswietlDaneWProgramie();
                        MessageBox.Show("Loaded data for user for date: " + wybranyDzien + "/" + wybranyMiesiac + "/" + wybranyRok);
                        labelStatusBar.Content = "Loading data for user";
                    }
                    else
                    {
                        MessageBox.Show("Nie znalezione pliku JSON. Utworzono nowy plik dla wybranej daty: " + wybranyDzien + "/" + wybranyMiesiac + "/" + wybranyRok);
                        wyczyscPola();
                        //nie - utworz pusty json
                        utworzNowyPlikJson_DaneUzytkownika(wybranyDzien, wybranyMiesiac, wybranyRok, nazwaFolderuUzytkownika);
                        //wczytaj json
                        wczytajPlikjson_DaneUzytkownika(wybranyDzien, wybranyMiesiac, wybranyRok, nazwaFolderuUzytkownika);
                        wyswietlDaneWProgramie();
                    }
                }
                else
                {
                    MessageBox.Show("Brak folderu do wczytania danych");
                }
            }
            else
            {
                MessageBox.Show("Please select user from the list");
                labelStatusBar.Content = "User not selected from the list";
            }

        }

        private void buttonBMISaveAction(object sender, RoutedEventArgs e)
        {
            int numerZaznaczonegoUzytkownika = listBoxUsers.SelectedIndex;
            if (numerZaznaczonegoUzytkownika != -1)
            {
                if (textBoxUserNumber.Text != "") //musi byc zaznaczony urzytkownik
                {
                    if (Int32.Parse(textBoxUserNumber.Text) == numerZaznaczonegoUzytkownika) //wczytany uzytkownik, jak jest wczytany to jest wartość w tym polu DISABLED(wyszarzone)
                    {   //int32 zmienia TEKST na INT
                        klasaDaneJSON.BMI.waga = float.Parse(textBoxBMIWeight.Text);
                        klasaDaneJSON.BMI.wzrost = float.Parse(textBoxBMIHeight.Text);
                        klasaDaneJSON.BMI.wynik = float.Parse(textBoxBMIIndex.Text);

                        zapiszDaneUzytkownika(numerZaznaczonegoUzytkownika);
                        MessageBox.Show("BMI data saved to JSON file");
                        labelStatusBar.Content = "BMI data saved successfully.";
                    }
                    else
                    {
                        MessageBox.Show("Other user selected than already loaded user data. Plase load the user again to the application.");
                    }
                }
                else
                {
                    MessageBox.Show("User selected, but not loaded. Plase load the user to the application.");
                }
            }
            else
            {
                MessageBox.Show("Please select user from the list");
                labelStatusBar.Content = "User not selected from the list";
            }
        }

        private void zapiszDaneUzytkownika(int numerZaznaczonegoUzytkownika)
        {
            string nazwaFolderuUzytkownika = klasaUzytkownicyJSON.Uzytkownicy[numerZaznaczonegoUzytkownika].folder;
            if (sprawdzCzyFolderIstnieje(nazwaFolderuUzytkownika) == true)
            {
                MessageBox.Show("Folder exists");
                //sprawdz czy json istnieje
                if (sprawdzCzyJsonIstnieje(wybranyDzien, wybranyMiesiac, wybranyRok, nazwaFolderuUzytkownika) == true)
                {
                    MessageBox.Show("JSON exists");
                    //tak
                    string nazwaPliku = wybranyDzien + wybranyMiesiac + wybranyRok;

                    // serialize JSON to a string and then write string to a file
                    File.WriteAllText(@"" + nazwaFolderuUzytkownika + "/" + nazwaPliku + ".json", JsonConvert.SerializeObject(klasaDaneJSON));
                }
                else
                {
                    MessageBox.Show("JSON not exists");
                }
            }
            else
            {
                MessageBox.Show("Folder not exists");
            }
        }

        private void buttonLLiquids(object sender, RoutedEventArgs e)
        {
            int numerZaznaczonegoUzytkownika = listBoxUsers.SelectedIndex;
            if (numerZaznaczonegoUzytkownika != -1)
            {
                if (textBoxUserNumber.Text != "")
                {
                    if (Int32.Parse(textBoxUserNumber.Text) == numerZaznaczonegoUzytkownika)
                    {
                        klasaDaneJSON.Plyny.ilosc = float.Parse(textBoxLiquids.Text);

                        zapiszDaneUzytkownika(numerZaznaczonegoUzytkownika);
                        MessageBox.Show("Liquids data saved to JSON file");
                        labelStatusBar.Content = "Liquids data saved successfully.";
                    } else
                    {
                        MessageBox.Show("Other user selected than already loaded user data. Plase load the user again to the application.");
                    }
                } else
                {
                    MessageBox.Show("User selected, but not loaded. Plase load the user to the application.");
                } 
            }
            else
            {
                MessageBox.Show("Please select user from the list");
                labelStatusBar.Content = "User not selected from the list";
            }
        }

        private void buttonMeals(object sender, RoutedEventArgs e)
        {
            int numerZaznaczonegoUzytkownika = listBoxUsers.SelectedIndex;
            if (numerZaznaczonegoUzytkownika != -1)
            {
                if (textBoxUserNumber.Text != "")
                {
                    if (Int32.Parse(textBoxUserNumber.Text) == numerZaznaczonegoUzytkownika)
                    {
                        klasaDaneJSON.Jedzenie.sniadanie = textBoxBreakfast.Text;
                        klasaDaneJSON.Jedzenie.drugieSniadanie = textBoxSecondBreakfast.Text;
                        klasaDaneJSON.Jedzenie.obiad = textBoxDinner.Text;
                        klasaDaneJSON.Jedzenie.podwieczorek = textBoxAfternoonMeal.Text;
                        klasaDaneJSON.Jedzenie.kolacja = textBoxSupper.Text;

                        zapiszDaneUzytkownika(numerZaznaczonegoUzytkownika);
                        MessageBox.Show("Food data saved to JSON file");
                        labelStatusBar.Content = "Food data saved successfully.";
                    }
                    else
                    {
                        MessageBox.Show("Other user selected than already loaded user data. Plase load the user again to the application.");
                    }
                }
                else
                {
                    MessageBox.Show("User selected, but not loaded. Plase load the user to the application.");
                }
            }
            else
            {
                MessageBox.Show("Please select user from the list");
                labelStatusBar.Content = "User not selected from the list";
            }
        }

        private void buttonExercises_Click(object sender, RoutedEventArgs e)
        {
            int numerZaznaczonegoUzytkownika = listBoxUsers.SelectedIndex;
            if (numerZaznaczonegoUzytkownika != -1)
            {
                if (textBoxUserNumber.Text != "")
                {
                    if (Int32.Parse(textBoxUserNumber.Text) == numerZaznaczonegoUzytkownika)
                    {
                        klasaDaneJSON.Cwiczenia.czyCwiczyles = bool.Parse(checkBoxExerciseToday.IsChecked.ToString());
                        klasaDaneJSON.Cwiczenia.ileMinut = float.Parse(textBoxHowManyMinutes.Text, CultureInfo.InvariantCulture.NumberFormat);
                        klasaDaneJSON.Cwiczenia.rodzajCwiczen = textBoxTypeOfExercis.Text;

                        zapiszDaneUzytkownika(numerZaznaczonegoUzytkownika);
                        MessageBox.Show("Exercises data saved to JSON file");
                        labelStatusBar.Content = "Exercises data saved successfully.";
                    }
                    else
                    {
                        MessageBox.Show("Other user selected than already loaded user data. Plase load the user again to the application.");
                    }
                }
                else
                {
                    MessageBox.Show("User selected, but not loaded. Plase load the user to the application.");
                }
            }
            else
            {
                MessageBox.Show("Please select user from the list");
                labelStatusBar.Content = "User not selected from the list";
            }
        }

        private void buttonBCalculateBmi(object sender, RoutedEventArgs e)
        {
            //BMI = waga / ((wzrost * wzrost) / 10000);
            float wynik = float.Parse(textBoxBMIWeight.Text) / ((float.Parse(textBoxBMIHeight.Text) * float.Parse(textBoxBMIHeight.Text)) / 10000);
            textBoxBMIIndex.Text = wynik.ToString();

            MessageBox.Show("BMI: " + wynik);
            labelStatusBar.Content = "Calculated BMI";
        }

        private void CalendarDateChanged(object sender, SelectionChangedEventArgs e)
        {
            labelSelectedDate.Content = CalendarItem.SelectedDate.Value.ToString("dd") + "/" + CalendarItem.SelectedDate.Value.ToString("MM") + "/" + CalendarItem.SelectedDate.Value.ToString("yyyy");
            //labelCurrentlyLoadedDate.Content = wybranyDzien + "/" + wybranyMiesiac + "/" + wybranyRok;
            Mouse.Capture(null);
        }

        private void buttonUSaveChanges(object sender, RoutedEventArgs e) 
        {
            if (textBoxUserNumber.Text != "")
            {
                int numerZaznaczonegoUzytkownika = Int32.Parse(textBoxUserNumber.Text);
                klasaUzytkownicyJSON.Uzytkownicy[numerZaznaczonegoUzytkownika].imie = textBoxName.Text;
                klasaUzytkownicyJSON.Uzytkownicy[numerZaznaczonegoUzytkownika].nazwisko = textBoxSurname.Text;
                klasaUzytkownicyJSON.Uzytkownicy[numerZaznaczonegoUzytkownika].folder = textBoxFolder.Text;

                File.WriteAllText(@"users.json", JsonConvert.SerializeObject(klasaUzytkownicyJSON));
                labelStatusBar.Content = "File saved successfully.";

                WczytajUzytkownikow();
                wyczyscPolaTekstoweUzytkownika();
                wyczyscPola();
            } else
            {
                MessageBox.Show("Please load the user from the list");
                labelStatusBar.Content = "Not loaded user from the list";
            }         
        }

        private void buttonUAdd(object sender, RoutedEventArgs e) 
        {
            if (textBoxName.Text != "" && textBoxSurname.Text != "" && textBoxFolder.Text != "")
            {
                ustawKalendarz();
                DaneUzytkownik klasaDaneUzytkownik = new DaneUzytkownik();
                klasaDaneUzytkownik.folder = textBoxFolder.Text;
                klasaDaneUzytkownik.imie = textBoxName.Text;
                klasaDaneUzytkownik.nazwisko = textBoxSurname.Text;
                klasaUzytkownicyJSON.Uzytkownicy.Add(klasaDaneUzytkownik);

                File.WriteAllText(@"users.json", JsonConvert.SerializeObject(klasaUzytkownicyJSON));

                utworzFolder(klasaDaneUzytkownik.folder);

                utworzNowyPlikJson_DaneUzytkownika(wybranyDzien, wybranyMiesiac, wybranyRok, klasaDaneUzytkownik.folder);

                WczytajUzytkownikow();

                MessageBox.Show("User successfully added.");
                labelStatusBar.Content = "User successfully added.";

                wyczyscPolaTekstoweUzytkownika();
                wyczyscPola();
            }
            else
            {
                MessageBox.Show("Please type data to Name, Surname and Folder fields");
                labelStatusBar.Content = "Missing data in Name, Surname and Folder fields";
            }

        }

        private void buttonUDelete(object sender, RoutedEventArgs e)  
        {
            int numerZaznaczonegoUzytkownika = listBoxUsers.SelectedIndex;
            if (numerZaznaczonegoUzytkownika != -1)
            {
                ustawKalendarz();

                usunFolder(klasaUzytkownicyJSON.Uzytkownicy[numerZaznaczonegoUzytkownika].folder);

                klasaUzytkownicyJSON.Uzytkownicy.RemoveAt(numerZaznaczonegoUzytkownika);

                File.WriteAllText(@"users.json", JsonConvert.SerializeObject(klasaUzytkownicyJSON));

                WczytajUzytkownikow();

                MessageBox.Show("User successfully deleted.");
                labelStatusBar.Content = "User successfully deleted.";

                wyczyscPolaTekstoweUzytkownika();
                wyczyscPola();
            }
            else
            {
                MessageBox.Show("Please select user from the list");
                labelStatusBar.Content = "User not selected";
            }

        }

        private void buttonULoad(object sender, RoutedEventArgs e)  
        {
            int numerZaznaczonegoUzytkownika = listBoxUsers.SelectedIndex;
            //Console.WriteLine(numerZaznaczonegoUzytkownika);
            if (numerZaznaczonegoUzytkownika != -1)
            {
                ustawKalendarz();
                // new objects!
                textBoxName.Text = klasaUzytkownicyJSON.Uzytkownicy[numerZaznaczonegoUzytkownika].imie;
                textBoxSurname.Text = klasaUzytkownicyJSON.Uzytkownicy[numerZaznaczonegoUzytkownika].nazwisko;
                textBoxFolder.Text = klasaUzytkownicyJSON.Uzytkownicy[numerZaznaczonegoUzytkownika].folder;
                textBoxUserNumber.Text = numerZaznaczonegoUzytkownika.ToString();
                MessageBox.Show("Selected user: " + klasaUzytkownicyJSON.Uzytkownicy[numerZaznaczonegoUzytkownika].imie + " " + klasaUzytkownicyJSON.Uzytkownicy[numerZaznaczonegoUzytkownika].nazwisko);
                labelStatusBar.Content = "User loaded successfully.";

                string nazwaFolderuUzytkownika = klasaUzytkownicyJSON.Uzytkownicy[numerZaznaczonegoUzytkownika].folder;
                if (sprawdzCzyFolderIstnieje(nazwaFolderuUzytkownika) == true)
                {
                    MessageBox.Show("Folder exists");
                    //sprawdz czy json istnieje
                    if(sprawdzCzyJsonIstnieje(wybranyDzien, wybranyMiesiac, wybranyRok, nazwaFolderuUzytkownika) == true)
                    {
                        MessageBox.Show("JSON exists");
                        //tak - wczytaj
                        wczytajPlikjson_DaneUzytkownika(wybranyDzien, wybranyMiesiac, wybranyRok, nazwaFolderuUzytkownika);
                        wyczyscPola();
                        wyswietlDaneWProgramie();
                    } else
                    {
                        MessageBox.Show("JSON not exists");
                        wyczyscPola();
                        //nie - utworz pusty json
                        utworzNowyPlikJson_DaneUzytkownika(wybranyDzien, wybranyMiesiac, wybranyRok, nazwaFolderuUzytkownika);
                        //wczytaj json
                        wczytajPlikjson_DaneUzytkownika(wybranyDzien, wybranyMiesiac, wybranyRok, nazwaFolderuUzytkownika);
                        wyswietlDaneWProgramie();
                    }
                } else
                {
                    MessageBox.Show("Folder not exists");
                    wyczyscPola();
                    //stworz folder
                    utworzFolder(nazwaFolderuUzytkownika);
                    //utworz json
                    utworzNowyPlikJson_DaneUzytkownika(wybranyDzien, wybranyMiesiac, wybranyRok, nazwaFolderuUzytkownika);
                    //wczytaj json
                    wczytajPlikjson_DaneUzytkownika(wybranyDzien, wybranyMiesiac, wybranyRok, nazwaFolderuUzytkownika);
                    wyswietlDaneWProgramie();
                }
            } else
            {
                MessageBox.Show("Please select user from the list");
                labelStatusBar.Content = "User not selected from the list";
            }
        }

        private void oAplikacji(object sender, RoutedEventArgs e)
        {
            MessageBox.Show("Nazwa aplikacji:\n-Dziennik - Zdrowy Tryb\n\nOpcje:\n-Obliczanie BMI\n-Raportowanie ilość spożytych płynów\n-Raportowanie posiłków\n-Raportowanie ćwiczeń");
        }

        private void oAutorze(object sender, RoutedEventArgs e)
        {
            MessageBox.Show("Autor: Katarzyna Mural\n\nKierunek: Informatyka \n\nSemestr: 3 \n\nPrzedmiot: Zastosowanie Programowania Obiektowego \n\nRok: 2016/2017");
        }

        /*
         * Pozostałe funkcje
         * 
         */

        private bool sprawdzCzyFolderIstnieje(string folder)
        {
            string sciezkaDoPliku = @"" + folder + "";
            if (Directory.Exists(sciezkaDoPliku))
            {
                return true;
            }
            else
            {
                return false;
            }  
        }

        private bool sprawdzCzyJsonIstnieje(string dzien, string miesiac, string rok, string folder)
        {
            string sciezkaDoPliku = @"" + folder + "/"+dzien+miesiac+rok+".json";
            if (File.Exists(sciezkaDoPliku))
            {
                return true;
            }
            else
            {
                return false;
            }
        }

        private void utworzNowyPlikJson_DaneUzytkownika(string dzien, string miesiac, string rok, string folder)  
        {
            DaneJSON NowaKlasaDaneJSON = new DaneJSON();
            DaneBMI NowaKlasaDaneBMI = new DaneBMI();
            DaneCwiczenia NowaKlasaDaneCwiczenia = new DaneCwiczenia();
            DaneData NowaKlasaDaneData = new DaneData();
            DaneJedzenie NowaKlasaDaneJedzenie = new DaneJedzenie();
            DanePlyny NowaKlasaDanePlyny = new DanePlyny();
            NowaKlasaDaneJSON.BMI = NowaKlasaDaneBMI;
            NowaKlasaDaneJSON.Cwiczenia = NowaKlasaDaneCwiczenia;
            NowaKlasaDaneJSON.Data = NowaKlasaDaneData;
            NowaKlasaDaneJSON.Jedzenie = NowaKlasaDaneJedzenie;
            NowaKlasaDaneJSON.Plyny = NowaKlasaDanePlyny;

            // JSON structure of empty file
            NowaKlasaDaneJSON.BMI.waga = 0;
            NowaKlasaDaneJSON.BMI.wzrost = 0;
            NowaKlasaDaneJSON.BMI.wynik = 0;

            NowaKlasaDaneJSON.Cwiczenia.czyCwiczyles = true;
            NowaKlasaDaneJSON.Cwiczenia.ileMinut = 0;
            NowaKlasaDaneJSON.Cwiczenia.rodzajCwiczen = " ";

            NowaKlasaDaneJSON.Data.dzien = dzien;
            NowaKlasaDaneJSON.Data.miesiac = miesiac;
            NowaKlasaDaneJSON.Data.rok = rok;

            NowaKlasaDaneJSON.Jedzenie.sniadanie = " ";
            NowaKlasaDaneJSON.Jedzenie.drugieSniadanie = " ";
            NowaKlasaDaneJSON.Jedzenie.obiad = " ";
            NowaKlasaDaneJSON.Jedzenie.podwieczorek = " ";
            NowaKlasaDaneJSON.Jedzenie.kolacja = " ";

            NowaKlasaDaneJSON.Plyny.ilosc = 0;

            string nazwaPliku = dzien + miesiac + rok;

            // serialize JSON to a string and then write string to a file -> przekształć json na tekst i później zapisz do pliku
            File.WriteAllText(@""+ folder+"/" + nazwaPliku + ".json", JsonConvert.SerializeObject(NowaKlasaDaneJSON));
        }

        private void wczytajPlikjson_DaneUzytkownika(string dzien, string miesiac, string rok, string folder)
        {
            string nazwaPliku = dzien + miesiac + rok;
            // read file into a string and deserialize JSON to a type. 
            //tworzy obiekt typu DaneJSON (klasa) z pliku w folderze wybranym np. kasiafolder/26012017.json. 
            // nastepnie podmieni to co wczytał z tym globalnie dostępnym
                        DaneJSON nowaKlasaDaneJSON = JsonConvert.DeserializeObject<DaneJSON>(File.ReadAllText(@""+ folder +"/"+nazwaPliku+".json"));
            klasaDaneJSON = nowaKlasaDaneJSON;
        }

        private void usunFolder(string folder)
        {
            string sciezkaDoFolderu = @"" + folder + "";

            try
            {
                // Determine whether the directory exists. ustal czy folder istnieje
                if (Directory.Exists(sciezkaDoFolderu))
                {
                    // Delete the directory. / usun folder
                    DirectoryInfo di = Directory.CreateDirectory(sciezkaDoFolderu);
                    di.Delete();
                    Console.WriteLine("The directory was deleted successfully.");
                    return;
                } else
                {
                    Console.WriteLine("The directory doesn't exists.");
                }
            }
            catch (Exception e)
            {
                Console.WriteLine("The process failed: {0}", e.ToString());
            }
            finally { }
        }

        private void utworzFolder(string folder)  
        {
            string sciezkaDoPliku = @"" + folder + "";

            try
            {
                // Determine whether the directory exists. ustal czy plik istnieje
                if (Directory.Exists(sciezkaDoPliku))
                {
                    Console.WriteLine("That path exists already.");
                    return;
                }

                // Try to create the directory. sprobuj utworzyc plik
                DirectoryInfo di = Directory.CreateDirectory(sciezkaDoPliku);
                Console.WriteLine("The directory was created successfully at {0}.", Directory.GetCreationTime(sciezkaDoPliku));
            }
            catch (Exception e)
            {
                Console.WriteLine("The process failed: {0}", e.ToString());
            }
            finally { }
        }

        private void wyswietlDaneWProgramie()
        {
            textBoxBMIWeight.Text = klasaDaneJSON.BMI.waga.ToString();
            textBoxBMIHeight.Text = klasaDaneJSON.BMI.wzrost.ToString();
            textBoxBMIIndex.Text = klasaDaneJSON.BMI.wynik.ToString();
            textBoxLiquids.Text = klasaDaneJSON.Plyny.ilosc.ToString();
            textBoxBreakfast.Text = klasaDaneJSON.Jedzenie.sniadanie.ToString();
            textBoxSecondBreakfast.Text = klasaDaneJSON.Jedzenie.drugieSniadanie.ToString();
            textBoxDinner.Text = klasaDaneJSON.Jedzenie.obiad.ToString();
            textBoxAfternoonMeal.Text = klasaDaneJSON.Jedzenie.podwieczorek.ToString();
            textBoxSupper.Text = klasaDaneJSON.Jedzenie.kolacja.ToString();
            checkBoxExerciseToday.IsChecked = klasaDaneJSON.Cwiczenia.czyCwiczyles;
            textBoxHowManyMinutes.Text = klasaDaneJSON.Cwiczenia.ileMinut.ToString();
            textBoxTypeOfExercis.Text = klasaDaneJSON.Cwiczenia.rodzajCwiczen.ToString();
        }

        private void wyczyscPola()
        {
            textBoxBMIWeight.Text = "";
            textBoxBMIHeight.Text = "";
            textBoxBMIIndex.Text = "";
            textBoxLiquids.Text = "";
            textBoxBreakfast.Text = "";
            textBoxSecondBreakfast.Text = "";
            textBoxDinner.Text = "";
            textBoxAfternoonMeal.Text = "";
            textBoxSupper.Text = "";
            checkBoxExerciseToday.IsChecked = false;
            textBoxHowManyMinutes.Text = "";
            textBoxTypeOfExercis.Text = "";
        }

        private void wyczyscPolaTekstoweUzytkownika()
        {
            textBoxFolder.Text = "";
            textBoxSurname.Text = "";
            textBoxName.Text = "";
            textBoxUserNumber.Text = "";
        }

        private void MenuItem_Click(object sender, RoutedEventArgs e)
        {

        }
    }
}
