package com.example.encryptionapp.services;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

public class DataService {
    public static final String[] AES_MODES = {"ECB", "CBC", "CFB", "OFB", "CTR"};

    public static final String[] AES_ALGORITHMS = {
            "AES/ECB/PKCS5Padding",
            "AES/CBC/PKCS5Padding",
            "AES/CFB/PKCS5Padding",
            "AES/OFB/PKCS5Padding",
            "AES/CTR/PKCS5Padding"
    };

    public static final int[] AES_KEYS_LENGTH = {
            128,
            192,
            256
    };

    public enum CipherMode {
        AES,
        RSA
    }

    public static void writer(long[] data, String filename) throws IOException {
        var outputWriter = new BufferedWriter(new FileWriter(filename));

        for (int i = 1; i < data.length; i++) {
            outputWriter.write(Integer.toString(i));
            outputWriter.write(" ");
            outputWriter.write(Long.toString(data[i]));
            outputWriter.newLine();
        }
        outputWriter.newLine();

        var newData = new long[data.length - 1];
        System.arraycopy(data, 1, newData, 0, data.length - 1);
        var mean = DataService.mean(newData);

        outputWriter.write(Double.toString(mean));
        outputWriter.write(" ");
        outputWriter.write(Double.toString(DataService.variance(mean, newData)));
        outputWriter.write(" ");
        outputWriter.write(Double.toString(DataService.standardDeviation(mean, newData)));

        outputWriter.flush();
        outputWriter.close();
    }

    public static double mean(long[] data) {
        long sum = 0;
        for (long datum : data) {
            sum += datum;
        }

        return Math.round(sum / data.length);
    }

    public static double variance(double mean, long[] data) {
        long standardDeviation = 0;
        for (long datum : data) {
            standardDeviation += Math.pow(datum - mean, 2);
        }

        return Math.round(standardDeviation / data.length);
    }

    public static double standardDeviation(double mean, long[] data) {
        long standardDeviation = 0;
        for (long datum : data) {
            standardDeviation += Math.pow(datum - mean, 2);
        }

        return Math.round(Math.sqrt(standardDeviation / data.length));
    }

    public static int[] changeStringToASCII(String message) {
        char[] messageCharArray = message.toCharArray();
        int[] messageIntArray = new int[messageCharArray.length];
        for (int i = 0; i < messageCharArray.length; i++) {
            messageIntArray[i] = messageCharArray[i];
        }
        System.out.println(messageIntArray.length);
        return messageIntArray;
    }

    public static void calculateHistogram(int[] intArray, String filename) throws IOException {
        int min = Arrays.stream(intArray).min().getAsInt();
        int max = Arrays.stream(intArray).max().getAsInt();

        var outputWriter = new BufferedWriter(new FileWriter(filename));

        for (int i = min; i <= max; i++) {
            var count = 0;
            for (int j = 0; j < intArray.length; j++) {
                if (i == intArray[j]) {
                    count++;
                }
            }
            outputWriter.write(i + " " + count);
            outputWriter.newLine();
        }

        outputWriter.flush();
        outputWriter.close();
    }

    public static String generateRandomString(int stringLength) {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(stringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        return generatedString;
    }

}