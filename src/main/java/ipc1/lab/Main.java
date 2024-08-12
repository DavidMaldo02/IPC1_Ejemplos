package ipc1.lab;

import java.io.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        File sourceFile = new File("./src/main/resources/test.csv");
        File resultFile = new File("./src/main/resources/result.html");

        try (BufferedReader br = new BufferedReader(new FileReader(sourceFile));
             BufferedWriter bw = new BufferedWriter(new FileWriter(resultFile))
        ) {
            String line;
            ArrayList<String> list = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                list.add(line);
            }

            int matrixSize = list.size();
            int[][] content = new int[matrixSize][matrixSize];
            for (int i = 0; i < matrixSize; i++) {
                String[] numbers = list.get(i).split(",");
                for (int j = 0; j < matrixSize; j++) {
                    content[i][j] = Integer.parseInt(numbers[j]);
                }
            }

            imprimirArreglo(content, matrixSize);

            int[][] sortedContent = new int[matrixSize][matrixSize];

            for (int i = 0; i < matrixSize; i++) {
                int[] tmp = bubbleSort(content[i]);
                for (int j = 0; j < tmp.length; j++) {
                    sortedContent[i][j] = tmp[j];
                }
            }

            System.out.println();

            imprimirArreglo(sortedContent, matrixSize);

            bw.write("""
                    <!DOCTYPE html>
                    <html lang="es">
                    <head>
                    <title>Resultados</title>
                    </head>
                    <body>""");
            bw.write("<h1>Matriz ordenada</h1>");
            bw.write("<table>\n");
            for (int i = 0; i < matrixSize; i++) {
                bw.write("<tr>");
                for (int j = 0; j < matrixSize; j++) {
                    bw.write("<td>" + sortedContent[i][j] + "</td>\n");
                }
                bw.write("</tr>\n");
            }
            bw.write("</table>\n");
            // closing tags
            bw.write("</body>\n</html>");
        } catch (IOException ioException) {
            System.out.println(ioException.getMessage());
        }
    }

    public static int[] bubbleSort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
        return array;
    }

    public static void imprimirArreglo(int[][] array, int matrixSize) {
        for (int i = 0; i < matrixSize; i++) {
            for (int j = 0; j < matrixSize; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }
    }
}