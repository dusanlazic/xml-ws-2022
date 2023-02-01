<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:z="http://www.zavod.com/Zig" version="2.0"
                xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                xsi:schemaLocation="http://www.zavod.com/Zig file:/home/s4ndu/FTN/semestar-7/XML/xml-ws-2022/zavod/data/z1.xsd">
    <xsl:param name="qr_code_image" />
    <xsl:param name="primerak_znaka" />
    <xsl:param name="broj_klasa" />
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

                .is-centered {
                text-align: center;
                }

                .is-top-vertical {
                vertical-align: top;
                }

                table,
                th,
                td {
                border: 1px solid black;
                border-collapse: collapse;
                }

                table.is-borderless,
                .is-borderless th,
                .is-borderless td {
                border: none !important;
                border-collapse: collapse;
                }

                table {
                width: 100%;
                }

                .h-4 {
                height: 40px
                }

                .h-5 {
                height: 50px
                }

                .h-6 {
                height: 60px
                }

                .border-top-none,
                .border-top-none th,
                .border-top-none td {
                border-top: none;
                }

                .pl-1 {
                padding-left: 10px;
                }

                .pl-2 {
                padding-left: 20px !important;
                }

                .mb-0 {
                margin-bottom: 0px;
                }

                .mb-1 {
                margin-bottom: 10px;
                }

                .mt-0 {
                margin-top: 0px;
                }

                .mt-6 {
                margin-top: 60px;
                }

                .is-blue {
                color: rgb(0, 49, 139);
                }
            </style>
            <body>
                <h2>ЗАХТЕВ ЗА ПРИЗНАЊЕ ЖИГА</h2>
                <h3>Завод за интелектуалну својину, Књегиње Љубице 5, 11000 Београд</h3>
                <p class="is-centered">(попунити на рачунару)</p>
                <table>
                    <tr>
                        <td colspan="3" class="pl-1"><b>1. Подносилац пријаве:</b> име и презиме/пословно име, улица и број, поштански
                            број, место,
                            држава пребивалишта/седишта:</td>
                    </tr>
                    <tr class="h-6">
                        <td colspan="3" class="pl-2 is-blue">
                            <xsl:choose>
                                <xsl:when test="//z:Podnosilac/z:poslovno_ime">
                                    <xsl:value-of select="//z:Podnosilac/z:poslovno_ime"></xsl:value-of>
                                </xsl:when>
                                <xsl:otherwise>
                                    <xsl:value-of select="//z:Podnosilac/z:ime"></xsl:value-of>
                                    <xsl:text> </xsl:text>
                                    <xsl:value-of select="//z:Podnosilac/z:prezime"></xsl:value-of>
                                </xsl:otherwise>
                            </xsl:choose>
                        </td>
                    </tr>
                    <tr>
                        <td class="pl-1">телефон: <span class="is-blue"><xsl:value-of select="//z:Podnosilac/z:telefon"></xsl:value-of></span></td>
                        <td class="pl-1">e-mail: <span class="is-blue"><xsl:value-of select="//z:Podnosilac/z:email"></xsl:value-of></span></td>
                        <td class="pl-1">факс: <span class="is-blue"><xsl:value-of select="//z:Podnosilac/z:faks"></xsl:value-of></span></td>
                    </tr>
                </table>
                <table class="border-top-none">
                    <tr>
                        <td colspan="3" class="pl-1"><b>2. Пуномоћник:</b> име и презиме/пословно име, улица и број, поштански број,
                            место, држава
                            пребивалишта/седишта:</td>
                    </tr>
                    <tr class="h-6">
                        <td colspan="3" class="pl-2 is-blue">
                            <xsl:choose>
                                <xsl:when test="//z:Punomocnik/z:poslovno_ime">
                                    <xsl:value-of select="//z:Punomocnik/z:poslovno_ime"></xsl:value-of>
                                </xsl:when>
                                <xsl:otherwise>
                                    <xsl:value-of select="//z:Punomocnik/z:ime"></xsl:value-of>
                                    <xsl:text> </xsl:text>
                                    <xsl:value-of select="//z:Punomocnik/z:prezime"></xsl:value-of>
                                </xsl:otherwise>
                            </xsl:choose>
                        </td>
                    </tr>
                    <tr>
                        <td class="pl-1">телефон: <span class="is-blue"><xsl:value-of select="//z:Punomocnik/z:telefon"></xsl:value-of></span></td>
                        <td class="pl-1">e-mail: <span class="is-blue"><xsl:value-of select="//z:Punomocnik/z:email"></xsl:value-of></span></td>
                        <td class="pl-1">факс: <span class="is-blue"><xsl:value-of select="//z:Punomocnik/z:faks"></xsl:value-of></span></td>
                    </tr>
                </table>
                <table class="border-top-none">
                    <tr>
                        <td colspan="3" class="pl-1"><b>3. Подаци о заједничком представнику ако постоји више подносилаца пријаве:</b>
                        </td>
                    </tr>
                    <tr class="h-6 pl-2">
                        <td colspan="3" class="pl-2 is-blue">
                            <xsl:choose>
                                <xsl:when test="//z:Predstavnik/z:poslovno_ime">
                                    <xsl:value-of select="//z:Predstavnik/z:poslovno_ime"></xsl:value-of>
                                </xsl:when>
                                <xsl:otherwise>
                                    <xsl:value-of select="//z:Predstavnik/z:ime"></xsl:value-of>
                                    <xsl:text> </xsl:text>
                                    <xsl:value-of select="//z:Predstavnik/z:prezime"></xsl:value-of>
                                </xsl:otherwise>
                            </xsl:choose>
                        </td>
                    </tr>
                    <tr>
                        <td class="pl-1">телефон: <span class="is-blue"><xsl:value-of select="//z:Predstavnik/z:telefon"></xsl:value-of></span></td>
                        <td class="pl-1">e-mail: <span class="is-blue"><xsl:value-of select="//z:Predstavnik/z:email"></xsl:value-of></span></td>
                        <td class="pl-1">факс: <span class="is-blue"><xsl:value-of select="//z:Predstavnik/z:faks"></xsl:value-of></span></td>
                    </tr>
                </table>
                <table class="border-top-none">
                    <colgroup>
                        <col span="1" style="width: 50%;"/>
                        <col span="1" style="width: 50%;"/>
                    </colgroup>
                    <tr>
                        <td class="pl-1"><b>4. Пријава се подноси за:</b></td>
                        <td class="pl-1" rowspan="2"><b>в) изглед знака:</b></td>
                    </tr>
                    <tr>
                        <td class="pl-1">
                            <b>а)</b>
                            <span class="is-blue"><xsl:value-of select="//z:Zig/z:tip_ziga"></xsl:value-of></span>
                        </td>
                    </tr>
                    <tr>
                        <td class="pl-1">
                            <b>б)</b>
                            <span class="is-blue"><xsl:value-of select="//z:Zig/z:tip_znaka"></xsl:value-of></span>
                        </td>
                        <td class="pl-1 is-centered" rowspan="5">
                            <img>
                                <xsl:attribute name="src">
                                    <xsl:value-of select="$primerak_znaka"/>
                                </xsl:attribute>
                                <xsl:attribute name="style">width: 300px; height: 300px;</xsl:attribute>
                            </img>
                        </td>
                    </tr>
                    <tr>
                        <td class="pl-1 is-top-vertical">
                            <b>5. Назначење боје, односно боја из којих се знак састоји:</b>
                            <p class="is-blue mb-0"><xsl:value-of select="//z:Zig/z:boja"></xsl:value-of></p>
                        </td>
                    </tr>
                    <tr>
                        <td class="pl-1 is-top-vertical">
                            <b>6. Транслитерација знака:</b>
                            <p class="is-blue mb-0"><xsl:value-of select="//z:Zig/z:transliteracija"></xsl:value-of></p>
                        </td>
                    </tr>
                    <tr>
                        <td class="pl-1 is-top-vertical">
                            <b>7. Превод знака*:</b>
                            <p class="is-blue mb-0"><xsl:value-of select="//z:Zig/z:prevod"></xsl:value-of></p>
                        </td>
                    </tr>
                    <tr>
                        <td class="pl-1 is-top-vertical">
                            <b>8. Опис знака:</b>
                            <p class="is-blue mb-0"><xsl:value-of select="//z:Zig/z:opis"></xsl:value-of></p>
                        </td>
                    </tr>
                </table>
                <table class="border-top-none">
                    <tr>
                        <td colspan="3" class="pl-1"><b>9. Бројеви класа робе и услуга према Ничанској класификацији:</b></td>
                    </tr>
                    <tr class="h-6 pl-2">
                        <td colspan="3" class="pl-2 is-blue">
                            <xsl:value-of select="//z:Zig/z:klase_robe"></xsl:value-of>
                        </td>
                    </tr>
                </table>
                <table class="border-top-none">
                    <tr>
                        <td colspan="3" class="pl-1 h-6">
                            <b>10. Затражено право првенства и основ:</b>
                            <p class="is-blue pl-2"><xsl:value-of select="//z:Zig/z:pravo_prvenstva"></xsl:value-of></p>
                        </td>
                    </tr>
                </table>

                <table style="margin-top: 200px">
                    <colgroup>
                        <col span="1" style="width: 25%;"/>
                        <col span="1" style="width: 25%;"/>
                        <col span="1" style="width: 50%;"/>
                    </colgroup>
                    <tr>
                        <td colspan="1" class="pl-1 h-5 is-top-vertical"><b>11. Плаћене таксе:</b></td>
                        <td colspan="1" class="pl-1 h-5 is-top-vertical"><b>Динара</b></td>
                        <td colspan="1" rowspan="4" class="pl-1 h-5 is-top-vertical is-centered">
                            <p class="mb-1 mt-0"><b>Потпис подносиоца захтева</b></p>

                        </td>
                    </tr>
                    <tr class="h-6 pl-2">
                        <td colspan="1" class="pl-1">
                            <b>а) основна такса</b>
                        </td>
                        <td colspan="1" class="pl-2 is-blue">
                            <xsl:value-of select="//z:Placanje/z:osnovna_taksa"></xsl:value-of>
                        </td>
                    </tr>
                    <tr class="h-6 pl-2">
                        <td colspan="1" class="pl-1">
                            <b>б) за <span class="is-blue"><xsl:value-of select="$broj_klasa"/></span> класа</b>
                            <br></br>
                            <b>в) за графичко решење</b>
                        </td>
                        <td colspan="1" class="pl-2 is-blue">
                            <xsl:value-of select="//z:Placanje/z:taksa_klasa"></xsl:value-of>
                            <br></br>
                            <xsl:value-of select="//z:Placanje/z:taksa_grafickog_resenja"></xsl:value-of>
                        </td>
                    </tr>
                    <tr class="h-6 pl-2">
                        <td colspan="1" class="pl-1">
                            <b>УКУПНО</b>
                        </td>
                        <td colspan="1" class="pl-2 is-blue">
                            <xsl:value-of select="//z:Placanje/z:ukupno"></xsl:value-of>
                        </td>
                    </tr>
                </table>

                <table class="mt-6 is-borderless">
                    <colgroup>
                        <col span="1" style="width: 50%;"/>
                        <col span="1" style="width: 50%;"/>
                    </colgroup>
                    <tr>
                        <td colspan="1" class="is-centered">
                            <p>QR код до документа</p>
                            <img>
                                <xsl:attribute name="src">
                                    <xsl:value-of select="$qr_code_image"/>
                                </xsl:attribute>
                            </img>
                        </td>
                        <td colspan="1" class="is-centered">
                            <p>Број пријаве жига:</p>
                            <p class="is-blue"><xsl:value-of select="//z:Informacije_Zavoda/z:broj_prijave"></xsl:value-of></p>
                            <p><b>Датум подношења:</b></p>
                            <p class="is-blue"><xsl:value-of select="//z:Informacije_Zavoda/z:datum_podnosenja"></xsl:value-of></p>
                        </td>
                    </tr>
                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>