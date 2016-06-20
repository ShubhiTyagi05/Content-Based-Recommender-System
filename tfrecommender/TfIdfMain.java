package com.infosys.iip.tfrecommender;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Multimap;

public class TfIdfMain
{
    public static void main(String[] args)
    {
        // TODO Auto-generated method stub
        Recommendation r = new Recommendation();
        List<List<String>> listOfSentences = new ArrayList<List<String>>();
        listOfSentences.add(r.processSentence("This is a lovely day"));
        listOfSentences.add(r.processSentence("It has been a lovely evening"));
        listOfSentences.add(r.processSentence("This day has been awful"));
        listOfSentences.add(r.processSentence("The play that featured in the festival yesterday evening was amazing."));
        r.createCorpus(listOfSentences);
       
        //****************************DISPLAYING OUTPUT**********************************//
        Multimap<Double, String> similarSentences = r.findSimilarity(1,
                listOfSentences);
        System.out.println("Similar Sentences in Decreasing Order");
        List<Double> scores =new ArrayList<Double>(similarSentences.keySet());
        Collections.sort(scores);
        Collections.reverse(scores);
        for (Double d : scores)
        {
            System.out.println("Score : " + d
                    + "    Sentence : " + similarSentences.get(d).toString());
        }
        //******************************************************************************//
       
    }
}
