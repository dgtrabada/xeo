#primero de ingles a ....
# corregimos español y de español a portuges,frances, catalan ....

cadena=""
for((i=0;i<$(wc -l src/languages/english | cut -d' ' -f1);i++)) 
do 
cadena=${cadena}$(sed -n "$((i+1)),$((i+1)) p" src/languages/english)%0A
done

echo arabic
firefox 'http://translate.google.com/translate_t?hl=es#en|ar|'"$cadena"
echo catalan
firefox 'http://translate.google.com/translate_t?hl=es#en|ca|'"$cadena"
echo czech
firefox 'http://translate.google.com/translate_t?hl=es#en|cs|'"$cadena"
echo english
firefox 'http://translate.google.com/translate_t?hl=es#en|en|'"$cadena"
echo french
firefox 'http://translate.google.com/translate_t?hl=es#en|fr|'"$cadena"
echo galician
firefox 'http://translate.google.com/translate_t?hl=es#en|gl|'"$cadena"
echo german
firefox 'http://translate.google.com/translate_t?hl=es#en|de|'"$cadena"
echo hindi
firefox 'http://translate.google.com/translate_t?hl=es#en|hi|'"$cadena"
echo italiano
firefox 'http://translate.google.com/translate_t?hl=es#en|it|'"$cadena"
echo japanese    
firefox 'http://translate.google.com/translate_t?hl=es#en|ja|'"$cadena"
echo mandarin
firefox 'http://translate.google.com/translate_t?hl=es#en|zh-TW|'"$cadena"
echo polish 
firefox 'http://translate.google.com/translate_t?hl=es#en|pl|'"$cadena"
echo portuguese 
firefox 'http://translate.google.com/translate_t?hl=es#en|pt|'"$cadena"
echo russian 
firefox 'http://translate.google.com/translate_t?hl=es#en|ru|'"$cadena"
echo spanish
firefox 'http://translate.google.com/translate_t?hl=es#en|es|'"$cadena" 
echo WU '(chino simplificado)'
firefox 'http://translate.google.com/translate_t?hl=es#en|zh-CN|'"$cadena"

echo falta : euskara y bengali


