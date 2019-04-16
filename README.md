# Ohjelmistotekniikka

## Dokumentaatio
[Vaatimusmäärittely](https://github.com/tumajote/ot-harjoitustyo/blob/master/dokumentaatio/vaatimusmaarittely.md)

[Työaikakirjanpito](https://github.com/tumajote/ot-harjoitustyo/blob/master/dokumentaatio/tuntikirjanpito.md)

[Arkkitehtuuri](https://github.com/tumajote/ot-harjoitustyo/blob/master/dokumentaatio/arkkitehtuuri.md)

## Releaset

[Viikko5](https://github.com/tumajote/ot-harjoitustyo/releases/tag/viikko5)

## Komentorivitoiminnot

### Käynnistys

Ohjelma käynnistyy komennolla
```
mvn compile exec:java -Dexec.mainClass=GUI.LaunchApplication
```

### Testaus

Testit suoritetaan komennolla

```
mvn test
```

Testikattavuusraportti luodaan komennolla

```
mvn jacoco:report
```

Kattavuusraporttia voi tarkastella avaamalla selaimella tiedosto _target/site/jacoco/index.html_

