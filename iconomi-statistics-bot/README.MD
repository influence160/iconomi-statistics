run with 
VM Arguments : -Dspring.config.import=classpath:datasource.properties login password
program arguments : --login=xxxxxxxxx --pwd=yyyyyyyyyy


$ java -Dspring.config.import=classpath:datasource.properties  -jar ./iconomi-statistics-bot-0.0.1-SNAPSHOT.jar --login=login --pwd=password