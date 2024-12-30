package io.teknek.collections.evolving;

import io.teknek.collections.ElementBasedReadAccess;
import io.teknek.collections.list.MutableArrayList;

import java.util.Arrays;

public class ListZipper<T> {
    MutableArrayList data;
    int focus;
    int rightSize;
}

class Zipper<T> {

    protected ListZipper listZipper;

    Zipper(T ... t){
        listZipper = new ListZipper();
        listZipper.data = new MutableArrayList();
        Arrays.stream(t).forEach(x -> listZipper.data.add(x));
        listZipper.focus = 0;
        listZipper.rightSize = t.length;
        //
    }

}
