import java.util.InputMismatchException;
import java.util.Scanner;

import com.mysql.cj.protocol.Resultset;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.*;

public class Program{
static Connection conn;
    
    public static void main(String[] args) {
		try (Scanner terimaInput = new Scanner (System.in)) {
            String pilihanUser;
            boolean isLanjutkan = true;
            				
            String url = "jdbc:mysql://localhost:3306/transaksi";

            try{
                Class.forName("com.mysql.cj.jdbc.Driver");
            	conn = DriverManager.getConnection(url,"root","");
            	System.out.println("Class Driver ditemukan");
            	
            	while (isLanjutkan) {
            		System.out.println("\n------------------");
            		System.out.println("Database Transaksi");
            		System.out.println("------------------");
            		System.out.println("1. Lihat Data Transaksi");
            		System.out.println("2. Tambah Data Transaksi");
            		System.out.println("3. Ubah Data Transaksi");
            		System.out.println("4. Hapus Data Transaksi");
            		System.out.println("5. Cari Data Transaksi");
            		
            		System.out.print("\nPilihan anda (1/2/3/4/5): ");
            		pilihanUser = terimaInput.next();
            		
            		switch (pilihanUser) {
            		case "1":
            			lihatdata();
            			break;
            		case "2":
            			tambahdata();
            			break;
            		case "3":
            			ubahdata();
            			break;
            		case "4":
            			hapusdata();
            			break;
            		case "5":
            			caridata();
            			break;
            		default:
            			System.err.println("\nInput anda tidak ditemukan\nSilakan pilih [1-5]");
            		}
            		
            		System.out.print("\nApakah Anda ingin melanjutkan [y/n]? ");
            		pilihanUser = terimaInput.next();
            		isLanjutkan = pilihanUser.equalsIgnoreCase("y");
            	}
            	System.out.println("\nBye.... Selamat Berjumpa Kembali!!!");
            	
            }
            catch(ClassNotFoundException ex) {
            	System.err.println("Driver Error");
            	System.exit(0);
            }
            catch(SQLException e){
            	System.err.println("Tidak berhasil koneksi");
            }
        }
	}
	
	private static void lihatdata() throws SQLException {
		String text1 = "\n===Daftar Seluruh Data Transaksi===";
		System.out.println(text1.toUpperCase());
						
		String sql ="SELECT * FROM transaksi";
		Statement statement = conn.createStatement();
		ResultSet result = statement.executeQuery(sql);
		
		while(result.next()){
			System.out.print("\nNo. Faktur\t: ");
            System.out.print(result.getString("faktur"));
            System.out.print("\nNama Barang\t: ");
            System.out.print(result.getString("nama_brg"));
            System.out.print("\nHarga Barang\t: Rp ");
            System.out.print(result.getInt("harga"));
            System.out.print("\nJumlah Barang\t: Rp ");
            System.out.print(result.getInt("jumlah"));
            System.out.print("\n");
		}
	}
		
	private static void tambahdata() throws SQLException{
		String text2 = "\n===Tambah Data Transaksi===";
		System.out.println(text2.toUpperCase());
		
		try (Scanner terimaInput = new Scanner (System.in)) {
            try {
            
            System.out.print("No. Faktur\t: ");
            String faktur = terimaInput.next();
            System.out.print("Nama Barang\t: ");
            String nama_brg = terimaInput.next();
            System.out.print("Harga Barang\t: ");
            int harga = terimaInput.nextInt();
            System.out.print("Jumlah Barang\t: ");
            int jumlah = terimaInput.nextInt();
            String sql = "INSERT INTO transaksi (faktur, nama_brg, harga, jumlah) VALUES ('"+faktur+"','"+nama_brg+"','"+harga+"','"+jumlah+"')";
            			
            Statement statement = conn.createStatement();
            statement.execute(sql);
            System.out.println("Berhasil input data");

            } catch (SQLException e) {
                System.err.println("Terjadi kesalahan input data");
            } catch (InputMismatchException e) {
            	System.err.println("Inputlah dengan angka saja");
            }
        }
	}
	
	private static void ubahdata() throws SQLException{
		String text3 = "\n===Ubah Data Mahasiswa===";
		System.out.println(text3.toUpperCase());
		
		try (Scanner terimaInput = new Scanner (System.in)) {
            try {
                lihatdata();
                System.out.print("Masukkan No. Faktur yang akan di ubah atau update : ");
                String faktur = terimaInput.nextLine();
                
                String sql = "SELECT * FROM transaksi WHERE faktur = " +faktur;
                
                Statement statement = conn.createStatement();
                ResultSet result = statement.executeQuery(sql);
                
                if(result.next()){
                    
                    System.out.print("Nama Barang ["+result.getString("nama_brg")+"]\t: ");
                    String nama_brg = terimaInput.nextLine();
                    
                    System.out.print("Harga Barang ["+result.getInt("harga")+"]\t: ");
                    Integer harga = terimaInput.nextInt();

                    System.out.print("Jumlah Barang ["+result.getInt("Jumlah")+"]\t: ");
                    Integer jumlah = terimaInput.nextInt();
                       
                    sql = "UPDATE transaksi SET nama_brg='"+nama_brg+"',harga= '"+harga+"',jumlah= '"+jumlah+"' WHERE faktur='"+faktur+"'";
                    //System.out.println(sql);

                    if(statement.executeUpdate(sql) > 0){
                        System.out.println("Berhasil memperbaharui data transaksi (faktur "+faktur+")");
                    }
                }
                statement.close();        
            } catch (SQLException e) {
                System.err.println("Terjadi kesalahan dalam mengedit data");
                System.err.println(e.getMessage());
            }
        } catch (NumberFormatException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
		}
	
	private static void hapusdata() {
		String text4 = "\n===Hapus Data Transaksi===";
		System.out.println(text4.toUpperCase());
		
		try (Scanner terimaInput = new Scanner (System.in)) {
            try{
                lihatdata();
                System.out.print("Ketik No. Faktur Transaksi yang akan Anda Hapus : ");
                String faktur = terimaInput.nextLine();
                
                String sql = "DELETE FROM transaksi WHERE faktur = "+ faktur;
                Statement statement = conn.createStatement();
                //ResultSet result = statement.executeQuery(sql);
                
                if(statement.executeUpdate(sql) > 0){
                    System.out.println("Berhasil menghapus data transaksi (faktur "+faktur+")");
                }
   }catch(SQLException e){
                System.out.println("Terjadi kesalahan dalam menghapus data barang");
                }
        } catch (NumberFormatException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
		}
	
	private static void caridata () throws SQLException {
		String text5 = "\n===Cari Data Transaksi===";
		System.out.println(text5.toUpperCase());
		
		try (Scanner terimaInput = new Scanner (System.in)) {
            System.out.print("Masukkan Nama Barang : ");
            
            String keyword = terimaInput.nextLine();
            Statement statement = conn.createStatement();
            String sql = "SELECT * FROM transaksi WHERE nama_brg LIKE '%"+keyword+"%'";
            ResultSet result = statement.executeQuery(sql); 
                    
            while(result.next()){
            	System.out.print("\nNo. Faktur\t: ");
                System.out.print(result.getString("faktur"));
                System.out.print("\nNama Barang\t: ");
                System.out.print(result.getString("nama_brg"));
                System.out.print("\nHarga Barang\t: ");
                System.out.print(result.getInt("harga"));
                System.out.print("\nJumlah Barang\t: ");
                System.out.print(result.getInt("jumlah"));
                System.out.print("\n");
            }
        }
	}
	
}
