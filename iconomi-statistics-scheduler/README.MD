
##schedule quartz jobs for periodic load and store data from iconomi to local data base

run with 
VM Arguments : -Dspring.config.import=classpath:datasource.properties
program arguments : --API_KEY=XXXXXXXX --API_SECRET=YYYYYYYYY

$ java -Dspring.config.import=classpath:datasource.properties  -jar ./iconomi-statistics-bot-0.0.1-SNAPSHOT.jar --API_KEY=API_KEY --API_SECRET=API_SECRET

for more informations about API_KEY and API_SECRET check https://github.com/iconomi-ag/iconomi-api/blob/master/web/description.md
