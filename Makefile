include config.mk

format:
	./mvnw spotless:apply

start:
	./mvnw clean spring-boot:run


build_backend:
	docker build -t $(BACKEND_NAME):$(BACKEND_VERSION) .
