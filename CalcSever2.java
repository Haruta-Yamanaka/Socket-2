import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
public class CalcSever2 {
    public static void main(String arg[]) {
        try {
            /* 通信の準備をする */
            Scanner scanner = new Scanner(System.in);
            System.out.print("ポートを入力してください(5000など) → ");
            int port = scanner.nextInt();
            scanner.close();
            System.out.println("localhostの" + port + "番ポートで待機します");
            ServerSocket server = new ServerSocket(port); // ポート番号を指定し、クライアントとの接続の準備を行う

            Socket socket = server.accept(); // クライアントからの接続要求を待ち、
            // 要求があればソケットを取得し接続を行う
            System.out.println("接続しました。相手の入力を待っています......");

            boolean isContinue =true;

            while(isContinue){
                System.out.println("数字を受け付けています");
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

            Number2 number = (Number2) ois.readObject();// ここをあとで数字だけをもつシリアライズしたクラスに当てはめる
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            Number2 response = number;
            System.out.println("最大の約数を求めます");
            response.exec();//結果をクライアントに渡す
            System.out.println("計算完了です。クライアントに結果を送信します。");
            oos.writeObject(response);
        
            if(number.getNumber() <= 1){
            // close処理
            isContinue = false;
            ois.close();
            oos.close();
            break;
            }
            }
            // socketの終了。
            socket.close();
            server.close();
            
            

        } // エラーが発生したらエラーメッセージを表示してプログラムを終了する
        catch (BindException be) {
            be.printStackTrace();
            System.out.println("ポート番号が不正、ポートが使用中です");
            System.err.println("別のポート番号を指定してください(6000など)");
        } catch (Exception e) {
            System.err.println("エラーが発生したのでプログラムを終了します");
            throw new RuntimeException(e);
        }
    }
}
