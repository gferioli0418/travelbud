# travelbud

## Setup
In Cloud Shell, run:
`edit .customize_environment`

Ensure that it has the following lines:
```shell
npm install -g css-validator
npm install -g clang-format
npm install -g html-validate
npm install -g prettier
```

## Local development
Run:
```shell
mvn appengine:run
```

## Deploying
Run:
```shell
mvn appengine:deploy
```

## HTML/CSS Validation
Run:
```shell
make validate
```

## Format Code
Run:
```shell
make pretty
```