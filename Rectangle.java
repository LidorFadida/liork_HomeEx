package com.company;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;

public class Rectangle extends Shape implements Writable {
    private int x, y;
    @Override
    public void write(OutputStream outputStream) throws IOException {
        byte[] buffer = new byte[4];
        ByteBuffer.wrap(buffer).putInt(this.x);
        outputStream.write(buffer);
        ByteBuffer.wrap(buffer).putInt(this.y);
        outputStream.write(buffer);
    }

    @Override
    public void read(InputStream inputStream) throws IOException {
        byte[] buffer = new byte[4];
        int[] data = new int[2];
        int actuallyRead ;

        for (int i = 0; i < data.length; i++) {
            actuallyRead = inputStream.read(buffer);
            if (actuallyRead!=4)
                throw new  IOException("not enough data to define a circle");
            data[i] = ByteBuffer.wrap(buffer).getInt();
        }
        this.x = data[0];
        this.y = data[1];
    }
    public Rectangle(InputStream inputStream) throws IOException {
        read(inputStream);
    }

    public Rectangle() {
    }

    public Rectangle(int x, int y) {
        this.x = x;
        this.y = y;
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

    @Override
    public String toString() {
        return "Rectangle[x:"+x+" y:"+y+"]";
    }
}
