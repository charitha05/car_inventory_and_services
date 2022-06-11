package review3;
import java.util.*;
class cartinfo{
	Scanner k=new Scanner(System.in);
	public List<Integer> prod=new ArrayList<Integer>(); 
	public List<Integer> num=new ArrayList<Integer>(); 
	public List<Integer> serc=new ArrayList<Integer>();
	public List<Integer> tim=new ArrayList<Integer>();
	int cust=0;
	public cartinfo() {
		cust++;
	}
	public int getprod() {
		System.out.print("Enter product number: ");
		int x=k.nextInt();
		return x;
	}
	public int getserv() {
		System.out.print("Enter car service number: ");
		int x=k.nextInt();
		return x;
	}
	public int getnump() {
		System.out.print("Enter number of products: ");
		int x=k.nextInt();
		return x;
	}
	public void setval(int x,int y) {//overloading
		int flag=0;
		for(int n=0;n<prod.size();n++) { // to update number of products if same product customer buys again
			if(x==prod.get(n)) {
				int z=num.get(n);
				num.add(n,y+z);
				flag=1;
				break;
			}
		}
		if(flag==0) {
		prod.add(x);
		num.add(y);}
	}
	public void setval(int x) {//overloading
	    serc.add(x); 
	}
	public void timadd(int t) {
	    tim.add(t);}
	public List<Integer> dispro() {
		return prod;
	}
	public List<Integer> disnpro() {
		return num;
	}
	public List<Integer> discarser() {
		return serc;
	}
	public List<Integer> distime() {
		return tim;
	}
	public void clearl() {
			prod.clear();
			num.clear();
			serc.clear();
			tim.clear();
	}
}
class customer extends cartinfo {
	Scanner k=new Scanner(System.in);
	String[] name=new String[10];
	int custid;
	long[] phone=new long[10];
	int i=0;
	public customer() {
		custid=i+1;
		i=i+1;
	}
	public void disprof(int j) {
		System.out.print("Name: "+name[j]+" \nPhone number: "+phone[j]+" \n");
	}
	public boolean verifycust(int id,String passc) {
		int flag=0,flag1=0;
		long n;
		String s="pass@12"+id;
		try {
		for(int i=0;i<10;i++) {
		if(id==(i+1) && passc.equals(s)) {
			flag=1;
			if(name[i]==null) {
				//validate name if invalid (length<4) raise an exception
				do {
					flag1=0;
					System.out.print("Enter name: ");
					name[i]=k.next();
					if(name[i].length()>=4) {
						flag1=1;
					}
				}while(flag1==0);
				//validate phone number if invalid number(length!=10 and starts with other than 9,8,7) raise an exception
				do {
					flag1=0;
					System.out.print("Enter phone number: ");
					n=k.nextLong();
					String str = Long.toString(n);
					if(str.length()==10 && (str.charAt(0)==55 || str.charAt(0)==56 || str.charAt(0)==57)) {
						phone[i]=n;
						flag1=1;
					}
					else {
						System.out.print("Phone number length error \n");
						throw new err();
					}
				}while(flag1==0);
			}
			break;
		}
		}}
		catch(err e) {
			
		}
		if(flag==1) {
			return true;
		}
		else {
			return false;
		}
	}
	public String cgetpass() {
		System.out.print("Enter password: ");
		String pass=k.next();
		return pass;
	}
	public int cgetid() {
		System.out.print("Enter id: ");
		int id=k.nextInt();
		return id;
	}
}
class err extends Exception{
	public err() {
		System.out.print("Invalid details \n");
	}
}
class payment extends billing{
	Scanner k=new Scanner(System.in);
	card ca=new card();
	upi ui=new upi();
	int npayment=0;
	public boolean pay=false;
	public payment() {
		npayment=npayment+1;
	}
	boolean calcost(List<Integer> ccost,List<Integer> pcost,List<String> nprod,List<String> ncar,List<Integer> prods,List<Integer> nums,
			List<Integer> sercs,List<Integer> tim,List<String> timings) {
		double cost=0,scost=0,tcost=0;
		int flag=0,flag1=0;
		if(!prods.isEmpty()) { //!serc.isEmpty()
				for(int i=0;i<nprod.size();i++) {
					for(int j=0;j<prods.size();j++) {
						if(prods.get(j)==(i+1)) {
							cost+=nums.get(j)*pcost.get(i);
							flag=1;
						}
					}
					}
				System.out.print("Total cost of products: "+cost+" \n");
		}
		if(!sercs.isEmpty()) {
			for(int i=0;i<ncar.size();i++) {
				for(int j=0;j<sercs.size();j++) {
					if(sercs.get(j)==(i+1)) {
						scost+=ccost.get(i);
						flag=1;
					}
				}
				}
			System.out.print("Total cost of car service: "+scost+" \n");
		}
		if(flag==1) {
			tcost=cost+scost;
			System.out.print("Total cost: "+tcost+" \n");
			tcost-=tcost*discount.discal()/100.00;
			System.out.print("Total cost after dicount: "+tcost+" \n");
			System.out.print("Select mode of payment \n1-Card \n2-Upi \n");
			int ch=k.nextInt();
			if(ch==1) {
				long cardnum;
				int cvv,emonth,eyear,otp;
				System.out.print("Enter card number: ");
				cardnum=k.nextLong();
				System.out.print("Enter CVV: ");
				cvv=k.nextInt();
				System.out.print("Enter expiry month: ");
				emonth=k.nextInt();
				System.out.print("Enter expiry year: ");
				eyear=k.nextInt();
				System.out.print("Enter otp: ");
				otp=k.nextInt();
				if(ca.card_validate(cardnum, cvv, emonth, eyear, otp)) {
					System.out.print("\n\n\t\t================BILL================ \n");
				billdisplay(ccost,pcost,nprod,ncar,prods,nums,sercs,tim,timings);
				flag1=1;
				}
			}
			else if(ch==2) {
				int upass;
				 String uid;
				System.out.print("Enter UPI id: ");
				uid=k.next();
				System.out.print("Enter UPI password: ");
				upass=k.nextInt();
				if(ui.upi_validate(uid, upass)) {
					System.out.print("\n\n\t\t================BILL================ \n");
					billdisplay(ccost,pcost,nprod,ncar,prods,nums,sercs,tim,timings);
					flag1=1;
				}
			}
			else {
				System.out.print("Invalid choice payment failed..! :( \n");
			}
		}
		else {
			System.out.print("No items in cart to buy :( \n");
		}
		return flag1==1;
	}
}
class card{
	boolean card_validate(long cardnum,int cvv,int emonth,int eyear,int otp){
		if(cardnum==90123456 && cvv==101 && emonth==10 && eyear==2025 && otp==9754) {
			System.out.print("Payement successfull..!! :) \n");
			return true;
		}
		else {
			System.out.print("Payment unsuccessfull due to incorrect card number,cvv,expiry month,expiry year,otp \n");
			return false;
		}
	}
}
class upi{
	boolean upi_validate(String uid,int upass) {
		if(uid.equals("12345@ybl") && upass==1234) {
			System.out.print("Payement successfull..!! :) \n");
			return true;
		}
		else {
			System.out.print("Payment unsuccessfull due to incorrect UPI id,UPI password \n");
			return false;
		}
	}
}
class discount{
	static float discal() {
		float dis=30;
		return dis;
	}
}
class billing{
	void billdisplay(List<Integer> ccost,List<Integer> pcost,List<String> nprod,List<String> ncar,List<Integer> prods,List<Integer> nums,
			List<Integer> sercs,List<Integer> tim,List<String> timings) {
		if(!prods.isEmpty()) { //!serc.isEmpty()
			for(int i=0;i<nprod.size();i++) {
				for(int j=0;j<prods.size();j++) {
					if(prods.get(j)==(i+1)) {
						System.out.print("\t\tProducts: \n\n"+nprod.get(i)+" \tNumber of products:"+nums.get(j)+
								" \tCost of each item: "+pcost.get(i)+" \nDate of purchase: 12/01/2021 \nDate of delivery: 20/01/2021 \n\n");
					}
				}
				}
	}
	if(!sercs.isEmpty()) {
		for(int i=0;i<ncar.size();i++) {
			for(int j=0;j<sercs.size();j++) {
				if(sercs.get(j)==(i+1)) {
					System.out.print("Car service: \n"+ncar.get(i)+" \tCost of service: "+ccost.get(i));
				}
			}
			}
	}
	if(!tim.isEmpty()) {
		for(int i=0;i<timings.size();i++) {
			for(int j=0;j<tim.size();j++) {
				if(tim.get(j)==(i+1)) {
					System.out.print(" \tTimings: "+timings.get(i)+" \n");
				}
			}
			}
	}
	}
}