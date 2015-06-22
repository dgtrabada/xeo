cd language

#firefox 'http://translate.google.com/translate_t?hl=es#en|es|english%0Aspanish%0Aedit%0Aedit atoms%0Aopen%0Aclose%0Apreview%0Apreview (save as)%0Aduplicate selected atoms%0Asuprime selected atoms%0Acopy selected atoms%0Achange Z%0A'


traducir=""
fin=$(wc -l english | cut -d' ' -f1)
for((i=1;i<$((fin));i++))
do
col=$(sed -n "$((i+1)),$((i+1)) p" english)
nota=$(echo $col | cut -d' ' -f1)
cadena=$(echo $col | cut -d' ' -f2-)
#echo $cadena
traducir=${traducir}${cadena}%0A
done

fire=firefox" 'http://translate.google.com/translate_t?hl=es#en|es|"$traducir"'"

echo $fire
