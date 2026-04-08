
#!/bin/bash
cd target
./mvnw package -DskipTests
 scp bookstore-0.0.1-SNAPSHOT.jar lottav@softala.haaga-helia.fi:

ssh lottav@softala.haaga-helia.fi "java -jar harjoitusprojekti-0.0.1-SNAPSHOT.jar"