#!/bin/bash

vgscan -v

powtorka="y"
while [ "$powtorka" = y ]
do

echo
echo "Podaj macierz RAID(np. /dev/md0) lub partycje(np. /dev/sdb1), aby stworzyc wolumin fizyczny:"
read nazwaMacierz
pvcreate $nazwaMacierz
pvdisplay

echo
echo "Czy utworzyc nastepny wolumin fizyczny? (y/n)"
read wyborNastepne

if [ $wyborNastepne = "n" ]
then
powtorka="n"
fi

done


powtorka="y"
while [ "$powtorka" = y ]
do
echo
echo "Podaj nazwe grupy woluminu:"
read grupaWoluminu
echo
echo "Podaj woluminy fizyczne."
echo "Dla RAID(np. /dev/md0 lub /dev/md0 /dev/md1 ...)"
echo "Dla partycji(np. /dev/sdb1 lub /dev/sdb1 /dev/sdc1 ...):"
read woluminyFizyczne
vgcreate $grupaWoluminu $woluminyFizyczne
vgscan
vgdisplay

echo
echo "Czy utworzyc nastepna grupe woluminu? (y/n)"
read wyborNastepne

if [ $wyborNastepne = "n" ]
then
powtorka="n"
fi

done


powtorka="y"
while [ "$powtorka" = y ]
do

echo "Czy chcesz utworzyc wolumin logiczny? (y/n)"
read opcjaYN

if [ $opcjaYN = y ]
then
echo
echo "Podaj nazwe woluminu logicznego:"
read nazwaWolLog
echo
echo "Podaj rozmiar woluminu logicznego(np. 64M)"
echo "(rozmiar musi byc potega cyfry 2)"
read rozmiarWolLog
lvcreate -L $rozmiarWolLog -n $nazwaWolLog $grupaWoluminu
lvdisplay
fi

if [ $opcjaYN = n ]
then 
powtorka="n"
fi
done