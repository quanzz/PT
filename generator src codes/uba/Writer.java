// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Writer.java

package uba;


public interface Writer
{

    public abstract void start();

    public abstract void end();

    public abstract void startFile(String s);

    public abstract void endFile();

    public abstract void startSection(int i, String s);

    public abstract void startAboutSection(int i, String s);

    public abstract void endSection(int i);

    public abstract void addProperty(int i, String s, boolean flag);

    public abstract void addProperty(int i, int j, String s);

    public abstract void startComment();

    public abstract void endComment();

    public abstract void addComment(String s);
}
