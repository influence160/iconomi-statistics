
##Load datas from iconomi using the REST API and the REST client https://github.com/influence160/iconomi-java

run with 
VM Arguments : -Dspring.config.import=classpath:datasource.properties login password
program arguments : --API_KEY=XXXXXXXX --API_SECRET=YYYYYYYYY

$ java -Dspring.config.import=classpath:datasource.properties  -jar ./iconomi-statistics-data-importer-0.2.0-SNAPSHOT.jar --API_KEY=API_KEY --API_SECRET=API_SECRET

for more informations about API_KEY and API_SECRET check https://github.com/iconomi-ag/iconomi-api/blob/master/web/description.md
