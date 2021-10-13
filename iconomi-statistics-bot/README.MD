
##Load datas from iconomi using selenium

**_This project is deprecated and was replaced by iconomi-statistics-data-importer_**

run with 
VM Arguments : -Dspring.config.import=classpath:datasource.properties login password
program arguments : --login=xxxxxxxxx --pwd=yyyyyyyyyy


$ java -Dspring.config.import=classpath:datasource.properties  -jar ./iconomi-statistics-bot-0.0.1-SNAPSHOT.jar --login=login --pwd=password