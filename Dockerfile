FROM eclipse-temurin:17-jammy AS build
WORKDIR /build
RUN apt-get update; apt-get -y install git protobuf-compiler golang-go protoc-gen-go
COPY /build_all.sh /build/
COPY /mapping_engine /build/mapping_engine
COPY /mapping_language /build/mapping_language
RUN ./build_all.sh


FROM ubuntu:22.04 as final
WORKDIR /whistle
COPY --from=build /build/mapping_engine/main/main /usr/bin/whistle
ENTRYPOINT ["/usr/bin/whistle"]
CMD ["--help"]



