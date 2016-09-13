rsync -av --delete -e 'ssh -p22' ../xeo dani@fast05.ftmc.uam.es:/home/dani/bin/
