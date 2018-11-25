package com.company;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class Main{
    public static void main(String[] args) {
        String path = "/home/asus/IdeaProjects/Lab_2.2/src/com/company/input.txt";
        String save = "/home/asus/IdeaProjects/Lab_2.2/src/com/company/result.txt";

        int minSymbols = 3;
        int maxSymbols = 5;

        //читаем файл в коллекцию
        List<String> textList = readFile(path);

        //выводим начальный текст
        System.out.println("Текст:\n");
        textList.forEach(System.out::println);

        //разделение между текстом
        System.out.println("\n");

        textList = deleteBadWordsToCollection(textList, minSymbols, maxSymbols);

        //выводим конечный текст
        System.out.println("Отформатированый текст:\n");
        textList.forEach(System.out::println);

        //сохраняем данные в файл
        saveToFile(save, textList);
    }

    /**
     * Сохраняем данные в файл.
     *
     * @param path - путь для создания файла и сохранения в него;
     * @param list - то, что запишем в файл (коллекция)
     */
    public static void saveToFile(String path, List<String> list) {
        try {
            Files.write(Paths.get(path), list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Читаем файл в коллекцию List<String> по пути path.
     *
     * @param path - путь к файлу;
     * @return коллекцию с данными из файла;
     */
    public static List<String> readFile(String path) {
        List<String> list = null;
        try {
            list = Files.readAllLines(Paths.get(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * Метод получает кол-во слов в строке по параметрам.
     *
     * @param line       - строка для поиска слов;
     * @param minSymbols - минимальное кол-во символов в слове включительно;
     * @param maxSymbols - максимальное кол-во символов в слове включительно;
     * @return кол-во слов, удовлетворяющих параметрам;
     */
    public static int getCountBadWords(String line, int minSymbols, int maxSymbols) {
        int result = 0;
        if (line != null) {
            line = line.trim(); //удаляем пробелы в начале и конце строки
            if (line.length() != 0) {
                for (String word : line.split("\\s+")) { //делим строку на массив по пробелам
                    int length = word.length();
                    if (length >= minSymbols && length <= maxSymbols) {
                        result++;
                    }
                }
            }
        }
        return result;
    }

    /**
     * Метод возвращает новую коллекцию, без слов не удовлетворяющих требованию.
     *
     * @param list - коллекция данных для редоктирования;
     * @param minSymbols - минимальное кол-во символов в слове включительно;
     * @param maxSymbols - максимальное кол-во символов в слове включительно;
     * @return новую коллекцию с редактируемым текстом;
     */
    public static List<String> deleteBadWordsToCollection(List<String> list, int minSymbols, int maxSymbols) {
        List<String> resultList = new ArrayList<>();
        if (list != null && !list.isEmpty()) {
            for (String line : list) {
                line = line.trim();
                int countWords = getCountBadWords(line, minSymbols, maxSymbols);
                line = deleteBadWords(line, countWords, minSymbols, maxSymbols);
                resultList.add(line);
            }
        }
        return resultList;
    }

    /**
     * Удаляем из строки "плохие" слова.
     *
     * @param line - строка для удаления в ней слов;
     * @param countBadWords - кол-во для удаления слов;
     * @param minSymbols - минимальное кол-во символов в слове включительно;
     * @param maxSymbols - максимальное кол-во символов в слове включительно;
     * @return строку без "плохих" слов;
     */
    public static String deleteBadWords(String line, int countBadWords, int minSymbols, int maxSymbols) {
        StringBuilder sb = new StringBuilder();
        if (line.length() != 0) {
//            if (countBadWords > 0) {
                if (countBadWords % 2 != 0) {
                    countBadWords--;
                }
                for (String word : line.split(" ")) {
                    if (word.length() != 0) {
                        if ( word.length() >= minSymbols && word.length() <= maxSymbols) {
                            countBadWords--;
                        } else {
                            sb.append(word).append(" ");
                        }
                    } else {
                        sb.append(" ");
                    }
                }
            }


        return sb.length() == 0 ? line : sb.toString();
    }
}