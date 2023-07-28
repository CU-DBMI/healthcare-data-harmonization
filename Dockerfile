FROM eclipse-temurin:17-jammy AS build
WORKDIR /build
RUN apt-get update; apt-get -y install git protobuf-compiler golang-go protoc-gen-go
COPY /build_all.sh /build/
COPY /mapping_engine /build/mapping_engine
COPY /mapping_language /build/mapping_language
RUN ./build_all.sh


FROM ubuntu:22.04 as final

WORKDIR /whistle-cli
RUN apt-get update && \
    apt-get install -y python3 curl rustc && \
    rm -rf /var/lib/apt/lists/*
RUN export POETRY_HOME=/whistle-cli; curl -sSL https://install.python-poetry.org | python3 -
COPY --from=build /build/mapping_engine/main/main /usr/bin/whistle
COPY /docker-setup/usr/bin/whistle-cli /usr/bin/whistle-cli
COPY /docker-setup/whistle-cli/pyproject.toml /docker-setup/whistle-cli/poetry.lock  ./
COPY /docker-setup/whistle-cli/poetry-docker.toml ./poetry.toml
RUN bin/poetry install --no-root --no-directory
COPY /docker-setup/whistle-cli/README.md .
COPY /docker-setup/whistle-cli/src/ ./src
RUN bin/poetry install --no-dev
#
WORKDIR /whistle
ENTRYPOINT ["/usr/bin/whistle-cli"]
CMD ["--help"]



