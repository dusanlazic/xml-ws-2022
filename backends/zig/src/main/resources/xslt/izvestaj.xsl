<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:i="http://www.zavod.com/Izvestaj" version="2.0"
                xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                xsi:schemaLocation="http://www.zavod.com/Izvestaj">
    <xsl:template match="/">
        <html>
            <style>
                body {
                font-family: Arial, Helvetica, sans-serif;
                margin-left: 50px;
                margin-right: 50px;
                }

                h2,
                h3 {
                text-align: center;
                }

                h2 {
                margin-bottom: 0px;
                }

                h3 {
                margin-top: 0px;
                }

                table,
                th,
                td {
                border: 1px solid black;
                border-collapse: collapse;
                }

                table {
                width: 100%;
                }

            </style>
            <body>
                <h2>IZVEŠTAJ O ZAHTEVIMA ZA PRIZNANjE ŽIGA</h2>
                <h3>Izveštaj za period od <xsl:value-of select="//i:Izvestaj/@datumOd"/> do <xsl:value-of select="//i:Izvestaj/@datumDo"/></h3>

                <table>
                    <colgroup>
                        <col span="1" style="width: 80%;"/>
                        <col span="1" style="width: 20%;"/>
                    </colgroup>
                    <tr>
                        <td>
                            Broj prihvaćenih zahteva
                        </td>
                        <td>
                            <strong><xsl:value-of select="//i:Izvestaj/i:broj_prihvacenih"/></strong>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Broj odbijenih zahteva
                        </td>
                        <td>
                            <strong><xsl:value-of select="//i:Izvestaj/i:broj_odbijenih"/></strong>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Ukupan broj podnetih zahteva
                        </td>
                        <td>
                            <strong><xsl:value-of select="//i:Izvestaj/i:broj_podnetih"/></strong>
                        </td>
                    </tr>
                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>