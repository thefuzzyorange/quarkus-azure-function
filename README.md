# quarkus-azure-function
Quarkus Azure Function Chunked Encoding

Demonstrates http chunked-encoding error encountered when running Quarkus application as an Azure Function.

##Description
When data exceeds a certain limit Quarkus HTTP response uses chunk encoding.
This breaks clients when the application is run as an Azure Function.

The application exports Eclipse MicroProfile Config as JSON on /api/config which is roughly 20KB. 
This is returned as HTTP chunk encoded

##Testing

Execute: mvn clean compile quarkus:dev
curl -i localhost:8080/api/config

Returns 
TTP/1.1 200 OK
Content-Type: application/json
Transfer-Encoding: chunked

<data>
  
  
Execute: mvn clean compile install azure-functions:run
curl -i localhost:7071/api/config

Returns
TTP/1.1 200 OK
Date: Thu, 08 Oct 2020 15:19:48 GMT
Content-Type: application/json
Server: Kestrel
Transfer-Encoding: chunked

curl: (56) Illegal or missing hexadecimal sequence in chunked-encoding

