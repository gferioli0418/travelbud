CLANG_FORMAT=clang-format --style=Google

pretty:
	prettier --write src/main/webapp/*.{html,css}
	find src/main/java -iname *.java | xargs $(CLANG_FORMAT) -i
	find src/main/webapp -iname *.js | xargs $(CLANG_FORMAT) -i

validate:
	html-validate src/main/webapp/*.html
	css-validator src/main/webapp/*.css

build:
	mvn compile