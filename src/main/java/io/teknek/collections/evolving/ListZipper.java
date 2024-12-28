package io.teknek.collections.evolving;

import io.teknek.collections.ElementBasedReadAccess;
import io.teknek.collections.list.MutableArrayList;

public class ListZipper {

}

class Zipper<T> {
    MutableArrayList<T> left;
    MutableArrayList<T> right;
    T focus;
    Zipper(ElementBasedReadAccess<T> list){

        left = new MutableArrayList<>();
        focus = list.elementAt(0);
        //
    }

}
