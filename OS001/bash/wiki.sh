#!/bin/bash
let n=$#-1
i=0
url="https://en.wikipedia.org/w/api.php?action=opensearch&namespace=0&limit=1&format=json&search="
for var in "$@"

do
    if [ $i -ne $n ]
    then
        url+=${var}"_"
    else
        url+=${var}
    fi
    let i=$i+1
done
if [ $# -eq 0 ]
then
    echo "Enter the search String"
    read search
    search=${search##*( )}
    search=$(echo $search | tr -s ' ' | tr ' ' '_') 
    url+="$search"
fi
json=$(curl $url)
IFS=','
read -ra urlString <<< "$json"
responseUrl="${urlString[-1]}"
store1=$(echo $responseUrl | tr '[' ' ' | tr ']' ' ')
store=$(echo $store1 | tr -s ' ')
echo -ne "###               (33%)\r"
sleep 0.5
echo -ne "#########         (66%)\r"
sleep 0.2
echo -ne "#############     (100%)\r"
echo -ne "\n"
echo $store
echo $store >> ./results.txt