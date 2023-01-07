<?xml version="1.0"?>
<xsl:stylesheet version="1.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:rdf="http://www.w3.org/1999/02/22-rdf-syntax-ns#"
                xmlns:a="http://www.zavod.com/Autorska"
                xmlns:pred="http://www.zavod.com/Autorska/pred"
>
    <xsl:template match="/">
        <rdf:RDF>
            <xsl:apply-templates />
        </rdf:RDF>
    </xsl:template>

    <xsl:template match="a:Zahtev">
        <rdf:Description rdf:about="bilosta">
            <pred:Broj_prijave>
                <xsl:value-of select="./a:Informacije_Zavoda/a:broj_prijave"></xsl:value-of>
            </pred:Broj_prijave>
            <pred:Naziv>
                <xsl:value-of select="./a:Podnosilac/a:ime"></xsl:value-of>
                <xsl:choose>
                    <xsl:when test="./a:Podnosilac/a:sediste">
                    </xsl:when>
                    <xsl:otherwise>
                        <xsl:text> </xsl:text><xsl:value-of select="./a:Podnosilac/a:prezime"></xsl:value-of>
                    </xsl:otherwise>
                </xsl:choose>
            </pred:Naziv>
            <pred:Datum_podnosenja>
                <xsl:value-of select="./a:Informacije_Zavoda/a:datum_podsnosenja"></xsl:value-of>
            </pred:Datum_podnosenja>
        </rdf:Description>
    </xsl:template>
</xsl:stylesheet>