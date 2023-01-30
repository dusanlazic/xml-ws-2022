<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:r="http://www.zavod.com/Resenje" version="2.0"
                xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                xsi:schemaLocation="http://www.zavod.com/Resenje file:/home/pavle/Documents/XML/Projekat/xml-ws-2022/backends/autorska/data/r1.xsd">

    <xsl:template match="/">
        <html>
            <head>

            </head>
            <body>
                <h2>Rešenje zahteva <xsl:value-of select="//r:Resenje/zahtev/broj_prijave"></xsl:value-of></h2>
                <br />
                <br />
                <h2>Sluzbenik</h2>
                <table>
                    <tr>
                        <td>Ime</td>
                        <td><xsl:value-of select="//r:Resenje/sluzbenik/ime"></xsl:value-of></td>
                    </tr>
                    <tr>
                        <td>Prezime</td>
                        <td><xsl:value-of select="//r:Resenje/sluzbenik/prezime"></xsl:value-of></td>
                    </tr>
                </table>
                <br></br>
                <h2>Odluka</h2>
                <table>
                    <tr>
                        <td>Odluka</td>
                        <xsl:choose>
                            <xsl:when test="//r:Resenje/odluka/prihvacen">
                                <xsl:text>Prihvacen</xsl:text>
                            </xsl:when>
                            <xsl:otherwise>
                                <xsl:text>Odbijen</xsl:text>
                            </xsl:otherwise>
                        </xsl:choose>
                    </tr>
                    <xsl:choose>
                        <xsl:when test="//r:Resenje/odluka/prihvacen">
                            <tr>
                                <td>Razlog</td>
                                <td><xsl:value-of select="//r:Resenje/odluka/obrazlozenje"></xsl:value-of></td>
                            </tr>
                        </xsl:when>
                    </xsl:choose>
                    <tr>
                        <td>Datum</td>
                        <td><xsl:value-of select="//r:Resenje/odluka/datum"></xsl:value-of></td>
                    </tr>
                </table>

            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>