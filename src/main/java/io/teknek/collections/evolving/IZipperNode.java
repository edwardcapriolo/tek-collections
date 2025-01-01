package io.teknek.collections.evolving;

public interface IZipperNode {
    IZipperNode left();
    IZipperNode right();
    IZipperNode down();
    IZipperNode up();
    Object value();
}
