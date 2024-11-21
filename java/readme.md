#create project only requires to create new project
mvn archetype:generate -DgroupId=com.example -DartifactId=grpc-user-service -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false
#build
mvn clean install
#Run
mvn exec:java -Dexec.mainClass="com.example.UserServer"
mvn exec:java -Dexec.mainClass="com.example.Client"