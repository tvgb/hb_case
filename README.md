## Case for hb (REST API)

### Setup guide
1. Denne guiden antar at du har GIT installert allerede.

2. Klon ned repoet via URI:
https://github.com/tvgb/hb_case.git

3. Sørg for at du har JDK 17 installert. Dette kan gjøres herfra om ikke: https://bell-sw.com/pages/downloads/#jdk-17-lts (eventuelle åpne terminaler må restartes etter at installasjonen er ferdig)

4. Åpne en terminal i mappen med navn "hb_case" og skriv deretter kommandoen "./mvnw spring-boot:run".

### Kjøre jar i jvm
Du kan også kjøre kommandoen "./mvnw clean package" inni mappen for å bygge en .jar-fil som kan kjøres direkte med kommandoen "java -jar target\demo-0.0.1-SNAPSHOT.jar".

### Åpne swagger
Når du har fått REST-APIet til å kjøre kan du se dokumentasjonen i swagger på denne URL-en: http://localhost:8080/swagger-ui/index.html