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
                aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
                <h2>ИЗВЕШТАЈ О ЗАХТЕВИМА ЗА ПРИЗНАЊЕ ЖИГА</h2>
                <h3>Извештај за период од <xsl:value-of select="//i:Izvestaj/@datumOd"/> до <xsl:value-of select="//i:Izvestaj/@datumDo"/></h3>

                <table>
                    <colgroup>
                        <col span="1" style="width: 80%;"/>
                        <col span="1" style="width: 20%;"/>
                    </colgroup>
                    <tr>
                        <td>
                            Број прихваћених захтева
                        </td>
                        <td>
                            <strong><xsl:value-of select="//i:Izvestaj/i:broj_prihvacenih"/></strong>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Број одбијених захтева
                        </td>
                        <td>
                            <strong><xsl:value-of select="//i:Izvestaj/i:broj_odbijenih"/></strong>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Укупан број поднетих захтева
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