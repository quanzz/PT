// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RDFWriter.java

package uba;

import java.io.*;

// Referenced classes of package uba:
//            Writer, Generator

public abstract class RDFWriter
    implements Writer
{

    public RDFWriter(Generator generator)
    {
        out = null;
        this.generator = generator;
    }
    
    public RDFWriter(){
    	
    }

    public void start()
    {
    }

    public void end()
    {
    }

    public void startFile(String fileName)
    {
        try
        {
            out = new PrintStream(new FileOutputStream(fileName));
            String s = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>";
            out.println(s);
            s = "<rdf:RDF";
            out.println(s);
            writeHeader();
        }
        catch(IOException e)
        {
            System.out.println("Create file failure!");
        }
    }

    public void endFile()
    {
        String s = "</rdf:RDF>";
        out.println(s);
        out.close();
    }

    public void startSection(int classType, String id)
    {
        generator.startSectionCB(classType);
        out.println();
        String s = "<ub:" + Generator.CLASS_TOKEN[classType] + " " + "rdf:about" + "=\"" + id + "\">";
        out.println(s);
    }
    
    public void startAnArticle(String classStr, String id)
    {
        out.println();
        String s = "<owl:NamedIndividual" + " " + "rdf:about" + "=\"" + id + "\">";
        out.println(s);
    }
    
    public void startANewArticle(String classStr, String id)
    {
        out.println();
        String s = "<ub:" + classStr + " " + "rdf:about" + "=\"" + id + "\">";
        out.println(s);
    }

    public void startAboutSection(int classType, String id)
    {
        generator.startAboutSectionCB(classType);
        out.println();
        String s = "<ub:" + Generator.CLASS_TOKEN[classType] + " " + "rdf:about" + "=\"" + id + "\">";
        out.println(s);
    }

    public void endSection(int classType)
    {
        String s = "</ub:" + Generator.CLASS_TOKEN[classType] + ">";
        out.println(s);
    }
    
    public void endANewArticle(String classStr)
    {
        String s = "</ub:" + classStr + ">";
        out.println(s);
    }
    
    public void endAnArticle(String classStr)
    {
        String s = "</owl:NamedIndividual>";
        out.println(s);
    }

    public void addProperty(int property, String value, boolean isResource)
    {
        generator.addPropertyCB(property);
        String s;
        if(isResource)
            s = "   <ub:" + Generator.PROP_TOKEN[property] + " " + "rdf:resource" + "=\"" + value + "\" />";
        else
            s = "   <ub:" + Generator.PROP_TOKEN[property] + ">" + value + "</" + "ub:" + Generator.PROP_TOKEN[property] + ">";
        out.println(s);
    }
    
    public void addPropertyPTU(String property, String value)
    {
        String s;
        s = "   <ub:" + property + " " + "rdf:resource" + "=\"" + value + "\" />";
       out.println(s);
    }

    public void addProperty(int property, int valueClass, String valueId)
    {
        generator.addPropertyCB(property);
        generator.addValueClassCB(valueClass);
        String s = "   <ub:" + Generator.PROP_TOKEN[property] + ">\n" + "      <" + "ub:" + Generator.CLASS_TOKEN[valueClass] + " " + "rdf:about" + "=\"" + valueId + "\" />" + "   </" + "ub:" + Generator.PROP_TOKEN[property] + ">";
        out.println(s);
    }

    public void startComment()
    {
        out.println();
        String s = "<rdf:Description rdf:about=\"\">";
        out.println(s);
    }

    public void endComment()
    {
        String s = "</rdf:Description>";
        out.println(s);
    }

    public void addComment(String comment)
    {
        String s = "   <rdfs:comment>" + comment + "</" + "rdfs:" + "comment" + ">";
        out.println(s);
    }

    protected abstract void writeHeader();

    protected static final String T_ONTO_NS = "ub";
    protected static final String T_ONTO_PREFIX = "ub:";
    protected static final String T_RDF_NS = "rdf";
    protected static final String T_RDF_PREFIX = "rdf:";
    protected static final String T_RDFS_NS = "rdfs";
    protected static final String T_RDFS_PREFIX = "rdfs:";
    protected static final String T_RDF_ID = "rdf:ID";
    protected static final String T_RDF_ABOUT = "rdf:about";
    protected static final String T_RDF_RES = "rdf:resource";
    protected static final String T_SPACE = " ";
    protected PrintStream out;
    protected Generator generator;
}
