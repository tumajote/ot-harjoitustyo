## Sovelluslogiikka

Sovelluksen sovelluslogiikka perustuu Imagedata-luokalle, joka sisältää kuvatiedoston matriisiesityksenä (openCV Mat-olio). Imagedata olio saa matriisin FileLoader luokalta, jolle annetaan polku kuvatiedostoon (joko jpeg tai png) Fileloader luokka muuntaa kuvan matriisiksi ja siirtää sen antaa sen Imagedata-luokalle. Imagedata päivittää kyseisen kuvan ominaisuudet(koko, histogrammi) graafiseen käyttöliittymään. 

Toiminnallisista kokonaisuuksista vastaa rajapinnan metodit toteuttavat luokat (pakkauksessa methods). Metodit sisältävät erilaisia tapoja muokata kuvaa ja ne voidaan antaa Imagedatalle toteutettaviksi. 

Imagedatan ja ohjelman muiden osien suhdetta kuvaava luokka/pakkauskaavio:

![pakkaus/luokkakaavio_SIP](https://github.com/tumajote/ot-harjoitustyo/blob/master/dokumentaatio/SIP.png)

### Päätoiminnallisuudet

Kuvataan seuraavaksi sovelluksen toimintalogiikka geneerisen kuvanmuokkausoperaation sekvenssikaaviona.


