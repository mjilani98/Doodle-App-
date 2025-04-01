package com.example.hw4q3;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import java.util.LinkedList;

public class GraphicsView extends View
{
    private DoodleModel doodle;
    private Paint paint;

    //array store square location
    private float[] squareLocation;

    //String of colors
    String[] colorsArray = {
            "#000000", // Black
            "#FF0000", // Red
            "#008000", // Green
            "#0000FF", // Blue
            "#FFFF00", // Yellow
            "#A52A2A", // Brown
            "#808080", // Grey
            "#FFFFFF"  // White
    };

    //public constructor
    public GraphicsView(Context context,DoodleModel doodle)
    {
        super(context);

        //setting doodle model
        this.doodle = doodle;

        //creating paint object with stroke width 40
        paint = new Paint();
        paint.setStrokeWidth(40);
    }

    //method draws graphics
    public void onDraw(Canvas canvas)
    {
        //get list of points
        LinkedList<Point> list = doodle.getList();

        //color
        int color =1;

        //go through list of points
        for(int i = 0 ; i < list.size() ; i++)
        {
            //get the point from the list
            Point point = list.get(i);

            //get the color from the point
             color = point.color;

            //convert color code to rgb and set color
            paint.setColor(Color.parseColor(colorsArray[color]));

            //get x,y of point
            float x = point.x;
            float y = point.y;

            //draw point at x , y
            canvas.drawPoint(x,y,paint);
        }

        //get current color
        paint.setColor(Color.parseColor(colorsArray[doodle.getColor()]));
        paint.setStyle(Paint.Style.FILL);

        //draw filled square at right bottom corner
        drawSquare(canvas , color);

    }

    //method draws square on the bottom right corner
    private void drawSquare(Canvas canvas, int color)
    {
        // Get canvas dimensions
        int canvasWidth = canvas.getWidth();
        int canvasHeight = canvas.getHeight();

        // Define the size of the square
        int squareSize = 300;

        // square position
        float left = canvasWidth - squareSize - 100; // 30px margin
        float top = canvasHeight - squareSize - 200; // 30px margin
        float right = canvasWidth - 100; // 30px margin
        float bottom = canvasHeight - 200; // 30px margin

        // Set paint color to the current selection
        paint.setColor(Color.parseColor(colorsArray[doodle.getColor()]));
        paint.setStyle(Paint.Style.FILL);

        // Draw the square
        canvas.drawRect(left, top, right, bottom, paint);

        //storing square location in an array
        squareLocation = new float[]{left, top, right, bottom};
    }

    //method returns the square location
    public float[] getSquareLocation()
    {
        return squareLocation;
    }


}