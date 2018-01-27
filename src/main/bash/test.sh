#!/usr/bin/env bash

echo "Welcome. Filename: \$0 = $0"
not_empty_file=$0

if [ -s $not_empty_file ]
then
    echo "OK. Not empty"
fi

if [ ! -d $not_empty_file ]
then
    echo "OK. Not directory"
fi

# weird short ifelse structure.
readable=$([ -r $not_empty_file ] || echo 'RRR' && echo 'Nr')
echo "Permissions: $readable $([ -w $not_empty_file ]) %n \r"

# test command. Just test condition. Return '0' if OK. 1 - otherwise.
test -r $not_empty_file
echo $?
test -w $not_empty_file
echo $?

if ! [[ -x $not_empty_file && ( -s $not_empty_file || -r $not_empty_file) ]]
then
    echo "complex if statement OK"
fi


if (( 11 % 2 == 1)); then
    echo Ariphmetic condition is OK
fi