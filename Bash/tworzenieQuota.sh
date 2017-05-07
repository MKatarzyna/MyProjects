#!/bin/bash

echo "Czy utworzyc uzytkownika?(y/n)"
read wybor

powtorka="y"
while [ "$powtorka" = y ]
do
if [ $wybor = "y" ]
then
echo "Podaj nazwe uzytkownika:"
read user
adduser $user
fi
echo "Czy utworzyc dodatkowego uzytkownika?(y/n)"
read wybor
if [ $wybor = "n" ]
then
powtorka="n"
fi
done

echo "Czy wyswietlic wszytkich uzytkownikow?(y/n)"
read wybor
if [ $wybor = "y" ]
then
cat /etc/passwd
fi

echo "Czy utworzyc nowa grupe?(y/n)"
read wybor
powtorka="y"
while [ "$powtorka" = y ]
do
if [ $wybor = "y" ]
then
echo "Podaj nazwe grupy:"
read grupa
addgroup $grupa
fi
echo "Czy utworzyc nastepna grupe?(y/n)"
read wybor
if [ $wybor = "n" ]
then
powtorka="n"
fi
done

echo "Czy wyswietlic wszytkie grupy?(y/n)"
read wybor
if [ $wybor = "y" ]
then
cat /etc/group
fi

echo "Czy chcesz dopisac uzytkownika do grupy?(y/n)"
read wybor
powtorka="y"
while [ "$powtorka" = y ]
do
if [ $wybor = "y" ]
then
echo "Podaj uzytkownika(np. user1):"
read user
echo "Podaj grupe(np. grupa1):"
read grupa
usermod -g $grupa $user
fi

echo "Czy chcesz dopisac nastepnego uzytkownika do grupy?(y/n)"
read wybor
if [ $wybor = "n" ]
then
powtorka="n"
fi
done

echo
echo "Czy chcesz skonfigurowac QUOTA?(y/n)"
read wybor
if [ $wybor = "y" ]
then
echo "Podaj nazwe partycji do zamontowania dysku(np. /dev/md0)"
read partycja
echo "Podaj nazwe katalogu do zamontowania dysku(np. /media/mojDysk1)"
read sciezka
#umount $partycja $sciezka
echo "Nalezy zedytowac i zapisać plik FSTAB. Dodaj(,usrquota,grpquota) i zapisz plik."
echo "Kliknij ENTER"
read zmienna
echo
nano /etc/fstab
echo
mkdir $sciezka
echo
echo "Wlaczenie QUOTA"
quotaon -avug
echo "Odmontowanie napedu"
umount $sciezka
echo "Ponowne zamontowanie napedu"
mount $sciezka
echo "Wlaczenie QUOTACHECK dla sprawdzenia/wygenerowania quota.user i quota.group plikow"
quotaoff -augv
quotacheck -augv
quotaon -avug
quotacheck -augv
echo
echo
echo
echo "Czy chcesz ustawic limity plikow dla uzytkownika?(y/n)"
read wybor
powtorka="y"
while [ "$powtorka" = y ]
do
if [ $wybor = "y" ]
then
echo "Podaj uzytkownika(np. user1)"
read user
echo "Podaj limit SOFT(np. 10000)"
read limitSoft
echo "Podaj limit HARD(np. 12500)"
read limitHard
echo "Podaj sciezka z nazwa katalogu do zamontowanego dysku(np. /media/mojDysk1)"
read sciezka
setquota -u $user $limitSoft $limitHard 0 0 $sciezka
fi
echo "Czy chcesz ustawic limity plikow dla nastepnego uzytkownika?(y/n)"
read wybor
if [ $wybor = "n" ]
then
powtorka="n"
fi
done
echo
echo "Czy chcesz ustawic limity plikow dla grupy?(y/n)"
read wybor
powtorka="y"
while [ "$powtorka" = y ]
do
if [ $wybor = "y" ]
then
echo "Podaj grupe(np. grupa1)"
read grupa
echo "Podaj limit SOFT(np. 25000)"
read limitSoft
echo "Podaj limit HARD(np. 37500)"
read limitHard
echo "Podaj sciezka z nazwa katalogu do zamontowanego dysku(np. /media/mojDysk1)"
read sciezka
setquota -g $grupa $limitSoft $limitHard 0 0 $sciezka
fi
echo "Czy chcesz ustawic limity plikow dla nastepnej grupy?(y/n)"
read wybor
if [ $wybor = "n" ]
then
powtorka="n"
fi
done
echo
echo
echo "Czy chcesz zobaczyc plik z ustawionymi limitami dla uzytkownika?(y/n)"
read wybor
powtorka="y"
while [ "$powtorka" = y ]
do
if [ $wybor = "y" ]
then
echo "Podaj uzytkownika(np. user1)"
read user
edquota -u $user
fi
echo "Czy chcesz zobaczyc plik z ustawionymi limitami dla nastepnego uzytkownika?(y/n)"
read wybor
if [ $wybor = "n" ]
then
powtorka="n"
fi
done
echo
echo
echo "Czy chcesz zobaczyc plik z ustawionymi limitami dla grup?(y/n)"
read wybor
powtorka="y"
while [ "$powtorka" = y ]
do
if [ $wybor = "y" ]
then
echo "Podaj grupe(np. grupa1)"
read grupa
edquota -g $grupa
fi
echo "Czy chcesz zobaczyc plik z ustawionymi limitami dla nastepnej grupy?(y/n)"
read wybor
if [ $wybor = "n" ]
then
powtorka="n"
fi
done
echo
echo
fi



echo
echo "Czy chcesz wyswietlic raport QUOTA dla uzytkownikow?(y/n)"
read wybor
if [ $wybor = "y" ]
then
echo "Podaj sciezka z nazwa katalogu do zamontowanego dysku(np. /media/mojDysk1)"
read sciezka
repquota -u $sciezka
fi
echo

echo
echo "Czy chcesz wyswietlic raport QUOTA dla grupy?(y/n)"
read wybor
if [ $wybor = "y" ]
then
echo "Podaj sciezka z nazwa katalogu do zamontowanego dysku(np. /media/mojDysk1)"
read sciezka
repquota -g $sciezka
fi
echo
