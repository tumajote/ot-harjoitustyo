# Simple image processor
Sovellus tarjoaa yksinkertaisia kuvanmuokkaus ja analysointi työkaluja sekä mahdollisuuden tallentaa muokatun kuvan levylle.

## Dokumentaatio
[Käyttöohje](https://github.com/tumajote/ot-harjoitustyo/blob/master/dokumentaatio/kayttoohje.md)

[Vaatimusmäärittely](https://github.com/tumajote/ot-harjoitustyo/blob/master/dokumentaatio/vaatimusmaarittely.md)

[Arkkitehtuuri](https://github.com/tumajote/ot-harjoitustyo/blob/master/dokumentaatio/arkkitehtuuri.md)

[Testausdokumentti](https://github.com/tumajote/ot-harjoitustyo/blob/master/dokumentaatio/testausdokumentti.md)

[Työaikakirjanpito](https://github.com/tumajote/ot-harjoitustyo/blob/master/dokumentaatio/tuntikirjanpito.md)


## Releaset

[Viikko5](https://github.com/tumajote/ot-harjoitustyo/releases/tag/viikko5)

[Viikko6](https://github.com/tumajote/ot-harjoitustyo/releases/tag/Viikko6)

[Loppupalautus](https://github.com/tumajote/ot-harjoitustyo/releases/tag/loppupalautus)

## Komentorivitoiminnot

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

### Suoritettavan jarin generointi

Komento

```
mvn package
```

generoi hakemistoon _target_ suoritettavan jar-tiedoston _SimpleImageProcessor-1.0-SNAPSHOT.jar_

### JavaDoc

JavaDoc generoidaan komennolla

```
mvn javadoc:javadoc
```

JavaDocia voi tarkastella avaamalla selaimella tiedosto _target/site/apidocs/index.html_

### Checkstyle

Tiedostoon [checkstyle.xml](https://github.com/tumajote/ot-harjoitustyo/blob/master/SimpleImageProcessor/checkstyle.xml) määrittelemät tarkistukset suoritetaan komennolla

```
 mvn jxr:jxr checkstyle:checkstyle
```

Mahdolliset virheilmoitukset selviävät avaamalla selaimella tiedosto _target/site/checkstyle.html_

