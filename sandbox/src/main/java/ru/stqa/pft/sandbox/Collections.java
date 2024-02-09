package ru.stqa.pft.sandbox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Collections {
    public static void main(String[] args) {
        String[] langs = {"JAVA","C#","PYTHON","PHP"};

        List<String> languages = Arrays.asList("JAVA","C#","PYTHON","PHP");

        for (String l : languages)
            System.out.println("Я хочу выучить: " + l);
        }
    }

