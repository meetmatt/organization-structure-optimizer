# Organization Structure Optimizer
Welcome to the documentation for the **Organization Structure Optimizer** aka **OSO**!

## Features
- Analyzes the organization structure
- Identifies potential improvements
- Detects disbalance between managers' and subordinates' salaries
- Finds inefficiently long reporting lines
- Supports structured CSV files as input
- Generates human-readable reports to console
- Works entirely via the command line interface
- Supports containerized (e.g. Docker, Kubernetes) and non-containerized runtime environments

**Note**: Windows is **NOT** supported as containerized build environment
and may not even work as containerized runtime (not tested).

## Installation

### Maven
If you'd like to try chances with the locally provided environment, run this command to build the project:
```sh
mvn clean validate test package site
```

### Docker
Run the following command to build the docker image:
```sh
./bin/docker-build.sh
```
That's the recommended way which guarantees consistent results in most containerized environments **except Windows**.

## Usage
OSO requires an input file describing the organization structure. Only CSV format is supported.
Here's an example of a `schema.csv`:
```csv
Id,firstName,lastName,salary,managerId
123,Joe,Doe,60000,
124,Martin,Chekov,45000,123
125,Bob,Ronstad,47000,123
300,Alice,Hasacat,50000,124
305,Brett,Hardleaf,34000,300
```

### General usage
Command receives absolute path to the CSV file as a single argument:
```sh
java -jar [PATH_TO_JAR] [PATH_TO_CSV]
```

For example:
```sh
java -jar ./oso.jar ./schema.csv
```

### Local JRE
If you've received this application with source code (e.g. a Git repo),
there's a handy script under the [./.docker](./.docker) directory which runs
the artifact built by default under [./target](./target) directory:
```sh
./.docker/entrypoint.sh /tmp/schema.csv
```

### Docker
```sh
docker run -v ./schema.csv:/tmp/schema.csv oso:snapshot /tmp/schema.csv
```
