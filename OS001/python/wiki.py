import requests
import json
import sys

url = "https://en.wikipedia.org/w/api.php?action=opensearch&namespace=0&limit=1&format=json&search="

n = len(sys.argv)
searchString = ""

if(n == 1 or n == 0):
    searchString = input("Enter the search string: ")
    searchString = searchString.strip()
    url = url + searchString
else:
    for i in sys.argv:
        if i == __file__:
            continue
        url = url + i.strip() + " "
    url = url.strip()

url = ' '.join(url.split(' '))
url = '_'.join(url.split(' '))

response = requests.request("GET", url,)

print("\n")

f = open("results.txt", "a")
try:
    print(response.json()[3][0])
    f.write(response.json()[3][0]+"\n")
except:
    print("Not found")
    f.write("Not found\n")
finally:
    f.close()