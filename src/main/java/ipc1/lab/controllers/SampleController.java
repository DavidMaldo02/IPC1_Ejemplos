package ipc1.lab.controllers;

import ipc1.lab.models.Sample;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class SampleController {
    public static ArrayList<Sample> samples = new ArrayList<>();

    public static void loadSamples(String path) {
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            ArrayList<String> list = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                list.add(line);
            }
            for (int i = 1; i < list.size(); i++) {
                String[] infoMuestras = list.get(i).split(",");
                // Transformar la muestra a un arreglo de enteros 1; 0; 0; 1
                String[] muestra = infoMuestras[2].split(";");
                int longitudMatriz = (int) Math.sqrt(muestra.length);
                int[][] matrizMuestra = new int[longitudMatriz][longitudMatriz];
                for (int j = 0; j < longitudMatriz; j++) {
                    for (int k = 0; k < longitudMatriz; k++) {
                        matrizMuestra[j][k] = Integer.parseInt(muestra[j * longitudMatriz + k]);
                    }
                }
                Sample sample = new Sample(infoMuestras[0], infoMuestras[1], matrizMuestra);
                samples.add(sample);
            }
        } catch (IOException ioException) {
            System.out.println(ioException.getMessage());
        } finally {
            for (Sample sample : samples)
                System.out.println(sample);
        }
    }
}
