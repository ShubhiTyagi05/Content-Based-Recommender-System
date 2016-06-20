package com.infosys.iip.tfrecommender;

import java.util.ArrayList;
import java.util.List;

import com.google.common.base.Splitter;

public class CosineSim
{
    List<Double> vector1 = new ArrayList<Double>();
    List<Double> vector2 = new ArrayList<Double>();
    double       sim     = 0.0;

    CosineSim(List<String> s1, List<String> s2 , Recommendation r)
    {
        TfIdf ti = new TfIdf(r);
        vector1 = ti.getTfVector(s1);
        vector2 = ti.getTfVector(s2);
        System.out.println("Vector 1 " + vector1.toString());
        System.out.println("Vector 2 " + vector2.toString());
    }

    public double getCosineSim(List<Double> vector1, List<Double> vector2)
    {
        //System.out.println(" "+vector1.size()+" "+vector2.size());
        for (int i = 0; i < vector1.size(); i++)
        {
            this.sim += vector1.get(i) * vector2.get(i);
        }
        return this.sim;
    }

    /**
     * @return the sim
     */
    public final double getSim()
    {
        getCosineSim(vector1, vector2);
        return sim;
    }
}
