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
                        ZAHTEV ZA UNOŠENjE U EVIDENCIJU I DEPONOVANjE AUTORSKIH DELA
                    </h1>
                </div>

                <div class="section1">
                    <div class="item">
                        1) Podnosilac - ime, prezime, adresa i državljanstvo autora ili drugog nosioca autorskog prava ako je podnosilac fizičko lice, odnosno poslovno ime i sedište nosioca autorskog prava ako je podnosilac pravno lice*:
                        <table>
                            <tr>
                                <th>Ime</th>
                                <xsl:choose>
                                    <xsl:when test="//a:Podnosilac/a:sediste">
                                        <th>Sediste</th>
                                    </xsl:when>
                                    <xsl:otherwise>
                                        <th>Prezime</th>
                                        <th>Drzavljanstvo</th>
                                    </xsl:otherwise>
                                </xsl:choose>

                                <th>Telefon</th>
                                <th>Email</th>
                            </tr>
                            <tbody>
                                <tr><td class="answer"><xsl:value-of select="//a:Podnosilac/a:ime"></xsl:value-of></td>
                                <xsl:choose>
                                    <xsl:when test="//a:Podnosilac/a:sediste">
                                        <td class="answer"><xsl:value-of select="//a:Podnosilac/a:sediste"></xsl:value-of></td>
                                    </xsl:when>
                                    <xsl:otherwise>
                                        <td class="answer"><xsl:value-of select="//a:Podnosilac/a:prezime"></xsl:value-of></td>
                                        <td class="answer"><xsl:value-of select="//a:Podnosilac/a:drzavljanstvo"></xsl:value-of></td>
                                    </xsl:otherwise>
                                </xsl:choose>
                                <td class="answer"><xsl:value-of select="//a:Podnosilac/a:telefon"></xsl:value-of></td>
                                <td class="answer"><xsl:value-of select="//a:Podnosilac/a:email"></xsl:value-of></td></tr>
                            </tbody>
                        </table>
                    </div>
                    <div class="item">
                        2) Pseudonim ili znak autora, (ako ga ima):
                        <p class="answer"><xsl:value-of select="//a:Podnosilac/a:pseudonim"></xsl:value-of></p>
                    </div>
                    <div class="item">
                        4) Naslov autorskog dela, odnosno alternativni naslov, ako ga ima, po kome autorsko delo može da se identifikuje*:
                        <p class="answer"><xsl:value-of select="//a:Delo/a:naslov"></xsl:value-of></p>
                        <p class="answer"><xsl:value-of select="//a:Delo/a:alternativni_naslov"></xsl:value-of></p>
                    </div>
                    <div class="item">
                        5) Podaci o naslovu autorskog dela na kome se zasniva delo prerade, ako je u pitanju autorsko delo prerade, kao i podatak o autoru izvornog dela:

                    </div>
                    <div class="item">
                        6) Podaci o vrsti autorskog dela (književno delo, muzičko delo, likovno delo, računarski program i dr.) *:
                        <p class="answer"><xsl:value-of select="//a:Delo/a:vrsta_dela"></xsl:value-of></p>
                    </div>
                    <div class="item">
                        7) Podaci o formi zapisa autorskog dela (štampani tekst, optički disk i slično) *:
                        <p class="answer"><xsl:value-of select="//a:Delo/a:forma_zapisa"></xsl:value-of></p>
                    </div>
                    <div class="item">
                        8) Podaci o autoru ako podnosilac prijave iz tačke 1. ovog zahteva nije autor i to: prezime, ime, adresa i državljanstvo autora (grupe autora ili koautora), a ako su u pitanju jedan ili više autora koji nisu živi, imena autora i godine smrti autora a ako je u pitanju autorsko delo anonimnog autora navod da je autorsko delo delo anonimnog autora:
                    </div>
                    <div class="item">
                        9) Podatak da li je u pitanju autorsko delo stvoreno u radnom odnosu:
                        <p class="answer"><xsl:value-of select="//a:Delo/a:radni_odnos"></xsl:value-of></p>
                    </div>
                    <div class="item">
                        10) Način korišćenja autorskog dela ili nameravani način korišćenja autorskog dela:
                        <p class="answer"><xsl:value-of select="//a:Delo/a:nacin_koriscenja"></xsl:value-of></p>
                    </div>
                </div>
                <h2>
                    POPUNjAVA ZAVOD:
                </h2>
            </body>
        </html>

    </xsl:template>
</xsl:stylesheet>