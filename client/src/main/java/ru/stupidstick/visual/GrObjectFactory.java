package ru.stupidstick.visual;

import java.util.ArrayList;

public class GrObjectFactory {
    private ArrayList<GrObject> list = new ArrayList<>();
    public GrObjectFactory(){
        list.add(new Circle());
        list.add(new Zwezda());
        list.add(new MyString());
        }
    public ArrayList<String> getNames(){
        ArrayList<String> out = new ArrayList<>();
        for(GrObject ss : list)
            out.add(ss.getName());
        return out;
        }
    public GrObject create(String name){
        try {
            for (GrObject ss : list)
                if (ss.getName().equals(name))
                    return ss.getClass().newInstance();
            } catch (Exception ee){
                return null;
                }
        return null;
        }
}
