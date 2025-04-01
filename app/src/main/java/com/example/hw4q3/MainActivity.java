package com.example.hw4q3;

import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity
{
    private DoodleModel doodle;
    private GraphicsView graphicsView;
    private GestureDetector gestureDetector;
    private float[] squareLocation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //create doodle model
        doodle = new DoodleModel();

        //create graphic view
        graphicsView = new GraphicsView(this,doodle);

        //set content view
        setContentView(graphicsView);

        //create touch handler
        TouchHandler handler = new TouchHandler();

        //create gesture detector
        gestureDetector = new GestureDetector(this, handler);



    }

    //Event handler of touch event
    public boolean onTouchEvent(MotionEvent event)
    {
        //find x,y of event
        int x = (int)event.getX();
        int y = (int)event.getY();

        //check x ,y inside square
        if(insideSquare(x,y)) // if yes
        {
            //pass event to gesture detector
            gestureDetector.onTouchEvent(event);
        }
        else
        {
            //find action of event
            int typeEvent = event.getAction();

            // if action is down  or move
            if(typeEvent == MotionEvent.ACTION_DOWN || typeEvent == MotionEvent.ACTION_MOVE)
            {
                //add point to doodle model
                doodle.addPoint(x,y);
                graphicsView.postInvalidate();
            }


        }



        return true;
    }

    //method determines if the touch event is inside square
    private boolean insideSquare(int x , int y)
    {
        //get the square location
        squareLocation = graphicsView.getSquareLocation();

        //getting the square coordinates
        int left  = (int)squareLocation[0];
        int top  = (int)squareLocation[1];
        int right  = (int)squareLocation[2];
        int bottom  = (int)squareLocation[3];

        //determine if inside square
        if((x>=left && x<=right) && (y>=top && y<=bottom)) // inside square
            return true;
        else
            return false; //out of square

    }

    //touch handler class
    private class TouchHandler extends GestureDetector.SimpleOnGestureListener
    {
        public boolean onSingleTapConfirmed(MotionEvent event)
        {
            //go to next color of doodleModel
            doodle.nextColor();
            graphicsView.postInvalidate();
            return true;
        }

    }
}