package model;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * このプログラムで取り扱うラベル名を保持する。
 */
public class LabelInfo {
    public final Set<String> labelList;

    public LabelInfo() {
        labelList = new HashSet<>();
    }

    public void addLabel(String label) {
        labelList.add(label);
    }
}
