import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.BindException;
import java.net.Socket; //ネットワーク関連のパッケージを利用する
import java.util.Scanner;
public class CalcClient2 {
    public static void main(String arg[]) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("ポートを入力してください(5000など) → ");
            int port = scanner.nextInt();
            System.out.println("localhostの" + port + "番ポートに接続を要求します");
            Socket socket = new Socket("localhost", port);
            System.out.println("接続されました");

            boolean isContinue = true;

        while(isContinue){
            System.out.println("数字を入力してください");
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            String num = scanner.next();
            Number2 number = new Number2();
            number.setExecNumber(Integer.parseInt(num));
            oos.writeObject(number);
            oos.flush();
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            if(Integer.parseInt(num) <= 1){
                System.out.println("1が入力されました。プログラムを終了します。");
                ois.close();
                oos.close();
                socket.close();
                isContinue = false;
                break;
                }
            Number2 serverNum = (Number2) ois.readObject();
            int replayMsg = serverNum.getResult();
            System.out.println(replayMsg);

}

        } // エラーが発生したらエラーメッセージを表示してプログラムを終了する
        catch (BindException be) {
            be.printStackTrace();
            System.err.println("ポート番号が不正か、サーバが起動していません");
            System.err.println("サーバが起動しているか確認してください");
            System.err.println("別のポート番号を指定してください(6000など)");
        } catch (Exception e) {
            System.err.println("エラーが発生したのでプログラムを終了します");
            throw new RuntimeException(e);
        }
        
    }
}
