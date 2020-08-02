#!/bin/fish

javac Kadai5.java
java Kadai5 food.csv  > ./kadai5.txt
java Kadai5 food.csv N >> ./kadai5.txt
java Kadai5 food.csv C >> ./kadai5.txt
java Kadai5 food.csv F >> ./kadai5.txt
vi ./kadai5.txt
