# darfat-spray-rest-h2
Assesment Test Scala - Salestock Indonesia - November 2015

# -- Test CURL --


# Insert Data :
curl -H "Accept: application/json" -H "Content-type: application/json" -X POST -d '{ "cartCode":"001","productCode": "002", "qty" : 2,"total" : 1000 }' http://localhost:8080/cart

curl -H "Accept: application/json" -H "Content-type: application/json" -X POST -d '{ "cartCode":"001","productCode": "003", "qty" : 1,"total" : 500 }'  http://localhost:8080/cart


curl -H "Accept: application/json" -H "Content-type: application/json" -X POST -d '{ "cartCode":"002","productCode": "005", "qty" : 2,"total" : 300 }' http://localhost:8080/cart

# Retrieve ALL Data
curl -H GET http://localhost:8080/cart

# Retrieve By Code
curl -H GET http://localhost:8080/cart/002

# Update By COde
curl -v -X "Accept: application/json" -H "Content-type: application/json" -X PUT -d '{ "cartCode":"002","productCode": "005", "qty" : 1,"total" : 150 }' http://localhost:8080/cart/002

curl -H GET http://localhost:8080/cart/002

# Delete By Code
curl -v -X DELETE http://localhost:8080/cart/002

curl -H GET http://localhost:8080/cart
