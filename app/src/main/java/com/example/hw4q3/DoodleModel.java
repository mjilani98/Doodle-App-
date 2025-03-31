package com.example.hw4q3;

import java.util.LinkedList;

public class DoodleModel
{
    private int color ;

    //Linked list of points
    private LinkedList<Point> list;

    //public constructor
    public DoodleModel()
    {
        color = 0 ;
        list = new LinkedList<>();
    }

    //method points to a color
    public void nextColor()
    {
        color = (color + 1) & 8;
    }

    //method adds a point to the list of point
    public void addPoint(int x , int y )
    {
        //create a point using x , y , color
        Point point  = new Point(x,y,color);

        //add the point to the list
        list.add(point);
    }

    //method returns the color
    public int getColor()
    {
        return color;
    }

    //method returns the listv
    public LinkedList getList()
    {
        return list;
    }


}
