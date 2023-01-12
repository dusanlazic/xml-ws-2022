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
            <pred:Datum_podnosenja>
                <xsl:value-of select="./z:Informacije_Zavoda/z:datum_podsnosenja"></xsl:value-of>
            </pred:Datum_podnosenja>
        </rdf:Description>
    </xsl:template>
</xsl:stylesheet>