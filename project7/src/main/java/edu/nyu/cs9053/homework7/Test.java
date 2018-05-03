package edu.nyu.cs9053.homework7;

public class Test {
	public static void main(String[] args) {
		CryptoWallet<Litecoin> cryptoWallet = new CryptoWallet<Litecoin>(new LitecoinArrayCreator());
		cryptoWallet.add(new Litecoin(3));
		cryptoWallet.add(new Litecoin(4));
		cryptoWallet.add(new Litecoin(5));
		cryptoWallet.remove(new Litecoin(5));
		for (int i = 0; i < 3; i++) {
			System.out.println(cryptoWallet.get(i).getAmount());
		}


	}
}
