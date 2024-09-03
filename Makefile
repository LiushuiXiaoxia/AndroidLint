

lint:
	./gradlew clean --console=plain
	./gradlew assemble --console=plain
	./gradlew :app:lintDebug --console=plain
	echo "Linting done"