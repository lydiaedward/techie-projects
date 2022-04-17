## Suburbs Processor
This application exposes 2 APIs
1. /api/suburbs/add - Adds given suburbs to in memory database
2. /api/suburbs/search - Searches suburbs from DB based on given postcode range

## Run Command
mvn clean install spring-boot:run

## Sample Add Request
### API Endpoint
http://localhost:8085/api/suburbs/add

### API Request:
{
"suburbDetails": [
{
"name": "Perth",
"postCode": "6001"
},
{
"name": "Highgate",
"postCode": "6003"
},
{
"name": "Northbridge",
"postCode": "6003"
},
{
"name": "East Perth",
"postCode": "6004"
},
{
"name": "West Perth",
"postCode": "6005"
},
{
"name": "Kings Park",
"postCode": "6005"
},
{
"name": "North Perth",
"postCode": "6006"
},
{
"name": "Leederville",
"postCode": "6007"
},
{
"name": "West Leederville",
"postCode": "6007"
},
{
"name": "Daglish",
"postCode": "6008"
},
{
"name": "Shenton Park",
"postCode": "6008"
},
{
"name": "Subiaco",
"postCode": "6008"
},
{
"name": "Subiaco East",
"postCode": "6008"
},
{
"name": "Crawley",
"postCode": "6009"
},
{
"name": "Dalkeith",
"postCode": "6009"
},
{
"name": "Nedlands",
"postCode": "6009"
},
{
"name": "Nedlands Dc",
"postCode": "6009"
},
{
"name": "Claremont",
"postCode": "6010"
},
{
"name": "Karrakatta",
"postCode": "6010"
},
{
"name": "Mount Claremont",
"postCode": "6010"
},
{
"name": "Swanbourne",
"postCode": "6010"
}
]
}

## Sample Search Request
### API Endpoint: 
http://localhost:8085/api/suburbs/search

### API Request:
{
"searchCriteria": {
"endPostCode": "6010",
"startPostCode": "6004"
}
}