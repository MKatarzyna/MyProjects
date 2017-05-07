#!/bin/bash

#Odmontowanie dysku
echo
echo "Czy chcesz odmontowac dysk? (y/n)"
read wyborNastepne

powtorka="y"
while [ "$powtorka" = y ]
do

if [ $wyborNastepne = "y" ]
then
echo
echo "Podaj sciezke do dysku RAID(np. /dev/md0) lub woluminu logicznego(np. /dev/vg00/lv00)"
read macierz
umount $macierz
fi

echo "Czy odmontowac nastepny dysk? (y/n)"
read wyborNastepne

if [ $wyborNastepne = "n" ]
then
powtorka="n"
fi

done

#usuniecie lvm
echo
echo "Czy chcesz usunac konfiguracje LVM z dysku? (y/n)"
read wyborNastepne

if [ $wyborNastepne = "y" ]
then
echo


echo "USUWANIE WOLUMINU LOGICZNEGO"
powtorka="y"
while [ "$powtorka" = y ]
do
echo "Podaj nazwe woluminu logicznego(np. /dev/vg00/lv00)"
read nazwaWolLog
lvremove $nazwaWolLog <<EEOF
y
EEOF

echo "Czy wyswietlic woluminy logiczne? (y/n)"
read wyborWyswietlWolLog

if [ $wyborWyswietlWolLog = "y" ]
then
lvdisplay
fi

echo "Czy usunac nastepny wolumin logiczny? (y/n)"
read wyborNastepne

if [ $wyborNastepne = "n" ]
then
powtorka="n"
fi
done


echo "USUWANIE GRUPY WOLUMINU"
powtorka="y"
while [ "$powtorka" = y ]
do
echo "Podaj nazwe grupy woluminu(np. vg00)"
read nazwaGrWol
vgremove $nazwaGrWol

echo "Czy wyswietlic grupy woluminow? (y/n)"
read wyborWyswietlWolLog

if [ $wyborWyswietlWolLog = "y" ]
then
vgdisplay $nazwaGrWol
fi

echo "Czy usunac nastepna grupe woluminu? (y/n)"
read wyborNastepne

if [ $wyborNastepne = "n" ]
then
powtorka="n"
fi
done

echo
echo "USUWANIE WOLUMINU FIZYCZNEGO"
powtorka="y"
while [ "$powtorka" = y ]
do
echo
echo "Podaj nazwe woluminu fizycznego(dla RAID np. /dev/md0 lub dla partycji /dev/sdb1) do usuniecia"
read nazwaWolFiz
pvremove $nazwaWolFiz
echo
echo "Czy wyswietlic woluminy fizyczne? (y/n)"
read wyborWyswietlWolLog

if [ $wyborWyswietlWolLog = "y" ]
then
pvdisplay
fi
echo
echo "Czy usunac nastepny wolumin fizyczny? (y/n)"
read wyborNastepne

if [ $wyborNastepne = "n" ]
then
powtorka="n"
fi
done


fi


#usuniecie raida
echo
echo "Czy chcesz usunac RAID z dysku? (y/n)"
read wyborNastepne

powtorka="y"
while [ "$powtorka" = y ]
do

if [ $wyborNastepne = "y" ]
then
echo
echo "Podaj sciezke do dysku RAID(np. /dev/md0)"
read macierz
mdadm --stop $macierz
fi

echo "Czy usunac RAID z nastepnego dysku? (y/n)"
read wyborNastepne

if [ $wyborNastepne = "n" ]
then
powtorka="n"
fi

done



#usuwanie partycji

echo
echo "Czy chcesz usunac partycje? (y/n)"
read wyborNastepne

powtorka="y"
while [ "$powtorka" = y ]
do

if [ $wyborNastepne = "y" ]
then
echo
echo "Podaj nazwe dysku do usuniecia partycji(np. /dev/sdb): "
read partycja
fdisk $partycja <<EEOF
d
w
EEOF
fi

echo
echo "Czy chcesz sprawdzic wszystkie partycje? (y/n)"
read wyborPartycji
if [ $wyborPartycji = "y" ]
then
echo
fdisk -l
fi

echo
echo "Czy usunac nastepna partycje? (y/n)"
read wyborNastepne

if [ $wyborNastepne = "n" ]
then
powtorka="n"
fi

done



#usuwanie folderow
echo
echo "Czy usunac folder ? (y/n)"
read wyborNastepne

powtorka="y"
while [ "$powtorka" = y ]
do

if [ $wyborNastepne = "y" ]
then
echo "Podaj nazwe folderu do usuniecia."
echo "Dla RAID(np. /media/nazwa_folderu)."
echo "Dla LVM(np. /mnt/nazwa_folderu)."
read deleteFolder

rm -r $deleteFolder
fi

echo "Czy usunac nastepny folder? (y/n)"
read wyborNastepne

if [ $wyborNastepne = "n" ]
then
powtorka="n"
fi

done
