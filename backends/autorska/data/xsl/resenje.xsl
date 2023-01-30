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
                <h2>Rešenje zahteva: <xsl:value-of select="//r:zahtev/r:broj_prijave"></xsl:value-of></h2>
                <br />
                <br />
                <h2>Sluzbenik</h2>
                <table>
                    <tr>
                        <td>Ime: </td>
                        <td><xsl:value-of select="//r:sluzbenik/r:ime"></xsl:value-of></td>
                    </tr>
                    <tr>
                        <td>Prezime: </td>
                        <td><xsl:value-of select="//r:sluzbenik/r:prezime"></xsl:value-of></td>
                    </tr>
                </table>
                <br></br>
                <h2>Odluka</h2>
                <table>
                    <tr>
                        <td>Odluka:</td>
                        <xsl:choose>
                            <xsl:when test="//r:odluka/r:prihvacen">
                                <td><xsl:text>Prihvacen</xsl:text></td>
                            </xsl:when>
                            <xsl:otherwise>
                                <td><xsl:text>Odbijen</xsl:text></td>
                            </xsl:otherwise>
                        </xsl:choose>
                    </tr>
                    <xsl:choose>
                        <xsl:when test="//r:odluka/r:prihvacen">
                            <tr>
                                <td>Razlog:</td>
                                <td><xsl:value-of select="//r:odluka/r:obrazlozenje"></xsl:value-of></td>
                            </tr>
                        </xsl:when>
                    </xsl:choose>
                    <tr>
                        <td>Datum:</td>
                        <td><xsl:value-of select="//r:odluka/r:datum"></xsl:value-of></td>
                    </tr>
                </table>

            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>