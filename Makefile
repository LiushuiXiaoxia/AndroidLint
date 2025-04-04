

all:
	./gradlew clean --console=plain
	./gradlew assemble --console=plain
	./gradlew :app:lint --console=plain
	echo "all done"

lint:
	cd subs && ./gradlew clean
	./gradlew clean --console=plain
	./gradlew lintRelease --console=plain -q
	#./gradlew :liba:lint --console=plain
	#./gradlew :liba:lintRelease --console=plain
	echo "Linting done"