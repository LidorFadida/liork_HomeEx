package com.company;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static com.company.ShapeDict.*;

public class Main {

    public static void main(String[] args) {
	// write your code here
        File file = new File("C:\\Users\\liork\\IdeaProjects\\myfile.bin");
        Shape[] shapes = new Shape[4];
        shapes[1] = new Circle(4,5,6);
        shapes[2] = new Rectangle(1,9);
        shapes[0] = new Triangle(6,9,10);
        shapes[3] = new Circle(12,0,4);

        writeShapes(file,shapes);
        readShapes(file);
    }

    public static void readShapes(File file){
        InputStream inputStream = null;
        List <Shape> shapes = new ArrayList<>();
        try {
            inputStream = new FileInputStream(file);
            int preByte;

            while ((preByte = inputStream.read())!=-1){
                ShapeDict shapeDict = ShapeDict.valueOf(preByte);
                switch (shapeDict){
                    case CIRCLE:
                        shapes.add(new Circle(inputStream));
                        break;
                    case RECTANGLE:
                        shapes.add(new Rectangle(inputStream));
                        break;
                    case TRIANGLE:
                        shapes.add(new Triangle(inputStream));
                        break;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        for (Shape shape : shapes) {
            System.out.println(shape);
        }
    }

    public static void writeShapes(File file, Shape[] shapes){
        OutputStream outputStream = null;

        try {
            outputStream = new FileOutputStream(file);
            for (Shape shape : shapes) {
                if (shape instanceof Circle){
                    outputStream.write(CIRCLE.getValue());
                    Circle circle = (Circle) shape;
                    circle.write(outputStream);
                }
                else if (shape instanceof Rectangle){
                    outputStream.write(RECTANGLE.getValue());
                    Rectangle rectangle = (Rectangle) shape;
                    rectangle.write(outputStream);
                }
                else if (shape instanceof Triangle){
                    outputStream.write(TRIANGLE.getValue());
                    Triangle triangle = (Triangle) shape;
                    triangle.write(outputStream);
                }

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
