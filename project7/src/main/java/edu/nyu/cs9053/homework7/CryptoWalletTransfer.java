package edu.nyu.cs9053.homework7;

public class CryptoWalletTransfer {

	public <T extends Cryptocurrency> void Transfer(CryptoWallet<? extends T> sourceWallet, CryptoWallet<? super T> destinationWallet) {
		for (int i = 0; i < sourceWallet.size(); i++) {
			destinationWallet.add(sourceWallet.get(i));
		}

	}

} 