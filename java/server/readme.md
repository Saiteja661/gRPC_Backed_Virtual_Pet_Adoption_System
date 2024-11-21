#create project only requires to create new project
mvn archetype:generate -DgroupId=com.example -DartifactId=grpc-user-service -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false
#build
mvn clean install
#Run
mvn exec:java -Dexec.mainClass="com.pet.PetServer"
#Test 
mvn test

## to create docker container
`docker build -t grpc-pet-service-server .`
## to run docker
`docker run --rm grpc-pet-service-server`