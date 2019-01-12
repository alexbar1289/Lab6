package sample;

import java.util.HashMap;
import java.util.Map;

public class Matrix {
    Map<Integer, String> education(Map<Integer, Double> mx1, Map<Integer, Double> mx2)
    {
        HashMap<Integer, String> mx3 = new HashMap<Integer, String>();
        for (int i = 0; i < (mx1.size() | mx2.size()); i++){
            double mx1_el = mx1.get(i);
            double mx2_el = mx2.get(i);
            double mx3_el = mx1_el + mx2_el;
            mx3.put(i, String.valueOf(mx3_el));
        }
        return mx3;
    }
}
