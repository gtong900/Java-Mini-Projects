package edu.nyu.cs9053.homework8;

import java.util.LinkedList;
import static edu.nyu.cs9053.homework8.ValidTextKeyPress.*;

public class Test {

    public static void main(String[] args){

        TextingDictionary dictionary = new TextingDictionary();
        dictionary.insert("apsddd");
        dictionary.insert("adiknrr");
        dictionary.insert("akP");
        LinkedList<ValidTextKeyPress> keyword = new LinkedList<>();
        keyword.add(Two);
        keyword.add(Three);
        keyword.add(Four);
        keyword.add(Five);
        keyword.add(Six);
        for (String s : dictionary.search(keyword)) {
            System.out.println(s);
        }
        /*
        dictionary.search({Two, Seven, Two})
        dictionary.search({Two, Seven, Four})
        dictionary.search({Two, Eight})
        dictionary.insert("butte")
        dictionary.search({Two, Eight})
        */
    }
}