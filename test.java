package review3;
import java.util.*;//parent 
class cardetails{
	Scanner k=new Scanner(System.in);
	List<String> cartype=new ArrayList<String>();  
	int a=0;
	public void getcartype() {
		System.out.print("Enter car type: ");
		cartype.add(a,k.next());
		a=a+1;
	}
}
interface searchinfo {
abstract public boolean validate_search(int x,int y);
}
interface carservice{
	final cardetails car=new cardetails();//composition
	void addservice();
	public boolean validate_serv(int x);
	void serinfo();
}
interface productinfo{
	void addproduct();
	void proinfo();
}
class feedback{
	static List<Double> rating=new ArrayList<Double>();
	static Scanner k=new Scanner(System.in);
	static int a=2;
	void rate() {
		rating.add(0.0);
		rating.add(4.0);
		rating.add(3.5);
	}
}
class admin implements carservice,productinfo,searchinfo{
	Scanner k=new Scanner(System.in);
	feedback f;//aggregation
	int service_id,admin_id,product_id;
	public List<String> serinf=new ArrayList<String>(); 
	public List<Integer> sercost=new ArrayList<Integer>(); 
	public List<String> proinf=new ArrayList<String>(); 
	public List<Integer> procost=new ArrayList<Integer>();  
	public List<Integer> pronum=new ArrayList<Integer>();  
	public List<String> timings=new ArrayList<String>(); 
	public List<Integer> maxslot=new ArrayList<Integer>();
	int i=0,a,b;
	public admin() { //for assigning admin_id,car service,car product
		admin_id=i+1;
		i=i+1;
		serinf.add("Car repair");
		serinf.add("Car wash");
		serinf.add("Tyres air fill");
		serinf.add("suspension and steering");
		sercost.add(2000);
		sercost.add(500);
		sercost.add(200);
		sercost.add(3500);
		proinf.add("tyres");
		proinf.add("air bags");
		proinf.add("engine oil");
		pronum.add(10);
		pronum.add(5);
		pronum.add(20);
		procost.add(500);
		procost.add(1000);
		procost.add(300);
		timings.add("10:00 am");
		timings.add("11:00 am");
		timings.add("12:00 pm");
		timings.add("04:00 pm");
		maxslot.add(10);
		maxslot.add(5);
		maxslot.add(2);
		maxslot.add(10);
	}
	public boolean verifylogin(int sampid,String samppass) { //verify admin login id
		if(sampid==1 && samppass.equals("Pass@123")) {
			return true;
		}
		else {
			return false;
		}
	}
	public String getpass() {  // getting admin password for login 
		System.out.print("Enter password: ");
		String pass=k.next();
		return pass;
	}
	public int getid() { // getting admin id for login 
		System.out.print("Enter id: ");
		int id=k.nextInt();
		return id;
	}
	public void addservice() { // to add new services by admin
		System.out.print("Enter new service: ");
		String samp=k.next();
		System.out.print("Enter cost of the service: ");
		int x=k.nextInt();
		serinf.add(samp);
		sercost.add(x);
	}
	public void serinfo() { // to display all services 
		for(int i=0;i<serinf.size();i++) {
			if(!serinf.get(i).equals(null)) {
			System.out.print((i+1)+":"+serinf.get(i)+" \tCost: "+sercost.get(i)+" \n");
			}
		}
	}
	public void addproduct() { // to add new products by admin
		System.out.print("Enter new product: ");
		String samp=k.next();
		proinf.add(samp);
		System.out.print("Enter number of products: ");
		int x=k.nextInt();
		pronum.add(x);
		System.out.print("Enter cost of product: ");
		int y=k.nextInt();
		procost.add(y);
		b=b+1;
	}
	public boolean updatenum(int pid) {
		boolean flag=false;
		for(int i=0;i<proinf.size();i++) {
			if(pid==(i+1)) {
				System.out.print("Enter number of products: ");
				int n=pronum.get(i);
				pronum.set(i,k.nextInt()+n);
				flag=true;
			}
		}
		return flag;
	}
	public void addtim() {
		System.out.print("Enter new timings: ");
		timings.add(k.next());
		System.out.print("Enter maximum slots of new timings: ");
		maxslot.add(k.nextInt());
		System.out.print("New timings added successfully..! \n");
	}
	public boolean updateslot(int tid) {
		boolean flag=false;
		for(int i=0;i<timings.size();i++) {
			if(tid==(i+1)) {
				System.out.print("Enter maximum slots: ");
				int n=maxslot.get(i);
				maxslot.set(i,k.nextInt()+n);
				flag=true;
			}
		}
		return flag;
	}
	public void proinfo() { // to display all products
		for(int i=0;i<proinf.size();i++) {
				f=new feedback();
				f.rate();
			System.out.print((i+1)+":"+proinf.get(i)+" \tNumber of products: "+pronum.get(i)+
					" \tCost of each product: "+procost.get(i)+" \tRating: "+f.rating.get(i)+" \n");
		}
	}
	public boolean validate_search(int x,int y) {
		int i;
		boolean z=false;
		for(i=0;i<pronum.size();i++) {
			if((x-1)==i && y<=pronum.get(i) && !pronum.get(i).equals(null)) {
				System.out.print("Item added to cart.!!! \n");
				int sa=pronum.get(i)-y;
				pronum.add(i,sa);
				z=true;
				break;
			}
		}
		if(z) {
		return z;}
		else {
			System.out.print("Unable to add items to cart due to following errors : \n1-Item not found \n"+
		"2-Number of items exceeded than items in stock. \n");
		}
		return z;
		
	}
	public void discar(List<Integer> p,List<Integer> n,List<Integer> cs,List<Integer> ctim) {
		int flag=0;
		if(!p.isEmpty()) {
		for(int i=0;i<proinf.size();i++) {
			for(int j=0;j<p.size();j++) {
				if(p.get(j)==(i+1)) {
					System.out.print(p.get(j)+":"+proinf.get(i)+" \tNumber of items:"+n.get(j)+" \n");
					flag=1;
				}
			}
		}}
		if(!cs.isEmpty()) {
		for(int i=0;i<serinf.size();i++) {
			for(int j=0;j<cs.size();j++) {
				if((i+1)==cs.get(j)) {
					System.out.print("Car service : "+serinf.get(i));
					flag=1;
				}
			}
		}}
		if(!ctim.isEmpty()) {
			for(int i=0;i<maxslot.size();i++) {
				for(int j=0;j<ctim.size();j++) {
					if((i+1)==ctim.get(j)) {
						System.out.print(" \tTimings: "+timings.get(i)+" \n");
						flag=1;
					}
				}
			}
		}
		if(flag==0) {
			System.out.print("Sorry.., your cart is empty :( \n");
		}
	}
	public boolean validate_serv(int x) {
		boolean z=false;
		for(int i=0;i<serinf.size();i++) {
			if(x==(i+1) && !serinf.get(i).equals(null)) {
				System.out.print("Car service has been selected \n");
				z=true;
				break;
			}
		}
		if(z) {
			return z;}
			else {
				System.out.print("Unable to add items to cart due to following errors : \n1-Item not found \n"+
			"2-Number of items exceeded than items in stock. \n");
			}
			return z;
	}
	public boolean removprod(int pr) {
		boolean flag=false;
		for(int i=0;i<proinf.size();i++) {
			if(pr==(i+1)) {
				proinf.remove(i);
				pronum.remove(i);
				procost.remove(i);
				f.rating.remove(i);
				flag=true;
			}
		}
		if(flag) {
			return true;
		}else {
		return false;}
	}
	public void timdis(){
		for(int i=0;i<timings.size();i++) {
			System.out.print((i+1)+"."+timings.get(i)+" \tSlot remaining: "+maxslot.get(i)+" \n");
		}
	}
	public boolean timevalid(int tid) {
		boolean flag=false;
		for(int i=0;i<timings.size();i++) {
			if(tid==(i+1)) {
				if(maxslot.get(i)!=0) {
					System.out.print("Slot selected..! :) \n");
					int y=maxslot.get(i);
					maxslot.set(i, y-1);
					flag=true;
				}
				else {
					System.out.print("Sorry slots are full try another slot..! :( \n");
				}
			}
		}
		return flag;
	}
}
class query{
	void display() {
		System.out.print("What auto repair services do you offer? \nWe cover all car repair and maintenance work outside of a shop. Everything from a simple oil and filter change \nto replacing brake pads and rotors, timing belts, catalytic converters and diagnosing and repairing most ignition, \nelectrical and exhaust problems can be done when and where it's most convenient for you.");
	}
}
public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		admin a=new admin();
		query q=new query();
		List <payment> pa=new ArrayList <payment>();
		Scanner k=new Scanner(System.in);
		List <customer> c=new ArrayList <customer>();
		int ch1,ch2,stu=0;
		boolean exit1=false,exit2=false;
		do {
			System.out.print("Enter 1-admin \n2-customer \n3-exit");
			ch1=k.nextInt();
			if(ch1==1) {
				exit1=false;
				int id=a.getid();
				String pass=a.getpass();
				if(a.verifylogin(id,pass)) {
					System.out.print("Login successfull!!"+" \n");
					do {
						System.out.println("\n\n\t\t======================CAR PARTS & SERVICES======================\n");
						System.out.println("\t\t*                1.to add service     2.to add product        *\n");
						System.out.println("\t\t*                3.Check customers       4.remove product     *\n");
						System.out.println("\t\t*           5.update number of products    6.update slots     *\n");
						System.out.println("\t\t*               7.add timings                  8.Logout       *\n");
						System.out.println("\t\t===============================================================\n");
						ch2=k.nextInt();
						if(ch2==1) {
							a.addservice();
							a.serinfo();
						}
						else if(ch2==2) {
							a.addproduct();
							a.proinfo();
						}
						else if(ch2==3) {
							if(c.isEmpty()) {
								System.out.print("Sorry there are no customers registered..! :( \n");
							}else {
								System.out.print("Number of customers: "+c.size()+" \n");
							for(int i=0;i<c.size();i++) {
								System.out.print("Customer "+(i+1)+" \n");
								c.get(i).disprof(i);
								System.out.print(" \n");
							}}
						}
						else if(ch2==4) {
							a.proinfo();
							System.out.print("Enter product id: ");
							int np=k.nextInt();
							if(a.removprod(np)) {
								System.out.print("Product removed successfully..! \n");
							}
							else {
								System.out.print("Sorry product could not be removed..! \n");
							}
						}
						else if(ch2==5) {
							a.proinfo();
							System.out.print("Enter product id: ");
							if(a.updatenum(k.nextInt())) {
								System.out.print("Number of products updated successfully..! \n");
								a.proinfo();
							}
							else {
								System.out.print("Sorry number of products could not be updated \n");
							}
						}
						else if(ch2==6) {
							a.timdis();
							System.out.print("Enter timings id: ");
							if(a.updateslot(k.nextInt())) {
								System.out.print("Maximum slots updated successfully..! \n");
								a.timdis();
							}
							else {
								System.out.print("Sorry maximum slots could not be updated \n");
							}
						}
						else if(ch2==7) {
							a.addtim();
							a.timdis();
						}
						else {
							exit1=true;
						}
					}while(!exit1);
				}
				else {
					System.out.print("Invalid login");
					exit2=true;
				}
			}
			else if(ch1==2) {
				customer cu=new customer();
				c.add(cu);
				payment pay=new payment();
				pa.add(pay);
				exit1=false;
				System.out.print("Enter id: ");
				int id=k.nextInt();
				String cpass=c.get(id-1).cgetpass();
				if(c.get(id-1).verifycust(id,cpass)) {
					System.out.print("Login successfull!!"+" \n");
					do {
						System.out.println("\n\n\t\t======================CAR PARTS & SERVICES=====================\n");
						System.out.println("\t\t*                1.buy product    2.car service               *\n");
						System.out.println("\t\t*                3.display cart  4.display profile            *\n");
						System.out.println("\t\t*                5.Checkout        6.Query                  *\n");
						System.out.println("\t\t*                             7.Logout                        *\n");
						System.out.println("\t\t===============================================================\n");
						ch2=k.nextInt();
						if(ch2==1) {
							a.proinfo();
							int x=c.get(id-1).getprod();
							int y=c.get(id-1).getnump();
							if(a.validate_search(x,y)) {
								c.get(id-1).setval(x,y);//overloading
							}
						}
						else if(ch2==2) {
							boolean flagt=false;
							a.serinfo();
							int x=c.get(id-1).getserv();
							if(a.validate_serv(x)) {
								a.car.getcartype();
								do {
									a.timdis();
								System.out.print("Enter timings: ");
								int t=k.nextInt();
									if(a.timevalid(t)) {
										flagt=true;
										c.get(id-1).setval(x);//overloading
										c.get(id-1).timadd(t);
									}
								}while(!flagt);
							}
						}
						else if(ch2==3) {
							List<Integer> p=c.get(id-1).dispro();  
							List<Integer> n=c.get(id-1).disnpro(); 
							List<Integer> cs=c.get(id-1).discarser();
							List<Integer> ctim=c.get(id-1).distime();
							a.discar(p,n,cs,ctim);
						}
						else if(ch2==4) {
							c.get(id-1).disprof(id-1);
						}
						else if(ch2==5) {
							if(pa.get(id-1).calcost(a.sercost,a.procost,a.proinf,a.serinf,c.get(id-1).prod,c.get(id-1).num,c.get(id-1).serc,c.get(id-1).tim,
									a.timings))
									{
								c.get(id-1).clearl();
							}
						}
						else if(ch2==6) {
							q.display();
						}
						else {
							exit1=true;
						}
					}while(!exit1);
					stu=stu+1;
				}
				else {
					System.out.print("Invalid login");
					exit2=true;
				}
			}
			else {
				exit2=true;
			}
		}while(!exit2);

	}

}
