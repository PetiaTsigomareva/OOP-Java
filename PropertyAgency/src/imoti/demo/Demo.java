package imoti.demo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import imoti.agency.Agency;
import imoti.estate.Estate;
import imoti.person.Agent;
import imoti.person.Buyer;
import imoti.person.Seller;

public class Demo {

	public static void main(String[] args) {
		// Task1
		System.out.println("1.CREATE AGENCY");
		Agency agency = new Agency("Talants Etaits", "028564125", "Sofia ul.Orel23");
		List<Seller> sellers = new ArrayList<>();

		for (int i = 0; i < 5; i++) {
			agency.getAgents().add(Agent.createRandomAgent(agency));
		}
		agency.printAgents();

		// Task2
		System.out.println("2.SELLERS");
		for (int i = 0; i < 30; i++) {
			sellers.add(Seller.createRandomSeller());
		}
		for (int i = 0; i < sellers.size(); i++) {
			System.out.println(sellers.get(i));

		}

		// Task3
		System.out.println("3.REGISTRATION SELLERS ESTATE");
		for (int i = 0; i < sellers.size(); i++) {
			agency.registerSeller(sellers.get(i));
		}
		agency.printCatalog();

		// Task4.
		List<Buyer> buyers = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			buyers.add(Buyer.createRandomBuyer());
		}
		System.out.println("4.BUYERS:");
		for (int i = 0; i < buyers.size(); i++) {
			System.out.println(buyers.get(i));

		}

		// Task5
		// 5.BUYERS ESTATE SEARCH REQUESTS TO AGENCY
		System.out.println("5.BUYERS ESTATE SEARCH REQUESTS TO AGENCY ");
		// TODO FOR EVERY BUYER =>AGENT == FOR EVERY AGENT=>BUYERS
		for (int i = 0; i < buyers.size(); i++) {
			agency.registerBuyer(buyers.get(i));
			System.out.println("FOR EVERY BUYER =>AGENT ");
			System.out.println(buyers.get(i).getAgent());
		}
		System.out.println("FOR EVERY AGENT=>BUYERS ");
		for (int i = 0; i < agency.getAgents().size(); i++) {
			// agency.getAgents().get(i).getBuyers();
			Iterator<Buyer> it = agency.getAgents().get(i).getBuyers().iterator();
			while (it.hasNext()) {
				Buyer buyer = it.next();
				System.out.println(buyer);
			}
		}

		// 6.BUYERS REQUEST FOR 3-PROPERTY ESPECTIONS
		System.out.println("6.BUYERS REQUEST FOR 3-PROPERTY ESPECTIONS ");
		for (int b = 0; b < buyers.size(); b++) {
			agency.getCatalog().entrySet();

			for (int i = 0; i < 3; i++) {
				// TODO GET RONDOM ESTATE FROM AGENCY CATALOG
				Estate randomEstate = Estate.getRandomEstate();
				agency.requestPropertyInspection(randomEstate, buyers.get(b));

			}
			System.out.println(buyers.get(b).toString());
		}

	}

}
