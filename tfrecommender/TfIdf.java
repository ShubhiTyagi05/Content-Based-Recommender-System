package com.infosys.iip.tfrecommender;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.google.common.base.Splitter;

public class TfIdf
{
    Recommendation r = new Recommendation();

    TfIdf(Recommendation r)
    {
        this.r = r;
    }

    public List<Double> getTfVector(List<String> sentence)
    {
        List<Double> tfVector = new ArrayList<Double>();
        for (String word : r.getAllWords())
        {
            if (sentence.contains(word))
            {
                double tf = this.getTf(word,sentence);
                double idf = this.getIdf(word);
                tfVector.add(tf * idf);
            }
            else
            {
                tfVector.add(0.0);
            }
        }
        //System.out.println(tfVector.toString());
        return tfVector;
    }

    private double getIdf(String word)
    {
        double idf = 0.0;
        double count = 0;
        List<List<String>> listOfSentences = r.getListOfSentences();
        for (List<String> sentence : listOfSentences)
        {
            if (sentence.contains(word))
            {
                count++;
            }
        }
        idf = Math.log((double) listOfSentences.size() / count);
        System.out.println(" IDF :" + idf + " Count : " + count);
        return idf;
    }

    private double getTf(String word,List<String> sentence)
    {
        System.out.println("Sentence : "+sentence.toString());
        double tf = 0.0;
        double frequency = Collections.frequency(sentence, word);
        double size = (double)sentence.size();
        tf = frequency/size;
        System.out.print("Word  : " + word + " TF : " + tf);
        return tf;
    }
}
