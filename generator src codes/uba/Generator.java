// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Generator.java

package uba;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;

// Referenced classes of package uba:
//            DAMLWriter, OWLWriter, Writer

public class Generator
{
    private class PubInfo
    {

        public String id;
        public String name;
        public ArrayList authors;

        private PubInfo()
        {
        }

    }

    private class TAInfo
    {

        public int indexInGradStud;
        public int indexInCourse;

        private TAInfo()
        {
            indexInGradStud = 0;
            indexInCourse = 0;
        }

    }

    private class RAInfo
    {

        public int indexInGradStud;

        private RAInfo()
        {
            indexInGradStud = 0;
        }

    }

    private class CourseInfo
    {

        public int indexInFaculty;
        public int globalIndex;

        private CourseInfo()
        {
            indexInFaculty = 0;
            globalIndex = 0;
        }

    }

    protected class PropInfo
    {

        public int logNum;
        public long logTotal;

        protected PropInfo()
        {
            logNum = 0;
            logTotal = 0L;
        }
    }

    private class InstanceInfo
    {

        public int num;
        public int total;
        public int count;
        public int logNum;
        public long logTotal;

        private InstanceInfo()
        {
            num = 0;
            total = 0;
            count = 0;
            logNum = 0;
            logTotal = 0L;
        }

    }


    public Generator()
    {
        seed = 0L;
        baseSeed = 0L;
        log = null;
        instances = new InstanceInfo[CLASS_NUM];
        for(int i = 0; i < CLASS_NUM; i++)
            instances[i] = new InstanceInfo();

        properties = new PropInfo[PROP_NUM];
        for(int i = 0; i < PROP_NUM; i++)
            properties[i] = new PropInfo();

        random = new Random();
        underCourses = new ArrayList();
        gradCourses = new ArrayList();
        remainingUnderCourses = new ArrayList();
        remainingGradCourses = new ArrayList();
        publications = new ArrayList();
    }

    public void start(int univNum, int startIndex, int seed, boolean daml, String ontology)
    {
        this.ontology = ontology;
        isDAML = daml;
        if(daml)
            writer = new DAMLWriter(this);
        else
            writer = new OWLWriter(this);
        this.startIndex = startIndex;
        baseSeed = seed;
        instances[0].num = univNum;
        instances[0].count = startIndex;
        generate();
        System.out.println("See log.txt for more details.");
    }

    private void setInstanceInfo()
    {
        for(int i = 0; i < CLASS_NUM; i++)
        {
            switch(i)
            {
            case 4: // '\004 FullProfessor'
                instances[i].num = randomRange(7, 10);
                break;

            case 5: // '\005 AssociateProfessor'
                instances[i].num = randomRange(10, 14);
                break;

            case 6: // '\006 AssistantProfessor'
                instances[i].num = randomRange(8, 11);
                break;

            case 7: // '\007 Lecturer'
                instances[i].num = randomRange(5, 7);
                break;

            case 9: // '\t UndergraduateStudent'
                instances[i].num = randomRange(8 * instances[2].total, 14 * instances[2].total);
                break;

            case 10: // '\n GraduateStudent'
                instances[i].num = randomRange(3 * instances[2].total, 4 * instances[2].total);
                break;

            case 11: // '\013 TeachingAssistant'
                instances[i].num = randomRange(instances[10].total / 5, instances[10].total / 4);
                break;

            case 12: // '\f ResearchAssistant'
                instances[i].num = randomRange(instances[10].total / 4, instances[10].total / 3);
                break;

            case 18: // '\022 ResearchGroup'
                instances[i].num = randomRange(10, 20);
                break;

            case 2: // '\002 Faculty'
            case 3: // '\003 Professor'
            case 8: // '\b Student'
            case 13: // '\r Course'
            case 14: // '\016 GraduateCourse'
            case 15: // '\017 Publication'
            case 16: // '\020 Chair'
            case 17: // '\021 Research'
            default:
                instances[i].num = CLASS_INFO[i][0];
                break;

            case 0: // '\0 University'
            case 1: // '\001 Department'
                break;
            }
            instances[i].total = instances[i].num;
            int j;
            for(int subClass = i; (j = CLASS_INFO[subClass][1]) != -1; subClass = j)
                instances[j].total += instances[i].num;

        }

    }

    /**
     * generateUniv -> each department a file
     *                 set the number of departments
     *                 generateDept  ->
     *                                  set numbers of other classes
     *                                  
     * 
     */
    private void generate()
    {
        System.out.println("Started...");
        try
        {
            log = new PrintStream(new FileOutputStream(System.getProperty("user.dir") + "\\" + "log.txt"));
            writer.start();
            for(int i = 0; i < instances[0].num; i++)
                generateUniv(i + startIndex);

            writer.end();
            log.close();
        }
        catch(IOException e)
        {
            System.out.println("Failed to create log file!");
        }
        System.out.println("Completed!");
    }

    private void generateUniv(int index)
    {
        seed = baseSeed * 0xffffffff80000000L + (long)index;
        random.setSeed(seed);
        instances[1].num = randomRange(15, 25);
        instances[1].count = 0;
        for(int i = 0; i < instances[1].num; i++)
            generateDept(index, i);

    }

    private void generateDept(int univIndex, int index)
    {
        String fileName = System.getProperty("user.dir") + "\\" + getName(0, univIndex) + '_' + index + getFileSurfix();
        writer.startFile(fileName);
        setInstanceInfo();
        underCourses.clear();
        gradCourses.clear();
        remainingUnderCourses.clear();
        remainingGradCourses.clear();
        for(int i = 0; i < 100; i++)
            remainingUnderCourses.add(new Integer(i));

        for(int i = 0; i < 100; i++)
            remainingGradCourses.add(new Integer(i));

        publications.clear();
        for(int i = 0; i < CLASS_NUM; i++)
            instances[i].logNum = 0;

        for(int i = 0; i < PROP_NUM; i++)
            properties[i].logNum = 0;

        // a chair should be some full processor.
        chair = random.nextInt(instances[4].total);
        
        // when generating the first department
        // it should first generate the info of univ
        // this info occurs once.
        if(index == 0)
            generateASection(0, univIndex);
        generateASection(1, index);
        for(int i = 2; i < CLASS_NUM; i++)
        {
            instances[i].count = 0;
            for(int j = 0; j < instances[i].num; j++)
                generateASection(i, j);

        }

        generatePublications();
        generateCourses();
        generateRATA();
        System.out.println(fileName + " generated");
        String bar = "";
        for(int i = 0; i < fileName.length(); i++)
            bar = bar + '-';

        log.println(bar);
        log.println(fileName);
        log.println(bar);
        generateComment();
        writer.endFile();
    }

    private void generateASection(int classType, int index)
    {
        updateCount(classType);
        switch(classType)
        {
        case 0: // '\0'
            generateAUniv(index);
            break;

        case 1: // '\001'
            generateADept(index);
            break;

        case 2: // '\002'
            generateAFaculty(index);
            break;

        case 3: // '\003'
            generateAProf(index);
            break;

        case 4: // '\004'
            generateAFullProf(index);
            break;

        case 5: // '\005'
            generateAAssoProf(index);
            break;

        case 6: // '\006'
            generateAAsstProf(index);
            break;

        case 7: // '\007'
            generateALecturer(index);
            break;

        case 9: // '\t'
            generateAUnderStud(index);
            break;

        case 10: // '\n'
            generateAGradStud(index);
            break;

        case 13: // '\r'
            generateACourse(index);
            break;

        case 14: // '\016'
            generateAGradCourse(index);
            break;

        case 18: // '\022'
            generateAResearchGroup(index);
            break;
        }
    }

    private void generateAUniv(int index)
    {
        writer.startSection(0, getId(0, index));
        writer.addProperty(0, getRelativeName(0, index), false);
        writer.endSection(0);
    }

    private void generateADept(int index)
    {
        writer.startSection(1, getId(1, index));
        writer.addProperty(0, getRelativeName(1, index), false);
        writer.addProperty(14, 0, getId(0, instances[0].count - 1));
        writer.endSection(1);
    }

    private void generateAFaculty(int index)
    {
        writer.startSection(2, getId(2, index));
        generateAFaculty_a(2, index);
        writer.endSection(2);
    }

    private void generateAFaculty_a(int type, int index)
    {
        int indexInFaculty = instances[2].count - 1;
        writer.addProperty(0, getRelativeName(type, index), false);
        int courseNum = randomRange(1, 2);
        for(int i = 0; i < courseNum; i++)
        {
            int courseIndex = facultyBindCourse(indexInFaculty);
            writer.addProperty(2, getId(13, courseIndex), true);
        }

        courseNum = randomRange(1, 2);
        for(int i = 0; i < courseNum; i++)
        {
            int courseIndex = facultyBindGradCourse(indexInFaculty);
            writer.addProperty(2, getId(14, courseIndex), true);
        }

        writer.addProperty(3, 0, getId(0, random.nextInt(1000)));
        writer.addProperty(4, 0, getId(0, random.nextInt(1000)));
        writer.addProperty(5, 0, getId(0, random.nextInt(1000)));
        writer.addProperty(15, getId(1, instances[1].count - 1), true);
        writer.addProperty(12, getEmail(type, index), false);
        writer.addProperty(13, "xxx-xxx-xxxx", false);
    }

    private int facultyBindCourse(int indexInFaculty)
    {
        int pos = randomRange(0, remainingUnderCourses.size() - 1);
        pos = 0;
        CourseInfo course = new CourseInfo();
        course.indexInFaculty = indexInFaculty;
        course.globalIndex = ((Integer)remainingUnderCourses.get(pos)).intValue();
        underCourses.add(course);
        remainingUnderCourses.remove(pos);
        return course.globalIndex;
    }

    private int facultyBindGradCourse(int indexInFaculty)
    {
        int pos = randomRange(0, remainingGradCourses.size() - 1);
        pos = 0;
        CourseInfo course = new CourseInfo();
        course.indexInFaculty = indexInFaculty;
        course.globalIndex = ((Integer)remainingGradCourses.get(pos)).intValue();
        gradCourses.add(course);
        remainingGradCourses.remove(pos);
        return course.globalIndex;
    }

    private void generateAProf(int index)
    {
        writer.startSection(3, getId(3, index));
        generateAProf_a(3, index);
        writer.endSection(3);
    }

    private void generateAProf_a(int type, int index)
    {
        generateAFaculty_a(type, index);
        writer.addProperty(11, getRelativeName(17, random.nextInt(30)), false);
    }

    private void generateAFullProf(int index)
    {
        String id = getId(4, index);
        writer.startSection(4, id);
        generateAProf_a(4, index);
        if(index == chair)
            writer.addProperty(9, getId(1, instances[1].count - 1), true);
        writer.endSection(4);
        generateFacultyPublications(id, 15, 20);
    }

    private void generateAAssoProf(int index)
    {
        String id = getId(5, index);
        writer.startSection(5, id);
        generateAProf_a(5, index);
        writer.endSection(5);
        generateFacultyPublications(id, 10, 18);
    }

    private void generateAAsstProf(int index)
    {
        String id = getId(6, index);
        writer.startSection(6, id);
        generateAProf_a(6, index);
        writer.endSection(6);
        generateFacultyPublications(id, 5, 10);
    }

    private void generateALecturer(int index)
    {
        String id = getId(7, index);
        writer.startSection(7, id);
        generateAFaculty_a(7, index);
        writer.endSection(7);
        generateFacultyPublications(id, 0, 5);
    }

    private void generateFacultyPublications(String author, int min, int max)
    {
        int num = randomRange(min, max);
        for(int i = 0; i < num; i++)
        {
            PubInfo publication = new PubInfo();
            publication.id = getId(15, i, author);
            publication.name = getRelativeName(15, i);
            publication.authors = new ArrayList();
            publication.authors.add(author);
            publications.add(publication);
        }

    }

    private void generateGradStudPublications(String author, int min, int max)
    {
        int num = randomRange(min, max);
        ArrayList list = randomList(num, 0, publications.size() - 1);
        for(int i = 0; i < list.size(); i++)
        {
            PubInfo publication = (PubInfo)publications.get(((Integer)list.get(i)).intValue());
            publication.authors.add(author);
        }

    }

    private void generatePublications()
    {
        for(int i = 0; i < publications.size(); i++)
            generateAPublication((PubInfo)publications.get(i));

    }

    private void generateAPublication(PubInfo publication)
    {
        writer.startSection(15, publication.id);
        writer.addProperty(0, publication.name, false);
        for(int i = 0; i < publication.authors.size(); i++)
            writer.addProperty(8, (String)publication.authors.get(i), true);

        writer.endSection(15);
    }

    private void generateAStudent_a(int type, int index)
    {
        writer.addProperty(0, getRelativeName(type, index), false);
        writer.addProperty(7, getId(1, instances[1].count - 1), true);
        writer.addProperty(12, getEmail(type, index), false);
        writer.addProperty(13, "xxx-xxx-xxxx", false);
    }

    private void generateAUnderStud(int index)
    {
        writer.startSection(9, getId(9, index));
        generateAStudent_a(9, index);
        int n = randomRange(2, 4);
        ArrayList list = randomList(n, 0, underCourses.size() - 1);
        for(int i = 0; i < list.size(); i++)
        {
            CourseInfo info = (CourseInfo)underCourses.get(((Integer)list.get(i)).intValue());
            writer.addProperty(1, getId(13, info.globalIndex), true);
        }

        if(0 == random.nextInt(5))
            writer.addProperty(6, selectAdvisor(), true);
        writer.endSection(9);
    }

    private void generateAGradStud(int index)
    {
        String id = getId(10, index);
        writer.startSection(10, id);
        generateAStudent_a(10, index);
        int n = randomRange(1, 3);
        ArrayList list = randomList(n, 0, gradCourses.size() - 1);
        for(int i = 0; i < list.size(); i++)
        {
            CourseInfo info = (CourseInfo)gradCourses.get(((Integer)list.get(i)).intValue());
            writer.addProperty(1, getId(14, info.globalIndex), true);
        }

        writer.addProperty(3, 0, getId(0, random.nextInt(1000)));
        if(0 == random.nextInt(1))
            writer.addProperty(6, selectAdvisor(), true);
        generateGradStudPublications(id, 0, 5);
        writer.endSection(10);
    }

    private String selectAdvisor()
    {
        int profType = randomRange(4, 6);
        int index = random.nextInt(instances[profType].total);
        return getId(profType, index);
    }

    private void generateATA(TAInfo ta)
    {
        writer.startAboutSection(11, getId(10, ta.indexInGradStud));
        writer.addProperty(10, getId(13, ta.indexInCourse), true);
        writer.endSection(11);
    }

    private void generateARA(RAInfo ra)
    {
        writer.startAboutSection(12, getId(10, ra.indexInGradStud));
        writer.endSection(12);
    }

    private void generateACourse(int index)
    {
        writer.startSection(13, getId(13, index));
        writer.addProperty(0, getRelativeName(13, index), false);
        writer.endSection(13);
    }

    private void generateAGradCourse(int index)
    {
        writer.startSection(14, getId(14, index));
        writer.addProperty(0, getRelativeName(14, index), false);
        writer.endSection(14);
    }

    private void generateCourses()
    {
        for(int i = 0; i < underCourses.size(); i++)
            generateACourse(((CourseInfo)underCourses.get(i)).globalIndex);

        for(int i = 0; i < gradCourses.size(); i++)
            generateAGradCourse(((CourseInfo)gradCourses.get(i)).globalIndex);

    }

    private void generateRATA()
    {
        ArrayList tas = new ArrayList();
        ArrayList ras = new ArrayList();
        ArrayList list = randomList(instances[11].total + instances[12].total, 0, instances[10].total - 1);
        ArrayList courseList = randomList(instances[11].total, 0, underCourses.size() - 1);
        int i;
        for(i = 0; i < instances[11].total; i++)
        {
            TAInfo ta = new TAInfo();
            ta.indexInGradStud = ((Integer)list.get(i)).intValue();
            ta.indexInCourse = ((CourseInfo)underCourses.get(((Integer)courseList.get(i)).intValue())).globalIndex;
            generateATA(ta);
        }

        for(; i < list.size(); i++)
        {
            RAInfo ra = new RAInfo();
            ra.indexInGradStud = ((Integer)list.get(i)).intValue();
            generateARA(ra);
        }

    }

    private void generateAResearchGroup(int index)
    {
        String id = getId(18, index);
        writer.startSection(18, id);
        writer.addProperty(14, getId(1, instances[1].count - 1), true);
        writer.endSection(18);
    }

    private String getFileSurfix()
    {
        return isDAML ? ".daml" : ".owl";
    }

    private String getId(int classType, int index)
    {
        String id;
        switch(classType)
        {
        case 0: // '\0'
            id = "http://www." + getRelativeName(classType, index) + ".edu";
            break;

        case 1: // '\001'
            id = "http://www." + getRelativeName(classType, index) + "." + getRelativeName(0, instances[0].count - 1) + ".edu";
            break;

        default:
            id = getId(1, instances[1].count - 1) + '/' + getRelativeName(classType, index);
            break;
        }
        return id;
    }

    private String getId(int classType, int index, String param)
    {
        String id;
        switch(classType)
        {
        case 15: // '\017'
            id = param + '/' + CLASS_TOKEN[classType] + index;
            break;

        default:
            id = getId(classType, index);
            break;
        }
        return id;
    }

    private String getName(int classType, int index)
    {
        String name;
        switch(classType)
        {
        case 0: // '\0 University'
            name = getRelativeName(classType, index);
            break;

        case 1: // '\001 Department'
            name = getRelativeName(classType, index) + '_' + (instances[0].count - 1);
            break;

        case 13: // '\r Course'
        case 14: // '\016 GraduateCourse'
        case 17: // '\021 Research'
            name = getRelativeName(classType, index) + '_' + (instances[1].count - 1);
            break;

        default:
            name = getRelativeName(classType, index) + '_' + (instances[1].count - 1) + '_' + (instances[0].count - 1);
            break;
        }
        return name;
    }

    private String getRelativeName(int classType, int index)
    {
        String name;
        switch(classType)
        {
        case 0: // '\0 University'
            name = CLASS_TOKEN[classType] + index;
            break;

        case 1: // '\001 Department'
            name = CLASS_TOKEN[classType] + index;
            break;

        default:
            name = CLASS_TOKEN[classType] + index;
            break;
        }
        return name;
    }

    private String getEmail(int classType, int index)
    {
        String email = "";
        switch(classType)
        {
        case 0: // '\0'
            email = email + getRelativeName(classType, index) + "@" + getRelativeName(classType, index) + ".edu";
            break;

        case 1: // '\001'
            email = email + getRelativeName(classType, index) + "@" + getRelativeName(classType, index) + "." + getRelativeName(0, instances[0].count - 1) + ".edu";
            break;

        default:
            email = email + getRelativeName(classType, index) + "@" + getRelativeName(1, instances[1].count - 1) + "." + getRelativeName(0, instances[0].count - 1) + ".edu";
            break;
        }
        return email;
    }

    private void updateCount(int classType)
    {
        instances[classType].count++;
        int i;
        for(int subClass = classType; (i = CLASS_INFO[subClass][1]) != -1; subClass = i)
            instances[i].count++;

    }

    private ArrayList randomList(int num, int min, int max)
    {
        ArrayList list = new ArrayList();
        ArrayList tmp = new ArrayList();
        for(int i = min; i <= max; i++)
            tmp.add(new Integer(i));

        for(int i = 0; i < num; i++)
        {
            int pos = randomRange(0, tmp.size() - 1);
            list.add((Integer)tmp.get(pos));
            tmp.remove(pos);
        }

        return list;
    }

    private int randomRange(int min, int max)
    {
        return min + random.nextInt((max - min) + 1);
    }

    private void generateComment()
    {
        int classInstNum = 0;
        long totalClassInstNum = 0L;
        int propInstNum = 0;
        long totalPropInstNum = 0L;
        String comment = "External Seed=" + baseSeed + " Interal Seed=" + seed;
        log.println(comment);
        log.println();
        comment = "CLASS INSTANCE# TOTAL-SO-FAR";
        log.println(comment);
        comment = "----------------------------";
        log.println(comment);
        for(int i = 0; i < CLASS_NUM; i++)
        {
            comment = CLASS_TOKEN[i] + " " + instances[i].logNum + " " + instances[i].logTotal;
            log.println(comment);
            classInstNum += instances[i].logNum;
            totalClassInstNum += instances[i].logTotal;
        }

        log.println();
        comment = "TOTAL: " + classInstNum;
        log.println(comment);
        comment = "TOTAL SO FAR: " + totalClassInstNum;
        log.println(comment);
        comment = "PROPERTY---INSTANCE NUM";
        log.println();
        comment = "PROPERTY INSTANCE# TOTAL-SO-FAR";
        log.println(comment);
        comment = "-------------------------------";
        log.println(comment);
        for(int i = 0; i < PROP_NUM; i++)
        {
            comment = PROP_TOKEN[i] + " " + properties[i].logNum;
            comment = comment + " " + properties[i].logTotal;
            log.println(comment);
            propInstNum += properties[i].logNum;
            totalPropInstNum += properties[i].logTotal;
        }

        log.println();
        comment = "TOTAL: " + propInstNum;
        log.println(comment);
        comment = "TOTAL SO FAR: " + totalPropInstNum;
        log.println(comment);
        System.out.println("CLASS INSTANCE #: " + classInstNum + ", TOTAL SO FAR: " + totalClassInstNum);
        System.out.println("PROPERTY INSTANCE #: " + propInstNum + ", TOTAL SO FAR: " + totalPropInstNum);
        System.out.println();
        log.println();
    }

    public void startSectionCB(int classType)
    {
        instances[classType].logNum++;
        instances[classType].logTotal++;
    }

    public void startAboutSectionCB(int classType)
    {
        startSectionCB(classType);
    }

    public void addPropertyCB(int property)
    {
        properties[property].logNum++;
        properties[property].logTotal++;
    }

    public void addValueClassCB(int classType)
    {
        instances[classType].logNum++;
        instances[classType].logTotal++;
    }

    public static void main(String args[])
    {
        int univNum = 1;
        int startIndex = 0;
        int seed = 0;
        boolean daml = false;
        String ontology = null;
        try
        {
            for(int i = 0; i < args.length;)
            {
                String arg = args[i++];
                if(arg.equals("-univ"))
                {
                    if(i < args.length)
                    {
                        arg = args[i++];
                        univNum = Integer.parseInt(arg);
                        if(univNum < 1)
                            throw new NumberFormatException();
                    } else
                    {
                        throw new NumberFormatException();
                    }
                } else
                if(arg.equals("-index"))
                {
                    if(i < args.length)
                    {
                        arg = args[i++];
                        startIndex = Integer.parseInt(arg);
                        if(startIndex < 0)
                            throw new NumberFormatException();
                    } else
                    {
                        throw new NumberFormatException();
                    }
                } else
                if(arg.equals("-seed"))
                {
                    if(i < args.length)
                    {
                        arg = args[i++];
                        seed = Integer.parseInt(arg);
                        if(seed < 0)
                            throw new NumberFormatException();
                    } else
                    {
                        throw new NumberFormatException();
                    }
                } else
                if(arg.equals("-daml"))
                    daml = true;
                else
                if(arg.equals("-onto"))
                {
                    if(i < args.length)
                    {
                        arg = args[i++];
                        ontology = arg;
                    } else
                    {
                        throw new Exception();
                    }
                } else
                {
                    throw new Exception();
                }
            }

            if(((long)startIndex + (long)univNum) - 1L > 0x7fffffffL)
            {
                System.err.println("Index overflow!");
                throw new Exception();
            }
            if(null == ontology)
            {
                System.err.println("ontology url is requested!");
                throw new Exception();
            }
        }
        catch(Exception e)
        {
            System.err.println("Usage: Generator\n\t[-univ <num of universities(1~2147483647)>]\n\t[-index <start index(0~2147483647)>]\n\t[-seed <seed(0~2147483647)>]\n\t[-daml]\n\t-onto <ontology url>");
            System.exit(0);
        }
        (new Generator()).start(univNum, startIndex, seed, daml, ontology);
    }

    protected static final int CS_C_NULL = -1;
    protected static final int CS_C_UNIV = 0;
    protected static final int CS_C_DEPT = 1;
    protected static final int CS_C_FACULTY = 2;
    protected static final int CS_C_PROF = 3;
    protected static final int CS_C_FULLPROF = 4;
    protected static final int CS_C_ASSOPROF = 5;
    protected static final int CS_C_ASSTPROF = 6;
    protected static final int CS_C_LECTURER = 7;
    protected static final int CS_C_STUDENT = 8;
    protected static final int CS_C_UNDERSTUD = 9;
    protected static final int CS_C_GRADSTUD = 10;
    protected static final int CS_C_TA = 11;
    protected static final int CS_C_RA = 12;
    protected static final int CS_C_COURSE = 13;
    protected static final int CS_C_GRADCOURSE = 14;
    protected static final int CS_C_PUBLICATION = 15;
    protected static final int CS_C_CHAIR = 16;
    protected static final int CS_C_RESEARCH = 17;
    protected static final int CS_C_RESEARCHGROUP = 18;
    protected static final int CS_P_NAME = 0;
    protected static final int CS_P_TAKECOURSE = 1;
    protected static final int CS_P_TEACHEROF = 2;
    protected static final int CS_P_UNDERGRADFROM = 3;
    protected static final int CS_P_GRADFROM = 4;
    protected static final int CS_P_DOCFROM = 5;
    protected static final int CS_P_ADVISOR = 6;
    protected static final int CS_P_MEMBEROF = 7;
    protected static final int CS_P_PUBLICATIONAUTHOR = 8;
    protected static final int CS_P_HEADOF = 9;
    protected static final int CS_P_TAOF = 10;
    protected static final int CS_P_RESEARCHINTEREST = 11;
    protected static final int CS_P_EMAIL = 12;
    protected static final int CS_P_TELEPHONE = 13;
    protected static final int CS_P_SUBORGANIZATIONOF = 14;
    protected static final int CS_P_WORKSFOR = 15;
    private static final int CLASS_INFO[][] = {
        {
            2, -1 // University [= T
        }, {
            1, -1 // Department [= T
        }, {
            0, -1 // Faculty [= T
        }, {
            0, 2 // Professor [= T
        }, {
            0, 3 // FullProfessor [= Professor
        }, {
            0, 3 // AssociateProfessor [= Professor
        }, {
            0, 3 // AssistantProfessor [= Professor
        }, {
            0, 2 // Lecturer [= Faculty
        }, {
            0, -1 // Student
        }, {
            0, 8 // UndergraduateStudent [= Student
        }, {
            0, 8 // GraduateStudent [= Student
        }, {
            0, -1 // TeachingAssistant
        }, {
            0, -1 // ResearchAssistant
        }, {
            0, -1 // Course
        }, {
            0, -1 // GraduateCourse
        }, {
            0, -1 // Publication
        }, {
            0, -1 // Chair
        }, {
            0, -1 // Research
        }, {
            0, -1 // ResearchGroup
        }
    };
    protected static final String CLASS_TOKEN[] = {
        "University", "Department", "Faculty", "Professor", "FullProfessor", "AssociateProfessor", "AssistantProfessor", "Lecturer", "Student", "UndergraduateStudent", 
        "GraduateStudent", "TeachingAssistant", "ResearchAssistant", "Course", "GraduateCourse", "Publication", "Chair", "Research", "ResearchGroup"
    };
    protected static final int CLASS_NUM = CLASS_INFO.length;
    private static final int INDEX_NUM = 0;
    private static final int INDEX_SUPER = 1;
    protected static final String PROP_TOKEN[] = {
        "name", "takesCourse", "teacherOf", "undergraduateDegreeFrom", "mastersDegreeFrom", "doctoralDegreeFrom", "advisor", "memberOf", "publicationAuthor", "headOf", 
        "teachingAssistantOf", "researchInterest", "emailAddress", "telephone", "subOrganizationOf", "worksFor"
    };
    protected static final int PROP_NUM = PROP_TOKEN.length;
    private static final int UNDER_COURSE_NUM = 100;
    private static final int GRAD_COURSE_NUM = 100;
    private static final int UNIV_NUM = 1000;
    private static final int RESEARCH_NUM = 30;
    private static final int DEPT_MIN = 15;
    private static final int DEPT_MAX = 25;
    private static final int FULLPROF_PUB_MIN = 15;
    private static final int FULLPROF_PUB_MAX = 20;
    private static final int ASSOPROF_PUB_MIN = 10;
    private static final int ASSOPROF_PUB_MAX = 18;
    private static final int ASSTPROF_PUB_MIN = 5;
    private static final int ASSTPROF_PUB_MAX = 10;
    private static final int GRADSTUD_PUB_MIN = 0;
    private static final int GRADSTUD_PUB_MAX = 5;
    private static final int LEC_PUB_MIN = 0;
    private static final int LEC_PUB_MAX = 5;
    private static final int FACULTY_COURSE_MIN = 1;
    private static final int FACULTY_COURSE_MAX = 2;
    private static final int FACULTY_GRADCOURSE_MIN = 1;
    private static final int FACULTY_GRADCOURSE_MAX = 2;
    private static final int UNDERSTUD_COURSE_MIN = 2;
    private static final int UNDERSTUD_COURSE_MAX = 4;
    private static final int GRADSTUD_COURSE_MIN = 1;
    private static final int GRADSTUD_COURSE_MAX = 3;
    private static final int RESEARCHGROUP_MIN = 10;
    private static final int RESEARCHGROUP_MAX = 20;
    private static final int R_FULLPROF_DEPT_MIN = 7;
    private static final int R_FULLPROF_DEPT_MAX = 10;
    private static final int R_ASSOPROF_DEPT_MIN = 10;
    private static final int R_ASSOPROF_DEPT_MAX = 14;
    private static final int R_ASSTPROF_DEPT_MIN = 8;
    private static final int R_ASSTPROF_DEPT_MAX = 11;
    private static final int R_LEC_DEPT_MIN = 5;
    private static final int R_LEC_DEPT_MAX = 7;
    private static final int R_UNDERSTUD_FACULTY_MIN = 8;
    private static final int R_UNDERSTUD_FACULTY_MAX = 14;
    private static final int R_GRADSTUD_FACULTY_MIN = 3;
    private static final int R_GRADSTUD_FACULTY_MAX = 4;
    private static final int R_GRADSTUD_TA_MIN = 4;
    private static final int R_GRADSTUD_TA_MAX = 5;
    private static final int R_GRADSTUD_RA_MIN = 3;
    private static final int R_GRADSTUD_RA_MAX = 4;
    private static final int R_UNDERSTUD_ADVISOR = 5;
    private static final int R_GRADSTUD_ADVISOR = 1;
    private static final char ID_DELIMITER = 47;
    private static final char INDEX_DELIMITER = 95;
    private InstanceInfo instances[];
    protected PropInfo properties[];
    private Writer writer;
    private boolean isDAML;
    protected String ontology;
    Random random;
    long seed;
    long baseSeed;
    private ArrayList underCourses;
    private ArrayList gradCourses;
    private ArrayList remainingUnderCourses;
    private ArrayList remainingGradCourses;
    private ArrayList publications;
    private int chair;
    int startIndex;
    private static final String LOG_FILE = "log.txt";
    PrintStream log;

}
