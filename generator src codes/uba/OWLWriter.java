// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   OWLWriter.java

package uba;

import java.io.PrintStream;

// Referenced classes of package uba:
//            RDFWriter, Generator

public class OWLWriter extends RDFWriter
{

    public OWLWriter(Generator generator)
    {
        super(generator);
    }
    
    public OWLWriter(){
    	
    }

    protected void writeHeader()
    {
        String s = "xmlns:rdf=\"http://www.w3.org/1999/02/22-rdf-syntax-ns#\"";
        super.out.println(s);
        s = "xmlns:rdfs=\"http://www.w3.org/2000/01/rdf-schema#\"";
        super.out.println(s);
        s = "xmlns:owl=\"http://www.w3.org/2002/07/owl#\"";
        super.out.println(s);
        s = "xmlns:ub=\"http://swat.cse.lehigh.edu/onto/univ-bench.owl#\">";
        super.out.println(s);
//        s = "<owl:Ontology rdf:about=\"\">";
//        super.out.println(s);
//        s = "<owl:imports rdf:resource=\"" + super.generator.ontology + "\" />";
//        super.out.println(s);
//        s = "</owl:Ontology>";
//        super.out.println(s);
        super.out.println();
        s = "<owl:ObjectProperty rdf:about=\"http://swat.cse.lehigh.edu/onto/univ-bench.owl#cite\"/>";
        super.out.println(s);
        s = "<owl:ObjectProperty rdf:about=\"http://swat.cse.lehigh.edu/onto/univ-bench.owl#referTo\"/>";
        super.out.println(s);
        
    }

    private static final String T_OWL_NS = "owl";
    private static final String T_OWL_PREFIX = "owl:";
}
