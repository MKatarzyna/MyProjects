#!/bin/bash

wybranaOpcjaRaid=2
powtorka="tak"
while [ "$powtorka" = tak ]
do

echo "Czy chcesz stworzyc RAID 0 czy RAID 1?"
echo "(dla RAID 0 wpisz: 0, dla RAID 1 wpisz: 1)"
read wybranaOpcjaRaid
echo
if [ $wybranaOpcjaRaid = 0 ]
then
echo "Wybrales RAID 0"
powtorka="koniec"
fi

if [ $wybranaOpcjaRaid = 1 ]
then
echo "Wybrales RAID 1"
powtorka="koniec"
fi

done

echo "Podaj nazwe nowej macierzy RAID(np. /dev/md0)"
read nazwaMacierzy

poziom="raid"
parametr2="$poziom$wybranaOpcjaRaid"

echo "Podaj sciezke do pierwszej partycji(np. /dev/sdb1): "
read partycja1
echo
echo "Podaj sciezke do drugiej partycji(np. /dev/sdc1): "
read partycja2

mdadm --create $nazwaMacierzy --level=$parametr2 --raid-devices=2 $partycja1 $partycja2 <<EEOF
y
EEOF
echo
echo "STATUS MACIERZY:"
mdadm --detail $nazwaMacierzy
echo 
