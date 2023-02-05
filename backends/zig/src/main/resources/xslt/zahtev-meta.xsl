<?xml version="1.0"?>
<xsl:stylesheet version="1.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:rdf="http://www.w3.org/1999/02/22-rdf-syntax-ns#"
                xmlns:z="http://www.zavod.com/Zig"
                xmlns="http://www.zavod.com/Zig"
                xmlns:pred="http://www.zavod.com/Zig/pred/"
>
    <xsl:template match="/">
        <rdf:RDF>
            <xsl:apply-templates />
        </rdf:RDF>
    </xsl:template>

    <xsl:template match="z:Zahtev">
        <rdf:Description rdf:about="http://www.zavod.com/Zig/{./z:Informacije_Zavoda/z:broj_prijave}">
            <pred:Broj_prijave>
                <xsl:value-of select="./z:Informacije_Zavoda/z:broj_prijave"></xsl:value-of>
            </pred:Broj_prijave>

            <pred:Naziv>
                <xsl:value-of select="./z:Podnosilac/z:ime"></xsl:value-of>
                <xsl:choose>
                    <xsl:when test="./z:Podnosilac/z:sediste">
                    </xsl:when>
                    <xsl:otherwise>
                        <xsl:text> </xsl:text><xsl:value-of select="./z:Podnosilac/z:prezime"></xsl:value-of>
                    </xsl:otherwise>
                </xsl:choose>
            </pred:Naziv>

            <pred:Datum_podnosenja>
                <xsl:value-of select="./z:Informacije_Zavoda/z:datum_podnosenja"></xsl:value-of>
            </pred:Datum_podnosenja>

            <pred:Ukupno_placeno>
                <xsl:value-of select="./z:Placanje/z:ukupno"></xsl:value-of>
            </pred:Ukupno_placeno>

            <pred:Nalog>
                <xsl:apply-templates select="./z:Informacije_Sistema/z:email"></xsl:apply-templates>
            </pred:Nalog>

            <pred:Status_resenja>
                <xsl:apply-templates select="./z:Informacije_Zavoda/z:status_resenja"></xsl:apply-templates>
            </pred:Status_resenja>

        </rdf:Description>
    </xsl:template>
</xsl:stylesheet>