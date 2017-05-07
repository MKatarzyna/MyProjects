#!/bin/bash

powtorka="y"
while [ "$powtorka" = y ]
do
echo
echo "Podaj sciezke do macierzy RAID(np. /dev/md0)"
echo "lub woluminu logicznego(np. /dev/vg00/lv00)"
read nazwaMacierzy
echo
echo "Podaj sytem plikow(np. mkfs.ext4)"
read systemPlikow

$systemPlikow $nazwaMacierzy
echo
echo "Czy chcesz wykonac MKFS na nastepnej partycji? (y/n)"
read opcjaYN

if [ $opcjaYN = n ]
then 
powtorka="n"
fi

done