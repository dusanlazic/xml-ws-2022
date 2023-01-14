<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:a="http://www.zavod.com/Autorska" version="2.0"
                xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                xsi:schemaLocation="http://www.zavod.com/Autorska file:/home/s4ndu/FTN/semestar-7/XML/xml-ws-2022/zavod/data/a1.xsd">

    <xsl:template match="/">
        <html>
            <style>
                body {
                border: 2px solid black;
                }
                h1, h2 {
                text-align: center;
                }
                .box {
                border-bottom: 2px solid black;
                padding: 1%;
                }
                .section1 {
                padding: 1%;
                }
                .item {
                margin-bottom: 40px;
                }
                .answer {
                color: navy;
                }
            </style>
            <body>
                <div class="box">
                    <strong>ZAVOD ZA INTELEKTUALNU SVOJINU - OBRAZAC A-1</strong><br/>
                    Beograd, Kneginje Ljubice 5
                    <br />
                    <h1>
                        ZAHTEV ZA UNOŠENJE U EVIDENCIJU I DEPONOVANJE AUTORSKIH DELA
                    </h1>
                </div>

                <div class="section1">
                    <div class="item">
                        1) Podnosilac - ime, prezime, adresa i državljanstvo autora ili drugog nosioca autorskog prava ako je podnosilac fizičko lice, odnosno poslovno ime i sedište nosioca autorskog prava ako je podnosilac pravno lice*:
                        <table>
                            <tbody>
                                <tr>
                                    <td>Ime:</td>
                                    <td class="answer"><xsl:value-of select="//a:Podnosilac/a:ime" /></td>
                                </tr>
                                <xsl:choose>
                                    <xsl:when test="//a:Podnosilac/a:sediste">
                                        <tr>
                                            <td>Sedište:</td>
                                            <td class="answer"><xsl:value-of select="//a:Podnosilac/a:sediste" /></td>
                                        </tr>
                                    </xsl:when>
                                    <xsl:otherwise>
                                        <tr>
                                            <td>Prezime:</td>
                                            <td class="answer"><xsl:value-of select="//a:Podnosilac/a:prezime" /></td>
                                        </tr>
                                        <tr>
                                            <td>Drzavljanstvo:</td>
                                            <td class="answer"><xsl:value-of select="//a:Podnosilac/a:drzavljanstvo" /></td>
                                        </tr>
                                    </xsl:otherwise>
                                </xsl:choose>
                                <tr>
                                    <td>Broj telefona:</td>
                                    <td class="answer"><xsl:value-of select="//a:Podnosilac/a:telefon" /></td>
                                </tr>
                                <tr>
                                    <td>Email:</td>
                                    <td class="answer"><xsl:value-of select="//a:Podnosilac/a:email" /></td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                    <div class="item">
                        2) Pseudonim ili znak autora, (ako ga ima):
                        <p class="answer">
                            <xsl:choose>
                                <xsl:when test="//a:Podnosilac/a:pseudonim">
                                    <xsl:value-of select="//a:Podnosilac/a:pseudonim" />
                                </xsl:when>
                                <xsl:otherwise>
                                    <xsl:text>Nema pseudonima</xsl:text>
                                </xsl:otherwise>
                            </xsl:choose>
                        </p>
                    </div>
                    <div class="item">
                        3) Ime, prezime i adresa punomoćnika, ako se prijava podnosi preko punomoćnika:
                        <p class="answer">
                            <xsl:choose>
                                <xsl:when test="//a:Punomocnik/a:ime">
                                    <xsl:value-of select="//a:Podnosilac/a:ime" />
                                    <xsl:text> </xsl:text>
                                    <xsl:value-of select="//a:Podnosilac/a:prezime" />
                                    <xsl:text>, </xsl:text>
                                    <xsl:value-of select="//a:Podnosilac/a:adresa" />
                                </xsl:when>
                                <xsl:otherwise>
                                    <xsl:text>Nema punomoćnika</xsl:text>
                                </xsl:otherwise>
                            </xsl:choose>
                        </p>
                    </div>
                    <div class="item">
                        4) Naslov autorskog dela, odnosno alternativni naslov, ako ga ima, po kome autorsko delo može da se identifikuje*:
                        <p class="answer"><xsl:value-of select="//a:Delo/a:naslov" /></p>
                        <p class="answer"><xsl:value-of select="//a:Delo/a:alternativni_naslov" /></p>
                    </div>
                    <div class="item">
                        5) Podaci o naslovu autorskog dela na kome se zasniva delo prerade, ako je u pitanju autorsko delo prerade, kao i podatak o autoru izvornog dela:
                        <xsl:choose>
                            <xsl:when test="//a:Delo/a:Originalni_Autor/a:ime">
                                <p class="answer"><xsl:value-of select="/a:Delo/a:Originalni_Autor/a:originalni_naslov" /></p>
                                <br></br>
                                <table>
                                    <tr>
                                        <td>Ime autora:</td>
                                        <td class="answer"><xsl:value-of select="/a:Delo/a:Originalni_Autor/a:ime" /></td>
                                    </tr>
                                    <tr>
                                        <td>Prezime autora:</td>
                                        <td class="answer"><xsl:value-of select="/a:Delo/a:Originalni_Autor/a:prezime" /></td>
                                    </tr>
                                    <tr>
                                        <td>Drzavljanstvo autora:</td>
                                        <td class="answer"><xsl:value-of select="/a:Delo/a:Originalni_Autor/a:drzavljanstvo" /></td>
                                    </tr>
                                </table>
                            </xsl:when>
                            <xsl:otherwise>
                                <p class="answer"><xsl:text>Nije prerada</xsl:text></p>
                            </xsl:otherwise>
                        </xsl:choose>
                    </div>
                    <div class="item" style="margin-top: 20px;">
                        6) Podaci o vrsti autorskog dela (književno delo, muzičko delo, likovno delo, računarski program i dr.) *:
                        <p class="answer"><xsl:value-of select="//a:Delo/a:vrsta_dela" /></p>
                    </div>
                    <div class="item">
                        7) Podaci o formi zapisa autorskog dela (štampani tekst, optički disk i slično) *:
                        <p class="answer"><xsl:value-of select="//a:Delo/a:forma_zapisa" /></p>
                    </div>
                    <div class="item">
                        8) Podaci o autoru ako podnosilac prijave iz tačke 1. ovog zahteva nije autor i to: prezime, ime, adresa i državljanstvo autora (grupe autora ili koautora), a ako su u pitanju jedan ili više autora koji nisu živi, imena autora i godine smrti autora a ako je u pitanju autorsko delo anonimnog autora navod da je autorsko delo delo anonimnog autora:
                        <xsl:choose>
                            <xsl:when test="//a:Delo/a:Autor/a:ime">
                                <p class="answer"><xsl:value-of select="/a:Delo/a:Autor/a:originalni_naslov" /></p>
                                <br></br>
                                <table>
                                    <tr>
                                        <td>Ime autora:</td>
                                        <td class="answer"><xsl:value-of select="/a:Delo/a:Autor/a:ime" /></td>
                                    </tr>
                                    <tr>
                                        <td>Prezime autora:</td>
                                        <td class="answer"><xsl:value-of select="/a:Delo/a:Autor/a:prezime" /></td>
                                    </tr>
                                    <tr>
                                        <td>Drzavljanstvo autora:</td>
                                        <td class="answer"><xsl:value-of select="/a:Delo/a:Autor/a:drzavljanstvo" /></td>
                                    </tr>
                                </table>
                            </xsl:when>
                            <xsl:otherwise>
                                <p class="answer"><xsl:text>Podnosilac je autor</xsl:text></p>
                            </xsl:otherwise>
                        </xsl:choose>
                    </div>
                    <div class="item">
                        9) Podatak da li je u pitanju autorsko delo stvoreno u radnom odnosu:
                        <p class="answer">
                            <xsl:choose>
                                <xsl:when test="//a:Delo/a:radni_odnos">
                                    <xsl:text>Zasnovano je u radnom odnosu</xsl:text>
                                </xsl:when>
                                <xsl:otherwise>
                                    <xsl:text>Nije zasnovano u random odnosu</xsl:text>
                                </xsl:otherwise>
                            </xsl:choose>
                        </p>
                    </div>
                    <div class="item">
                        10) Način korišćenja autorskog dela ili nameravani način korišćenja autorskog dela:
                        <p class="answer"><xsl:value-of select="//a:Delo/a:nacin_koriscenja" /></p>
                    </div>
                </div>
                <h2>
                    POPUNJAVA ZAVOD:
                </h2>
                <div class="section1">
                    <strong>Prilozi uz prijavu:</strong>
                    <div>
                        opis autorskog dela (ako je delo podneto na optičkom disku);
                    </div>
                    <div>
                        primer autorskog dela (slika, video zapis, audio zapis)
                    </div>
                </div>
                <br />
                <br />
                <br />
                <div>
                    <div style="width: 25%; border: 1px solid black;">
                        <div style="border: 1px solid black; border-width: 1px 0 0 1px; height: 60px; padding: 2%;">
                            <div>
                                Broj prijave:
                            </div>
                            <div style="margin-top: 10px;">
                                <xsl:value-of select="//a:Informacije_Zavoda/a:broj_prijave" />
                            </div>
                        </div>
                        <div style="border: 1px solid black; border-width: 1px 0 0 1px; height: 60px; padding: 2%;">
                            <div>
                                Datum podnosenja:
                            </div>
                            <div style="margin-top: 10px;">
                                <xsl:value-of select="//a:Informacije_Zavoda/a:datum_podsnosenja" />
                            </div>
                        </div>
                    </div>
                </div>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>