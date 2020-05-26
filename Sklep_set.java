package set;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class Sklep_set {

	public static void wyswietl(HashMap<String, Double> prd) {

		for (String i : prd.keySet()) {
			System.out.println(i + " " + prd.get(i) + " zl");
		}
	}

	public static int losuj(Scanner gry) throws IOException {

		int index = 1;
		BufferedReader reader = new BufferedReader(
				new FileReader("C:\\Users\\PC\\eclipse-workspace\\progamy\\src\\set\\baza_gier.txt"));
		int lines = 1;
		while (reader.readLine() != null)
			lines++;

		reader.close();

		for (int i = 0; i < 5; i++) {

			index = (int) (Math.random() * lines + 1);
			if (index == 0)
				index++;
			if (index == 21)
				index--;
			return index;
		}
		return index;

	}

	public static int znajdz(String a) {

		int x = 0;
		char[] tab = a.toCharArray();
		for (int i = 0; i < a.length(); i++) {
			if (tab[i] == ',')
				x = i;
		}
		return x;
	}

	public static void faktura(HashMap<String, Double> koszyk, PrintWriter obj, Double koncowa) {

		for (String i : koszyk.keySet()) {

			obj.println("Kupione produkty: " + i + " " + koszyk.get(i));
		}

		obj.println("Laczna cena: " + koncowa);
		obj.close();
	}

	public static void przypisanie(HashMap<String, Double> produkt) throws IOException {

		File plik = new File("C:\\Users\\PC\\eclipse-workspace\\progamy\\src\\set\\produkty.txt");
		File gry = new File("C:\\Users\\PC\\eclipse-workspace\\progamy\\src\\set\\baza_gier.txt");

		Scanner obj = new Scanner(plik);

		for (int i = 0; i < 5; i++) {

			Scanner baza = new Scanner(gry);
			int index = losuj(baza);
			int licznik = 1;
			String wyraz = "";
			for (licznik = 1; licznik <= index; licznik++) {

				if (licznik == index) {
					wyraz = baza.nextLine();
				} else
					baza.nextLine();

			}

			int x = znajdz(wyraz);

			String kl = wyraz.substring(0, x);
			String war = wyraz.substring(x + 1, wyraz.length());

			Double w = Double.parseDouble(war);

			produkt.put(kl, w);

		}

	}

	public static String jaki_klucz(HashMap<String, Double> prd, int nr) {

		int licznik = 0;
		String klucz = "";

		for (String i : prd.keySet()) {
			licznik++;
			if (licznik == nr)
				klucz = i;
		}
		return klucz;
	}

	public static void main(String[] args) throws IOException {

		HashMap<String, Double> produkt = new HashMap<String, Double>();
		HashMap<String, Double> koszyk = new HashMap<String, Double>();

		boolean stop = true;

		PrintWriter v = new PrintWriter("C:\\Users\\PC\\eclipse-workspace\\progamy\\src\\set\\faktura.txt");

		String klucz = "";
		Double wartosc;

		Double laczna_wartosc = (double) 0;
		przypisanie(produkt);

		while (stop) {

			System.out.println(
					"Produkty:(podaj nr gry ktory chcesz kupic), \nwpisz 'k' zeby wejsc w koszyk, wpisz 'q' zeby wyjsc");
			System.out.println();
			wyswietl(produkt);

			Scanner a = new Scanner(System.in);

			String wybor = a.nextLine();

			switch (wybor) {

			case "1":
				klucz = jaki_klucz(produkt, 1);
				wartosc = produkt.get(klucz);

				koszyk.put(klucz, wartosc);
				produkt.remove(klucz);
				laczna_wartosc += koszyk.get(klucz);
				break;
			case "2":
				klucz = jaki_klucz(produkt, 2);
				wartosc = produkt.get(klucz);
				koszyk.put(klucz, wartosc);
				produkt.remove(klucz);
				laczna_wartosc += koszyk.get(klucz);
				break;
			case "3":
				klucz = jaki_klucz(produkt, 3);
				wartosc = produkt.get(klucz);
				koszyk.put(klucz, wartosc);
				produkt.remove(klucz);
				laczna_wartosc += koszyk.get(klucz);
				break;
			case "4":
				klucz = jaki_klucz(produkt, 4);
				wartosc = produkt.get(klucz);
				koszyk.put(klucz, wartosc);
				produkt.remove(klucz);
				laczna_wartosc += koszyk.get(klucz);
				break;
			case "5":
				klucz = jaki_klucz(produkt, 5);
				wartosc = produkt.get(klucz);
				koszyk.put(klucz, wartosc);
				produkt.remove(klucz);
				laczna_wartosc += koszyk.get(klucz);
				break;
			case "k":

				System.out.println("Koszyk:");
				wyswietl(koszyk);
				System.out.println();

				System.out.println("Laczna wartosc koszyka: " + laczna_wartosc);
				System.out.println();

				break;
			case "q":

				faktura(koszyk, v, laczna_wartosc);
				System.out.println("Do pliku zostala wygenerowana faktura");
				stop = false;
				break;
			}
		}

	}

}
