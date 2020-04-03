/*
Autobazar

Hlavny program
 - vytvorte a naplnte zoznam aut v autobazare.
 - privitajte zakaznike a ponuknite mu dostupne auta
 - pytajte sa zakaznika ktore auto chce, az kym si nezvoli platnu moznost
 - ked si zakaznik auto vyberie, opytajte sa ho na zmenu farby
	- ak chce zmenit, opytajte sa na aku a farbu zmente
	- ak nie, pokrecujte dalej
 - autobazar ma koleso stastia, kde moze zakaznik vyhrat zlavu na auto
	- ak vyhra, vypise sa mu hlaska a uplatni zlava
	- ak nevyhra, vypise na mu hlaska
 - na zaver vypise sumarum objednavky
 
Na informacie o vozidlach pouzite tvorbu vlastnej triedy polu s konstruktorom a funkciami.
Vytvorte triedu s nazvom Vozidlo.
Bude mat clenske premenne:
 - znacka
 - rocnik
 - farba
 - pocet najazdenych kilometrov
 - cena
Pri tejto triede uvazujte clenske funkcie:
 - zmena farby vozidla
 - zmena ceny vozisla podla zlavy
*/

import java.util.Scanner;

public class Main
{

	public static void main(String[] args)
	{
		Scanner konzolovyVstup = new Scanner(System.in);
		Vozidlo[] zoznam = vytvorZoznam(); // vytvor zoznam
		
		System.out.println("Vitajte v autobazare!");
      	System.out.println("Vyberte si jedno z nasej ponuky:");
		
		citajZoznam(zoznam); // precitaj zoznam
		
		// zakaznik vybera moznost az kym nezada jednu z dostupnych
		int moznost = -1;
		do
		{
			System.out.print("Vyberte si vozidlo zadanim poradoveho cisla: ");
			moznost = konzolovyVstup.nextInt();
			moznost--; // upravenie moznosti na index
		} while(moznost < 0 || moznost >= 5); // porovnanie moznych indexov, t.j. 0, 1, 2, 3, 4 = 5 vozidiel
		
		Vozidlo zvoleneVozidlo = zoznam[moznost];
		konzolovyVstup.nextLine();
		
		// zmena farby
		System.out.print("Chcete zmenit farbu? (y/n): ");
		char farbaMoznost = konzolovyVstup.nextLine().charAt(0);
		if(farbaMoznost == 'y')
		{
			System.out.print("Napiste novu farbu vozidla: ");
			String novaFarba = konzolovyVstup.nextLine();
			zvoleneVozidlo.zmenFarbu(novaFarba);
		}
		
		// koleso stastia
		System.out.println("\nTocime kolesom stastia...");
		double zlava = vytocZlavu();
		if(zlava > 0)
		{
			System.out.println("Gratulujeme ziskali ste zlavu " + zlava + "%!\n");
			zvoleneVozidlo.zlacniVozidlo(zlava);
		}
		else
			System.out.println("Lutujeme, zlavu ste neziskali :(\n");
		
		System.out.println("Gratulujeme Vam k novemu vozidlu!");
		infoVozidlo(zvoleneVozidlo);
	}
	
	// vytvor zoznam vozidiel
	public static Vozidlo[] vytvorZoznam()
	{
		Vozidlo[] zoznam = new Vozidlo[5]; // vytvor zoznam 5 vozidiel
		
		zoznam[0] = new Vozidlo("Peugeot 3008 ALLURE", 2019, "Biela metalíza", 3, 26590); // napln zoznam vozidlom c.1
		zoznam[1] = new Vozidlo("Škoda Superb Combi", 2015, "Hnedá sv. metalíza", 123536, 12990); // napln zoznam vozidlom c.1
		zoznam[2] = new Vozidlo("Subaru Outback", 2016, "Modrá metalíza", 41747, 21500); // napln zoznam vozidlom c.1
		zoznam[3] = new Vozidlo("Volkswagen Passat", 2012, "Biela", 318000, 7570); // napln zoznam vozidlom c.1
		zoznam[4] = new Vozidlo("Nissan Qashqai", 2016, "Biela metalíza", 16614, 16490); // napln zoznam vozidlom c.1
		
		return zoznam;
	}
	
	// precitaj zoznam vozidiel
	public static void citajZoznam(Vozidlo[] zoznam)
	{
		for(int i = 0; i < zoznam.length; i++)
		{
			System.out.print((i+1) + ". ");
			infoVozidlo(zoznam[i]);
			System.out.print("\n");
		}
	}
	
	// precitaj info o vozidle
	public static void infoVozidlo(Vozidlo vozidlo)
	{
		System.out.println(vozidlo.getZnacka());
		System.out.println("rocnik: " + vozidlo.getRocnik());
		System.out.println("farba: " + vozidlo.getFarba());
		System.out.println("najzdene kilometre: " + vozidlo.getNajazdeneKM());
		System.out.println("cena: " + vozidlo.getCena() + "€");
	}
	
	// vytoc zlavu
	public static double vytocZlavu()
	{
		int random = (int) (Math.random() * 100);
		
		if(random > 95)
			return 30;
		else if(random > 90)
			return 25;
		else if(random > 80)
			return 20;
		else if(random > 70)
			return 15;
		else if(random > 55)
			return 10;
		else if(random > 40)
			return 5;
		else
			return 0;
	}
}

class Vozidlo
{
	// deklaracia clenskych premennych (vlastnosti)
	private static String znacka;
	private static int rocnik;
	private static String farba;
	private static int najazdeneKM;
	private static double cena;
	
	// konstruktor na vytvorenie noveho objektu triedy Vozidlo
	Vozidlo(String znacka, int rocnik, String farba, int najazdeneKM, double cena)
	{
		this.znacka = znacka;
		this.rocnik = rocnik;
		this.farba = farba;
		this.najazdeneKM = najazdeneKM;
		this.cena = cena;
	}
	
	// deklaracia clenskych metod
	// zmena farby vozidla
	public void zmenFarbu(String novaFarba)
	{
		this.farba = novaFarba;
	}
	
	// zmena ceny vozisla podla zlavy
	public void zlacniVozidlo(double oKolkoPercent)
	{
		this.cena *= 1 - (oKolkoPercent / 100);
	}
	
	// deklaracia get metod
	public String getZnacka()
	{
		return this.znacka;
	}
	
	public int getRocnik()
	{
		return this.rocnik;
	}
	
	public String getFarba()
	{
		return this.farba;
	}
	
	public int getNajazdeneKM()
	{
		return this.najazdeneKM;
	}
	
	public double getCena()
	{
		return this.cena;
	}
}

