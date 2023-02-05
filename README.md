# Zavod za intelektualnu svojinu

- [@pavleglusac](https://github.com/pavleglusac) SW 19/2019
- [@dusanlazic](https://github.com/dusanlazic) SW 4/2019

## Uputstvo za pokretanje

Potrebno je imati instaliran JDK 1.8 i node 16 za Angular 13 aplikaciju.

Potrebno je pokrenuti apache tomee plus 8.0.13 na portu 8080 sa sledecim war fajlovima u webapps direktorijumu
- exist-korisnici.war
- exist-autorska.war
- exist-zig.war
- fuseki-autorska.war
- fuseki-zig.war

Potrebno je dodati ZahteviDataset u fuseki baze.

Nakon što je tomee server pokrenut, moguće je pokrenuti bekend aplikacije u direktorijumima
- backends/korisnici
- backends/autorska
- backends/zig

Backend aplikacije će se nalaziti na portovima
- korisnicki backend: 8083
- zig backed:         8082
- autorska backend:   8081

Frontend aplikacija pokreće se ng serve komandom i njen port je 4200
