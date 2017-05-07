#!/bin/bash

echo "Skrypt do tworzenia macierzy RAID i uzycia LVM"
echo
echo "Podaj sciezke z nazwa do pierwszego dysku(np. /dev/sdb): "
read dyskA
echo
echo "Podaj sciezke z nazwa do drugiego dysku(np. /dev/sdc): "
read dyskB
echo
echo "Dane dysku: $dyskA"
fdisk -l $dyskA
echo
echo "Dane dysku: $dyskB"
fdisk -l $dyskB
echo
echo "Skonfigurowac partycje dla RAID(1) czy LVM(2)?"
read konfiguracja

if [ $konfiguracja = "1" ]
then
echo "Utworzenie partycji dla pierwszego dysku(konfiguracja dla LVM): $dyskA "
# Utworzenie partycji dla pierwszego dysku
fdisk $dyskA <<EEOF
n
p
1


t
fd
p
w
EEOF

# Utworzenie partycji dla drugiego dysku
echo "Utworzenie partycji dla drugiego dysku(konfiguracja dla LVM): $dyskB "
echo
fdisk $dyskB <<EEOF
n
p
1


t
fd
p
w
EEOF
fi


if [ $konfiguracja = "2" ]
then
echo "Utworzenie partycji dla pierwszego dysku(konfiguracja dla RAID): $dyskA "
# Utworzenie partycji dla pierwszego dysku
fdisk $dyskA <<EEOF
n
p
1


t
8e
p
w
EEOF

# Utworzenie partycji dla drugiego dysku
echo "Utworzenie partycji dla drugiego dysku(konfiguracja dla RAID): $dyskB "
echo
fdisk $dyskB <<EEOF
n
p
1


t
8e
p
w
EEOF
fi