#rm -f CHARGES
#rm -f *.dat 
#rm -f *.xv *.ac 
../progs/fireball.x >> salida.out  
