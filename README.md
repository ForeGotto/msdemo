# msdemo
## A micro service providing encryption and decryption service
## To encrypt a string, visit the url below
```
http://localhost:8080/encipher?password="password"&plaintext="plaintext"
```
## e.g.
```
http://localhost:8080/encipher?password="hello"&plaintext="hahahaha"
```
## And you will get this:
```
{"result":"Ctd2lrSXNFto8jAfuJMM2pUoX0ZQDF6TktJ4d7xBmq8="}
```
## To decrypt a piece of ciphertext
```
http://localhost:8080/decipher?password="password"&cipherText="cipherText"
```
## e.g.
```
http://localhost:8080/decipher?password="hello"&cipherText="Ctd2lrSXNFto8jAfuJMM2pUoX0ZQDF6TktJ4d7xBmq8="
```
## Then you get this
```
{"result":"hahahaha"}
```
## If your input is incorrect, then you will get the error info
```json
{"result":"cipher text"}
```
