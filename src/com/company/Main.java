package com.company;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class Main {
    public static void main(String[] args) {

        OperationWithText text = new OperationWithText();
        String path = "/home/asus/IdeaProjects/Lab_2.2/src/com/company/input.txt";
        String save = "/home/asus/IdeaProjects/Lab_2.2/src/com/company/result.txt";

        int minSymbols = 3;
        int maxSymbols = 5;

        //читаем файл в коллекцию
        List<String> textList = text.readFile(path);

        //выводим начальный текст
        System.out.println("Текст:\n");
        textList.forEach(System.out::println);

        //разделение между текстом
        System.out.println("\n");

        textList = text.deleteBadWordsToCollection(textList, minSymbols, maxSymbols);

        //выводим конечный текст
        System.out.println("Отформатированый текст:\n");
        textList.forEach(System.out::println);

        //сохраняем данные в файл
        text.saveToFile(save, textList);
    }
}

