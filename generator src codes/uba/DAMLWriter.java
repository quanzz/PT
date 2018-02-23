// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DAMLWriter.java

package uba;

import java.io.PrintStream;

// Referenced classes of package uba:
//            RDFWriter, Generator

public class DAMLWriter extends RDFWriter
{

    public DAMLWriter(Generator generator)
    {
        super(generator);
    }

    protected void writeHeader()
    {
        String s = "xmlns:rdf=\"http://www.w3.org/1999/02/22-rdf-syntax-ns#\"";
        super.out.println(s);
        s = "xmlns:rdfs=\"http://www.w3.org/2000/01/rdf-schema#\"";
        super.out.println(s);
        s = "xmlns:daml=\"http://www.daml.org/2001/03/daml+oil#\"";
        super.out.println(s);
        s = "xmlns:ub=\"" + super.generator.ontology + "#\">";
        super.out.println(s);
        s = "<rdf:Description rdf:about=\"\">";
        super.out.println(s);
        s = "<daml:imports rdf:resource=\"" + super.generator.ontology + "\" />";
        super.out.println(s);
        s = "</rdf:Description>";
        super.out.println(s);
    }

    private static final String T_DAML_NS = "daml";
    private static final String T_DAML_PREFIX = "daml:";
}
