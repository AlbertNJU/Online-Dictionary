
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;



public class Client {

	
	Client() throws Exception{
		//reg = new theRegister();
		//login = new theLogin();
	}
	 
	public static void main(String[] arges) throws Exception {
		try {
			//Client client = new Client();
			InetAddress addr = InetAddress.getByName(null);
			System.out.println(addr.getHostAddress());
			System.out.println("connection_0");
			Socket sk = new Socket(addr.getHostAddress(), 8888);
			System.out.println("connection_1");
			PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(sk.getOutputStream())), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(sk.getInputStream()));	
			InputStreamReader str=new InputStreamReader(System.in);
                       
			/*char[] a=new char[100];
            str.read(a);
            out.println(new String(a));*/
            //System.out.println("你好，我是服务器。我使用的端口号："+ 8888 +"词abc的百度翻译点赞数为："+ 1);	
           
            //login search
           /* char[] id = new char[10];
            str.read(id);
            String searchUserID = "SEARCH USER " + id.toString() +" LOGIN";
            out.println(searchUserID);
            System.out.println(in.readLine());
            System.out.println(in.readLine());*/
           
            //word search
            char[] word = new char[80];
            str.read(word);
            String searchWord = "SEARCH WORD "+ word.toString();
            out.println(searchWord);
            System.out.println(in.readLine());
           
            //add user
           /* char[] add_id = new char[80];
            char[] add_password = new char[80];
            str.read(add_id);
            str.read(add_password);
            String addUser = "ADD USER "+ add_id.toString() + " " + add_password.toString();
            out.println(addUser);
            System.out.println(in.readLine());
           
            //add word & web
            char[] add_searchWeb = new char[80];
            char[] add_searchWordOnline = new char[80];
            str.read(add_searchWordOnline);
            str.read(add_searchWeb);
            String addSearchWeb = "ADD WORD "+ add_searchWordOnline.toString() + " " + add_searchWeb.toString();
            out.println(addSearchWeb);
            System.out.println(in.readLine());
           
            //delete word & web
            char[] del_searchWeb = new char[80];
            char[] del_searchWordOnline = new char[80];
            str.read(del_searchWordOnline);
            str.read(del_searchWeb);
            String delSearchWeb = "ADD WORD "+ del_searchWordOnline.toString() + " " + del_searchWeb.toString();
            out.println(delSearchWeb);
            System.out.println(in.readLine());*/
           
            
            while(true);
        } catch (Exception e) {
        	 System.out.println(e);
        }
	}
}