# Getting Started

## Installation

Use the following command to install the project:

```bash
./mvnw clean install
```

## Usage (Compile)

Use the following command to compile the project:

```bash
./mvnw compile
```

## Usage (Development)

Use the following command to run the project:

```bash
./mvnw spring-boot:run
```

## Usage (Production)

Use the following command to run the project:

```bash
./mvnw clean package
java -jar target/*.jar
```

## Testing

Use the following command to run the tests:

```bash
./mvnw test
```

## Fix problems with git pull

If you have problems with `git pull`, use the following command:

```bash
git add -A
git stash
git pull
```

## Run mysql container with podman

Use the following command to run a mysql container:

```bash
podman run -d -p 3307:3306 -e MYSQL_DATABASE=mydatabase -e MYSQL_USER=myuser -e MYSQL_PASSWORD=secret -e 
MYSQL_ROOT_PASSWORD=alguno mysql:9.0.0
```

### Maven Parent overrides

Due to Maven's design, elements are inherited from the parent POM to the project POM.
While most of the inheritance is fine, it also inherits unwanted elements like `<license>` and `<developers>` from the parent.
To prevent this, the project POM contains empty overrides for these elements.
If you manually switch to a different parent and actually want the inheritance, you need to remove those overrides.

