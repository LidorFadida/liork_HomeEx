package com.company;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;

public class Circle extends Shape implements Writable{
    private int x, y, radius;
    @Override
    public void write(OutputStream outputStream) throws IOException {
        byte[] buffer = new byte[4];
        ByteBuffer.wrap(buffer).putInt(this.x);
        outputStream.write(buffer);
        ByteBuffer.wrap(buffer).putInt(this.y);
        outputStream.write(buffer);
        ByteBuffer.wrap(buffer).putInt(this.radius);
        outputStream.write(buffer);
    }

    @Override
    public void read(InputStream inputStream) throws IOException {
        byte[] buffer = new byte[4];
        int[] data = new int[3];
        int actuallyRead ;

        for (int i = 0; i < data.length; i++) {
            actuallyRead = inputStream.read(buffer);
            if (actuallyRead!=4)
                throw new  IOException("not enough data to define a circle");
            data[i] = ByteBuffer.wrap(buffer).getInt();
        }
        this.radius = data[0];
        this.x = data[1];
        this.y = data[2];

    }
    public Circle(InputStream inputStream) throws IOException {
        read(inputStream);
    }

    public Circle() {
    }

    public Circle(int x, int y, int radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    @Override
    public String toString() {
        return "Circle[radius:"+radius+" x:"+x+" y:"+y+"]";
    }
}
