import java.util.Scanner;
public class Transaksi extends Barang{
    String faktur;
    int jumlahBrg,sub;
    double dis,total;

    Scanner inputTrx = new Scanner(System.in);

    public Transaksi(String namaBrg, int hargaBrg) {
        super(namaBrg, hargaBrg);
    }

    @Override
    public void noFaktur(){
        System.out.println("No. Faktur: ");
        faktur=inputTrx.next();
        faktur=faktur.toUpperCase();
        faktur=faktur.trim();
    }
    
    @Override
    public void jumlah(){
        System.out.println("Jumlah Barang: ");
        jumlahBrg = inputTrx.nextInt();
    }
    
    @Override
    public void subTotal(){
        sub=jumlahBrg*hargaBrg;
        System.out.println("Sub Total Harga: "+sub);
        
    }
    @Override
    public void discount() {
        if(sub>1000000){
            dis=0.20;
        }
        else if(sub>700000&&sub<=1000000){
            dis=0.15;
        }
        else if(sub>500000&&sub<=7000000){
            dis=0.10;
        }
        else if(sub>250000&&sub<=500000){
            dis=0.05;
        }
        else{
            dis=0;
        }
        System.out.println("Discount: "+dis*100+"%");       
    }

    @Override
    public void totalHarga() {
        total=(sub-(sub*dis));
        System.out.println("Total Harga: "+total);    
    }

    @Override
    public void TampilInfo(){
        System.out.println("------------------------------------------------------");
        System.out.println("\nMENAMPILKAN DATA TRANSAKSI");
        System.out.println("No. Faktur : "+faktur);
        System.out.println("Nama Barang : "+namaBrg);
        System.out.println("Harga Barang : "+hargaBrg);
        System.out.println("Jumlah Barang : "+jumlahBrg);
        System.out.println("SubTotal : "+sub);
        System.out.println("Discount : "+100*dis+"%");
        System.out.println("Total Harga : " +total);
        System.out.println("------------------------------------------------------");
    }

}