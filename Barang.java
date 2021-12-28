import java.util.Scanner;
public class Barang implements Penjualan {
    String namaBrg;
    int hargaBrg;

    Scanner inputBrg = new Scanner(System.in);

    public Barang(String namaBrg, int hargaBrg){
        this.namaBrg = namaBrg;
        this.hargaBrg = hargaBrg;
    }

    public void noFaktur(){
    }

    public void jumlah(){

    }

   public void subTotal(){
    }

    public void discount(){
    }

    public void totalHarga(){
    }

    @Override
    public void namaBarang(){
        // TODO Auto-generated method stub
        System.out.println("Nama Barang: ");
        namaBrg = inputBrg.nextLine();
        namaBrg = namaBrg.toLowerCase();
        namaBrg = namaBrg.trim();
    }

    @Override
    public void hargaBarang(){
        // TODO Auto-generated method stub
        System.out.println("Harga Barang: ");
        hargaBrg = inputBrg.nextInt();
    }

    public void TampilInfo() {
    }

}