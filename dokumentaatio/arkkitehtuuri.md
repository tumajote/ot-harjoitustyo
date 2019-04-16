## Sovelluslogiikka

Sovelluksen sovelluslogiikka perustuu Imagedata-luokalle, joka sisältää kuvatiedoston matriisiesityksenä (openCV Mat-olio). Imagedata olio saa matriisin FileLoader luokalta, jolle annetaan polku kuvatiedostoon (joko jpeg tai png). Fileloader luokka muuntaa kuvan matriisiksi ja asettaa sen Imagedata kuvan muuttujaksi. Imagedata päivittää kyseisen kuvan ja sen ominaisuudet(koko, histogrammi) graafiseen käyttöliittymään. 

Toiminnallisista kokonaisuuksista vastaa rajapinnan metodit toteuttavat luokat (pakkauksessa methods). Metodit sisältävät erilaisia tapoja muokata kuvaa ja ne voidaan antaa Imagedatalle toteutettaviksi. 

Imagedatan ja ohjelman muiden osien suhdetta kuvaava luokka/pakkauskaavio:

![pakkaus/luokkakaavio_SIP](https://github.com/tumajote/ot-harjoitustyo/blob/master/dokumentaatio/SIP.png)

### Päätoiminnallisuudet

Kuvataan seuraavaksi sovelluksen toimintalogiikka päätoiminnallisuuden osalta sekvenssikaaviona.

#### kuvan lataaminen sovellukseen
Load image nappia painaessa avautuu valintaikkuna, josta voi valita kuvatiedoston levyltä. Nappia painessa siirtyy sovelluksen kontrolli seuraavasti

![Load image toiminnallisuus](https://github.com/tumajote/ot-harjoitustyo/blob/master/dokumentaatio/load_image.png)

Käyttäjän painaessa load image nappia tapahtumakäsittelijä kutsuu esiin Filechooser-ikkunan, joka palauttaa käyttäjän valitseman tiedoston. Graafinen käyttöliittymä luo FileLoader olion, joka muuntaa annetun tiedoston Mat-matriisiksi ja toimittaa sen ImageData luokalle. Imagedata luokka muuntaa matriisiin JavaFx:n näytettäväksi sopivaksi Image-olioksi, luo kuvasta histogrammin ja mitat sekä palauttaa ne graafisen käyttöliittymään käyttäjän nähtäviksi.


