package com.infosys.iip.tfrecommender;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

public class Recommendation
{
    List<List<String>> listOfSentences = new ArrayList<List<String>>();
    List<String>       allWords        = new ArrayList<String>();
   // List<String>       totalWords      = new ArrayList<String>();
    double             score           = 0.0;
    String             id              = null;

    public void createCorpus(List<List<String>> listOfSentences)
    {
        this.listOfSentences = listOfSentences;
        for (List<String> sentence : listOfSentences)
        {
            for (String word : sentence)
            {
                if (!(allWords.contains(word)))
                {
                    allWords.add(word);
                }
     //           totalWords.add(word);
            }
        }
        System.out.println("Lsi Of Sentences : "+listOfSentences.toString());
        System.out.println("Corpus Created  \n Word Vector : "
                + allWords.toString());
       // System.out.println("Total Words : " + totalWords.toString());
    }

    public List<String> processSentence(String sentence)
    {
        List<String> words = new ArrayList<String>();
        sentence = sentence.toLowerCase().replace(",", " ").replace(".", " ")
                .replace("!", " ").replace("?", " ").replace(";", " ");
        words = Splitter.on(" ").omitEmptyStrings().trimResults()
                .splitToList(sentence);
        return words;
    }

    public Multimap<Double, String> findSimilarity(int index,
            List<List<String>> listOfSentences)
    {
        Multimap<Double, String> similarSentences =ArrayListMultimap.create();
        for (int i = 0; i < listOfSentences.size(); i++)
        {
            if (i != index)
            {
                CosineSim cs = new CosineSim(listOfSentences.get(index),
                        listOfSentences.get(i), this);
                similarSentences.put(cs.getSim() , Joiner.on(" ")
                        .join(listOfSentences.get(i)));
            }
        }
        return similarSentences;
    }

    /**
     * @return the allWords
     */
    public final List<String> getAllWords()
    {
        return allWords;
    }

    /**
     * @return the totalWords
     */
/*    public final List<String> getTotalWords()
    {
        return totalWords;
    }*/

    /**
     * @return the listOfSentences
     */
    public final List<List<String>> getListOfSentences()
    {
        return listOfSentences;
    }
}
