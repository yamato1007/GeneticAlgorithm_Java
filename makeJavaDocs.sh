#!/bin/sh

DIRNAME=documents
JAVAFILESNAME=javaFiles.txt
SRCDIR=src

rm -rf "${DIRNAME}"
mkdir $DIRNAME

find $SRCDIR -name '*.java' -print > $JAVAFILESNAME
javadoc -d $DIRNAME @"${JAVAFILESNAME}"
