#!/bin/bash

powtorka="y"
while [ "$powtorka" = y ]
do

echo
echo "Podaj nazwe katalogu do zamontowania macierzy RAID(np. /media/nazwa_katalogu) lub do zamontowania woluminu logicznego(np. /mnt/nazwa_katalogu)"
read katalog

mkdir -p $katalog
echo
echo "Czy chcesz zamontowac wolumin logiczy lub macierz RAID? (y/n) "
read potwierdzenie

if [ $potwierdzenie = "y" ]
then
echo
echo "Podaj nazwe woluminu logicznego(np. /dev/vg00/lv00) lub macierzy RAID(np. /dev/md0)"
read nazwaMacierzy
mount $nazwaMacierzy $katalog
fi

echo
echo "Czy chcesz zamontowac kolejny wolumin logiczy lub macierz RAID? (y/n)"
read opcjaYN

if [ $opcjaYN = n ]
then 
powtorka="n"
fi

done