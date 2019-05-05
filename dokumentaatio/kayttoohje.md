# Käyttöohje

Lataa tiedosto [SimpleImageProcessor.jar](https://github.com/tumajote/ot-harjoitustyo/releases/download/loppupalautus/SimpleImageProcessor.jar)

## Ohjelman käynnistäminen

Ohjelma käynnistetään komennolla 

```
java -jar SimpleImageProcessor.jar
```

## Kuvan lataaminen käsittelyä varten

Paina load image nappia ja valitse haluttu kuva levyltä.

## Kuvan tallentaminen 

Kuva tallentuu painamalla save image nappia. Valitse tiedostonimi, lisää tiedoston perään joko .jpg tai .png halutun tiedostomuodon mukaisesti sekä osoita ikkunasta polku mihin kuva levyllä tallennetaan.

## Histogrammi

Käyttöliittymän vasemmassa yläkulmassa on histogrammi joka kolmikanava värikuvan tapauksessa esittää, jokaisen kanavan pikseleiden valoarvojen jakautumisen. Yksikanava mustavalkokuvan tapauksessa histogrammi esittää kyseisen kanavan valoarvot.

## Histogrammi

Histogrammin alla on kuvan mittasuhteet pikseleinä.

## Kuvan muuttaminen mustavalkoiseksi

Painamalla convert to grayscale -nappia kuva konvertoidaan mustavalkoiseksi. Mustavalko kuvaa voi muokata eteenpäin muilla työkaluilla. Reset-nappi palauttaa kuvan värilliseksi.

## Kuvan orientaation muokkaaminen

Painamalla rotate nappia kuva kääntyy 90 astetta myötäpäivään. 

## Kuvan kirkkauden ja kontrastin säätäminen

Brightness ja contrast liukurit säätelevät kuvan kirkkautta ja kontrastia. Isommat arvot lisäävät niitä ja pienemmät vähentävät. Reset napit palauttavat liukurit ja arvot alkuasetuksiinsa.

## Kuvan tarkkuuden säätäminen

Sharpness liukuri säätelee kuvan tarkkuutta. Isommat arvot lisäävät tarkkuutta ja pienemmät vähentävät. Reset nappi palauttaa liukurin ja tarkkuuden alkuasetukseen.

## Ohjelma sammutus

Ohjelma sammuu sulkemalla ikkunan.


