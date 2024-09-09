package ipc1.lab.models;

import javax.swing.*;

public class ProcessThread extends Thread {
    Product product;
    JProgressBar progressBar;
    String line;

    public ProcessThread(Product product, JProgressBar progressBar, String line) {
        this.product = product;
        this.progressBar = progressBar;
        this.line = line;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public JProgressBar getProgressBar() {
        return progressBar;
    }

    public void setProgressBar(JProgressBar progressBar) {
        this.progressBar = progressBar;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    @Override
    public void run() {
        System.out.println("Inicio hilo " + this.line);
        int fraction = 100 / product.getProcessTime();
        int progress = 0;
        for (int i = 0; i < product.getProcessTime(); i++) {
            try {
                Thread.sleep(1000);
                progress += fraction;
                progressBar.setValue(progress);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        progressBar.setValue(100);
        System.out.println("Fin hilo de " + this.line);
    }
}
