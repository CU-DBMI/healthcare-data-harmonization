run this from the base directory:

gradle :runtime:run -q --args="-m $(pwd)/ws2-example/main.wstl -i $(pwd)/ws2-example/in.json"

For Java 11 cli run, something like this once the dependencies are downloaded:

java -cp all-dependencies/* \
    -jar runtime/build/libs/runtime-dev-SNAPSHOT.jar  \
    com.google.cloud.verticals.foundations.dataharmonization.Main 
    -m $(pwd)/ws2-example/main.wstl \
    -i $(pwd)/ws2-example/in.json



