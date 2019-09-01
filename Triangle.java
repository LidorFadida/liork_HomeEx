package com.company;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;

public class Triangle extends Shape implements Writable {
    private int corner1, corner2, corner3;
    @Override
    public void write(OutputStream outputStream) throws IOException {
        byte[] buffer = new byte[4];
        ByteBuffer.wrap(buffer).putInt(this.corner1);
        outputStream.write(buffer);
        ByteBuffer.wrap(buffer).putInt(this.corner2);
        outputStream.write(buffer);
        ByteBuffer.wrap(buffer).putInt(this.corner3);
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
        this.corner1 = data[0];
        this.corner2 = data[1];
        this.corner3 = data[2];
    }

    public Triangle(InputStream inputStream) throws IOException {
        read(inputStream);
    }

    public Triangle() {
    }

    public Triangle(int corner1, int corner2, int corner3) {
        this.corner1 = corner1;
        this.corner2 = corner2;
        this.corner3 = corner3;
    }

    public int getCorner1() {
        return corner1;
    }

    public void setCorner1(int corner1) {
        this.corner1 = corner1;
    }

    public int getCorner2() {
        return corner2;
    }

    public void setCorner2(int corner2) {
        this.corner2 = corner2;
    }

    public int getCorner3() {
        return corner3;
    }

    public void setCorner3(int corner3) {
        this.corner3 = corner3;
    }

    @Override
    public String toString() {
        return "Triangle[corner1:"+corner1+" corner2:"+corner2+" corner3:"+corner3+"]";
    }
}
